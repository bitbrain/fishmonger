package de.bitbrain.fishmonger.ui.dialog;

import de.bitbrain.fishmonger.i18n.Messages;

import java.util.ArrayList;
import java.util.List;

public final class DialogPool {

   private static final List<Messages> DELIVERY_DIALOGS = new ArrayList<Messages>() {{
      add(Messages.FISH_DELIVERY_01);
      add(Messages.FISH_DELIVERY_02);
      add(Messages.FISH_DELIVERY_03);
      add(Messages.FISH_DELIVERY_04);
      add(Messages.FISH_DELIVERY_05);
      add(Messages.FISH_DELIVERY_06);
      add(Messages.FISH_DELIVERY_07);
      add(Messages.FISH_DELIVERY_08);
      add(Messages.FISH_DELIVERY_09);
      add(Messages.FISH_DELIVERY_10);
      add(Messages.FISH_DELIVERY_11);
      add(Messages.FISH_DELIVERY_12);
      add(Messages.FISH_DELIVERY_13);
      add(Messages.FISH_DELIVERY_14);
      add(Messages.FISH_DELIVERY_15);
      add(Messages.FISH_DELIVERY_16);
      add(Messages.FISH_DELIVERY_17);
      add(Messages.FISH_DELIVERY_18);
      add(Messages.FISH_DELIVERY_19);

   }};

   public static Messages getRandomDeliveryMessage() {
      int randomIndex = (int)(DELIVERY_DIALOGS.size() * Math.random());
      return DELIVERY_DIALOGS.get(randomIndex);
   }
}
