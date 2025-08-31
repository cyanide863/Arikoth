package arikoth.content.otherPlanets;

import arc.struct.Seq;
import mindustry.game.Objectives;

import static arikoth.content.otherPlanets.SerpuloBlocks.*;
import static arikoth.content.otherPlanets.SerpuloItems.*;
import static mindustry.content.Items.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.node;
import static mindustry.content.TechTree.nodeRoot;

public class GhereonTech {
    public static void load(){
        nodeRoot(TechTreeContent.ghereonIcon.name, TechTreeContent.ghereonIcon, () -> {
            node(titaniumCarbide, Seq.with(
                    new Objectives.Research(tiCaKiln)
            ), ()-> {
            });
            node(tiCaKiln, Seq.with(
                    new Objectives.SectorComplete(stainedMountains),
                    new Objectives.Produce(graphite),
                    new Objectives.Produce(titanium)
            ), ()-> {
            });
            node(neuro, Seq.with(
                    new Objectives.SectorComplete(stainedMountains),
                    new Objectives.Research(titaniumCarbide)
            ), ()-> {
                node(ember, Seq.with(
                        new Objectives.Research(neuro)
                ), ()-> {
                });
            });
        });
    }
}