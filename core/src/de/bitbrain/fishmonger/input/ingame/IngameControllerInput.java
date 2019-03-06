package de.bitbrain.fishmonger.input.ingame;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.math.Vector3;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.behavior.movement.Movement;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.util.Updateable;
import de.bitbrain.fishmonger.catching.FishingRod;
import de.bitbrain.fishmonger.screens.IngameScreen;
import de.bitbrain.fishmonger.screens.LevelSelectionScreen;

import java.util.HashMap;
import java.util.Map;

import static de.bitbrain.braingdx.behavior.movement.Orientation.*;

public class IngameControllerInput extends ControllerAdapter implements Updateable {

   private final Movement<Orientation> movement;
   private final FishingRod rod;
   private final GameContext context;
   private final IngameScreen ingameScreen;

   private Map<Integer, Float> axis = new HashMap<Integer, Float>();

   private Orientation currentOrientation = null;

   public IngameControllerInput(Movement<Orientation> movement, FishingRod rod, GameContext context, IngameScreen ingameScreen) {
      this.movement = movement;
      this.rod = rod;
      this.context = context;
      this.ingameScreen = ingameScreen;
   }

   @Override
   public boolean axisMoved(Controller controller, int axisIndex, float value) {
      axis.put(axisIndex, value);
      if (axisIndex == Xbox.L_STICK_HORIZONTAL_AXIS && value  < -0.5) {
         currentOrientation = LEFT;
         return true;
      }
      if (axisIndex == Xbox.L_STICK_HORIZONTAL_AXIS && value > 0.5) {
         currentOrientation = RIGHT;
         return true;
      }
      if (axisIndex == Xbox.L_STICK_VERTICAL_AXIS && value < -0.5) {
         currentOrientation = UP;
         return true;
      }
      if (axisIndex == Xbox.L_STICK_VERTICAL_AXIS && value > 0.5) {
         currentOrientation = DOWN;
         return true;
      }
      if (axisIndex == Xbox.R_STICK_HORIZONTAL_AXIS && value < -0.5) {
         currentOrientation = LEFT;
         return true;
      }
      if (axisIndex == Xbox.R_STICK_HORIZONTAL_AXIS && value > 0.5) {
         currentOrientation = RIGHT;
         return true;
      }
      if (axisIndex == Xbox.R_STICK_VERTICAL_AXIS && value < -0.5) {
         currentOrientation = UP;
         return true;
      }
      if (axisIndex == Xbox.R_STICK_VERTICAL_AXIS && value > 0.5) {
         currentOrientation = DOWN;
         return true;
      }
      return false;
   }

   @Override
   public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
      if (value == PovDirection.north) {
         currentOrientation = UP;
         return true;
      }
      if (value == PovDirection.south) {
         currentOrientation = DOWN;
         return true;
      }
      if (value == PovDirection.west) {
         currentOrientation = LEFT;
         return true;
      }
      if (value == PovDirection.east) {
         currentOrientation = RIGHT;
         return true;
      }
      if (value == PovDirection.center) {
         currentOrientation = null;
      }
      return false;
   }

   @Override
   public boolean buttonDown(Controller controller, int buttonIndex) {
      if (buttonIndex == getThrowButton(controller)) {
         rod.throwRod();
         return true;
      }
      if (buttonIndex == getAbortButton(controller)) {
         ingameScreen.setGameOver();
         context.getScreenTransitions().out(new LevelSelectionScreen(ingameScreen.getGame()), 1f);
         return true;
      }

      return false;
   }

   private int getAbortButton(Controller controller) {
      if (Xbox.isXboxController(controller)) {
         return Xbox.B;
      }
      return -1;
   }

   private int getThrowButton(Controller controller) {
      if (Xbox.isXboxController(controller)) {
         return Xbox.A;
      }
      return -1;
   }

   @Override
   public void update(float delta) {

      float tolerance = 0.2f;

      // Left trigger
      Float leftX = axis.get(Xbox.L_STICK_HORIZONTAL_AXIS);
      Float leftY = axis.get(Xbox.L_STICK_VERTICAL_AXIS);

      if (leftX != null && leftY != null) {
         if (leftX < tolerance && leftX > -tolerance && leftY < tolerance && leftY > -tolerance) {
            currentOrientation = null;
            axis.clear();
         }
      }

      // Right trigger
      Float rightX = axis.get(Xbox.R_STICK_HORIZONTAL_AXIS);
      Float rightY = axis.get(Xbox.R_STICK_VERTICAL_AXIS);

      if (rightX != null && rightY != null) {
         if (rightX < tolerance && rightX > -tolerance && rightY < tolerance && rightY > -tolerance) {
            currentOrientation = null;
            axis.clear();
         }
      }

      if (currentOrientation != null) {
         movement.move(currentOrientation);
      }
   }
}
