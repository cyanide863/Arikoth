
package arikoth.graphics;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.Rand;
import arc.util.Time;
import mindustry.graphics.Layer;
import mindustry.type.Weather;
import arikoth.world.meta.ArikothEnv;

import static mindustry.Vars.*;

public class EnvRenderer {

    public static void init() {
        Core.assets.load("sprites/distortAlpha.png", Texture.class);

        renderer.addEnvRenderer(ArikothEnv.desert, () -> {
            Texture tex = Core.assets.get("sprites/distortAlpha.png", Texture.class);
            if(tex.getMagFilter() != Texture.TextureFilter.linear){
                tex.setFilter(Texture.TextureFilter.linear);
                tex.setWrap(Texture.TextureWrap.repeat);
            }

            Draw.z(state.rules.fog ? Layer.legUnit + 10 : Layer.effect);
            Weather.drawNoiseLayers(tex, Color.valueOf("ffad4d"), 1000f, 0.1f, 1.2f, 1.5f, 1f, 0f, 4, -0.3f, 0.02f, 0.8f, 0.9f);
            Draw.reset();
        });
    }
}
