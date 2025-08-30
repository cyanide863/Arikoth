package arikoth.world.blocks.env;

import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.content.Blocks;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

import static mindustry.type.Category.effect;

//author @Andromeda
//Modifications @cyanide
public class AFloor extends Floor {
    public Color cliffLightColor;
    public Color cliffDarkColor;

    public AFloor(String name) {
        super(name);
    }

    public AFloor(String name, int variants) {
        super(name, variants);
    }
}
