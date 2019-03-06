package de.bitbrain.fishmonger.input.levelselection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;
import de.bitbrain.fishmonger.screens.LevelSelectionScreen;
import de.bitbrain.fishmonger.ui.ButtonMenu;

public class LevelSelectionControllerInput extends ControllerAdapter {

   private final ButtonMenu menu;
   private final LevelSelectionScreen screen;

   public LevelSelectionControllerInput(ButtonMenu buttonMenu, LevelSelectionScreen screen) {
      this.menu = buttonMenu;
      this.screen = screen;
   }

   @Override
   public boolean axisMoved(Controller controller, int axisIndex, float value) {
      if (axisIndex == Xbox.L_STICK_VERTICAL_AXIS && value == -1) {
         menu.checkPrevious();
         return true;
      }
      if (axisIndex == Xbox.L_STICK_VERTICAL_AXIS && value == 1) {
         menu.checkNext();
         return true;
      }
      if (axisIndex == Xbox.R_STICK_VERTICAL_AXIS && value == -1) {
         menu.checkPrevious();
         return true;
      }
      if (axisIndex == Xbox.R_STICK_VERTICAL_AXIS && value == 1) {
         menu.checkNext();
         return true;
      }
      return false;
   }

   @Override
   public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
      if (value == PovDirection.north) {
         menu.checkPrevious();
         return true;
      }
      if (value == PovDirection.south) {
         menu.checkNext();
         return true;
      }
      return false;
   }

   @Override
   public boolean buttonDown(Controller controller, int buttonIndex) {
      if (buttonIndex == getExitButton(controller)) {
         Gdx.app.exit();
         return true;
      }
      if (buttonIndex == getSubmitButton(controller)) {
         this.menu.clickChecked();
         return true;
      }
      if (buttonIndex == getHelpButton(controller)) {
         screen.helpScreen();
         return true;
      }
      if (buttonIndex == getShopButton(controller)) {
         screen.shopScreen();
         return true;
      }
      return false;
   }

   private int getHelpButton(Controller controller) {
      if (Xbox.isXboxController(controller)) {
         return Xbox.START;
      }
      return -1;
   }

   private int getShopButton(Controller controller) {
      if (Xbox.isXboxController(controller)) {
         return Xbox.BACK;
      }
      return -1;
   }

   private int getExitButton(Controller controller) {
      if (Xbox.isXboxController(controller)) {
         return Xbox.B;
      }
      return -1;
   }

   private int getSubmitButton(Controller controller) {
      if (Xbox.isXboxController(controller)) {
         return Xbox.A;
      }
      return -1;
   }
}
