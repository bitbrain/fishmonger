package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.graphics.Color;
import de.bitbrain.braingdx.ui.AnimationDrawable;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.ui.dialog.Dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DialogManager {

   public interface DialogManagerListener {
      void afterLastDialog();
      void onDialog(Dialog dialog);
   }

   private List<Dialog> dialogs = new ArrayList<Dialog>();
   private List<DialogManagerListener> dialogManagerListeners = new CopyOnWriteArrayList<DialogManagerListener>();

   private Dialog currentDialog;

   private int lastSize;

   public void addDialog(String title, Messages message, AnimationDrawable avatar) {
      addDialog(title, message, avatar, Color.WHITE, false);
   }

   public void addDialog(String title, Messages message, AnimationDrawable avatar, Object args) {
      addDialog(title, message, avatar, Color.WHITE, false, args);
   }

   public void addDialog(String title, Messages message, AnimationDrawable avatar, Color color) {
      dialogs.add(new Dialog(title, message, avatar, color, false));
   }

   public void addDialog(String title, Messages message, AnimationDrawable avatar, boolean autoClose) {
      addDialog(title, message, avatar, Color.WHITE, autoClose);
   }

   public void addDialog(String title, Messages message, AnimationDrawable avatar, boolean autoClose, Object ... args) {
      addDialog(title, message, avatar, Color.WHITE, autoClose, args);
   }

   public void addDialog(String title, Messages message, AnimationDrawable avatar, Color color, boolean autoClose) {
      dialogs.add(new Dialog(title, message, avatar, color, autoClose));
   }

   public void addDialog(String title, Messages message, AnimationDrawable avatar, Color color, boolean autoClose, Object ... args) {
      dialogs.add(new Dialog(title, message, avatar, color, autoClose, args));
   }

   public void addListener(DialogManagerListener listener) {
      this.dialogManagerListeners.add(listener);
   }

   public void removeListener(DialogManagerListener listener) {
      this.dialogManagerListeners.remove(listener);
   }

   public boolean hasDialogs() {
      return !dialogs.isEmpty();
   }

   public boolean nextDialog() {
      if (dialogs.size() > 0) {
         lastSize = dialogs.size();
         currentDialog = dialogs.remove(0);
         for (DialogManagerListener listener : dialogManagerListeners) {
            listener.onDialog(currentDialog);
         }
         return true;
      } else if (lastSize > 0) {
         lastSize = 0;
         for (DialogManagerListener listener : dialogManagerListeners) {
            listener.afterLastDialog();
         }
      }
      currentDialog = null;
      return false;
   }

   public Dialog getCurrentDialog() {
      return currentDialog;
   }
}