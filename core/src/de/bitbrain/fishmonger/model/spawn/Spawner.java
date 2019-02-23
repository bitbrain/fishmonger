package de.bitbrain.fishmonger.model.spawn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.tmx.TiledMapAPI;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.behaviour.FishBehaviour;
import de.bitbrain.fishmonger.model.FishType;

public class Spawner {

   private final Vector2 origin = new Vector2();
   private final Vector2 bounds = new Vector2();
   private final int capacity;

   public Spawner(float x, float y, float width, float height, int capacity) {
      this.origin.set(x, y);
      this.bounds.set(width, height);
      this.capacity = capacity;
   }

   public void spawn(GameContext context, GameObject player) {
      for (int i = 0; i < capacity; ++i) {
         GameObject fish = context.getGameWorld().addObject();
         TiledMapAPI api = context.getTiledMapManager().getAPI();
         boolean positionFree = false;
         float targetX = 0f;
         float targetY = 0f;
         while (!positionFree) {
            targetX = origin.x + (float)(bounds.x * Math.random());
            targetY = origin.y + (float)(bounds.y * Math.random());
            fish.setDimensions(8f, 8f);
            targetX = (float) (Math.floor(targetX / api.getCellWidth()) * api.getCellWidth());
            targetY = (float) (Math.floor(targetY / api.getCellHeight()) * api.getCellHeight());
            positionFree = !api.isCollision(targetX, targetY, 0);
         }
         fish.setPosition(targetX, targetY);

         fish.setType(computeType());
         api.setLayerIndex(fish, 0);
         fish.setAttribute(Orientation.class, Orientation.DOWN);

         GameObject pointer = context.getGameWorld().addObject();
         pointer.setDimensions(8f, 8f);
         pointer.setType("NONE");
         pointer.setActive(false);

         MapProperties properties = new MapProperties();
         properties.put("collision", true);
         fish.setAttribute(MapProperties.class, properties);
         pointer.setAttribute(MapProperties.class, properties);

         FishBehaviour behavior = new FishBehaviour(fish, pointer, player, context.getTiledMapManager(), context.getEventManager());
         behavior.setMinLength(2);
         context.getBehaviorManager().apply(behavior);
      }
   }

   private FishType computeType() {
      while (true) {
         for (FishType t : FishType.values()) {
            if (Math.random() < t.getProbability()) {
              return t;
            }
         }
      }
   }
}
