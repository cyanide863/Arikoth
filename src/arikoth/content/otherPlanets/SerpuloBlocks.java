package arikoth.content.otherPlanets;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.Mathf;
import arikoth.content.ArikothFx;
import arikoth.content.ArikothItems;
import arikoth.palettes.VanillaPal;
import arikoth.world.entities.bullets.PosLightningType;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.EffectSpawnerPart;
import mindustry.entities.part.FlarePart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;

import static arc.graphics.g2d.Draw.color;
import static mindustry.type.ItemStack.with;

public class SerpuloBlocks {
    public static Block
    //turrets
    ember, prion, neuro, axon, dentrite, fulminate, castigate, pion,
    // factories
    tiCaKiln
    ;
    public static void load(){

        ember = new ItemTurret("ember"){{
            requirements(Category.turret, with(Items.silicon, 28, Items.graphite, 32, Items.titanium, 16, SerpuloItems.titaniumCarbide, 30));
            researchCost = with(Items.silicon, 280, Items.graphite, 320, Items.titanium, 160, SerpuloItems.titaniumCarbide, 300);
            size = 2;
            health = 1200;
            squareSprite = false;
            shootSound = Sounds.shootBig;
            minWarmup = 0.95f;
            shootWarmupSpeed = 0.1f;
            consumeAmmoOnce = false;
            targetAir = true;
            targetGround = true;
            range = 180;
            reload = 40;
            shootY = 4;
            recoil = 1.5f;
            shake = 4;
            velocityRnd = 0.1f;
            inaccuracy = 1.5f;
            ammoUseEffect = Fx.none;
            shoot.firstShotDelay = 38;
            shoot.shots = 4;
            shoot.shotDelay = 4;
            ammo(
                    Items.graphite, new BasicBulletType(6f, 25, "shell"){{
                        width = 8f;
                        height = 18f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 1.5f;
                        lifetime = 30;
                        despawnSound = Sounds.explosion;
                        hitShake = 2;
                        hitSound = Sounds.none;
                        trailWidth = 2f;
                        trailLength = 2;

                        hitColor = backColor = trailColor = Pal.graphiteAmmoBack;
                        frontColor = Pal.graphiteAmmoFront;
                        trailEffect = Fx.artilleryTrail;
                        trailInterval = 4;
                        hitEffect = despawnEffect;
                        despawnEffect = new ExplosionEffect(){{
                            sparks = 12;
                            sparkRad = 18;
                            sparkLen = 7;
                            sparkStroke = 2;
                            sparkColor = frontColor;
                            smokes = 6;
                            smokeSize = 12;
                            smokeRad = 12;
                            smokeColor = Color.darkGray;
                            waveRad = 12;
                            waveColor = sparkColor;
                        }};
                    }},
                    Items.titanium, new BasicBulletType(6f, 30, "shell"){{
                            width = 8f;
                            height = 18f;
                            shrinkY = 0.2f;
                            ammoMultiplier = 2f;
                            lifetime = 30;
                            despawnSound = Sounds.explosion;
                            hitShake = 2;
                            hitSound = Sounds.none;
                            trailWidth = 2f;
                            trailLength = 2;

                            hitColor = backColor = trailColor = Color.valueOf("919fe7");
                            frontColor = Color.valueOf("a4b8fa");
                            trailEffect = Fx.artilleryTrail;
                            trailInterval = 4;
                            hitEffect = despawnEffect;
                            despawnEffect = new ExplosionEffect(){{
                                sparks = 12;
                                sparkRad = 18;
                                sparkLen = 7;
                                sparkStroke = 2;
                                sparkColor = frontColor;
                                smokes = 6;
                                smokeSize = 12;
                                smokeRad = 12;
                                smokeColor = Color.darkGray;
                                waveRad = 12;
                                waveColor = sparkColor;
                          }};
                    }},
                    SerpuloItems.titaniumCarbide, new BasicBulletType(6f, 45, "shell"){{
                        width = 8f;
                        height = 18f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 3f;
                        hitEffect = Fx.disperseTrail;
                        despawnEffect = new ExplosionEffect(){{
                            sparks = 12;
                            sparkRad = 18;
                            sparkLen = 7;
                            sparkStroke = 2;
                            sparkColor = VanillaPal.tiCaAmmoLight;
                            smokes = 6;
                            smokeSize = 12;
                            smokeRad = 12;
                            smokeColor = Color.darkGray;
                            waveRad = 12;
                            waveColor = sparkColor;
                        }};;
                        lifetime = 40;
                        rangeChange = 60;
                        pierceCap = 3;
                        despawnSound = Sounds.explosion;
                        hitShake = 2;
                        hitSound = Sounds.none;
                        trailWidth = 2f;
                        trailLength = 2;

                        hitColor = backColor = trailColor = VanillaPal.tiCaAmmoDark;
                        frontColor = VanillaPal.tiCaAmmoLight;
                        trailEffect = Fx.artilleryTrail;
                        reloadMultiplier = 0.9f;
                        trailInterval = 4;
                    }}
            );
            drawer = new DrawTurret("ghereon-"){{
                parts.addAll(
                        new RegionPart("-front"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = 1.2f;
                            under = true;
                            heatColor = Pal.turretHeat;
                            moves.add(new PartMove(){{
                                y = -1.8f;
                                progress = PartProgress.recoil;
                            }});
                        }}
                );
            }};
        }};

