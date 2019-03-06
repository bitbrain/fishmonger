package de.bitbrain.fishmonger.input.levelselection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.bitbrain.fishmonger.screens.LevelSelectionScreen;
import de.bitbrain.fishmonger.ui.ButtonMenu;

public class LevelSelectionKeyboardInput extends InputAdapter {

   private final ButtonMenu menu;
   private final LevelSelectionScreen screen;

   public LevelSelectionKeyboardInput(ButtonMenu buttonMenu, LevelSelectionScreen screen) {
      this.menu = buttonMenu;
      this.screen = screen;
   }

   @Override
   public boolean keyDown(int keycode) {
      if (keycode == Input.Keys.ESCAPE) {
         Gdx.app.exit();
         return true;
      }
      if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
         menu.checkNext();
         return true;
      }
      if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
         menu.checkPrevious();
         return true;
      }
      if (keycode == Input.Keys.ENTER || keycode == Input.Keys.SPACE) {
         menu.clickChecked();
         return true;
      }
      if (keycode == Input.Keys.Q) {
         screen.shopScreen();
         return true;
      }
      if (keycode == Input.Keys.E) {
         screen.helpScreen();
         return true;
      }
      return false;
   }
}
