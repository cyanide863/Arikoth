package arikoth.world.blocks.env;

import mindustry.world.blocks.environment.Prop;
//author @Andromeda
/** Does nothing on its own. Used to place cliffs */
public class ACliffHelper extends Prop {

    public ACliffHelper(String name) {
        super(name);
        breakable = alwaysReplace = false;
        solid = true;
        fillsTile = false;
        hasShadow = false;
        variants = 0;
    }
}
