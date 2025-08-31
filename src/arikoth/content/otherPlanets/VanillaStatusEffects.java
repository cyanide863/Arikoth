package arikoth.content.otherPlanets;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import arikoth.palettes.VanillaPal;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.effect.ParticleEffect;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static mindustry.Vars.*;
import mindustry.content.StatusEffects.*;

public class VanillaStatusEffects{
    public static StatusEffect fluxed;

    public static void load(){

        fluxed = new StatusEffect("arikoth-fluxed"){{
            color = Color.valueOf("ffc455");
            damage = 0.2f;
            speedMultiplier = 0.8f;
            reloadMultiplier = 0.9f;
            effect = new ParticleEffect(){{
                lenFrom = 7;
                lenTo = 0;
                strokeFrom = 4;
                strokeTo = 0;
                length = 30;
                colorFrom = VanillaPal.phaseAmmoLight;
                colorTo = VanillaPal.phaseAmmoDark;
                lifetime = 15;
                line = true;
                interp = Interp.circleOut;
                particles = 5;
            }};
            transitionDamage = 2f;

            init(() -> {
                affinity(StatusEffects.shocked, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                    Fx.circleColorSpark.at(unit.x + Mathf.range(unit.bounds() / 2f), unit.y + Mathf.range(unit.bounds() / 2f));
                    result.set(fluxed, Math.min(time + result.time, 300f));
                });
            });
        }};
}}
