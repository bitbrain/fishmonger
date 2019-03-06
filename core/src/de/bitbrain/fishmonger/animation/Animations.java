package de.bitbrain.fishmonger.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.graphics.GameObjectRenderManager;
import de.bitbrain.braingdx.graphics.animation.*;
import de.bitbrain.braingdx.ui.AnimationDrawable;
import de.bitbrain.braingdx.util.Enabler;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.model.FishType;

import static de.bitbrain.fishmonger.model.FishType.*;

public class Animations {

   public static AnimationDrawable gierAnimationDrawable() {
      final Texture texture = SharedAssetManager.getInstance().get(Assets.Textures.PLAYER);
      AnimationSpriteSheet avatarSheet = new AnimationSpriteSheet(texture, 16);
      return new AnimationDrawable(avatarSheet, AnimationConfig.builder()
            .registerFrames(AnimationDrawable.DEFAULT_FRAME_ID, AnimationFrames.builder()
                  .resetIndex(0)
                  .duration(0.2f)
                  .origin(0, 4)
                  .direction(AnimationFrames.Direction.HORIZONTAL)
                  .playMode(Animation.PlayMode.LOOP_PINGPONG)
                  .frames(8)
                  .build()).build());
   }

   public static AnimationConfig gierAnimationConfig() {
      return AnimationConfig.builder()
            .registerFrames(Orientation.DOWN, AnimationFrames.builder()
                  .resetIndex(0)
                  .duration(0.1f)
                  .origin(0, 4)
                  .direction(AnimationFrames.Direction.HORIZONTAL)
                  .playMode(Animation.PlayMode.LOOP_PINGPONG)
                  .frames(8)
                  .build())
            .registerFrames(Orientation.UP, AnimationFrames.builder()
                  .resetIndex(0)
                  .duration(0.1f)
                  .origin(0, 5)
                  .direction(AnimationFrames.Direction.HORIZONTAL)
                  .playMode(Animation.PlayMode.LOOP_PINGPONG)
                  .frames(8)
                  .build())
            .registerFrames(Orientation.RIGHT, AnimationFrames.builder()
                  .resetIndex(0)
                  .duration(0.1f)
                  .origin(0, 6)
                  .direction(AnimationFrames.Direction.HORIZONTAL)
                  .playMode(Animation.PlayMode.LOOP_PINGPONG)
                  .frames(8)
                  .build())
            .registerFrames(Orientation.LEFT, AnimationFrames.builder()
                  .resetIndex(0)
                  .duration(0.1f)
                  .origin(0, 7)
                  .direction(AnimationFrames.Direction.HORIZONTAL)
                  .playMode(Animation.PlayMode.LOOP_PINGPONG)
                  .frames(8)
                  .build())
            .build();
   }

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
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.UP, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 1)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.RIGHT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 2)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.LEFT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.2f)
                        .origin(0, 3)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
                        .frames(8)
                        .build())
                  .build()
            , new AnimationTypeResolver<GameObject>() {
         @Override
         public Object getAnimationType(GameObject object) {
            return object.getAttribute(Orientation.class);
         }
      }, new PlayerAnimationEnabler()).offset(0f, 4f));
      context.getRenderManager().register("GIER", new AnimationRenderer(playerSheet,
            gierAnimationConfig()
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
      int numberOfAnimations = 4;
      int currentIndex = 0;
      for (FishType type : FishType.values()) {
         setupFishAnimation(currentIndex, type, fishSheet, context.getRenderManager());
         currentIndex += numberOfAnimations;
      }
   }

   public static AnimationDrawable createGierAvatar() {
      final Texture texture = SharedAssetManager.getInstance().get(Assets.Textures.AVATARS);
      AnimationSpriteSheet avatarSheet = new AnimationSpriteSheet(texture, 32);
      return new AnimationDrawable(avatarSheet,
            AnimationConfig.builder()
                  .registerFrames(AnimationDrawable.DEFAULT_FRAME_ID, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.15f)
                        .origin(0, 0)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP)
                        .frames(8)
                        .build())
                  .build()
      );


   }

   private static void setupFishAnimation(int startIndex, FishType fishType, AnimationSpriteSheet sheet, GameObjectRenderManager manager) {
      manager.register(fishType, new AnimationRenderer(sheet,
            AnimationConfig.builder()
                  .registerFrames(Orientation.DOWN, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, startIndex)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.UP, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, startIndex + 1)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.RIGHT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, startIndex + 2)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
                        .frames(8)
                        .build())
                  .registerFrames(Orientation.LEFT, AnimationFrames.builder()
                        .resetIndex(0)
                        .duration(0.1f)
                        .origin(0, startIndex + 3)
                        .direction(AnimationFrames.Direction.HORIZONTAL)
                        .playMode(Animation.PlayMode.LOOP_PINGPONG)
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
      }));
   }
}
