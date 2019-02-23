package de.bitbrain.fishmonger.i18n;

public enum Messages {

   FISH_PIRANHA_NAME("fish.piranha.name"),
   FISH_PIRANHA_DESCRIPTION("fish.piranha.description");

   private final String key;

   Messages(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }
}
