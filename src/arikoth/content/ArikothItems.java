package arikoth.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class ArikothItems {
    public static Item
            rhenium, nickel, strontium, quartz, amalgam, irium, tyrium, kaneturium;

    public static final Seq<Item> arikothItems = new Seq<>();

    public static void load() {
        rhenium = new Item("rhenium", Color.valueOf("#f2dba7")) {{
            hardness = 1;
            cost = 1.5f;
        }};
        nickel = new Item("nickel", Color.valueOf("#b0a9a1")) {{
            hardness = 1;
            cost = 1f;
        }};
        strontium = new Item("strontium", Color.valueOf("#e3987b")) {{
            hardness = 2;
            cost = 2f;
            flammability = 0.25f;
            explosiveness = 0.12f;
        }};
        quartz = new Item("quartz", Color.valueOf("#c4bdb9")) {{
            hardness = 2;
            cost = 2f;
            charge = 0.5f;
        }};
        amalgam = new Item("amalgam", Color.valueOf("#cebee8")) {{
            hardness = 1;
            cost = 2.5f;
            charge = 0.1f;
        }};
        irium = new Item("irium", Color.valueOf("#bbb8db")) {{
            hardness = 3;
            cost = 3.5f;
            charge = 0.5f;
        }};
        tyrium = new Item("tyrium", Color.valueOf("#8bbeb0")) {{
            hardness = 1;
            cost = 3.8f;
            charge = 1.2f;
            radioactivity = 1;
        }};
        kaneturium = new Item("kaneturium", Color.valueOf("#adb8ba")) {{
            hardness = 1;
            cost = 3.8f;
            charge = 0.6f;
            radioactivity = 1.5f;
            explosiveness = 0.8f;
        }};
        arikothItems.addAll(
                rhenium, nickel, strontium, quartz, amalgam, irium, tyrium, kaneturium
        );
    }
}