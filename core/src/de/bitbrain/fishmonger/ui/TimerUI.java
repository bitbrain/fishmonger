package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;

public class TimerUI extends Actor {

   private static final float PADDING = 9f;

   private final Texture texture;
   private final DeltaTimer timer;
   private final Label time;
   private final float duration;

   public TimerUI(DeltaTimer timer, float duration) {
      this.timer = timer;
      this.duration = duration;
      texture = GraphicsFactory.createTexture(2, 2, Color.WHITE);
      time = new Label(duration + " seconds", Styles.LABEL_TIME);
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {

      float percentage =  1f - timer.getTicks() / duration;

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

      // Set caption
      int remainingTimeInSeconds = (int)(duration * percentage);

      if (remainingTimeInSeconds == 1) {
         time.setText(Bundle.get(Messages.TIME_CAPTION));
      } else {
         time.setText(Bundle.get(Messages.TIME_CAPTION_PLURAL, remainingTimeInSeconds));
      }

      time.setPosition(getX() + getWidth() / 2f - time.getPrefWidth() / 2f, getY() + getHeight() / 2f - time.getPrefHeight() / 2f);
      time.draw(batch, parentAlpha);
   }
}
