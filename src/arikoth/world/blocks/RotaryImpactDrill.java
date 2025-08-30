
package arikoth.world.blocks;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.world.blocks.production.BurstDrill;


public class RotaryImpactDrill extends BurstDrill {

    TextureRegion[] arrowRegions, arrowBlurRegions;


    public RotaryImpactDrill(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        arrowRegions = new TextureRegion[arrows];
        arrowBlurRegions = new TextureRegion[arrows];

        for(int i=0; i < arrows ;i++){
            arrowRegions[i] = Core.atlas.find(name + "-arrows-" + (arrows-1-i));
            arrowBlurRegions[i] = Core.atlas.find(name + "-arrow-glow-" + (arrows-1-i));
        }
    }

    public class RotatyImpactDrillBuild extends BurstDrillBuild {

        public void draw(){
            Draw.rect(region, x, y);
            drawDefaultCracks();

            Draw.rect(topRegion, x, y);
            if(invertTime > 0 && topInvertRegion.found()){
                Draw.alpha(Interp.pow3Out.apply(invertTime));
                Draw.rect(topInvertRegion, x, y);
                Draw.color();
            }

            if(dominantItem != null && drawMineItem){
                Draw.color(dominantItem.color);
                Draw.rect(itemRegion, x, y);
                Draw.color();
            }

            float fract = smoothProgress;

            for(int j = 0; j < arrows; j++){
                float arrowFract = (arrows - 1 - j);
                float a = Mathf.clamp(fract * arrows - arrowFract);

                //TODO maybe just use arrow alpha and draw gray on the base?
                Draw.z(Layer.blockAdditive);
                Draw.blend(Blending.additive);
                Draw.color(baseArrowColor, arrowColor, a);
                Draw.rect(arrowRegions[j], x, y);
                Draw.color(arrowColor);
                Draw.blend();

                if(arrowBlurRegion.found()){
                    Draw.z(Layer.blockAdditive);
                    Draw.blend(Blending.additive);
                    Draw.alpha(Mathf.pow(a, 10f));
                    Draw.rect(arrowBlurRegions[j], x, y);
                    Draw.blend();
                }}
            Draw.color();

            if(glowRegion.found()){
                Drawf.additive(glowRegion, Tmp.c2.set(glowColor).a(Mathf.pow(fract, 3f) * glowColor.a), x, y);
            }
        }
    }
}
