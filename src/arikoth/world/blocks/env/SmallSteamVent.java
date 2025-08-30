package arikoth.world.blocks.env;

import arc.graphics.*;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.Team;
import mindustry.graphics.*;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

import static mindustry.Vars.*;

//ehe idk what im doing
public class SmallSteamVent extends Floor {

    public Effect effect = Fx.ventSteam;
    public Color effectColor = Pal.vent;
    public float effectSpacing = 15f;
    public float effectChance = 0.1f;

    @Override
    public boolean updateRender(Tile tile){
        return true;
    }

    public SmallSteamVent(String name){
        super(name);
        variants = 2;
    }

    @Override
    public void renderUpdate(UpdateRenderState state) {
        if (Mathf.chanceDelta(effectChance) && state.tile.block() == Blocks.air) {
            effect.at(state.tile.worldx(), state.tile.worldy(), effectColor);
        }
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return !wallOre || tile.block().solid;
    }

    @Override
    public void drawBase(Tile tile){
        Draw.rect(variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))], tile.worldx(), tile.worldy());
    }
}