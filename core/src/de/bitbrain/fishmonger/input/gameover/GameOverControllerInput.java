package de.bitbrain.fishmonger.input.gameover;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import de.bitbrain.fishmonger.screens.GameOverScreen;

public class GameOverControllerInput extends ControllerAdapter {

   private final GameOverScreen screen;

   public GameOverControllerInput(GameOverScreen screen) {
      this.screen = screen;
   }

   @Override
   public boolean buttonDown(Controller controller, int buttonIndex) {
      screen.exit();
      return true;
   }
}
