package de.bitbrain.fishmonger.model;

import com.badlogic.gdx.graphics.Texture;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;

public enum FishType {

   PIRANHA(Assets.Textures.PIRANHA, Messages.FISH_PIRANHA_NAME, Messages.FISH_PIRANHA_NAME_PLURAL, Messages.FISH_PIRANHA_DESCRIPTION, 500, 0.1f, 3),
   MACKEREL(Assets.Textures.MACKEREL, Messages.FISH_MACKEREL_NAME, Messages.FISH_MACKEREL_NAME_PLURAL, Messages.FISH_MACKEREL_DESCRIPTION, 100, 0.5f, 1),
   GIANT_PIRANHA(Assets.Textures.GIANT_PIRANHA, Messages.FISH_GIANT_PIRANHA_NAME, Messages.FISH_GIANT_PIRANHA_NAME_PLURAL, Messages.FISH_GIANT_PIRANHA_DESCRIPTION, 1500, 0.1f, 4),
   EEL(Assets.Textures.EEL, Messages.FISH_EEL_NAME, Messages.FISH_EEL_NAME_PLURAL, Messages.FISH_EEL_DESCRIPTION, 250, 0.2f, 2),
   STRIDER(Assets.Textures.STRIDER, Messages.FISH_STRIDER_NAME, Messages.FISH_STRIDER_NAME_PLURAL, Messages.FISH_STRIDER_DESCRIPTION, 50, 0.1f, 1);

   private final String assetId;
   private final Messages name;
   private final Messages pluralName;
   private final Messages description;
   private final int value;
   private final float probability;
   private final int tier;

   FishType(String assetId, Messages name, Messages pluralName, Messages description, int value, float probability, int tier) {
      this.assetId = assetId;
      this.name = name;
      this.pluralName = pluralName;
      this.description = description;
      this.value = value;
      this.probability = probability;
      this.tier = tier;
   }

   public int getTier() {
      return tier;
   }

   public Texture getAsset() {
      return SharedAssetManager.getInstance().get(assetId, Texture.class);
   }

   public String getName() {
      return Bundle.get(name);
   }

   public String getPluralName() {
      return Bundle.get(pluralName);
   }

   public String getDescription() {
      return Bundle.get(description);
   }

   public int getValue() {
      return value;
   }

   public float getProbability() {
      return probability;
   }
}
