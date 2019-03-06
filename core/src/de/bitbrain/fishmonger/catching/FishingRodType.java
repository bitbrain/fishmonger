package de.bitbrain.fishmonger.catching;

import de.bitbrain.fishmonger.Config;

public enum FishingRodType {
   DEFAULT( Config.FISHING_ROD_RANGE, Config.FISHING_ROD_THROW_INTERVAL, Config.FISHING_ROD_PULL_INTERVAL);

   private final int range;
   private final float throwInterval;
   private final float pullInterval;

   FishingRodType(int range, float throwInterval, float pullInterval) {
      this.range = range;
      this.throwInterval = throwInterval;
      this.pullInterval = pullInterval;
   }

   public int getRange() {
      return range;
   }

   public float getThrowInterval() {
      return throwInterval;
   }

   public float getPullInterval() {
      return pullInterval;
   }
}
