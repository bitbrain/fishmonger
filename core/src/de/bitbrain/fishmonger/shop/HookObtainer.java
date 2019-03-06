package de.bitbrain.fishmonger.shop;

import de.bitbrain.fishmonger.catching.HookType;
import de.bitbrain.fishmonger.progress.PlayerProgress;

public class HookObtainer implements Obtainer {

   private final HookType type;

   public HookObtainer(HookType type) {
      this.type = type;
   }

   @Override
   public boolean isObtained() {
      return PlayerProgress.getHookType().ordinal() >= type.ordinal();
   }
}
