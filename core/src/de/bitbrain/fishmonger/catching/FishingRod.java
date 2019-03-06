package de.bitbrain.fishmonger.catching;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.tmx.IndexCalculator;
import de.bitbrain.braingdx.tmx.TiledMapAPI;
import de.bitbrain.braingdx.tweens.GameObjectTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.event.FishingRodEvents;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.model.FishType;
import de.bitbrain.fishmonger.model.inventory.Inventory;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.model.inventory.ItemFactory;
import de.bitbrain.fishmonger.ui.DialogManager;
import de.bitbrain.fishmonger.ui.Toast;

import static de.bitbrain.braingdx.behavior.movement.Orientation.*;

public class FishingRod {

   private final DeltaTimer throwTimer = new DeltaTimer();

   private final GameObject player;
   private final GameContext context;
   private boolean throwing = false;
   private boolean pullingBack = false;
   private final Inventory inventory;
   private final DialogManager dialogManager;
   private final FishingRodType type;

   private int currentLength;

   public FishingRod(FishingRodType type, GameObject player, Inventory inventory, GameContext context, DialogManager dialogManager) {
      this.type = type;
      this.player = player;
      this.context = context;
      this.inventory = inventory;
      this.dialogManager = dialogManager;
   }

   public void update(float delta) {
      throwTimer.update(delta);
      if (!pullingBack && throwing && throwTimer.reached(type.getThrowInterval())) {
         throwTimer.reset();
         currentLength++;
         checkCurrentLocationForFish();
         if (currentLength > type.getRange()) {
            pullBack();
            Sound sound = SharedAssetManager.getInstance().get(Assets.Sounds.THROW_FAIL, Sound.class);
            sound.play();
         }
      }
      if (pullingBack && throwTimer.reached(type.getPullInterval())) {
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
         Sound sound = SharedAssetManager.getInstance().get(Assets.Sounds.THROW, Sound.class);
         sound.play();
         throwing = true;
         currentLength = 1;
         player.setActive(false);
         context.getEventManager().publish(new FishingRodEvents.ThrowRodEvent());
      } else if (inventory.isFull()) {
         Toast.getInstance().doToast(Bundle.get(Messages.INVENTORY_FULL));
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
      for (int i = 0; i < 3; ++i) {
         final GameObject object = api.getGameObjectAt(tileX, tileY, i);
         if (object != null && object.getType() instanceof FishType) {
            Item item = ItemFactory.retrieveFromGameObject(object);
            Toast.getInstance().doToast(Bundle.get(Messages.FISH_CAUGHT, item.getName()));
            context.getEventManager().publish(new FishingRodEvents.FishCatchedEvent(player, new Vector2(x, y), item));
            inventory.addItem(item);
            context.getGameWorld().remove(object);
            pullBack();
            Sound sound = SharedAssetManager.getInstance().get(Assets.Sounds.BITE, Sound.class);
            sound.play();
            return true;
         } else if (object != null && "GIER".equals(object.getType())) {
            dialogManager.addDialog("Richard Gier", Messages.ANGRY_RICHARD, Animations.createGierAvatar(), true);
            SharedTweenManager.getInstance().killTarget(object, GameObjectTween.OFFSET_Y);
            Tween.to(object, GameObjectTween.OFFSET_Y, 0.2f)
                  .ease(TweenEquations.easeOutCubic)
                  .target(8f)
                  .setCallbackTriggers(TweenCallback.COMPLETE)
                  .setCallback(new TweenCallback() {
                     @Override
                     public void onEvent(int type, BaseTween<?> source) {
                        Tween.to(object, GameObjectTween.OFFSET_Y, 0.2f)
                              .ease(TweenEquations.easeOutBounce)
                              .target(0f)
                              .start(SharedTweenManager.getInstance());
                     }
                  })
                  .start(SharedTweenManager.getInstance());
            dialogManager.nextDialog();
            pullBack();
            Sound sound = SharedAssetManager.getInstance().get(Assets.Sounds.BITE, Sound.class);
            sound.play();
         }
      }
      return false;
   }
}
