package de.bitbrain.fishmonger.input.help;

import com.badlogic.gdx.InputAdapter;
import de.bitbrain.fishmonger.screens.HelpScreen;

public class HelpKeyboardInput extends InputAdapter {

   private final HelpScreen screen;

   public HelpKeyboardInput(HelpScreen screen) {
      this.screen = screen;
   }

   @Override
   public boolean keyDown(int keycode) {
      screen.exit();
      return true;
   }
}
