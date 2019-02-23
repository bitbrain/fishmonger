package de.bitbrain.fishmonger.behaviour;

import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.event.GameEventManager;
import de.bitbrain.braingdx.tmx.TiledMapAPI;
import de.bitbrain.braingdx.tmx.TiledMapManager;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.GameObjectUtils;

public class FishBehaviour extends ChasingBehavior {

   private float INTERVAL = 12f;
   private float FLEE_TIME = 2f;

   private GameObject pointer;
   private GameObject player;
   private final DeltaTimer timer = new DeltaTimer();
   private final DeltaTimer fleeTimer = new DeltaTimer();
   private Vector2 opposite = new Vector2();

   public FishBehaviour(GameObject source, GameObject pointer, GameObject player, TiledMapManager tiledMapManager, GameEventManager eventManager) {
      super(source, pointer, tiledMapManager, eventManager);
      this.pointer = pointer;
      this.player = player;
      fleeTimer.update(FLEE_TIME);
   }

   @Override
   public void update(GameObject s, float delta) {
      super.update(s, delta);
      timer.update(delta);
      fleeTimer.update(delta);
      if (player != null && fleeTimer.reached(FLEE_TIME) && GameObjectUtils.distanceBetween(this.source, player) < 18f) {
         fleeTimer.reset();
         timer.reset();
         opposite.x = this.source.getLeft() - player.getLeft();
         opposite.y = this.source.getTop() - player.getTop();
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
      if (fleeTimer.reached(FLEE_TIME) && timer.reached(INTERVAL)) {
         TiledMapAPI api = tiledMapManager.getAPI();
         timer.reset();
         float targetX = (float)(api.getWorldWidth() * 2f * Math.random());
         float targetY = (float)(api.getWorldHeight()  * Math.random());
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
}
