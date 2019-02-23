package de.bitbrain.fishmonger.inventory;

import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.model.FishType;

public class ItemFactory {

   public static Item retrieveFromGameObject(GameObject object) {
      FishType fishType = (FishType)object.getAttribute(FishType.class);
      if (fishType != null) {
         return new Item(
               fishType.toString(),
               fishType.getAsset(),
               fishType.getName(),
               fishType.getDescription()
         );
      }
      return null;
   }
}
