package de.bitbrain.fishmonger.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.graphics.animation.*;
import de.bitbrain.braingdx.util.Enabler;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.model.FishType;

public class Animations {

   public static void setupPlayerAnimations(GameContext context) {
      final Texture playerTexture = SharedAssetManager.getInstance().get(Assets.Textures.PLAYER);
      AnimationSpriteSheet playerSheet = new AnimationSpriteSheet(playerTexture, 16);
      context.getRenderManager().register("PLAYER", new AnimationRenderer(playerSheet,
            AnimationConfig.builder()
                  .registerFrames(Orientation.DOWN, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 0)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.UP, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 1)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.RIGHT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 2)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.LEFT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 3)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .build()
            , new AnimationTypeResolver<GameObject>() {
         @Override
         public Object getAnimationType(GameObject object) {
            return object.getAttribute(Orientation.class);
         }
      }, new Enabler<GameObject>() {
         @Override
         public boolean isEnabledFor(GameObject target) {
            return target.getOffsetX() > 0 || target.getOffsetY() > 0;
         }
      }).offset(0f, 4f));
      context.getRenderManager().register("GIER", new AnimationRenderer(playerSheet,
            AnimationConfig.builder()
                  .registerFrames(Orientation.DOWN, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, 4)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.UP, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, 5)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.RIGHT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, 6)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.LEFT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, 7)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .build()
            , new AnimationTypeResolver<GameObject>() {
         @Override
         public Object getAnimationType(GameObject object) {
            return object.getAttribute(Orientation.class);
         }
      }, new GierAnimationEnabler()).offset(0f, 4f));
   }

   public static void setupFishAnimations(GameContext context) {
      final Texture fishTexture = SharedAssetManager.getInstance().get(Assets.Textures.FISH);
      AnimationSpriteSheet fishSheet = new AnimationSpriteSheet(fishTexture, 8);
      context.getRenderManager().register(FishType.PIRANHA, new AnimationRenderer(fishSheet,
            AnimationConfig.builder()
                  .registerFrames(Orientation.DOWN, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 0)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.UP, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 1)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.RIGHT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 2)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.LEFT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 3)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_REVERSED)
                        .frames(8)
                        .build())
                  .build()
            , new AnimationTypeResolver<GameObject>() {
         @Override
         public Object getAnimationType(GameObject object) {
            return object.getAttribute(Orientation.class);
         }
      }, new Enabler<GameObject>() {
         @Override
         public boolean isEnabledFor(GameObject target) {
            return target.getOffsetX() > 0 || target.getOffsetY() > 0;
         }
      }).offset(0f, 4f));
   }
}
