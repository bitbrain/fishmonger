package de.bitbrain.fishmonger.ui.dialog;

import com.badlogic.gdx.graphics.Color;
import de.bitbrain.braingdx.ui.AnimationDrawable;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;

public class Dialog {
	
	private final AnimationDrawable avatar;
	private final Messages message;
	private final String title;
	private final Color color;
	private final boolean autoClose;
	
	public Dialog(String title, Messages message, AnimationDrawable avatar, Color color, boolean autoClose) {
		this.message = message;
		this.title = title;
		this.avatar = avatar;
		this.color = color;
		this.autoClose = autoClose;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getTitle() {
		return title;
	}
	
	public AnimationDrawable getAvatar() {
		return avatar;
	}

	public String getText() {
		return Bundle.get(message);
	}

	public boolean isAutoClose() {
		return autoClose;
	}
}