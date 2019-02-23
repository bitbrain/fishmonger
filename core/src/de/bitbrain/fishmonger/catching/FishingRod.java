package de.bitbrain.fishmonger.catching;

import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.tmx.IndexCalculator;
import de.bitbrain.braingdx.tmx.TiledMapAPI;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.event.FishingRodEvents;
import de.bitbrain.fishmonger.model.FishType;
import de.bitbrain.fishmonger.model.inventory.Inventory;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.model.inventory.ItemFactory;

import static de.bitbrain.braingdx.behavior.movement.Orientation.*;

public class FishingRod {

   private final DeltaTimer throwTimer = new DeltaTimer();

   private final GameObject player;
   private final GameContext context;
   private boolean throwing = false;
   private boolean pullingBack = false;
   private final Inventory inventory;

   private int currentLength;

   public FishingRod(GameObject player, Inventory inventory, GameContext context) {
      this.player = player;
      this.context = context;
      this.inventory = inventory;
   }

   public void update(float delta) {
      throwTimer.update(delta);
      if (!pullingBack && throwing && throwTimer.reached(Config.FISHING_ROD_THROW_INTERVAL)) {
         throwTimer.reset();
         currentLength++;
         checkCurrentLocationForFish();
         if (currentLength == Config.FISHING_ROD_RANGE) {
            pullBack();
         }
      }
      if (pullingBack && throwTimer.reached(Config.FISHING_ROD_PULL_INTERVAL)) {
         throwTimer.reset();
         currentLength--;
         Orientation orientation = (Orientation)player.getAttribute(Orientation.class);
         float x = player.getLeft() + currentLength * context.getTiledMapManager().getAPI().getCellWidth() * orientation.getXFactor();
         float y = player.getTop() + currentLength * context.getTiledMapManager().getAPI().getCellHeight() * orientation.getYFactor();
         context.getEventManager().publish(new FishingRodEvents.MoveToLocationEvent(player, new Vector2(x, y)));
         if (currentLength <= 1) {
            player.setActive(true);
            throwing = false;
            pullingBack = false;
            context.getEventManager().publish(new FishingRodEvents.PullBackEvent(player));
         }
      }
   }

   public void throwRod() {
      if (!throwing && !inventory.isFull()) {
         throwing = true;
         currentLength = 1;
         player.setActive(false);
         context.getEventManager().publish(new FishingRodEvents.ThrowRodEvent());
      }
   }

   private void pullBack() {
      pullingBack = true;
   }

   private void checkCurrentLocationForFish() {
      if (!checkLane(false)) {
         checkLane(true);
      }
   }

   private boolean checkLane(boolean next) {
      TiledMapAPI api = context.getTiledMapManager().getAPI();
      Orientation orientation = (Orientation)player.getAttribute(Orientation.class);
      float x = player.getLeft() + currentLength * api.getCellWidth() * orientation.getXFactor();
      float y = player.getTop() + currentLength * api.getCellHeight() * orientation.getYFactor();
      if (next) {
         if (orientation == Orientation.DOWN || orientation == UP) {
            x += player.getWidth() / 2f;
         }
         if (orientation == RIGHT || orientation == LEFT) {
            y += player.getWidth() / 2f;
         }
      } else {
         context.getEventManager().publish(new FishingRodEvents.MoveToLocationEvent(player, new Vector2(x, y)));
      }
      int tileX = IndexCalculator.calculateIndex(x, api.getCellWidth());
      int tileY = IndexCalculator.calculateIndex(y, api.getCellHeight());
      GameObject object = api.getGameObjectAt(tileX, tileY, 0);
      if (object != null && object.getType() instanceof FishType) {
         Item item = ItemFactory.retrieveFromGameObject(object);
         context.getEventManager().publish(new FishingRodEvents.FishCatchedEvent(player, new Vector2(x, y), item));
         inventory.addItem(item);
         context.getGameWorld().remove(object);
         pullBack();
         return true;
      }
      return false;
   }
}
