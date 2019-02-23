package de.bitbrain.fishmonger.behaviour;

import de.bitbrain.braingdx.behavior.BehaviorAdapter;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.GameObjectUtils;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.model.inventory.Inventory;

public class SellToGierBehavior extends BehaviorAdapter {

   private final Inventory inventory;
   private boolean sold;

   public SellToGierBehavior(Inventory inventory) {
      this.inventory = inventory;
   }

   @Override
   public void update(GameObject source, GameObject target, float delta) {
      if (!source.getType().equals("PLAYER")) {
         return;
      }
      if (!target.getType().equals("GIER")) {
         return;
      }
      if (!sold && GameObjectUtils.distanceBetween(source, target) < Config.MINIMUM_SELL_RANGE) {
         sold = true;
         inventory.clearInventory();
      }
      if (sold && GameObjectUtils.distanceBetween(source, target) >= Config.SOLD_RANGE) {
         sold = false;
      }
   }
}
