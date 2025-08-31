package arikoth.content.otherPlanets;

import arc.graphics.*;
import arc.struct.*;
import arikoth.palettes.VanillaPal;
import mindustry.type.*;

public class SerpuloItems {
    public static Item
            titaniumCarbide, exosteel;

    public static final Seq<Item> arikothItems = new Seq<>();

    public static void load() {
        titaniumCarbide = new Item("titanium-carbide", VanillaPal.tiCaAmmoLight) {{
            hardness = 1;
            cost = 2f;
        }};
        exosteel = new Item("exosteel", VanillaPal.exosteelAmmoLight) {{
            hardness = 1;
            cost = 2.5f;
            charge = 1.5f;
        }};

        arikothItems.addAll(
                titaniumCarbide, exosteel
        );
    }
}