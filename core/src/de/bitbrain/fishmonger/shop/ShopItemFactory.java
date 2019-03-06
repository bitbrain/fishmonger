package de.bitbrain.fishmonger.shop;

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
      return new ShopItem(type.getName(), "a hook", false, type.getRarity(), type.getTexture(), 20000, runnable);
   }
}
