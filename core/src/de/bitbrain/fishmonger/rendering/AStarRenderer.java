package de.bitbrain.fishmonger.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.braingdx.graphics.pipeline.RenderLayer;
import de.bitbrain.braingdx.tmx.TiledMapManager;
import de.bitbrain.fishmonger.behaviour.ChasingBehavior;

public class AStarRenderer implements RenderLayer {
      
      private final TiledMapManager tiledMapManager;
      private final ChasingBehavior behavior;
      private Texture texture;
      
      public AStarRenderer(TiledMapManager manager, ChasingBehavior behavior) {
         this.tiledMapManager = manager;
         this.behavior = behavior;
         texture = GraphicsFactory.createTexture(2, 2, Color.GREEN);
      }

      @Override
      public void beforeRender() {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void render(Batch batch, float delta) {
         if (behavior.getPath() != null) {
            batch.begin();
            for (int i = 0; i < behavior.getPath().getLength(); ++i) {
               batch.draw(texture,
                     behavior.getPath().getX(i) * tiledMapManager.getAPI().getCellWidth(),
                     behavior.getPath().getY(i) * tiledMapManager.getAPI().getCellHeight(),
                     tiledMapManager.getAPI().getCellWidth(),
                     tiledMapManager.getAPI().getCellHeight());
            }
            batch.end();
         }
      }
   }