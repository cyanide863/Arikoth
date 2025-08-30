package arikoth.content;

import mindustry.world.meta.Attribute;

public class ArikothAttribute {
    public static Attribute
            slag;

    public static void load() {
        slag = Attribute.add("slag");
    }
}
