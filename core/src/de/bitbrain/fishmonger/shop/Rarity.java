package de.bitbrain.fishmonger.shop;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;

public enum Rarity {
   COMMON(Messages.SHOP_ITEM_RARITY_COMMON, null),
   RARE(Messages.SHOP_ITEM_RARITY_RARE, null),
   EPIC(Messages.SHOP_ITEM_RARITY_EPIC, null),
   LEGENDARY(Messages.SHOP_ITEM_RARITY_LEGENDARY, null);

   private final Messages name;
   private final Drawable icon;

   Rarity(Messages name, Drawable icon) {
      this.name = name;
      this.icon = icon;
   }

   public String getName() {
      return Bundle.get(name);
   }

   public Drawable getIcon() {
      return icon;
   }
}
