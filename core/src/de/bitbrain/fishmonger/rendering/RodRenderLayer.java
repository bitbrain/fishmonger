package de.bitbrain.fishmonger.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.event.GameEventListener;
import de.bitbrain.braingdx.event.GameEventManager;
import de.bitbrain.braingdx.graphics.pipeline.RenderLayer;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.event.FishingRodEvents;

import static de.bitbrain.braingdx.behavior.movement.Orientation.*;

public class RodRenderLayer implements RenderLayer {

   private final Vector2 currentRodLocation = new Vector2();
   private final GameObject player;
   private ShapeRenderer shapeRenderer;
   private final Sprite hook;

   private boolean enabled = false;

   private final GameEventListener<FishingRodEvents.MoveToLocationEvent> rodLocationListener = new GameEventListener<FishingRodEvents.MoveToLocationEvent>() {

      @Override
      public void onEvent(FishingRodEvents.MoveToLocationEvent event) {
         currentRodLocation.set(event.getLocation());
         currentRodLocation.x = event.getLocation().x + player.getWidth() / 2f;
         currentRodLocation.y = event.getLocation().y + player.getHeight() / 2f;
      }
   };

   private final GameEventListener<FishingRodEvents.ThrowRodEvent> throwRodListener = new GameEventListener<FishingRodEvents.ThrowRodEvent>() {

      @Override
      public void onEvent(FishingRodEvents.ThrowRodEvent event) {
         enabled = true;
         Orientation orientation = (Orientation) player.getAttribute(Orientation.class);
         currentRodLocation.x = player.getLeft() + player.getOffsetX() + player.getWidth() / 2f + player.getWidth() * orientation.getXFactor();
         currentRodLocation.y = player.getTop() + player.getOffsetY() + player.getHeight() / 2f + player.getHeight() * orientation.getYFactor();
      }
   };

   private final GameEventListener<FishingRodEvents.PullBackEvent> pullBackListener = new GameEventListener<FishingRodEvents.PullBackEvent>() {

      @Override
      public void onEvent(FishingRodEvents.PullBackEvent event) {
         enabled = false;
      }
   };

   public RodRenderLayer(GameEventManager eventManager, GameObject player) {
      this.player = player;
      eventManager.register(rodLocationListener, FishingRodEvents.MoveToLocationEvent.class);
      eventManager.register(throwRodListener, FishingRodEvents.ThrowRodEvent.class);
      eventManager.register(pullBackListener, FishingRodEvents.PullBackEvent.class);

      this.hook = new Sprite(SharedAssetManager.getInstance().get(Assets.Textures.HOOK, Texture.class));
   }

   @Override
   public void beforeRender() {

   }

   @Override
   public void render(Batch batch, float delta) {
      if (!enabled) {
         return;
      }
      if (shapeRenderer == null) {
         shapeRenderer = new ShapeRenderer(10);
      }
      shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
      shapeRenderer.setColor(Colors.FISHING_ROD);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
      Orientation orientation = (Orientation) player.getAttribute(Orientation.class);
      float startX = player.getLeft() + player.getOffsetX() + player.getWidth() / 2f + player.getWidth() * orientation.getXFactor();
      float startY = player.getTop() + player.getOffsetY() + player.getHeight() / 2f + player.getHeight() * orientation.getYFactor();
      shapeRenderer.rectLine(new Vector2(startX, startY), currentRodLocation,1);
      shapeRenderer.end();

      float hookX = currentRodLocation.x;
      float hookY = currentRodLocation.y;

      hook.setOrigin(4f, 4f);
      if (orientation == UP) {
         hook.setRotation(90f);
         hookX -= 3.6f;
      }
      if (orientation == DOWN) {
         hook.setRotation(-90f);
         hookX -= 4.5f;
         hookY -= 8f;
      }
      if (orientation == LEFT) {
         hook.setRotation(180);
         hookX -= 8f;
         hookY -= 3.6f;
      }
      if (orientation == RIGHT) {
         hook.setRotation(0);
         hookY -= 4.5f;
      }
      hook.setBounds(hookX, hookY, 8, 8);
      batch.begin();
      hook.draw(batch);
      batch.end();
   }
}
