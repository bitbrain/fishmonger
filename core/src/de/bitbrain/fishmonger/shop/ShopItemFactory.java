package de.bitbrain.fishmonger.shop;

import com.badlogic.gdx.graphics.Texture;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.catching.HookType;
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

   public ShopItem createInventoryItem(final int slots) {
      Runnable runnable = new Runnable() {
         @Override
         public void run() {
            PlayerProgress.setInventorySlots(slots);
         }
      };
      return new ShopItem(slots + " slot bag", new SlotObtainer(slots), Rarity.RARE, SharedAssetManager.getInstance().get(Assets.Textures.EEL, Texture.class), 1000 * slots * slots, runnable);
   }
}
