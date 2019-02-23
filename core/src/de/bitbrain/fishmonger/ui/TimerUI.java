package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.Config;

public class TimerUI extends Actor {

   private static final float PADDING = 9f;

   private final Texture texture;
   private final DeltaTimer timer;

   public TimerUI(DeltaTimer timer) {
      this.timer = timer;
      texture = GraphicsFactory.createTexture(2, 2, Color.WHITE);
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {

      float percentage =  1f - timer.getTicks() / Config.GAME_DURATION_IN_SECONDS;

      if (percentage < 0) {
         percentage = 0f;
      }
      if (percentage > 1f) {
         percentage = 1f;
      }

      batch.setColor(Colors.BORDER);
      batch.draw(texture, getX(), getY(), getWidth(), getHeight());
      batch.setColor(Colors.PROGRESS);
      batch.draw(texture, getX() + PADDING, getY() + PADDING, getWidth() - PADDING * 2f, getHeight() - PADDING * 2f);
      if (percentage > 0.05f) {
         batch.setColor(Colors.FOREGROUND);
         batch.draw(texture, getX() + PADDING, getY() + PADDING, getWidth() * percentage - PADDING * 2f, getHeight() - PADDING * 2f);
      }
   }
}
