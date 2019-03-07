package de.bitbrain.fishmonger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tmx.TiledMapType;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.SimpleWorldBounds;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.input.gameover.GameOverControllerInput;
import de.bitbrain.fishmonger.input.gameover.GameOverKeyboardInput;
import de.bitbrain.fishmonger.model.Money;
import de.bitbrain.fishmonger.model.inventory.Inventory;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.model.spawn.Spawner;
import de.bitbrain.fishmonger.ui.DialogManager;
import de.bitbrain.fishmonger.ui.DialogUI;
import de.bitbrain.fishmonger.ui.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.bitbrain.fishmonger.Colors.BACKGROUND;

public class GameOverScreen extends AbstractScreen {

   private final Money money;
   private final Inventory inventory;
   private final List<Item> delivered;
   private DialogManager dialogManager;
   private GameContext context;

   private boolean exiting = false, dialog = true;
   private DialogUI dialogUI;
   private Music music;

   public GameOverScreen(BrainGdxGame game, Money money, Inventory inventory, List<Item> delivered) {
      super(game);
      this.money = money;
      this.inventory = inventory;
      this.delivered = delivered;
   }

   @Override
   public void dispose() {
      super.dispose();
      Controllers.clearListeners();
   }

   @Override
   protected void onCreate(GameContext context) {
      music = SharedAssetManager.getInstance().get(Assets.Musics.RICHARD_GIER, Music.class);
      music.setLooping(true);
      music.setVolume(0.4f);
      music.play();
      this.dialogManager = new DialogManager();
      this.context = context;

      prepareGameOverDialog();

      setBackgroundColor(BACKGROUND);
      setupWorld(context);
      setupRenderer(context);
      setupUI(context);
      setupInput(context);

      dialogManager.nextDialog();
   }

   public void exit() {
      if (exiting) {
         return;
      }
      context.getScreenTransitions().out(new LevelSelectionScreen(getGame()), 0.5f);
      exiting = true;
      this.music.stop();
      Music music = SharedAssetManager.getInstance().get(Assets.Musics.MAIN_MENU, Music.class);
      music.setLooping(true);
      music.setVolume(0.4f);
      music.play();
   }

   @Override
   protected void onUpdate(float delta) {
      if (dialog) {
         dialog = false;
         return;
      }
      if (exiting) {
         return;
      }
      super.onUpdate(delta);
      if (!dialog && dialogUI.hasFinishedDialoging()) {
         exit();
      }
   }

   private void setupInput(GameContext context) {
      context.getInput().addProcessor(new GameOverKeyboardInput(this));
      Controllers.addListener(new GameOverControllerInput(this));
   }

   private void setupWorld(GameContext context) {
      TiledMap level = SharedAssetManager.getInstance().get(Assets.TiledMaps.GAME_OVER, TiledMap.class);
      context.getTiledMapManager().load(level, context.getGameCamera().getInternalCamera(), TiledMapType.ORTHOGONAL);
      context.getGameWorld().setBounds(new SimpleWorldBounds(
            context.getTiledMapManager().getAPI().getWorldWidth(),
            context.getTiledMapManager().getAPI().getWorldHeight())
      );
      List<Spawner> spawners = new ArrayList<Spawner>();
      for (GameObject o : context.getGameWorld()) {
         if ("PLAYER".equals(o.getType())) {
            configurePlayer(context, o);
         }
         if ("GIER".equals(o.getType())) {
            configureGier(context, o);
         }
         if ("SPAWN".equals(o.getType())) {
            int capacity = ((MapProperties)o.getAttribute(MapProperties.class)).get("capacity", 1, Integer.class);
            Spawner spawner = new Spawner(o.getLeft(), o.getTop(), o.getWidth(), o.getHeight(), capacity, 4);
            spawners.add(spawner);
         }
      }
      for (Spawner spawner : spawners) {
         spawner.spawn(context, null);
      }
   }

   private void setupRenderer(GameContext context) {
      Animations.setupPlayerAnimations(context);
      Animations.setupFishAnimations(context);
   }

