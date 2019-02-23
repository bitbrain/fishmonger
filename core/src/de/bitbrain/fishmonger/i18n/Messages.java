package de.bitbrain.fishmonger.i18n;

public enum Messages {

   FISH_PIRANHA_NAME("fish.piranha.name"),
   FISH_PIRANHA_DESCRIPTION("fish.piranha.description"),
   FISH_MACKEREL_NAME("fish.mackerel.name"),
   FISH_MACKEREL_DESCRIPTION("fish.mackerel.description");

   private final String key;

   Messages(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }
}
