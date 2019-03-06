package de.bitbrain.fishmonger.shop;

import com.badlogic.gdx.graphics.Texture;

public class ShopItem {

   private final String name;
   private final String description;
   private final Obtainer obtainer;
   private final Rarity rarity;
   private final Texture icon;
   private final int price;
   private final Runnable buyRunnable;

   public ShopItem(String name, Obtainer obtainer, Rarity rarity, Texture icon, int price, Runnable buyRunnable) {
      this.name = name;
      this.description = null;
      this.obtainer = obtainer;
      this.rarity = rarity;
      this.icon = icon;
      this.price = price;
      this.buyRunnable = buyRunnable;
   }

   public String getName() {
      return name;
   }

   public boolean isObtained() {
      return obtainer.isObtained();
   }

   public Rarity getRarity() {
      return rarity;
   }

   public Texture getIcon() {
      return icon;
   }

   public int getPrice() {
      return price;
   }

   public Runnable getBuyRunnable() {
      return buyRunnable;
   }

   @Override
   public String toString() {
      return "ShopItem{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", rarity=" + rarity +
            ", icon='" + icon + '\'' +
            ", price=" + price +
            ", buyRunnable=" + buyRunnable +
            '}';
   }
}
