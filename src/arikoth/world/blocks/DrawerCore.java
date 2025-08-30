package arikoth.world.blocks;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.util.Eachable;
import mindustry.entities.units.BuildPlan;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.DrawBlock;

public class DrawerCore extends CoreBlock {
    public DrawerCore(String name) {
        super(name);
    }
    public DrawBlock drawer;

    @Override
    public TextureRegion[] icons() {
        return drawer.finalIcons(this);
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        drawer.drawPlan(this, plan, list);
    }

}
