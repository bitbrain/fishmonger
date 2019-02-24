package de.bitbrain.fishmonger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.behavior.movement.RasteredMovementBehavior;
import de.bitbrain.braingdx.graphics.pipeline.layers.RenderPipeIds;
import de.bitbrain.braingdx.input.OrientationMovementController;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tmx.TiledMapType;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.SimpleWorldBounds;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.FishMongerGame;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.behaviour.SellToGierBehavior;
import de.bitbrain.fishmonger.catching.FishingRod;
import de.bitbrain.fishmonger.event.InventoryClearedEvent;
import de.bitbrain.fishmonger.event.ItemAddedToInventoryEvent;
import de.bitbrain.fishmonger.event.handler.InventoryClearedHandler;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.input.ingame.IngameControllerInput;
import de.bitbrain.fishmonger.input.ingame.IngameKeyboardInput;
import de.bitbrain.fishmonger.model.Money;
import de.bitbrain.fishmonger.model.inventory.Inventory;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.model.spawn.Spawner;
import de.bitbrain.fishmonger.rendering.RodRenderLayer;
import de.bitbrain.fishmonger.ui.*;

import java.util.ArrayList;
import java.util.List;

import static de.bitbrain.fishmonger.Colors.BACKGROUND;

public class IngameScreen extends AbstractScreen<BrainGdxGame> {

   private Inventory inventory;
   private GameContext context;
   private Money money;
   private List<Spawner> spawners = new ArrayList<Spawner>();
   private List<Item> deliveredItems = new ArrayList<Item>();
   private GameObject player;
   private FishingRod rod;
   private DeltaTimer timer;

   private boolean gameOver = false;
   private DialogManager dialogManager;

   public IngameScreen(BrainGdxGame game) {
      super(game);
   }

   @Override
   public void dispose() {
      super.dispose();
      Controllers.clearListeners();
   }

   @Override
   protected void onCreate(GameContext context) {
      setBackgroundColor(BACKGROUND);
     // context.getAudioManager().playMusic(Assets.Musics.OVERWORLD);

      this.timer = new DeltaTimer();
      this.context = context;
      this.money = new Money();
      this.inventory = new Inventory(context.getEventManager());
      this.dialogManager = new DialogManager();

      setupWorld(context);
      setupInput(context);
      setupRenderer(context);
      setupUI(context);
      setupEvents(context);
      setupShaders(context);


      this.rod = new FishingRod(player, inventory, context);
   }

   @Override
   protected void onUpdate(float delta) {
      if (gameOver) {
         return;
      }
      rod.update(delta);
      timer.update(delta);
      if (timer.reached(Config.GAME_DURATION_IN_SECONDS)) {
         player.setActive(false);
         gameOver = true;
         context.getScreenTransitions().out(new GameOverScreen(getGame(), money, inventory, deliveredItems), 1f);
         Toast.getInstance().doToast(Bundle.get(Messages.TIME_EXPIRED));
         return;
      }
      if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
         rod.throwRod();
      }
      super.onUpdate(delta);
   }

   private void setupInput(GameContext context) {
      context.getInput().addProcessor(new IngameKeyboardInput());
      Controllers.addListener(new IngameControllerInput());
   }

   private void setupWorld(GameContext context) {
      TiledMap level = SharedAssetManager.getInstance().get(Assets.TiledMaps.LEVEL_1, TiledMap.class);
      context.getTiledMapManager().load(level, context.getGameCamera().getInternalCamera(), TiledMapType.ORTHOGONAL);
      context.getGameWorld().setBounds(new SimpleWorldBounds(
            context.getTiledMapManager().getAPI().getWorldWidth(),
            context.getTiledMapManager().getAPI().getWorldHeight())
      );
      for (GameObject o : context.getGameWorld()) {
         if ("PLAYER".equals(o.getType())) {
            configurePlayer(context, o);
         }
         if ("GIER".equals(o.getType())) {
            configureGier(context, o);
         }
         if ("SPAWN".equals(o.getType())) {
            int capacity = ((MapProperties)o.getAttribute(MapProperties.class)).get("capacity", 1, Integer.class);
            Spawner spawner = new Spawner(o.getLeft(), o.getTop(), o.getWidth(), o.getHeight(), capacity);
            spawners.add(spawner);
         }
      }
      for (Spawner spawner : spawners) {
         spawner.spawn(context, player);
      }
   }

   private void setupShaders(GameContext context) {
      // TODO
   }

   private void setupRenderer(GameContext context) {
      Animations.setupPlayerAnimations(context);
      Animations.setupFishAnimations(context);

      context.getRenderPipeline().putAfter(RenderPipeIds.LIGHTING, "rod", new RodRenderLayer(context.getEventManager(), player));
   }

   private void configurePlayer(GameContext context, GameObject player) {
      this.player = player;
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

      OrientationMovementController controller = new OrientationMovementController();
      final RasteredMovementBehavior behavior = new RasteredMovementBehavior(controller, context.getTiledMapManager().getAPI())
            .interval(0.15f)
            .rasterSize(context.getTiledMapManager().getAPI().getCellWidth(), context.getTiledMapManager().getAPI().getCellHeight());
      context.getBehaviorManager().apply(behavior, player);
      context.getBehaviorManager().apply(new SellToGierBehavior(inventory, deliveredItems, dialogManager), player);
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
      InventoryUI inventoryUI = new InventoryUI();
      context.getEventManager().register(inventoryUI.inventoryClearedEventListener, InventoryClearedEvent.class);
      context.getEventManager().register(inventoryUI.itemAddedToInventoryEventListener, ItemAddedToInventoryEvent.class);
      context.getStage().addActor(inventoryUI);

      MoneyUI cashUI = new MoneyUI(money);
      cashUI.setPosition(35f, Gdx.graphics.getHeight() - 100f);
      context.getStage().addActor(cashUI);

      Toast.getInstance().init(context.getStage());

      TimerUI timerUI = new TimerUI(timer);
      timerUI.setBounds(Gdx.graphics.getWidth() - 35f - 400f, Gdx.graphics.getHeight() - 100f, 400f, 60f);
      context.getStage().addActor(timerUI);

      DialogUI dialogUI = new DialogUI(dialogManager);
      float width = Gdx.graphics.getWidth() / 2f;
      dialogUI.setHeight(130f);
      dialogUI.setWidth(width);
      dialogUI.setX(Gdx.graphics.getWidth() / 2f - width / 2f);
      context.getStage().addActor(dialogUI);
   }

   private void setupEvents(GameContext context) {
      context.getEventManager().register(new InventoryClearedHandler(money), InventoryClearedEvent.class);
   }
}
