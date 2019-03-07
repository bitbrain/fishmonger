package de.bitbrain.fishmonger.input.shopkeeper;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.bitbrain.fishmonger.screens.ShopkeeperScreen;
import de.bitbrain.fishmonger.ui.ButtonMenu;

public class ShopkeeperKeyboardInput extends InputAdapter {

   private final ButtonMenu menu;
   private final ShopkeeperScreen screen;

   public ShopkeeperKeyboardInput(ButtonMenu buttonMenu, ShopkeeperScreen screen) {
      this.menu = buttonMenu;
      this.screen = screen;
   }

   @Override
   public boolean keyDown(int keycode) {
      if (keycode == Input.Keys.ESCAPE) {
         screen.exit();
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
      return false;
   }
}
