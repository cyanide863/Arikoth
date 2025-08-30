package arikoth.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class ArikothLiquids {
    public static Liquid
            // liquid
            icher, liquidQuickSand, mercury, moltenSalt, steam, methane;

    public static void load(){
        // liquid
        icher = new Liquid("icher"){{
            color = Color.valueOf("edc879");
            gasColor = Color.valueOf("edc879");
            coolant = false;
            boilPoint = 0.6f;
            viscosity = 0.3f;
            explosiveness = 0.2f;
            temperature = 0.4f;
        }};
        liquidQuickSand = new Liquid("liquid-quicksand"){{
            color = Color.valueOf("fadab2");
            gasColor = Color.valueOf("fadab2");
            coolant = false;
            viscosity = 0.8f;
            temperature = 0.4f;
        }};
        mercury = new Liquid("mercury"){{
            color = Color.valueOf("807e8c");
            gasColor = Color.valueOf("807e8c");
            coolant = false;
            viscosity = 0.8f;
            temperature = 0.7f;
        }};
        moltenSalt = new Liquid("molten-salt"){{
            color = Color.valueOf("ffab6b");
            gasColor = Color.valueOf("ffab6b");
            coolant = false;
            viscosity = 0.6f;
            temperature = 0.8f;
        }};

        // gas
        steam = new Liquid("steam"){{
            gas = true;
            color = Color.valueOf("9f9f9f");
            coolant = false;
            temperature = 0.8f;
        }};
        methane = new Liquid("methane"){{
            gas = true;
            color = Color.valueOf("ffbf4a");
            coolant = false;
            flammability = 0.25f;
            explosiveness = 0.1f;
        }};
    }
}