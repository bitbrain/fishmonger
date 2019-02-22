package de.bitbrain.fishmonger.screens;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMap;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.RasteredMovementBehavior;
import de.bitbrain.braingdx.graphics.animation.AnimationConfig;
import de.bitbrain.braingdx.graphics.animation.AnimationFrames;
import de.bitbrain.braingdx.graphics.animation.AnimationRenderer;
import de.bitbrain.braingdx.graphics.animation.AnimationSpriteSheet;
import de.bitbrain.braingdx.input.OrientationMovementController;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tmx.TiledMapType;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.SimpleWorldBounds;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.FishMongerGame;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.input.ingame.IngameControllerInput;
import de.bitbrain.fishmonger.input.ingame.IngameKeyboardInput;

public class IngameScreen extends AbstractScreen<FishMongerGame> {

   public IngameScreen(FishMongerGame game) {
      super(game);
   }

   @Override
   public void dispose() {
      super.dispose();
      Controllers.clearListeners();
   }

   @Override
   protected void onCreate(GameContext context) {
      setBackgroundColor(Colors.BACKGROUND);
      setupWorld(context);
      setupInput(context);
      setupRenderer(context);
      setupShaders(context);
   }

   private void setupInput(GameContext context) {
      context.getInput().addProcessor(new IngameKeyboardInput());
      Controllers.addListener(new IngameControllerInput());
   }

   private void setupWorld(GameContext context) {
      TiledMap level = SharedAssetManager.getInstance().get(Assets.TiledMaps.LEVEL_1, TiledMap.class);
      context.getTiledMapManager().load(level, context.getGameCamera().getInternalCamera(), TiledMapType.ORTHOGONAL);
      context.getGameWorld().setBounds(new SimpleWorldBounds(
            context.getTiledMapManager().getAPI().getWorldWidth(),
            context.getTiledMapManager().getAPI().getWorldHeight())
      );
      for (GameObject o : context.getGameWorld()) {
         if ("PLAYER".equals(o.getType())) {
            configurePlayer(context, o);
         }
      }
   }

   private void setupShaders(GameContext context) {
      // TODO
   }

   private void setupRenderer(GameContext context) {
      final Texture playerTexture = SharedAssetManager.getInstance().get(Assets.Textures.PLAYER);
      AnimationSpriteSheet playerSheet = new AnimationSpriteSheet(playerTexture, 16);

      context.getRenderManager().register("PLAYER", new AnimationRenderer(playerSheet,
            AnimationConfig.builder()
                  .registerFrames("PLAYER", AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 0)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .build()
      ));
   }

   private void configurePlayer(GameContext context, GameObject player) {
      player.setDimensions(16f, 16f);
      float correctX = (float) (Math.floor(player.getLeft() / context.getTiledMapManager().getAPI().getCellWidth()) * context.getTiledMapManager().getAPI().getCellWidth());
      float correctY = (float) (Math.floor(player.getTop() / context.getTiledMapManager().getAPI().getCellHeight()) * context.getTiledMapManager().getAPI().getCellHeight());
      player.setPosition(correctX, correctY);
      context.getGameCamera().setStickToWorldBounds(true);
      context.getGameCamera().setDefaultZoomFactor(0.1f);
      context.getGameCamera().setZoomScalingFactor(0.0000001f);
      context.getGameCamera().setTrackingTarget(player);
      context.getGameCamera().setTargetTrackingSpeed(0.05f);
      player.setOrigin(player.getWidth() / 2f, player.getHeight() / 2f);

      OrientationMovementController controller = new OrientationMovementController();
      final RasteredMovementBehavior behavior = new RasteredMovementBehavior(controller, context.getTiledMapManager().getAPI())
            .interval(0.35f)
            .rasterSize(context.getTiledMapManager().getAPI().getCellWidth(), context.getTiledMapManager().getAPI().getCellHeight());
      context.getBehaviorManager().apply(behavior, player);
   }
}
