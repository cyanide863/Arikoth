package arikoth.content;

import arikoth.content.ArikothPlanets;
import mindustry.type.*;

public class ArikothSectors{
    public static SectorPreset
            awakening, sagar;

    public static void load(){
        //region arikoth

        awakening = new SectorPreset("awakening", ArikothPlanets.arikoth, 15){{
            alwaysUnlocked = true;
            addStartingItems = true;
            captureWave = 6;
            difficulty = 1;
        }};

        sagar = new SectorPreset("sagar", ArikothPlanets.arikoth, 3){{
            alwaysUnlocked = false;
            addStartingItems = true;
            captureWave = 20;
            difficulty = 2;
        }};
    }

    static void registerHiddenSectors(Planet planet, int... ids){
        for(int id : ids){
            new SectorPreset("sector-" + planet.name + "-" + id, "hidden/" + planet + "-" + id, planet, id){{
                requireUnlock = false;
            }};
            planet.sectors.get(id).generateEnemyBase = true;
        }
    }
}
