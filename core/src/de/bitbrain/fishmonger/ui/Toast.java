package de.bitbrain.fishmonger.ui;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;

/**
 * Produces onscreen toasts/dialogs
 */
public class Toast {

	private static final Toast INSTANCE = new Toast();
	
	private static final TweenManager tweenManager = SharedTweenManager.getInstance();
	
	private Label toast;
	
	private Stage stage;
	
	public static Toast getInstance() {
		return INSTANCE;
	}
	
	public void init(Stage stage) {
		this.stage = stage;
	}
	
	public void doToast(String text) {
		if (stage == null) {
			Gdx.app.log("WARN", "Unable to create toast! Stage not defined.");
			return;
		}
		if (this.toast != null) {
			tweenManager.killTarget(this.toast);
		}
		toast = new Label(text, Styles.LABEL_TOAST) {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				super.draw(batch, parentAlpha);
			}
		};
		toast.setAlignment(Align.center);
		toast.setColor(Color.WHITE.cpy());
		toast.setWidth(Gdx.graphics.getWidth());
		toast.setHeight(Gdx.graphics.getHeight());
		stage.addActor(toast);
		Tween.to(toast, ActorTween.ALPHA, 0.8f)
		     .delay(0.3f)
			 .target(0f)
		     .ease(TweenEquations.easeInQuad)
		     .setCallbackTriggers(TweenCallback.COMPLETE)
		     .setCallback(new TweenCallback() {
				@Override
				public void onEvent(int arg0, BaseTween<?> arg1) {
					stage.getActors().removeValue(toast, true);
				}
		     })
		     .start(tweenManager);
	}
}