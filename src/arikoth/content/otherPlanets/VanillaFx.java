package arikoth.content.otherPlanets;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.Mathf;
import arikoth.palettes.VanillaPal;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;

import static arc.graphics.g2d.Lines.lineAngle;
import static arc.math.Angles.randLenVectors;
import static arc.scene.actions.Actions.color;

public class VanillaFx {
    public static final Effect

    pionCharge = new Effect(38f, e -> {
        color(VanillaPal.phaseAmmoLight);

        randLenVectors(e.id, 14, 1f + 20f * e.fout(), e.rotation, 120f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 3f + 1f);
        });
    }),

    pionChargeStar = new Effect(38, e -> {
        Draw.color(VanillaPal.phaseAmmoLight);
        e.rotation = e.fin() * 200;
        for (int i = 0; i < 4; i++) {
            Drawf.tri(e.x, e.y, e.fout(Interp.circleIn) * 3, e.fout(Interp.circleIn) * 40, e.rotation + (90 * i));
        }
        Draw.color();
        for (int i = 0; i < 4; i++) {
            Drawf.tri(e.x, e.y, e.fout(Interp.circleIn) * 1.2f, e.fout(Interp.circleIn) * 30, e.rotation + (90 * i));
        }
    });

}
