package arikoth.world.drawers;

import arc.*;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.draw.DrawBlock;

public class DrawWeaveModif extends DrawBlock {
    public TextureRegion weaveAlt;
    public float rotateSpeed = 1f;
    public Color color;
    public float line1Speed = 2, line1AltSpeed = 2, line2Speed = 2, line2AltSpeed = 2;

    @Override
    public void draw(Building build){
        Draw.rect(weaveAlt, build.x, build.y, build.totalProgress() * rotateSpeed);

        Draw.color(color);
        Draw.alpha(build.warmup());

        Lines.lineAngleCenter(
                build.x + Mathf.sin(build.totalProgress() / line1Speed, 6f, Vars.tilesize / 3f * build.block.size),
                build.y,
                90,
                build.block.size * Vars.tilesize / 2f);

        Lines.lineAngleCenter(
                build.x + Mathf.sin(build.totalProgress() / line1AltSpeed, -6f, Vars.tilesize / 3f * build.block.size),
                build.y,
                90,
                build.block.size * Vars.tilesize / 2f);

        Lines.lineAngleCenter(
                build.x,
                build.y + Mathf.sin(build.totalProgress() / line2Speed , 6f, Vars.tilesize / 3f * build.block.size),
                0,
                build.block.size * Vars.tilesize / 2f);

        Lines.lineAngleCenter(
                build.x,
                build.y + Mathf.sin(build.totalProgress() / line2AltSpeed , -6f, Vars.tilesize / 3f * build.block.size),
                0,
                build.block.size * Vars.tilesize / 2f);


        Draw.reset();
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{weaveAlt};
    }

    @Override
    public void load(Block block){
        weaveAlt = Core.atlas.find(block.name + "-weaveAlt");
    }
}
