package arikoth.world.drawers;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.Interp.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.world.draw.DrawBlock;

public class DrawPolyFlame extends DrawBlock {
    public Color flameColor = Color.valueOf("f58349"), midColor = Color.valueOf("f2d585");
    public float flameRad = 1f, circleSpace = 2f, flameRadiusScl = 10f, flameRadiusMag = 0.6f, circleStroke = 1.5f, x = 0, y = 0;
    public float alpha = 0.5f;
    public int poly = 4;
    public int rot = 45;
    public int particleRotation = 45;
    public boolean polygon = false;
    public int polygonSides = 360;
    public int particles = 30;
    public float particleLife = 70f, particleRad = 7f, particleSize = 3f, fadeMargin = 0.4f, rotateScl = 1.5f;
    public Interp particleInterp = new PowIn(1.5f);
    public Interp particleSizeInterp = new PowIn(1.5f);

    @Override
    public void draw(Building build){

        if(build.warmup() > 0f && flameColor.a > 0.001f){
            Lines.stroke(circleStroke * build.warmup());

            float si = Mathf.absin(flameRadiusScl, flameRadiusMag);
            float a = alpha * build.warmup();
            Draw.blend(Blending.additive);

            Draw.color(midColor, a);
            Fill.poly(build.x + x, build.y + y, poly, flameRad + si, rot);

            Draw.color(flameColor, a);
            Lines.poly(build.x + x, build.y + y, poly, (flameRad + circleSpace + si) * build.warmup(), rot);

            float base = (Time.time / particleLife);
            rand.setSeed(build.id);
            for(int i = 0; i < particles; i++){
                float fin = (rand.random(1f) + base) % 1f, fout = 1f - fin;
                float angle = rand.random(360f) + (Time.time / rotateScl) % 360f;
                float len = particleRad * particleInterp.apply(fout);
                Draw.alpha(a * (1f - Mathf.curve(fin, 1f - fadeMargin)));
                if(polygon){
                    Fill.poly(
                            build.x + x + Angles.trnsx(angle, len),
                            build.y + y + Angles.trnsy(angle, len),
                            polygonSides,
                            particleSize * particleSizeInterp.apply(fin) * build.warmup(),
                            particleRotation
                    );
                }else{
                    Fill.circle(
                            build.x + x + Angles.trnsx(angle, len),
                            build.y + y + Angles.trnsy(angle, len),
                            particleSize * particleSizeInterp.apply(fin) * build.warmup()
                    );
                }
            }

            Draw.blend();
            Draw.reset();
        }
    }

}