   private void configurePlayer(GameContext context, GameObject player) {
      player.setDimensions(16f, 8f);
      player.setScaleY(2f);
      float correctX = (float) (Math.floor(player.getLeft() / context.getTiledMapManager().getAPI().getCellWidth()) * context.getTiledMapManager().getAPI().getCellWidth());
      float correctY = (float) (Math.floor(player.getTop() / context.getTiledMapManager().getAPI().getCellHeight()) * context.getTiledMapManager().getAPI().getCellHeight());
      player.setPosition(correctX, correctY);
      context.getGameCamera().setStickToWorldBounds(true);
      context.getGameCamera().setDefaultZoomFactor(0.1f);
      context.getGameCamera().setZoomScalingFactor(0.0000001f);
      context.getGameCamera().setTrackingTarget(player);
      context.getGameCamera().setTargetTrackingSpeed(0.1f);
      player.setOrigin(player.getWidth() / 2f, player.getHeight() / 2f);
      player.setActive(false);
      player.setAttribute(Orientation.class, Orientation.UP);
   }

   private void configureGier(GameContext context, GameObject gier) {
      gier.setDimensions(16f, 8f);
      gier.setScaleY(2f);
      float correctX = (float) (Math.floor(gier.getLeft() / context.getTiledMapManager().getAPI().getCellWidth()) * context.getTiledMapManager().getAPI().getCellWidth());
      float correctY = (float) (Math.floor(gier.getTop() / context.getTiledMapManager().getAPI().getCellHeight()) * context.getTiledMapManager().getAPI().getCellHeight());
      gier.setPosition(correctX, correctY);
      gier.setOrigin(gier.getWidth() / 2f, gier.getHeight() / 2f);
      gier.setAttribute(Orientation.class, Orientation.DOWN);
   }

   private void setupUI(GameContext context) {
      dialogUI = new DialogUI(dialogManager);
      float width = Gdx.graphics.getWidth() / 2f;
      dialogUI.setHeight(130f);
      dialogUI.setWidth(width);
      dialogUI.setX(Gdx.graphics.getWidth() / 2f - width / 2f);
      context.getStage().addActor(dialogUI);
      Toast.getInstance().init(context.getStage());
   }

   private void prepareGameOverDialog() {
      if (money.getAmount() == 0) {
         dialogManager.addDialog("Richard Gier", Messages.GAME_OVER_EMPTY, Animations.createGierAvatar(), true);
         return;
      }

      dialogManager.addDialog("Richard Gier", Messages.GAME_OVER_INTRODUCTION, Animations.createGierAvatar(), true);

      dialogManager.addDialog("Richard Gier", Messages.GAME_OVER_OVERVIEW, Animations.createGierAvatar(), true, constructFishString());

      dialogManager.addDialog("Richard Gier", Messages.GAME_OVER_RESULT, Animations.createGierAvatar(), true, money.getAmount());

     // dialogManager.addDialog("Richard Gier", , Animations.createGierAvatar());


      if (delivered.size() > 10) {
         dialogManager.addDialog("Richard Gier", Messages.GAME_OVER_SUFFICIENT, Animations.createGierAvatar(), true);
      } else {
         dialogManager.addDialog("Richard Gier", Messages.GAME_OVER_INSUFFICIENT_CASH, Animations.createGierAvatar(), true);
      }
   }

   private String constructFishString() {
      Map<String, Item> items = new HashMap<String, Item>();
      Map<String, Integer> amounts = new HashMap<String, Integer>();
      for (Item item : delivered) {
         items.put(item.getId(), item);
         Integer amount = amounts.get(item.getId());
         if (amount == null) {
            amounts.put(item.getId(), 1);
         } else {
            amounts.put(item.getId(), amount + 1);
         }
      }
      StringBuilder fishString = new StringBuilder();

      int counter = 0;
      for (Map.Entry<String, Integer> entry : amounts.entrySet()) {
         counter++;
         int amount = entry.getValue();
         Item item = items.get(entry.getKey());
         String name = amount != 1 ? item.getPluralName() : item.getName();
         if (amounts.size() > 1 && counter == amounts.size()) {
            fishString.append(" ").append(Bundle.get(Messages.AND)).append(" ");
         } else if (counter > 1) {
            fishString.append(" ");
         }
         fishString.append(amount).append(" ").append(name);
      }

      return fishString.toString();
   }
}
