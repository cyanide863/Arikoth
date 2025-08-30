
package arikoth.content;

import arc.struct.Seq;
import mindustry.game.Objectives;

import static arikoth.content.ArikothLiquids.*;
import static mindustry.content.SectorPresets.facility32m;
import static mindustry.content.TechTree.*;
import static arikoth.content.ArikothBlocks.*;
import static arikoth.content.ArikothItems.*;
import static arikoth.content.ArikothPlanets.*;
import static arikoth.content.ArikothSectors.*;
import static mindustry.content.Items.*;

public class ArikothTechTree {
    public static void load(){
        arikoth.techTree = nodeRoot("arikoth", coreSerenity, false, () -> {
            node(rhenium, Seq.with(new Objectives.OnSector(awakening)), ()-> {
                node(nickel, Seq.with(new Objectives.Research(rhenium)),  ()-> {
                    node(thorium, ()-> {
                        node(tyrium, ()-> {
                        });
                        node(kaneturium, ()-> {
                        });
                    });
                    node(strontium, ()-> {
                    });
                });
                node(quartz, ()-> {
                    node(irium, ()-> {
                    });
                    node(silicon, ()-> {
                    });
                });
                node(liquidQuickSand, ()-> {
                    node(mercury, ()-> {
                        node(amalgam, ()-> {
                        });
                    });
                    node(moltenSalt, ()-> {
                    });
                });
            });

            node(vaccumShaft, ()-> {
                node(vaccumShaftRouter, ()-> {
                    node(vaccumShaftOverpass, ()-> {
                    });
                });
            });

            node(plasmaDriller, Seq.with(new Objectives.Research(rhenium)), ()-> {
            });

            node(puncture, ()-> {
                node(seeker, Seq.with(new Objectives.OnSector(sagar)), ()-> {
                });
            });

            node(nickelWall, ()-> {
                node(nickelWallLarge, ()-> {
                });
            });

            node(mercurySynthesizer, Seq.with(new Objectives.Produce(silicon)), ()-> {

                node(amalgamFoundry, Seq.with(
                new Objectives.Research(mercurySynthesizer),
                new Objectives.Produce(mercury),
                new Objectives.Produce(quartz)
                ), ()-> {
                });
                node(tyriumWeaver, Seq.with(
                new Objectives.Produce(amalgam),
                new Objectives.Research(thorium),
                new Objectives.Produce(irium)
                ), ()-> {
                });
            });
            node(awakening, ()-> {
                node(sagar, ()-> {
                });
            });
        });
    }
}