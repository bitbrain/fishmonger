package de.bitbrain.fishmonger.shop;

import com.badlogic.gdx.graphics.Texture;
import de.bitbrain.braingdx.assets.SharedAssetManager;

public class ShopItem {

   private final String name;
   private final String description;
   private final boolean obtained;
   private final Rarity rarity;
   private final Texture icon;
   private final int price;
   private final Runnable buyRunnable;

   public ShopItem(String name, String description, boolean obtained, Rarity rarity, Texture icon, int price, Runnable buyRunnable) {
      this.name = name;
      this.description = description;
      this.obtained = obtained;
      this.rarity = rarity;
      this.icon = icon;
      this.price = price;
      this.buyRunnable = buyRunnable;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public boolean isObtained() {
      return obtained;
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
            ", obtained=" + obtained +
            ", rarity=" + rarity +
            ", icon='" + icon + '\'' +
            ", price=" + price +
            ", buyRunnable=" + buyRunnable +
            '}';
   }
}