        prion = new ItemTurret("prion"){{
            requirements(Category.turret, with(Items.silicon, 50, Items.graphite, 28, Items.plastanium, 30, SerpuloItems.titaniumCarbide, 68));
            size = 3;
            health = 1200;
            squareSprite = false;
            shootSound = Sounds.bang;
            minWarmup = 0.95f;
            shootWarmupSpeed = 0.1f;
            consumeAmmoOnce = false;
            targetAir = true;
            targetGround = true;
            range = 240;
            reload = 100;
            shootY = 4;
            recoil = 1.5f;
            shake = 4;
            velocityRnd = 0.1f;
            inaccuracy = 1.5f;
            ammoUseEffect = Fx.none;
            shoot = new ShootMulti(
            new ShootPattern(){{
                shots = 2;
                shotDelay = 30;
            }},
            new ShootPattern(){{
                shots = 4;
                shotDelay = 4;
            }}
            );
            ammo(
                    Items.silicon, new BasicBulletType(8f, 30, "shell"){{
                        width = 12f;
                        height = 18f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 2f;
                        lifetime = 30;
                        despawnSound = Sounds.explosion;
                        hitShake = 4;
                        homingPower = 0.02f;
                        homingRange = 90;
                        hitSound = Sounds.dullExplosion;
                        trailWidth = 2.5f;
                        trailLength = 10;

                        hitColor = backColor = trailColor = Pal.siliconAmmoBack;
                        frontColor = Pal.siliconAmmoFront;
                        trailEffect = Fx.artilleryTrail;
                        trailInterval = 4;
                        hitEffect = despawnEffect;
                        despawnEffect = new ExplosionEffect(){{
                            sparks = 18;
                            sparkRad = 40;
                            sparkLen = 7;
                            sparkStroke = 2;
                            sparkColor = frontColor;
                            smokes = 6;
                            smokeSize = 16;
                            smokeRad = 24;
                            smokeColor = Color.lightGray;
                            waveRad = 12;
                            waveColor = sparkColor;
                        }};
                    }},
                    SerpuloItems.titaniumCarbide, new BasicBulletType(8f, 45, "shell"){{
                        width = 8f;
                        height = 18f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 3f;
                        hitEffect = Fx.disperseTrail;
                        despawnEffect = new ExplosionEffect(){{
                            sparks = 12;
                            sparkRad = 18;
                            sparkLen = 7;
                            sparkStroke = 2;
                            sparkColor = VanillaPal.tiCaAmmoLight;
                            smokes = 6;
                            smokeSize = 12;
                            smokeRad = 12;
                            smokeColor = Color.darkGray;
                            waveRad = 12;
                            waveColor = sparkColor;
                        }};;
                        lifetime = 40;
                        rangeChange = 80;
                        pierceCap = 3;
                        despawnSound = Sounds.explosion;
                        hitShake = 2;
                        hitSound = Sounds.dullExplosion;
                        trailWidth = 2f;
                        trailLength = 2;

                        hitColor = backColor = trailColor = VanillaPal.tiCaAmmoDark;
                        frontColor = VanillaPal.tiCaAmmoLight;
                        trailEffect = Fx.artilleryTrail;
                        reloadMultiplier = 0.9f;
                        trailInterval = 4;
                        fragBullets = 3;
                        fragBullet = new BasicBulletType(2, 30){{
                            frontColor = VanillaPal.tiCaAmmoLight;
                            trailInterval = 4;
                            trailEffect = Fx.artilleryTrail;
                            lifetime = 20;
                            backColor = trailColor = hitColor = VanillaPal.tiCaAmmoDark;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }},
                    SerpuloItems.exosteel, new BasicBulletType(8f, 70, "shell"){{
                        width = 8f;
                        height = 18f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 3f;
                        hitEffect = Fx.disperseTrail;
                        despawnEffect = ArikothFx.spikyBoom;
                        lifetime = 40;
                        rangeChange = 80;
                        pierceCap = 6;
                        despawnSound = Sounds.explosion;
                        hitShake = 4;
                        fragBullets = 1;
                        fragBullet = new PosLightningType(30){{
                            rangeOverride = maxRange = 30;
                            lightningColor = VanillaPal.exosteelAmmoLight;
                            boltNum = 1;
                        }};
                        hitSound = Sounds.dullExplosion;
                        trailWidth = 2f;
                        trailLength = 2;

                        hitColor = backColor = trailColor = VanillaPal.exosteelAmmoDark;
                        frontColor = VanillaPal.exosteelAmmoLight;
                        trailEffect = Fx.artilleryTrail;
                        reloadMultiplier = 0.9f;
                        trailInterval = 4;
                    }}
            );
            drawer = new DrawTurret("ghereon-"){{
                parts.addAll(
                        new RegionPart("-front"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = -0.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                            moves.add(new PartMove(){{
                                y = -1.8f;
                                progress = PartProgress.recoil;
                            }});
                        }},
                        new RegionPart("-wing"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            moveRot = -45;
                            x = 30 / 4f;
                            y = -36 / 4f;
                            moveX = 0;
                            moveY = 1.2f;
                            under = true;
                            heatColor = Pal.turretHeat;
                            moves.add(new PartMove(){{
                                rot = -2;
                                progress = PartProgress.recoil;
                            }});
                        }}
                );
            }};
        }};

