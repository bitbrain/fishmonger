package de.bitbrain.fishmonger.event;

import de.bitbrain.braingdx.event.GameEvent;
import de.bitbrain.fishmonger.model.inventory.Item;

public class ItemAddedToInventoryEvent implements GameEvent {

   private Item item;
   private int index;

   public ItemAddedToInventoryEvent(int index, Item item) {
      this.index = index;
      this.item = item;
   }

   public Item getItem() {
      return item;
   }

   public int getIndex() {
      return index;
   }
}
