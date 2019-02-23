package de.bitbrain.fishmonger.model;

public class Money {

   private int amount;

   public int getAmount() {
      return amount;
   }

   public void addAmount(int amount) {
      this.amount += amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }
}
