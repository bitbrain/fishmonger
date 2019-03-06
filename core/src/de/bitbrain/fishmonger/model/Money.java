package de.bitbrain.fishmonger.model;

import de.bitbrain.fishmonger.progress.PlayerProgress;

public class Money {

   private int amount;

   public int getAmount() {
      return amount;
   }

   public void addAmount(int amount) {
      this.amount += amount;
      PlayerProgress.addMoney(amount);
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }
}
