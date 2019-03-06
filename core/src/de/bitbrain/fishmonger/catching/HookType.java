package de.bitbrain.fishmonger.catching;

import com.badlogic.gdx.graphics.Texture;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.shop.Rarity;

public enum HookType {
   DEFAULT(
         Messages.SHOP_ITEM_ROD_DEFAULT,
         Config.FISHING_ROD_RANGE,
         Config.FISHING_ROD_THROW_INTERVAL,
         Config.FISHING_ROD_PULL_INTERVAL,
         Assets.Textures.HOOK,
         Rarity.COMMON
   ),
   RARE(Messages.SHOP_ITEM_ROD_RARE,
         Config.FISHING_ROD_RANGE * 2,
         Config.FISHING_ROD_THROW_INTERVAL / 2,
         Config.FISHING_ROD_PULL_INTERVAL / 2,
         Assets.Textures.HOOK_RARE,
         Rarity.RARE
   );

   private final Messages name;
   private final int range;
   private final float throwInterval;
   private final float pullInterval;
   private final String assetId;
   private final Rarity rarity;

   HookType(Messages name, int range, float throwInterval, float pullInterval, String assetId, Rarity rarity) {
      this.name = name;
      this.range = range;
      this.throwInterval = throwInterval;
      this.pullInterval = pullInterval;
      this.assetId = assetId;
      this.rarity = rarity;
   }

   public Rarity getRarity() {
      return rarity;
   }

   public String getName() {
      return Bundle.get(name);
   }

   public Texture getTexture() {
      return SharedAssetManager.getInstance().get(assetId, Texture.class);
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
