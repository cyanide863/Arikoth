
package arikoth.graphics;

import arc.files.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static arc.Core.*;
import static mindustry.Vars.*;
public class ArikothShaders{

    public static @Nullable SurfaceShader aernaQuicksand;
    public static CacheLayer.ShaderLayer aernaQuicksandLayer;
    public static @Nullable SurfaceShader moltenSalt;
    public static CacheLayer.ShaderLayer moltenSaltLayer;

    public static void load(){
        aernaQuicksand = new SurfaceShader("aernaQuicksand");
        aernaQuicksandLayer = new CacheLayer.ShaderLayer(aernaQuicksand);
        CacheLayer.add(aernaQuicksandLayer);
        moltenSalt = new SurfaceShader("moltenSalt");
        moltenSaltLayer = new CacheLayer.ShaderLayer(moltenSalt);
        CacheLayer.add(moltenSaltLayer);
    }


    public static void dispose(){
        if(!headless){
            aernaQuicksand.dispose();
            moltenSalt.dispose();
        }
    }

    /**
     * Resolves shader files from this mod via {@link Vars#tree}.
     * @param name The shader file name, e.g. {@code my-shader.frag}.
     * @return The shader file, located inside {@code shaders/confictura/}.
     */
    public static Fi file(String name){
        return tree.get("shaders/" + name);
    }

    public static class ArikothLoadShader extends Shader{

        public ArikothLoadShader(String fragment, String vertex){
            super(
                    file(vertex + ".vert"),
                    file(fragment + ".frag")
            );
        }

        public void set(){
            Draw.shader(this);
        }

        @Override
        public void apply(){
            super.apply();

            setUniformf("u_time_millis", System.currentTimeMillis() / 1000f * 60f);
        }
    }

    public static class SurfaceShader extends Shader{
        Texture noiseTex;

        public SurfaceShader(String frag){
            super(Shaders.getShaderFi("screenspace.vert"), tree.get("shaders/" + frag + ".frag"));
            loadNoise();
        }

        public String textureName(){
            return "noise";
        }

        public void loadNoise(){
            assets.load("sprites/" + textureName() + ".png", Texture.class).loaded = t -> {
                t.setFilter(Texture.TextureFilter.linear);
                t.setWrap(Texture.TextureWrap.repeat);
            };
        }

        @Override
        public void apply(){
            setUniformf("u_campos",
                    camera.position.x - camera.width / 2,
                    camera.position.y - camera.height / 2
            );
            setUniformf("u_ccampos", camera.position);
            setUniformf("u_resolution", camera.width, camera.height);
            setUniformf("u_rresolution", graphics.getWidth(), graphics.getHeight());
            setUniformf("u_time", Time.time);

            if(hasUniform("u_noise")){
                if(noiseTex == null){
                    noiseTex = assets.get("sprites/" + textureName() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }
}
