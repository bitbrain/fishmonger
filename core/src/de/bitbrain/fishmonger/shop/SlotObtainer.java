package de.bitbrain.fishmonger.shop;

import de.bitbrain.fishmonger.progress.PlayerProgress;

public class SlotObtainer implements Obtainer {

   private final int numberOfSlots;

   public SlotObtainer(int numberOfSlots) {
      this.numberOfSlots = numberOfSlots;
   }

   @Override
   public boolean isObtained() {
      return PlayerProgress.getInventorySlots() >= numberOfSlots;
   }
}
