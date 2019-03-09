package de.bitbrain.fishmonger.behaviour;

import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.event.GameEventManager;
import de.bitbrain.braingdx.tmx.TiledMapAPI;
import de.bitbrain.braingdx.tmx.TiledMapManager;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.GameObjectUtils;

public class FishBehaviour extends ChasingBehavior {

   private float INTERVAL_MIN = 1f;
   private float INTERVAL_MAX = 2f;
   private float FLEE_TIME_MIN = 1f;
   private float FLEE_TIME_MAX = 2f;

   private GameObject pointer;
   private GameObject player;
   private final DeltaTimer timer = new DeltaTimer();
   private final DeltaTimer fleeTimer = new DeltaTimer();
   private Vector2 opposite = new Vector2();

   private float currentInterval, currentFleeInterval;

   public FishBehaviour(GameObject source, GameObject pointer, GameObject player, TiledMapManager tiledMapManager, GameEventManager eventManager) {
      super(source, pointer, tiledMapManager, eventManager);
      this.pointer = pointer;
      this.player = player;
      recomputeIntervals();
      timer.update(currentInterval);
      fleeTimer.update(currentFleeInterval);
   }

   @Override
   public void update(GameObject s, float delta) {
      super.update(s, delta);
      timer.update(delta);
      fleeTimer.update(delta);
      if (player != null && fleeTimer.reached(currentFleeInterval) && GameObjectUtils.distanceBetween(this.source, player) < 32f) {
         recomputeIntervals();
         fleeTimer.reset();
         timer.reset();
         opposite.x = this.player.getLeft() - source.getLeft();
         opposite.y = this.player.getTop() - source.getTop();
         opposite.scl(4f);
         opposite.rotate(180f);
         float targetX = source.getLeft() + opposite.x;
         float targetY = source.getTop() + opposite.y;
         if (targetX < 0f) {
            targetX = 0f;
         }
         if (targetY < 0f) {
            targetY = 0f;
         }
         if (targetX > tiledMapManager.getAPI().getWorldWidth()) {
            targetX = tiledMapManager.getAPI().getWorldWidth() - source.getWidth();
         }
         if (targetY > tiledMapManager.getAPI().getWorldHeight()) {
            targetY = tiledMapManager.getAPI().getWorldHeight() - source.getHeight();
         }
         pointer.setPosition(targetX, targetY);
      }
      if (timer.reached(currentInterval)) {
         recomputeIntervals();
         TiledMapAPI api = tiledMapManager.getAPI();
         timer.reset();

         float targetX = (float) (api.getWorldWidth() * Math.random());
         float targetY = (float) (api.getWorldHeight() * Math.random());

         if (targetX < 0f) {
            targetX = 0f;
         }
         if (targetY < 0f) {
            targetY = 0f;
         }
         if (targetX >= tiledMapManager.getAPI().getWorldWidth()) {
            targetX = tiledMapManager.getAPI().getWorldWidth() - source.getWidth();
         }
         if (targetY >= tiledMapManager.getAPI().getWorldHeight()) {
            targetY = tiledMapManager.getAPI().getWorldHeight() - source.getHeight();
         }
         pointer.setPosition(targetX, targetY);
      }
   }

   private void recomputeIntervals() {
      currentInterval = (float) (INTERVAL_MIN + Math.random() * (INTERVAL_MAX - INTERVAL_MIN));
      currentFleeInterval = (float) (FLEE_TIME_MIN + Math.random() * (FLEE_TIME_MAX - FLEE_TIME_MIN));
   }
}
