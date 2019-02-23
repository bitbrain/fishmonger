package de.bitbrain.fishmonger.animation;

import de.bitbrain.braingdx.util.Enabler;
import de.bitbrain.braingdx.world.GameObject;

public class PlayerAnimationEnabler implements Enabler<GameObject> {

   private boolean lastEnabled = false;
   @Override
   public boolean isEnabledFor(GameObject target) {
      boolean enabled = target.getOffsetX() != 0 || target.getOffsetY() != 0 || lastEnabled;
      lastEnabled = enabled;
      return enabled;
   }
}
