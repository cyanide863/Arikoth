package arikoth.world.blocks.env;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.Vars;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
//author @Andromeda
public class ACliff extends Prop {
    public TextureRegion[] lightRegions;
    public TextureRegion[] darkRegions;
    public TextureRegion capRegion;

    public ACliff(String name) {
        super(name);
        breakable = alwaysReplace = false;
        solid = true;
        fillsTile = false;
        hasShadow = false;
        variants = 0;
        saveData = true;
    }

    @Override
    public void load() {
        super.load();
        lightRegions = new TextureRegion[12];
        for(int i = 0; i < 12; i++){
            lightRegions[i] = Core.atlas.find(name+i+"-light", "arikoth-empty");
        }
        darkRegions = new TextureRegion[12];
        for(int i = 0; i < 12; i++){
            darkRegions[i] = Core.atlas.find(name+i+"-dark", "arikoth-empty");
        }

        capRegion = Core.atlas.find(name+"-cap", "arikoth-empty");
    }

    @Override
    public int minimapColor(Tile tile){
        return getColor(tile).rgba();
    }

    public Color getColor(Tile tile){
        if(tile.floor() instanceof AFloor sFloor && sFloor.cliffLightColor != null){
            return sFloor.cliffLightColor;
        }else{
            return Tmp.c1.set(tile.floor().mapColor).mul(1.6f);
        }
    }

    public Color getDarkColor(Tile tile){
        if(tile.floor() instanceof AFloor sFloor && sFloor.cliffDarkColor != null){
            return sFloor.cliffDarkColor;
        }else{
            return Tmp.c1.set(tile.floor().mapColor).mul(0.7f);
        }
    }

    @Override
    public void drawBase(Tile tile) {
        Draw.z(Layer.floor + 1f);
        if(tile.data == 0){
            Draw.color(getDarkColor(tile));
            Draw.rect(region, tile.worldx(), tile.worldy());
        }else{
            Draw.color(getDarkColor(tile));
            Draw.rect(darkRegions[tile.data - 1], tile.worldx(), tile.worldy());
            Draw.color(getColor(tile));
            Draw.rect(lightRegions[tile.data - 1], tile.worldx(), tile.worldy());
        }
        drawCaps(tile);
        Draw.reset();
    }

    public void drawCaps(Tile tile) {
        if (tile.data <= 4){ // Straight segments
            boolean light = Geometry.d4x(tile.data-1) + Geometry.d4y(tile.data-1) > 0; //Checks if cliff is facing right or up
            drawCap(tile, tile.data-1 - 1, light);
            drawCap(tile, tile.data-1 + 1, light);
        }else if (tile.data <= 12) { // Corners
            boolean light1 = Geometry.d4x(tile.data - 1) + Geometry.d4y(tile.data - 1) > 0;
            boolean light2 = Geometry.d4x(tile.data) + Geometry.d4y(tile.data) > 0;
            if (tile.data > 8) { // Flips colors for inner corners
                light1 = !light1;
                light2 = !light2;
            }
            drawCap(tile, tile.data - 1 - 1, light1);
            drawCap(tile, tile.data - 1 + 2, light2);
        }
    }

    public void drawCap(Tile tile, int rotation, boolean light){
        if (shouldDrawCapAt(tile, rotation)){
            if (light) {
                Draw.color(getColor(tile));
            }else{
                Draw.color(getDarkColor(tile));
            }
            Draw.rect(capRegion, tile.worldx() + Geometry.d4x(rotation) * Vars.tilesize, tile.worldy() + Geometry.d4y(rotation) * Vars.tilesize, rotation * 90);
        }
    }

    public boolean shouldDrawCapAt(Tile tile, int rotation){
        rotation = Mathf.mod(rotation, 4);
        if(tile.nearby(rotation) == null){
            return false;
        }
        return !(tile.nearby(rotation).block() instanceof ACliff) || tile.floor() != tile.nearby(rotation).floor();
    }

    public void process(Tile tile) {
        //Inner corners
        for(int i = 0; i < 4; i++) {
            if (helperAt(tile, Geometry.d4(i)) && helperAt(tile, Geometry.d4(i + 1))) {
                tile.data = (byte) (Mathf.mod(i, 4) + 9);
                return;
            }
        }
        //Straight segments
        for (int i = 0; i < 4; i++){
            if (helperAt(tile, Geometry.d4(i))){
                tile.data = (byte) (Mathf.mod(i + 2, 4) + 1);
                return;
            }
        }
        //Outer corners
        for(int i = 0; i < 4; i++){
            if (helperAt(tile, Geometry.d8edge(i))){
                tile.data = (byte) (Mathf.mod(i + 2, 4) + 5);
                return;
            }
        }
    }

    public boolean helperAt(Tile tile, Point2 point){
        if(tile.nearby(point) == null){
            return false;
        }
        return tile.nearby(point).block() instanceof ACliffHelper;
    }
}
