package de.bitbrain.fishmonger.screens;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.behavior.movement.RasteredMovementBehavior;
import de.bitbrain.braingdx.graphics.pipeline.layers.RenderPipeIds;
import de.bitbrain.braingdx.input.OrientationMovementController;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tmx.TiledMapType;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.SimpleWorldBounds;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.model.Money;
import de.bitbrain.fishmonger.model.inventory.Inventory;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.model.spawn.Spawner;

import java.util.ArrayList;
import java.util.List;

import static de.bitbrain.fishmonger.Colors.BACKGROUND;

public class GameOverScreen extends AbstractScreen {

   private final Money money;
   private final Inventory inventory;
   private final List<Item> delivered;

   public GameOverScreen(BrainGdxGame game, Money money, Inventory inventory, List<Item> delivered) {
      super(game);
      this.money = money;
      this.inventory = inventory;
      this.delivered = delivered;
   }

   @Override
   protected void onCreate(GameContext context) {
      setBackgroundColor(BACKGROUND);
      setupWorld(context);
      setupRenderer(context);
   }

   private void setupWorld(GameContext context) {
      TiledMap level = SharedAssetManager.getInstance().get(Assets.TiledMaps.GAME_OVER, TiledMap.class);
      context.getTiledMapManager().load(level, context.getGameCamera().getInternalCamera(), TiledMapType.ORTHOGONAL);
      context.getGameWorld().setBounds(new SimpleWorldBounds(
            context.getTiledMapManager().getAPI().getWorldWidth(),
            context.getTiledMapManager().getAPI().getWorldHeight())
      );
      List<Spawner> spawners = new ArrayList<Spawner>();
      GameObject player = null;
      for (GameObject o : context.getGameWorld()) {
         if ("PLAYER".equals(o.getType())) {
            configurePlayer(context, o);
            player = o;
         }
         if ("GIER".equals(o.getType())) {
            configureGier(context, o);
         }
         if ("SPAWN".equals(o.getType())) {
            int capacity = ((MapProperties)o.getAttribute(MapProperties.class)).get("capacity", 1, Integer.class);
            Spawner spawner = new Spawner(o.getLeft(), o.getTop(), o.getWidth(), o.getHeight(), capacity);
            spawners.add(spawner);
         }
      }
      for (Spawner spawner : spawners) {
         spawner.spawn(context, null);
      }
   }

   private void setupRenderer(GameContext context) {
      Animations.setupPlayerAnimations(context);
      Animations.setupFishAnimations(context);
   }

   private void configurePlayer(GameContext context, GameObject player) {
      player.setDimensions(16f, 8f);
      player.setScaleY(2f);
      float correctX = (float) (Math.floor(player.getLeft() / context.getTiledMapManager().getAPI().getCellWidth()) * context.getTiledMapManager().getAPI().getCellWidth());
      float correctY = (float) (Math.floor(player.getTop() / context.getTiledMapManager().getAPI().getCellHeight()) * context.getTiledMapManager().getAPI().getCellHeight());
      player.setPosition(correctX, correctY);
      context.getGameCamera().setStickToWorldBounds(true);
      context.getGameCamera().setDefaultZoomFactor(0.1f);
      context.getGameCamera().setZoomScalingFactor(0.0000001f);
      context.getGameCamera().setTrackingTarget(player);
      context.getGameCamera().setTargetTrackingSpeed(0.1f);
      player.setOrigin(player.getWidth() / 2f, player.getHeight() / 2f);
      player.setActive(false);
      player.setAttribute(Orientation.class, Orientation.UP);
   }

   private void configureGier(GameContext context, GameObject gier) {
      gier.setDimensions(16f, 8f);
      gier.setScaleY(2f);
      float correctX = (float) (Math.floor(gier.getLeft() / context.getTiledMapManager().getAPI().getCellWidth()) * context.getTiledMapManager().getAPI().getCellWidth());
      float correctY = (float) (Math.floor(gier.getTop() / context.getTiledMapManager().getAPI().getCellHeight()) * context.getTiledMapManager().getAPI().getCellHeight());
      gier.setPosition(correctX, correctY);
      gier.setOrigin(gier.getWidth() / 2f, gier.getHeight() / 2f);
      gier.setAttribute(Orientation.class, Orientation.DOWN);
   }
}
