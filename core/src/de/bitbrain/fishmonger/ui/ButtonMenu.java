package de.bitbrain.fishmonger.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.bitbrain.braingdx.tweens.ColorTween;
import de.bitbrain.fishmonger.Config;

import java.util.ArrayList;
import java.util.List;

public class ButtonMenu extends Table {

   public static class ButtonMenuStyle {
      public float buttonWidth = Config.MENU_BUTTON_WIDTH;
      public float buttonHeight = Config.MENU_BUTTON_HEIGHT;
      public boolean checkMode = false;
      public float padding = Config.MENU_BUTTON_PADDING;
      public boolean vertical = true;
      public float buttonAlpha = Config.MENU_BUTTON_ALPHA;
   }

   private TweenManager tweenManager;

   private List<Button> buttons = new ArrayList<Button>();

   private int currentCheckIndex = -1;

   private final ButtonMenuStyle style;

   public ButtonMenu(TweenManager tweenManager) {
      this(tweenManager, new ButtonMenuStyle());
   }

   public ButtonMenu(TweenManager tweenManager, ButtonMenuStyle style) {
      this.tweenManager = tweenManager;
      this.style = style;

      setTouchable(Touchable.childrenOnly);
   }

   public Cell<TextButton> add(String caption, final ClickListener listener) {
      final TextButton button = new TextButton(caption, Styles.BUTTON_MENU) {

         @Override
         public void setChecked(boolean isChecked) {
            if (!isChecked) {
               tweenManager.killTarget(this);
               Tween.to(this.getColor(), ColorTween.A, 1.0f).target(style.buttonAlpha).ease(TweenEquations.easeOutCubic).start(tweenManager);
            } else {
               tweenManager.killTarget(this);
               Tween.to(this.getColor(), ColorTween.A, 1.0f).target(1f).ease(TweenEquations.easeOutCubic).start(tweenManager);
               // TODO play audio
            }
            super.setChecked(isChecked);
         }
      };
      button.setColor(new Color(1f, 1f, 1f, style.buttonAlpha));
      button.addCaptureListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!button.isDisabled()) {
               listener.clicked(event, x, y);
            }
         }

         @Override
         public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            if (!button.isDisabled()) {
               listener.enter(event, x, y, pointer, fromActor);
            }
         }

         @Override
         public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            if (!button.isDisabled()) {
               listener.exit(event, x, y, pointer, toActor);
            }
         }
      });

      Cell<TextButton> cell = center().add(button).width(style.buttonWidth).height(style.buttonHeight);
      if (style.vertical) {
         row();
      }
      if (!buttons.isEmpty()) {
         if (style.vertical) {
            cell.padTop(style.padding);
         } else {
            cell.padLeft(style.padding);
         }
      }
      button.addCaptureListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!button.isDisabled()) {
               // TODO play select sound
            }
         }

         @Override
         public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            if (!button.isDisabled() && !button.isChecked()) {
               super.enter(event, x, y, pointer, fromActor);
               tweenManager.killTarget(button);
               Tween.to(button.getColor(), ColorTween.A, 1.0f).target(1f).ease(TweenEquations.easeOutCubic).start(tweenManager);
               // TODO play hover sound
               setChecked(buttons.indexOf(button));

            }
         }

         @Override
         public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            if (!button.isDisabled() && !button.isChecked()) {
               super.exit(event, x, y, pointer, toActor);
               if (event.getRelatedActor() == null || (!event.getRelatedActor().equals(button) &&
                     event.getRelatedActor() instanceof TextButton)) {
                  tweenManager.killTarget(button);
                  Tween.to(button.getColor(), ColorTween.A, 1.0f).target(style.buttonAlpha).ease(TweenEquations.easeOutCubic).start(tweenManager);
               }
            }
         }
      });
      buttons.add(button);
      validateCheckState();
      return cell;
   }

   public void checkNext() {
      setChecked(getNextIndex());
   }

   public void checkPrevious() {
      setChecked(getPreviusIndex());
   }

   public void clickChecked() {
      if (currentCheckIndex >= 0f && currentCheckIndex < buttons.size()) {
         Button button = buttons.get(currentCheckIndex);
         for (EventListener l : button.getCaptureListeners()) {
            if (l instanceof ClickListener) {
               ClickListener cl = (ClickListener)l;
               cl.clicked(new InputEvent(), 0f, 0f);
            }
         }
         button.setChecked(true);
      }
   }

   private void validateCheckState() {
      if (style.checkMode && buttons.size() == 1) {
         setChecked(0);
      }
   }

   private boolean isValidCheck(int index) {
      return index >= 0 && index < buttons.size() && !buttons.get(index).isDisabled();
   }

   private void setChecked(int index) {
      for (int i = 0; i < buttons.size(); ++i) {
         Button button = buttons.get(i);
         button.setChecked(i == index);
      }
      currentCheckIndex = index;
   }

   private int getNextIndex() {
      int index = currentCheckIndex;
      boolean inc = false;
      int count = -1;
      while (!inc || !isValidCheck(index)) {
         if (count > buttons.size()) {
            return -1;
         }
         inc = true;
         index++;
         if (index >= buttons.size()) {
            index = 0;
         }
         count++;
      }
      return index;
   }

   private int getPreviusIndex() {
      int index = currentCheckIndex;
      boolean dec = false;
      int count = -1;
      while (!dec || !isValidCheck(index)) {
         if (count > buttons.size()) {
            return -1;
         }
         dec = true;
         index--;
         if (index < 0) {
            index = buttons.size() - 1;
         }
         count++;
      }
      return index;
   }
}