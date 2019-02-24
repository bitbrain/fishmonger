package de.bitbrain.fishmonger.model.inventory;

import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.model.FishType;

public class ItemFactory {

   public static Item retrieveFromGameObject(GameObject object) {
      if (object.getType() instanceof FishType) {
         FishType type = (FishType) object.getType();
         return new Item(
               type.toString(),
               type.getAsset(),
               type.getName(),
               type.getPluralName(),
               type.getDescription(),
               type.getValue()
         );
      }
      return null;
   }
}
