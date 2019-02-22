package de.bitbrain.fishmonger.i18n;

public enum Messages {

   NONE("");

   private final String key;

   Messages(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }
}
