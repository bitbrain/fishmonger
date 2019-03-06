package de.bitbrain.fishmonger.screens;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tmx.TiledMapType;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.SimpleWorldBounds;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.catching.HookType;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.model.spawn.Spawner;
import de.bitbrain.fishmonger.progress.PlayerProgress;
import de.bitbrain.fishmonger.shop.ShopItemFactory;
import de.bitbrain.fishmonger.shop.ShopItem;
import de.bitbrain.fishmonger.ui.DialogManager;
import de.bitbrain.fishmonger.ui.DialogUI;
import de.bitbrain.fishmonger.ui.Toast;
import de.bitbrain.fishmonger.ui.shopkeeper.ShopkeeperUI;

import java.util.ArrayList;
import java.util.List;

import static de.bitbrain.fishmonger.Colors.BACKGROUND;

public class ShopkeeperScreen extends AbstractScreen<BrainGdxGame> {

   private boolean exiting = false;
   private boolean fadedIn = false;
   private GameContext context;
   private DialogManager dialogManager;
   private Music music;
   private DialogUI dialogUI;
   private ShopItemFactory itemFactory;
   private ShopkeeperUI shopkeeperUI;

   public ShopkeeperScreen(BrainGdxGame game) {
      super(game);
   }

   @Override
   protected void onCreate(GameContext context) {
      SharedAssetManager.getInstance().get(Assets.Musics.MAIN_MENU, Music.class).stop();
      music = SharedAssetManager.getInstance().get(Assets.Musics.RICHARD_GIER, Music.class);
      music.setLooping(true);
      music.setVolume(0.4f);
      music.play();

      setBackgroundColor(BACKGROUND);
      context.getScreenTransitions().in(0.2f);

      this.context = context;
      this.dialogManager = new DialogManager();
      this.itemFactory = new ShopItemFactory();

      prepareDialog();

      setupWorld(context);
      setupRenderer(context);
      setupUI(context);

      dialogManager.nextDialog();
   }

   @Override
   protected void onUpdate(float delta) {
      if (exiting) {
         return;
      }
      if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
         PlayerProgress.addMoney(1000000);
         shopkeeperUI.refresh();
      }
      if (dialogUI.hasFinishedDialoging() && !fadedIn) {
         fadedIn = true;
         Tween.to(shopkeeperUI, ActorTween.ALPHA, 0.5f)
               .target(1f)
               .start(SharedTweenManager.getInstance());
      }
      if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
         context.getScreenTransitions().out(new LevelSelectionScreen(getGame()), 0.3f);
         exiting = true;
         this.music.stop();
         Music music = SharedAssetManager.getInstance().get(Assets.Musics.MAIN_MENU, Music.class);
         music.setLooping(true);
         music.setVolume(0.4f);
         music.play();
      }
   }



   private void setupUI(GameContext context) {
      dialogUI = new DialogUI(dialogManager);
      float width = Gdx.graphics.getWidth() / 2f;
      dialogUI.setHeight(130f);
      dialogUI.setWidth(width);
      dialogUI.setX(Gdx.graphics.getWidth() / 2f - width / 2f);
      context.getStage().addActor(dialogUI);
      Toast.getInstance().init(context.getStage());

      List<ShopItem> items = new ArrayList<ShopItem>();

      for (HookType hookType : HookType.values()) {
         if (hookType != HookType.DEFAULT) {
            items.add(itemFactory.createHookItem(hookType));
         }
      }

      items.add(itemFactory.createInventoryItem(4));
      items.add(itemFactory.createInventoryItem(6));
      items.add(itemFactory.createInventoryItem(8));

      shopkeeperUI = new ShopkeeperUI(items);
      shopkeeperUI.getColor().a = 0f;
      context.getStage().addActor(shopkeeperUI);
   }

   private void setupRenderer(GameContext context) {
      Animations.setupPlayerAnimations(context);
      Animations.setupFishAnimations(context);
   }

   private void setupWorld(GameContext context) {
      TiledMap level = SharedAssetManager.getInstance().get(Assets.TiledMaps.SHOP, TiledMap.class);
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
            Spawner spawner = new Spawner(o.getLeft(), o.getTop(), o.getWidth(), o.getHeight(), capacity);
            spawners.add(spawner);
         }
      }
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

   private void prepareDialog() {
      dialogManager.addDialog(Bundle.get(Messages.SHOPKEEPER_TITLE), Messages.SHOPKEEPER_GREETING, Animations.createGierAvatar(), true);
   }
}
