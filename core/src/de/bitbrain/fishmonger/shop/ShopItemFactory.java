package de.bitbrain.fishmonger.shop;

import com.badlogic.gdx.graphics.Texture;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.catching.HookType;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.progress.PlayerProgress;

public class ShopItemFactory {

   public ShopItem createHookItem(final HookType type) {
      Runnable runnable = new Runnable() {
         @Override
         public void run() {
            PlayerProgress.setHookType(type);
         }
      };
      return new ShopItem(type.getName(), new HookObtainer(type), type.getRarity(), type.getTexture(), 15000 * (type.ordinal() * type.ordinal() * type.ordinal()), runnable);
   }

   public ShopItem createInventoryItem(Messages message, final int slots, Rarity rarity) {
      Runnable runnable = new Runnable() {
         @Override
         public void run() {
            PlayerProgress.setInventorySlots(slots);
         }
      };
      return new ShopItem(
            Bundle.get(message) + " (" + slots + " " + Bundle.get(Messages.SHOP_ITEM_BAG_SLOTS) + ")",
            new SlotObtainer(slots),
            rarity,
            SharedAssetManager.getInstance().get(Assets.Textures.BAG, Texture.class),
            1000 * slots * slots,
            runnable
      );
   }
}
