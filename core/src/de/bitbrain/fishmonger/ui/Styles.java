package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.assets.Assets;

public class Styles {

   public static final ImageButton.ImageButtonStyle INVENTORY_ICON = new ImageButton.ImageButtonStyle();
   public static final ImageButton.ImageButtonStyle INVENTORY_ICON_OCCUPIED = new ImageButton.ImageButtonStyle();
   public static final Label.LabelStyle INGAME_CASH = new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_TOAST = new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_CREDITS =  new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_ROD =  new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_LOGO =  new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_DIALOG =  new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_DIALOG_TITLE = new Label.LabelStyle();
   public static final TextButton.TextButtonStyle BUTTON_MENU = new TextButton.TextButtonStyle();
   public static final Label.LabelStyle LABEL_EARNINGS = new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_TIME = new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_SHOPPINGLIST = new Label.LabelStyle();
   public static ImageButton.ImageButtonStyle BUTTON_HELP = new ImageButton.ImageButtonStyle();
   public static ImageButton.ImageButtonStyle BUTTON_SHOP = new ImageButton.ImageButtonStyle();

   public static void load() {
      INVENTORY_ICON.up = new NinePatchDrawable(
            GraphicsFactory.createNinePatch(SharedAssetManager.getInstance().get(Assets.Textures.PANEL, Texture.class), 8)
      );
      INVENTORY_ICON_OCCUPIED.up = new NinePatchDrawable(
            GraphicsFactory.createNinePatch(SharedAssetManager.getInstance().get(Assets.Textures.PANEL_MEDIUM, Texture.class), 8)
      );
      INGAME_CASH.font = bake(Assets.Fonts.PIXELMIX, 60, true);
      INGAME_CASH.fontColor = Colors.FOREGROUND;

      LABEL_TOAST.font = bake(Assets.Fonts.PIXELMIX, 40, true);
      LABEL_TOAST.fontColor = Colors.FOREGROUND;

      LABEL_CREDITS.font = bake(Assets.Fonts.PIXELMIX, 14, true, 4f);
      LABEL_CREDITS.fontColor = Colors.FOREGROUND;

      LABEL_EARNINGS.font = bake(Assets.Fonts.PIXELMIX, 20, false);
      LABEL_EARNINGS.fontColor = Colors.FOREGROUND;

      LABEL_SHOPPINGLIST.font = bake(Assets.Fonts.PIXELMIX, 30, true, 6);
      LABEL_SHOPPINGLIST.fontColor = Colors.FOREGROUND;

      LABEL_LOGO.font = bake(Assets.Fonts.PIXELMIX, 50, true);
      LABEL_LOGO.fontColor = Colors.FOREGROUND;

      LABEL_DIALOG.font = bake(Assets.Fonts.PIXELMIX, 26);
      LABEL_DIALOG.fontColor = Colors.BORDER;

      LABEL_DIALOG_TITLE.font = bake(Assets.Fonts.PIXELMIX, 30);
      LABEL_DIALOG_TITLE.fontColor = Colors.FOREGROUND;

      LABEL_ROD.font = bake(Assets.Fonts.PIXELMIX, 20, true, 4f);
      LABEL_ROD.fontColor = Colors.FOREGROUND;

      LABEL_TIME.font = bake(Assets.Fonts.PIXELMIX, 28, false);
      LABEL_TIME.fontColor = Colors.BORDER;

      BUTTON_MENU.font = bake(Assets.Fonts.PIXELMIX, 30);
      BUTTON_MENU.fontColor = Colors.BORDER;
      BUTTON_MENU.overFontColor = Colors.FOREGROUND;
      BUTTON_MENU.checkedFontColor = Colors.FOREGROUND;
      Texture ninePatchTexture = SharedAssetManager.getInstance().get(Assets.Textures.PANEL_MEDIUM, Texture.class);
      BUTTON_MENU.over = new NinePatchDrawable(GraphicsFactory.createNinePatch(ninePatchTexture, 22));
      Texture defaultPatchTexture = SharedAssetManager.getInstance().get(Assets.Textures.PANEL, Texture.class);
      BUTTON_MENU.checked = BUTTON_MENU.over;
      BUTTON_MENU.up = new NinePatchDrawable(GraphicsFactory.createNinePatch(defaultPatchTexture, 22));

      Sprite sprite = new Sprite(SharedAssetManager.getInstance().get(Assets.Textures.QUESTION, Texture.class));
      BUTTON_HELP.imageUp = new SpriteDrawable(sprite);
      BUTTON_HELP.over = new NinePatchDrawable(GraphicsFactory.createNinePatch(ninePatchTexture, 22));
      BUTTON_HELP.checked = BUTTON_HELP.over;
      BUTTON_HELP.up = new NinePatchDrawable(GraphicsFactory.createNinePatch(defaultPatchTexture, 22));

      sprite = new Sprite(SharedAssetManager.getInstance().get(Assets.Textures.MONEY, Texture.class));
      BUTTON_SHOP.imageUp = new SpriteDrawable(sprite);
      BUTTON_SHOP.over = new NinePatchDrawable(GraphicsFactory.createNinePatch(ninePatchTexture, 22));
      BUTTON_SHOP.checked = BUTTON_SHOP.over;
      BUTTON_SHOP.up = new NinePatchDrawable(GraphicsFactory.createNinePatch(defaultPatchTexture, 22));
   }

   private static BitmapFont bake(String fontPath, int size, boolean border) {
      return bake(fontPath, size, border, 10f);
   }

   private static BitmapFont bake(String fontPath, int size, boolean border, float borderWidth) {
      FreeTypeFontGenerator generator = SharedAssetManager.getInstance().get(fontPath, FreeTypeFontGenerator.class);
      FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
      param.color = Color.WHITE;
      param.size = size;
      param.mono = true;
      if (border) {
         param.borderStraight = true;
         param.borderWidth = borderWidth;
         param.borderColor = Colors.BORDER;
      }
      return generator.generateFont(param);
   }

   private static BitmapFont bake(String fontPath, int size) {
      return bake(fontPath, size, false);
   }
}
