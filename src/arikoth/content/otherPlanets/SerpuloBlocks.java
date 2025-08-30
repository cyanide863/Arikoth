package arikoth.content.otherPlanets;

import arc.graphics.Color;
import arikoth.content.ArikothFx;
import arikoth.palettes.VanillaPal;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class SerpuloBlocks {
    public static Block
    //turrets
    fulminate, castigate, pion
    ;
    public static void load(){

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
                        splashDamage = 20;
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

                    Items.pyratite, new ArtilleryBulletType(2f, 30, "shell"){{
                        width = 12f;
                        height = 18f;
                        shrinkY = 0.2f;
                        splashDamageRadius = 42f;
                        splashDamage = 20;
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

                        status = StatusEffects.blasted;

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
            shootY = 4;
            recoil = 1.5f;
            shake = 2;
            velocityRnd = 0;
            inaccuracy = 1.5f;
            ammoUseEffect = Fx.none;
            shoot = new ShootAlternate(){{
                barrels = 4;
                spread = 6;
                shots = 8;
                shotDelay = 10;
            }};
            ammo(
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
    }
};