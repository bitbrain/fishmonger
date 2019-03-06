package de.bitbrain.fishmonger.input.help;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import de.bitbrain.fishmonger.screens.HelpScreen;

public class HelpControllerInput extends ControllerAdapter {

   private final HelpScreen screen;

   public HelpControllerInput(HelpScreen screen) {
      this.screen = screen;
   }

   @Override
   public boolean buttonDown(Controller controller, int buttonIndex) {
      screen.exit();
      return true;
   }
}
