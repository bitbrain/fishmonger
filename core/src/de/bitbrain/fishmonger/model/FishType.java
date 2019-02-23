package de.bitbrain.fishmonger.model;

import com.badlogic.gdx.graphics.Texture;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;

public enum FishType {

   PIRANHA(Assets.Textures.PIRANHA, Messages.FISH_PIRANHA_NAME, Messages.FISH_PIRANHA_DESCRIPTION);

   private String assetId;
   private Messages name;
   private Messages description;

   FishType(String assetId, Messages name, Messages description) {
      this.assetId = assetId;
      this.name = name;
      this.description = description;
   }

   public Texture getAsset() {
      return SharedAssetManager.getInstance().get(assetId, Texture.class);
   }

   public String getName() {
      return Bundle.get(name);
   }

   public String getDescription() {
      return Bundle.get(description);
   }
}
