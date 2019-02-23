package de.bitbrain.fishmonger.event;

import de.bitbrain.braingdx.event.GameEvent;
import de.bitbrain.fishmonger.model.inventory.Item;

import java.util.List;

public class InventoryClearedEvent implements GameEvent {

   private List<Item> items;

   public InventoryClearedEvent(List<Item> items) {
      this.items = items;
   }

   public List<Item> getItems() {
      return items;
   }
}