        neuro = new ItemTurret("neuro"){{
            requirements(Category.turret, with(SerpuloItems.titaniumCarbide, 30, Items.silicon, 26, Items.graphite, 30));
            researchCost = with(SerpuloItems.titaniumCarbide, 90, Items.silicon, 260, Items.graphite, 300);
            size = 2;
            health = 1200;
            squareSprite = false;
            shootSound = Sounds.spark;
            outlineColor = Pal.darkOutline;
            consumePower(360 / 60);
            consumeAmmoOnce = false;
            targetAir = true;
            targetGround = true;
            range = 90;
            reload = 80;
            shootY = 4;
            recoil = 1.5f;
            shake = 2;
            velocityRnd = 0;
            inaccuracy = 0;
            ammoUseEffect = Fx.none;
            shoot = new ShootSpread(9, 10);
            ammo(
                    Items.silicon, new BasicBulletType(6, 20, "circle-bullet"){{
                        width = 24f;
                        height = 8f;
                        shrinkY = 0.2f;
                        pierceCap = 4;
                        ammoMultiplier = 5f;
                        hitEffect = Fx.shootSmokeSquareSparse;
                        despawnEffect = Fx.shootSmokeSquareSparse;
                        shootEffect = Fx.colorSpark;
                        lifetime = 15;
                        despawnHit = true;
                        despawnSound = Sounds.spark;
                        hitSound = Sounds.spark;

                        status = StatusEffects.shocked;

                        hitColor = backColor = trailColor = frontColor = VanillaPal.exosteelAmmoLight;
                        trailEffect = Fx.disperseTrail;
                        trailRotation = true;
                        reloadMultiplier = 0.8f;
                        trailInterval = 4;
                    }}
            );
            drawer = new DrawTurret("ghereon-"){{
                parts.addAll(
                        new RegionPart("-sinks"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            moveRot = 0;
                            x = 0;
                            y = 0;
                            moveX = 1.5f;
                            moveY = -1.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }}
                );
            }};
        }};

        axon = new ItemTurret("axon"){{
            requirements(Category.turret, with(Items.silicon, 310, Items.graphite, 220, SerpuloItems.titaniumCarbide, 190, SerpuloItems.exosteel, 90));
            size = 4;
            rotateSpeed = 0.55f;
            health = 3200;
            armor = 18;
            squareSprite = false;
            shootSound = Sounds.mediumCannon;
            minWarmup = 0.95f;
            shootWarmupSpeed = 0.1f;
            ammoPerShot = 5;
            itemCapacity = 200;
            maxAmmo = 15;
            predictTarget = false;
            unitSort = UnitSorts.strongest;
            consumeAmmoOnce = false;
            targetAir = false;
            targetGround = true;
            range = 600;
            reload = 300;
            shootY = 12;
            recoil = 2f;
            shake = 12;
            velocityRnd = -0.2f;
            inaccuracy = 2f;
            ammoUseEffect = Fx.casing4;
            shoot.firstShotDelay = 90;
            shoot.shots = 5;
            shoot.shotDelay = 10;
            ammo(
                    Items.phaseFabric, new ArtilleryBulletType(2f, 10, "shell"){{
                        width = 18f;
                        height = 24f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 2f;
                        lifetime = 300;
                        parts.add(new FlarePart(){{
                            stroke = 5f;
                            radius = 0;
                            radiusTo = 28;
                            color1 = VanillaPal.phaseAmmoLight;
                            y = -4;
                            rotMove = 360;
                            progress = PartProgress.life.slope().curve(Interp.pow2Out);
                        }});
                        despawnSound = Sounds.explosionbig;
                        hitShake = 8;
                        hitSound = Sounds.none;
                        trailWidth = 3f;
                        splashDamage = 100;
                        splashDamageRadius = 64;
                        trailLength = 28;
                        lifeScaleRandMin = 0.9f;

                        hitColor = backColor = trailColor = VanillaPal.phaseAmmoLight;
                        frontColor = Color.white;
                        trailEffect = Fx.disperseTrail;
                        trailRotation = true;
                        trailInterval = 4;
                        despawnHit = true;
                        hitEffect = despawnEffect;
                        despawnEffect = new MultiEffect(
                                ArikothFx.spikyBoomMedium,
                                ArikothFx.tinyStarFour,
                                new Effect(90f, 160f, e -> {
                                    float circleRad = 6f + e.finpow() * 20f;

                                    color(VanillaPal.phaseAmmoLight, e.foutpow());
                                    Fill.circle(e.x, e.y, circleRad);
                                }).layer(Layer.bullet + 2f)
                        );
                        fragBullets = 16;
                        fragVelocityMax = 1.8f;
                        fragVelocityMin = 0.2f;
                        fragLifeMax = 1.5f;
                        fragLifeMin = 0.1f;
                        fragBullet = new ArtilleryBulletType(){{
                            hitColor = backColor = trailColor = VanillaPal.phaseAmmoLight;
                            frontColor = VanillaPal.phaseAmmoLight;
                            width = height = 12;
                            trailEffect = Fx.artilleryTrail;
                            speed = 2;
                            lifetime = 20;
                            shrinkY = 0.2f;
                            splashDamage = 40;
                            splashDamageRadius = 16;
                        }};
                    }},
                    SerpuloItems.exosteel, new ArtilleryBulletType(2f, 10, "shell"){{
                        width = 18f;
                        height = 24f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 2f;
                        lifetime = 300;
                        despawnSound = Sounds.explosionbig;
                        parts.add(new FlarePart(){{
                            stroke = 5f;
                            radius = 0;
                            radiusTo = 28;
                            sides = 6;
                            color1 = VanillaPal.exosteelAmmoLight;
                            y = -4;
                            rotMove = 45;
                            progress = PartProgress.life.slope().curve(Interp.pow2Out);
                        }});
                        hitShake = 10;
                        hitSound = Sounds.none;
                        trailWidth = 3f;
                        splashDamage = 150;
                        splashDamageRadius = 64;
                        trailLength = 28;
                        despawnHit = true;

                        hitColor = backColor = trailColor = VanillaPal.exosteelAmmoDark;
                        frontColor = VanillaPal.exosteelAmmoLight;
                        trailEffect = Fx.disperseTrail;
                        lifeScaleRandMin = 0.9f;
                        trailRotation = true;
                        trailInterval = 4;
                        hitEffect = despawnEffect;
                        despawnEffect = new MultiEffect(
                                ArikothFx.spikyBoomMedium,
                                ArikothFx.mediumStarFour,
                                new Effect(90f, 160f, e -> {
                                    float circleRad = 6f + e.finpow() * 20f;

                                    color(VanillaPal.exosteelAmmoLight, e.foutpow());
                                    Fill.circle(e.x, e.y, circleRad);
                                }).layer(Layer.bullet + 2f)
                        );
                        lightning = 6;
                        lightningColor = hitColor = VanillaPal.exosteelAmmoDark;
                        lightningLength = 8;
                        lightningLengthRand = 12;
                        intervalBullets = 1;
                        bulletInterval = 2;
                        intervalBullet = new LightningBulletType(){{
                            lightningColor = hitColor = VanillaPal.exosteelAmmoDark;
                            lightningLength = 3;
                            lightningLengthRand = 8;
                        }};
                        fragBullets = 16;
                        fragVelocityMax = 1.8f;
                        fragVelocityMin = 0.2f;
                        fragLifeMax = 1.5f;
                        fragLifeMin = 0.1f;
                        fragBullet = new ArtilleryBulletType(){{
                            hitColor = backColor = trailColor = VanillaPal.exosteelAmmoLight;
                            frontColor = VanillaPal.exosteelAmmoLight;
                            width = height = 12;
                            trailEffect = Fx.artilleryTrail;
                            speed = 2;
                            lifetime = 20;
                            shrinkY = 0.2f;
                            splashDamage = 40;
                            splashDamageRadius = 16;
                            lightning = 3;
                            lightningColor = hitColor;
                            lightningLength = 3;
                            lightningLengthRand = 2;
                        }};
                    }},
                    ArikothItems.kaneturium, new ArtilleryBulletType(2f, 10, "shell"){{
                        width = 18f;
                        height = 24f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 2f;
                        lifetime = 300;
                        despawnHit = true;
                        despawnSound = Sounds.explosionbig;
                        hitShake = 10;
                        hitSound = Sounds.none;
                        trailWidth = 3f;
                        splashDamage = 280;
                        splashDamageRadius = 96;
                        trailLength = 28;
                        lifeScaleRandMin = 0.9f;
                        parts.add(new FlarePart(){{
                            stroke = 5f;
                            radius = 0;
                            radiusTo = 28;
                            color1 = VanillaPal.kaneturiumAmmoLight;
                            y = -4;
                            rotMove = 360;
                            progress = PartProgress.life.slope().curve(Interp.pow2Out);
                        }});

                        hitColor = backColor = trailColor = VanillaPal.kaneturiumAmmoDark;
                        frontColor = VanillaPal.kaneturiumAmmoLight;
                        trailEffect = new Effect(40f, 160f, e -> {
                            Draw.color(e.color);
                            for(int s : Mathf.signs){
                                Drawf.tri(e.x, e.y, e.fout() * 6f, e.foutpow() * 18f + 6f, e.rotation + s * 90f);
                            }
                        });
                        trailInterval = 24;
                        trailRotation = true;
                        hitEffect = despawnEffect;
                        despawnEffect = new MultiEffect(
                                ArikothFx.spikyBoomMedium,
                                ArikothFx.mediumStarFour,
                                new Effect(90f, 160f, e -> {
                                    float circleRad = 6f + e.finpow() * 20f;

                                    color(VanillaPal.kaneturiumAmmoLight, e.foutpow());
                                    Fill.circle(e.x, e.y, circleRad);
                                }).layer(Layer.bullet + 2f)
                        );
                        fragBullets = 16;
                        fragRandomSpread = 0;
                        fragSpread = 22.5f;
                        fragVelocityMax = 1.1f;
                        fragVelocityMin = 0.9f;
                        fragLifeMax = 1.9f;
                        fragLifeMin = 0.8f;
                        fragBullet = new ArtilleryBulletType(){{
                            hitColor = backColor = trailColor = VanillaPal.kaneturiumAmmoDark;
                            frontColor = VanillaPal.kaneturiumAmmoLight;
                            width = height = 12;
                            trailEffect = Fx.artilleryTrail;
                            speed = 2;
                            lifetime = 10;
                            shrinkY = 0.2f;
                            splashDamage = 90;
                            splashDamageRadius = 16;
                            fragBullets = 1;
                            fragRandomSpread = 0;
                            fragVelocityMax = 1f;
                            fragVelocityMin = 0.8f;
                            fragLifeMax = 1.1f;
                            fragLifeMin = 0.8f;
                            fragBullet = new ArtilleryBulletType(){{
                                hitColor = backColor = trailColor = VanillaPal.kaneturiumAmmoDark;
                                frontColor = VanillaPal.kaneturiumAmmoLight;
                                width = height = 12;
                                trailEffect = Fx.artilleryTrail;
                                speed = 2;
                                lifetime = 10;
                                shrinkY = 0.2f;
                                splashDamage = 80;
                                splashDamageRadius = 32;
                            }};
                        }};
                    }}
            );
            drawer = new DrawTurret("ghereon-"){{
                parts.addAll(
                        new RegionPart("-barrel"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = 1f;
                            under = false;
                            heatColor = Pal.turretHeat;
                            moves.add(new PartMove(){{
                                y = -3.5f;
                                progress = PartProgress.heat;
                            }});
                        }},
                        new EffectSpawnerPart(){{
                            progress = PartProgress.charge;
                            effect = Fx.generatespark;
                            xRand = 4;
                            width = 30;
                            height = 30;
                            effectChance = 0.5f;
                        }}
                );
            }};
        }};

        fulminate = new ItemTurret("fulminate"){{
            requirements(Category.turret, with(Items.silicon, 40, Items.titanium, 72, Items.blastCompound, 28, Items.plastanium, 90));
            size = 3;
            health = 1305;
            squareSprite = false;
            shootSound = Sounds.mediumCannon;
            outlineColor = Pal.darkOutline;
            targetAir = false;
            ammoEjectBack = 2;
            ammoUseEffect = Fx.casing3Double;
            targetGround = true;
            range = 160;
            reload = 40;
            shootY = 4;
            recoil = 1.5f;
            shake = 8;
            velocityRnd = -0.4f;
            inaccuracy = 2.5f;
            shoot = new ShootSpread(15, 4);
            ammo(
                    Items.blastCompound, new ArtilleryBulletType(4f, 30, "shell"){{
                        width = 12f;
                        height = 18f;
                        shrinkY = 0.2f;
                        splashDamageRadius = 42f;
                        splashDamage = 40;
                        ammoMultiplier = 5f;
                        hitEffect = Fx.blastExplosion;
                        despawnEffect = Fx.blastExplosion;
                        lifetime = 40;
                        despawnHit = true;
                        despawnSound = Sounds.explosion;
                        hitShake = 2;
                        hitSound = Sounds.explosion;

                        status = StatusEffects.blasted;

                        hitColor = backColor = trailColor = Pal.blastAmmoBack;
                        frontColor = Pal.blastAmmoFront;
                        trailEffect = Fx.artilleryTrail;
                        reloadMultiplier = 0.8f;
                        trailInterval = 4;
                    }},

                    Items.thorium, new BasicBulletType(4f, 50, "shell"){{
                        width = 12f;
                        collidesAir = false;
                        pierceCap = 4;
                        height = 18f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 5f;
                        hitEffect = Fx.randLifeSpark;
                        despawnEffect = Fx.circleColorSpark;
                        lifetime = 40;
                        despawnHit = true;
                        despawnSound = Sounds.explosion;
                        hitShake = 2;
                        hitSound = Sounds.explosion;

                        status = StatusEffects.blasted;

                        hitColor = backColor = trailColor = Pal.thoriumAmmoBack;
                        frontColor = Pal.thoriumAmmoFront;
                        trailEffect = Fx.artilleryTrail;
                        reloadMultiplier = 0.8f;
                        trailInterval = 4;
                    }},

                    Items.pyratite, new ArtilleryBulletType(2f, 30, "shell"){{
                        width = 12f;
                        height = 18f;
                        shrinkY = 0.2f;
                        splashDamageRadius = 42f;
                        splashDamage = 35;
                        ammoMultiplier = 5f;
                        hitEffect = new ExplosionEffect(){{
                            sparks = 12;
                            sparkRad = 18;
                            sparkLen = 7;
                            sparkStroke = 2;
                            sparkColor = Pal.lightPyraFlame;
                            smokes = 6;
                            smokeSize = 12;
                            smokeRad = 12;
                            smokeColor = Color.darkGray;
                            waveRad = 12;
                            waveColor = sparkColor;
                        }};
                        despawnEffect = hitEffect;
                        makeFire = true;
                        incendAmount = 2;
                        incendChance = 0.2f;
                        incendSpread = 16;
                        lifetime = 80;
                        despawnHit = true;
                        despawnSound = Sounds.explosion;
                        hitShake = 4;
                        hitSound = Sounds.explosion;

                        status = StatusEffects.melting;

                        hitColor = backColor = trailColor = Pal.darkPyraFlame;
                        frontColor = Pal.lightPyraFlame;
                        trailEffect = Fx.artilleryTrail;
                        reloadMultiplier = 0.8f;
                        trailInterval = 4;
                    }},

                    Items.graphite, new ArtilleryBulletType(4f, 20, "shell"){{
                        width = 12f;
                        height = 18f;
                        shrinkY = 0.2f;
                        ammoMultiplier = 2f;
                        hitEffect = Fx.blastExplosion;
                        despawnEffect = Fx.blastExplosion;
                        lifetime = 40;
                        despawnHit = true;
                        despawnSound = Sounds.explosion;
                        hitShake = 2;
                        hitSound = Sounds.explosion;
                        splashDamage = 30;
                        splashDamageRadius = 16;

                        hitColor = backColor = trailColor = Pal.graphiteAmmoBack;
                        frontColor = Pal.graphiteAmmoFront;
                        trailEffect = Fx.artilleryTrail;
                        trailInterval = 4;
                    }}
            );
            drawer = new DrawTurret(){{
                parts.addAll(
                        new RegionPart("-front"){{
                            progress = PartProgress.recoil;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = -2.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }},
                        new RegionPart("-wing1"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = -0.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                            moves.add(new PartMove(){{x = 0; y = -1.5f; rot = 0; progress = PartProgress.recoil;}});
                        }},
                        new RegionPart("-wing2"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            moveRot = -8;
                            x = 0;
                            y = 0f;
                            moveX = 1;
                            moveY = 0.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                            moves.add(new PartMove(){{x = 0; y = -1.5f; rot = -5; progress = PartProgress.recoil;}});
                        }}
                );
            }};
        }};

        castigate = new ItemTurret("chastise"){{
            requirements(Category.turret, with(Items.titanium, 120, Items.thorium, 110, Items.silicon, 100, Items.graphite, 150, Items.plastanium, 80));
            size = 4;
            health = 2560;
            squareSprite = false;
            shootSound = Sounds.missileSmall;
            outlineColor = Pal.darkOutline;
            consumeAmmoOnce = false;
            minRange = 30;
            targetAir = true;
            targetGround = true;
            range = 280;
            reload = 130;
            unitSort = UnitSorts.strongest;
            shootY = 4;
            recoil = 1.5f;
            shake = 2;
            velocityRnd = 0;
            inaccuracy = 1.5f;
            ammoUseEffect = Fx.none;
            canOverdrive = false;
            minWarmup = 0.95f;
            shootWarmupSpeed = 0.05f;
            coolant = consumeCoolant(0.6f);
            shoot = new ShootAlternate(){{
                barrels = 4;
                spread = 6;
                shots = 8;
                shotDelay = 10;
            }};
            ammo(
                    Items.titanium, new MissileBulletType(4f, 10, "arikoth-rocket"){{
                        width = 12f;
                        height = 18f;
                        trailWidth = 2.5f;
                        trailLength = 12;
                        shrinkY = 0.2f;
                        weaveScale = 8;
                        weaveMag = 2;
                        splashDamageRadius = 32f;
                        splashDamage = 40;
                        ammoMultiplier = 5f;
                        hitEffect = ArikothFx.spikyBoomMedium;
                        despawnEffect = ArikothFx.spikyBoomMedium;
                        lifetime = 70;
                        homingPower = 0.02f;
                        despawnHit = true;
                        despawnSound = Sounds.dullExplosion;
                        hitShake = 4;
                        hitSound = Sounds.dullExplosion;

                        status = StatusEffects.blasted;

                        hitColor = backColor = trailColor = Color.valueOf("919fe7");
                        frontColor = Color.valueOf("a4b8fa");
                    }},
                    Items.surgeAlloy, new MissileBulletType(2f, 10, "arikoth-rocket"){{
                        width = 12f;
                        height = 18f;
                        trailWidth = 2.5f;
                        trailLength = 12;
                        shrinkY = 0.2f;
                        weaveScale = 8;
                        weaveMag = 2;
                        splashDamageRadius = 32f;
                        splashDamage = 50;
                        lightning = 4;
                        lightningLength = 12;
                        lightningLengthRand = 6;
                        lightningColor = Pal.surgeAmmoFront;
                        ammoMultiplier = 5f;
                        reloadMultiplier = 0.4f;
                        hitEffect = ArikothFx.spikyBoomMedium;
                        despawnEffect = ArikothFx.spikyBoomMedium;
                        lifetime = 70;
                        homingPower = 0.02f;
                        despawnHit = true;
                        despawnSound = Sounds.dullExplosion;
                        hitShake = 4;
                        hitSound = Sounds.dullExplosion;

                        status = StatusEffects.blasted;

                        hitColor = backColor = trailColor = Pal.surgeAmmoBack;
                        frontColor = Pal.surgeAmmoFront;
                    }},
                    Items.blastCompound, new MissileBulletType(2f, 10, "arikoth-rocket"){{
                        width = 12f;
                        height = 18f;
                        trailWidth = 2.5f;
                        trailLength = 12;
                        shrinkY = 0.2f;
                        weaveScale = 8;
                        weaveMag = 2;
                        splashDamageRadius = 42f;
                        splashDamage = 120;
                        ammoMultiplier = 5f;
                        hitEffect = ArikothFx.spikyBoomMedium;
                        despawnEffect = ArikothFx.spikyBoomMedium;
                        lifetime = 140;
                        despawnHit = true;
                        despawnSound = Sounds.dullExplosion;
                        hitShake = 4;
                        hitSound = Sounds.dullExplosion;

                        status = StatusEffects.blasted;

                        hitColor = backColor = trailColor = Pal.blastAmmoBack;
                        frontColor = Pal.blastAmmoFront;
                    }},
                    Items.plastanium, new MissileBulletType(4f, 10, "arikoth-rocket"){{
                        width = 14f;
                        height = 18f;
                        trailWidth = 3f;
                        trailLength = 16;
                        shrinkY = 0.2f;
                        weaveScale = 8;
                        weaveMag = 2;
                        splashDamageRadius = 32f;
                        splashDamage = 30;
                        ammoMultiplier = 5f;
                        hitEffect = ArikothFx.spikyBoom;
                        despawnEffect = ArikothFx.spikyBoom;
                        lifetime = 30;
                        despawnHit = true;
                        despawnSound = Sounds.dullExplosion;
                        hitShake = 4;
                        hitSound = Sounds.dullExplosion;
                        fragBullets = 3;
                        fragRandomSpread = 30;
                        fragLifeMax = 1.2f;
                        fragLifeMin = 0.8f;
                        fragVelocityMax = 1;
                        fragVelocityMin = 1;
                        fragBullet = new MissileBulletType(4f, 10, "arikoth-rocket"){{
                            width = 12f;
                            trailWidth = 2.5f;
                            trailLength = 16;
                            splashDamage = 25;
                            weaveMag = 2;
                            weaveScale = 2;
                            splashDamageRadius = 16;
                            height = 14f;
                            lifetime = 40f;
                            despawnEffect = hitEffect = Fx.plasticExplosionFlak;
                            trailColor = backColor = Pal.plastaniumBack;
                            frontColor = Pal.plastaniumFront;
                            collidesAir = false;
                            fragLifeMax = 1.2f;
                            fragRandomSpread = 12;
                            fragBullets = 2;
                            fragBullet = new BasicBulletType(2f, 10, "shell"){{
                                width = 4f;
                                splashDamage = 25;
                                splashDamageRadius = 16;
                                height = 14f;
                                lifetime = 20f;
                                trailEffect = Fx.missileTrailShort;
                                trailInterval = 4;
                                trailColor = hitColor = backColor = Pal.plastaniumBack;
                                frontColor = Pal.plastaniumFront;
                                collidesAir = false;
                                hitEffect = despawnEffect = Fx.hitBulletColor;
                            }};
                        }};

                        hitColor = backColor = trailColor = Pal.plastaniumBack;
                        frontColor = Pal.plastaniumFront;
                        ammoMultiplier = 6;
                        reloadMultiplier = 0.5f;
                    }}
            );
            drawer = new DrawTurret(){{
                parts.addAll(
                        new RegionPart("-sinks"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = -2.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }},
                        new RegionPart("-sinks2"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 2f;
                            moveY = -0;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }},
                        new RegionPart("-cover"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = -3;
                            moveY = 0;
                            under = false;
                            heatColor = Pal.turretHeat;
                        }}
                );
            }};
        }};

        pion = new ItemTurret("pion"){{
            requirements(Category.turret, with(Items.phaseFabric, 38, Items.silicon, 26, Items.graphite, 30));
            size = 2;
            health = 1200;
            squareSprite = false;
            shootSound = Sounds.laser;
            outlineColor = Pal.darkOutline;
            consumePower(880 / 60);
            consumeAmmoOnce = false;
            targetAir = true;
            targetGround = true;
            range = 180;
            reload = 40;
            shootY = 4;
            recoil = 1.5f;
            shake = 2;
            velocityRnd = 0;
            inaccuracy = 1.5f;
            ammoUseEffect = Fx.none;
            shoot.firstShotDelay = 38;
            shoot.shots = 3;
            shoot.shotDelay = 8;
            ammo(
                    Items.phaseFabric, new LaserBulletType(){{
                        width = 12f;
                        length = 180;
                        damage = 70;
                        status = VanillaStatusEffects.fluxed;
                        shootEffect = Fx.colorSparkBig;
                        hitEffect = Fx.hitBeam;
                        statusDuration = 6 * 60;
                        chargeEffect = new MultiEffect(VanillaFx.pionCharge, VanillaFx.pionChargeStar);
                        colors = new Color[]{VanillaPal.phaseAmmoDark.a(0.7f), VanillaPal.phaseAmmoLight.a(0.9f), Color.white};
                    }}
            );
            drawer = new DrawTurret(){{
                parts.addAll(
                        new RegionPart("-sinks"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 1;
                            moveY = -1;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }}
                );
            }};
        }};

        tiCaKiln = new GenericCrafter("titanium-carbide-kiln"){{
            requirements(Category.crafting, with(Items.silicon, 32, Items.copper, 55, Items.titanium, 65, Items.graphite, 38));

            size = 3;
            squareSprite = false;

            itemCapacity = 20;
            craftTime = 60f * 1f;
            hasLiquids = false;
            hasItems = true;
            hasPower = true;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.08f;

            consumeItems(with(Items.titanium, 1, Items.graphite, 2));
            consumePower(90f / 60f);

            outputItem = new ItemStack(SerpuloItems.titaniumCarbide, 1);

            craftEffect = new MultiEffect(
            new ParticleEffect(){{
                sizeFrom = 2;
                sizeTo = 5;
                length = 90;
                baseRotation = 72;
                cone = 4;
                colorFrom = Color.gray;
                colorTo = Color.darkGray.a(0);
                particles = 5;
                lifetime = 120;
                interp = Interp.pow3Out;
                sizeInterp = Interp.linear;
            }},
            new ParticleEffect(){{
                sizeFrom = 2;
                sizeTo = 5;
                length = 90;
                baseRotation = 72;
                cone = 4;
                colorFrom = Color.gray;
                colorTo = Color.darkGray.a(0);
                particles = 5;
                lifetime = 120;
                interp = Interp.pow3Out;
                sizeInterp = Interp.linear;
                startDelay = 2;
            }},
            new ParticleEffect(){{
                sizeFrom = 2;
                sizeTo = 5;
                length = 90;
                baseRotation = 72;
                cone = 4;
                colorFrom = Color.gray;
                colorTo = Color.darkGray.a(0);
                particles = 5;
                lifetime = 120;
                interp = Interp.pow3Out;
                sizeInterp = Interp.linear;
                startDelay = 4;
            }}
            );

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawFlame(){{
                        flameColor = VanillaPal.tiCaAmmoLight;
                        flameRadius = 3.5f;
                        flameRadiusScl = 4;
                        flameRadiusMag = 0.8f;
                    }}
            );
        }};

    }
};