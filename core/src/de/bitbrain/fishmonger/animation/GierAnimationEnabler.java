package de.bitbrain.fishmonger.animation;

import de.bitbrain.braingdx.util.Enabler;
import de.bitbrain.braingdx.world.GameObject;

public class GierAnimationEnabler implements Enabler<GameObject> {

   private static final long ANIMATION_DURATION_MS = 4000;
   private static final long STILL_DURATION_MS = 1000;

   private long lastAnimationDuration = -1;
   private long lastStillDuration = -1;

   private boolean isAnimating = false;

   @Override
   public boolean isEnabledFor(GameObject target) {
      long currentTime = System.currentTimeMillis();
      if (lastAnimationDuration < 0) {
         lastAnimationDuration = currentTime;
      }
      if (lastStillDuration < 0) {
         lastStillDuration = currentTime;
      }
      if (!isAnimating && currentTime - lastStillDuration >= STILL_DURATION_MS) {
         isAnimating = true;
         lastAnimationDuration = currentTime;
      } else if (isAnimating && currentTime - lastAnimationDuration >= ANIMATION_DURATION_MS) {
         isAnimating = false;
         lastStillDuration = currentTime;
      }
      return isAnimating;
   }
}
