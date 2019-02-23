package de.bitbrain.fishmonger.event;

import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.event.GameEvent;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.model.inventory.Item;

public interface FishingRodEvents {

   class ThrowRodEvent implements GameEvent {
   }

   class PullBackEvent implements GameEvent {

      private final GameObject player;

      public PullBackEvent(GameObject player) {
         this.player = player;
      }

      public GameObject getPlayer() {
         return player;
      }
   }

   class MoveToLocationEvent implements GameEvent {

      private final GameObject player;
      private final Vector2 location;

      public MoveToLocationEvent(GameObject player, Vector2 location) {
         this.player = player;
         this.location = location;
      }

      public GameObject getPlayer() {
         return player;
      }

      public Vector2 getLocation() {
         return location;
      }
   }

   class FishCatchedEvent implements GameEvent {

      private final GameObject player;
      private final Vector2 location;
      private final Item item;

      public FishCatchedEvent(GameObject player, Vector2 location, Item item) {
         this.player = player;
         this.location = location;
         this.item = item;
      }

      public GameObject getPlayer() {
         return player;
      }

      public Vector2 getLocation() {
         return location;
      }

      public Item getItem() {
         return item;
      }
   }
}
