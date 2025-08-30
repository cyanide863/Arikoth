package arikoth.content;

import arc.graphics.*;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.Seq;
import arikoth.Arikoth;
import arikoth.palettes.ArikothTurretPal;
import arikoth.palettes.ArikothUnitPal;
import arikoth.world.blocks.*;
import arikoth.world.blocks.env.ACliff;
import arikoth.world.blocks.env.ACliffHelper;
import arikoth.world.blocks.env.AFloor;
import arikoth.world.drawers.DrawPolyFlame;
import arikoth.world.drawers.DrawWeaveModif;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.*;
import arikoth.world.blocks.env.SmallSteamVent;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.ConsumeItemExplode;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static arikoth.graphics.ArikothShaders.*;
import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

public class ArikothBlocks {
    public static Block
            //cliff
            Acliff, Mcliff, AcliffHelper, hitsquare,
            //env
            duneSand, duneSandWall, duneSandMeld, quicksandTile,
            sandstone, sandstoneWall, sandstoneMeld,
            weatheredRhenium, weatheredRheniumPlate, weatheredRheniumWall, weatheredRheniumOre, weatheredRheniumVent, weatheredRheniumVentSmall, weatheredRheniumMeld,
            weatheredNickel, weatheredNickelSlate, weatheredNickelWall, pureNickelWall, weatheredNickelMeld,
            // graveyard
            drysalt, drysaltWall, drysaltMeld, moltenSaltTile,
            agedbone, agedboneWall, agedboneMeld,
            //crystalline
            reflectill, reflectillWall, chromatine, chromatineWall, quartzFloor, quartzWall,
            //mountians
            silicaFloor, silicaRock, silicaWall, weatheredSilica, weatheredSilicaSlate, weatheredSilicaWall, silicateSiliconOre, roundShale, shaleWavy,
            //ores
            strontiumOre, quartzOre,
            //props
            aereniteAmalgamLarge, drysaltMeldLarge, agedboneDetritus, agedboneDetritusLarge,
            //turrets
            puncture, seeker, calefex, glint, aperture,
            //drills
            plasmaDriller, advPlasmaDriller, impactQuarry,
            //dist
            vaccumShaftOverpass, vaccumShaft, vaccumShaftRouter, overflowSwitch, underflowSwitch,
            //liq
            fluidChannel, fluidChannelRouter, fluidChannelOverpass, hydraulicPump,
            //power
            helioPanel, teslaNode, advancedTeslaNode, strontiumGen,
            //def
            nickelWall, nickelWallLarge, amalgamWall, amalgamWallLarge,
            //craft
            mercurySynthesizer, amalgamFoundry, tyriumWeaver, burnerHeater, heatConductor,
            //units
            assaultForge, assaultReforger,
            //utilities
            coreSerenity;
    public static void load(){
        Acliff = new ACliff("cliff");
        Mcliff = new ACliff("metal-cliff");
        AcliffHelper = new ACliffHelper("cliff-helper");
        hitsquare = new StaticWall("hitbox"){{
            variants = 0;
        }};

        duneSand = new AFloor("aerna-sand"){{
            variants = 4;
            itemDrop = Items.sand;
            cliffDarkColor = Color.valueOf("#9a7f5c");
            cliffLightColor = Color.valueOf("#fadab2");
        }};
        duneSandWall = new StaticWall("aerna-sand-wall"){{
            variants = 3;
        }};
        duneSandMeld = new Prop("aerna-sand-meld"){{
            variants = 2;
        }};
        quicksandTile = new Floor("aerna-quicksand", 4) {{
            //status = ;
            statusDuration = 60f;
            speedMultiplier = 0.2f;
            drownTime = 300;
            liquidDrop = ArikothLiquids.liquidQuickSand;
            liquidMultiplier = 1f;
            isLiquid = true;
            cacheLayer = aernaQuicksandLayer;
        }};
        weatheredRhenium = new AFloor("weathered-rhenium"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#5e5541");
            cliffLightColor = Color.valueOf("#d4cda1");
        }};
        weatheredRheniumPlate = new AFloor("weathered-rhenium-plate"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#5e5541");
            cliffLightColor = Color.valueOf("#d4cda1");
        }};
        weatheredRheniumWall = new StaticTree("weathered-rhenium-wall"){{
            variants = 5;
        }};
        weatheredRheniumOre = new StaticTree("weathered-rhenium-ore"){{
            variants = 3;
            itemDrop = ArikothItems.rhenium;
        }};
        weatheredRheniumVent = new SteamVent("weathered-rhenium-vent"){{
            variants = 2;
            effect = Fx.ventSteam;
            effectColor = Color.valueOf("#807357");
            attributes.set(Attribute.steam, 1f);
            parent = ArikothBlocks.weatheredRhenium;
            blendGroup = ArikothBlocks.weatheredRhenium;
        }};
        weatheredRheniumVentSmall = new SmallSteamVent("weathered-rhenium-vent-small"){{
            variants = 2;
            effect = Fx.ventSteam;
            effectColor = Color.valueOf("#807357");
            attributes.set(Attribute.steam, 1f);
            effectSpacing = 15f;
            effectChance = 0.05f;
            blendGroup = ArikothBlocks.weatheredRhenium;
        }};
        weatheredRheniumMeld = new Prop("weathered-rhenium-meld"){{
            variants = 2;
        }};
        weatheredNickel = new AFloor("weathered-nickel"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#37312e");
            cliffLightColor = Color.valueOf("#918a84");
        }};
        weatheredNickelSlate = new AFloor("weathered-nickel-plate"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#37312e");
            cliffLightColor = Color.valueOf("#918a84");
        }};
        weatheredNickelWall = new StaticTree("weathered-nickel-wall"){{
            variants = 4;
        }};
        pureNickelWall = new StaticTree("pure-nickel-wall"){{
            variants = 3;
            itemDrop = ArikothItems.nickel;
        }};
        weatheredNickelMeld = new Prop("weathered-nickel-meld"){{
            variants = 2;
        }};
        drysalt = new AFloor("drysalt"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#727272");
            cliffLightColor = Color.valueOf("#ffffff");
        }};
        drysaltWall = new StaticWall("drysalt-wall"){{
            variants = 3;
        }};
        drysaltMeld = new Prop("drysalt-meld"){{
            variants = 2;
        }};
        moltenSaltTile = new Floor("molten-salt-tile", 0) {{
            status = StatusEffects.melting;
            statusDuration = 60f;
            speedMultiplier = 0.2f;
            drownTime = 300;
            liquidDrop = ArikothLiquids.moltenSalt;
            liquidMultiplier = 1f;
            isLiquid = true;
            cacheLayer = moltenSaltLayer;
        }};
        agedbone = new AFloor("aged-bone"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#746f65");
            cliffLightColor = Color.valueOf("#fffbf5");
        }};
        agedboneWall = new StaticWall("aged-bone-wall"){{
            variants = 3;
        }};
        // /// /// /// /// /// ///
        reflectill = new AFloor("reflectill"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#789694");
            cliffLightColor = Color.valueOf("#edfaf9");
        }};
        reflectillWall = new StaticWall("reflectill-wall"){{
            variants = 3;
        }};
        chromatine = new AFloor("chromatine"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#658978");
            cliffLightColor = Color.valueOf("#e1f5ec");
        }};
        chromatineWall = new StaticWall("chromatine-wall"){{
            variants = 3;
        }};
        quartzFloor = new AFloor("quartz-floor"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#726f6d");
            cliffLightColor = Color.valueOf("#fefffc");
        }};
        quartzWall = new StaticWall("quartz-wall"){{
            variants = 3;
            itemDrop = ArikothItems.quartz;
        }};
        // /// /// /// /// /// ///
        silicaFloor = new AFloor("silica-floor"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#262729");
            cliffLightColor = Color.valueOf("#696c70");
        }};
        silicaRock = new AFloor("silica-rock"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#262729");
            cliffLightColor = Color.valueOf("#696c70");
        }};
        silicaWall = new StaticWall("silica-wall"){{
            variants = 3;
        }};
        weatheredSilica = new AFloor("weathered-silicate"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#1d1d20");
            cliffLightColor = Color.valueOf("#66666b");
        }};
        weatheredSilicaSlate = new AFloor("weathered-silicate-slate"){{
            variants = 4;
            cliffDarkColor = Color.valueOf("#1d1d20");
            cliffLightColor = Color.valueOf("#66666b");
        }};
        weatheredSilicaWall = new StaticWall("weathered-silicate-wall"){{
            variants = 3;
        }};
        silicateSiliconOre = new StaticWall("silicate-silcon-ore"){{
            variants = 2;
            itemDrop = Items.silicon;
        }};
        roundShale = new AFloor("shale-round"){{
            variants = 3;
        }};
        shaleWavy = new AFloor("shale-wavy"){{
            variants = 3;
        }};
        //props
        aereniteAmalgamLarge = new TallBlock("aerenite-amalgam-large"){{
            variants = 2;
            clipSize = 128f;
            shadowAlpha = 0.5f;
            shadowOffset = -3f;
        }};
        drysaltMeldLarge = new TallBlock("drysalt-cluster"){{
            variants = 2;
            clipSize = 128f;
            shadowAlpha = 0.5f;
            shadowOffset = -2f;
        }};
        agedboneDetritus = new TallBlock("aged-bone-detritus"){{
            variants = 2;
            clipSize = 128f;
            shadowAlpha = 0.5f;
            shadowOffset = -2;
            shadowLayer = Layer.legUnit + 1.5f;
            layer = Layer.legUnit + 2f;
        }};
        agedboneDetritusLarge = new TallBlock("aged-bone-detritus-large"){{
            variants = 2;
            clipSize = 512;
            shadowAlpha = 0.5f;
            shadowOffset = -4;
            solid = false;
            shadowLayer = Layer.legUnit + 2.5f;
            layer = Layer.legUnit + 4f;
        }};
        //ore
        strontiumOre = new OreBlock("strontium-ore"){{
            variants = 3;
            itemDrop = ArikothItems.strontium;
        }};
        quartzOre = new OreBlock("quartz-ore"){{
            variants = 3;
            itemDrop = ArikothItems.quartz;
        }};
        //turrets
        puncture = new ItemTurret("puncture"){{
            requirements(Category.turret, with(ArikothItems.rhenium, 40));
            size = 2;
            health = 600;
            squareSprite = false;
            shootSound = Sounds.shootSnap;
            outlineColor = ArikothUnitPal.turretOutline;
            targetAir = false;
            targetGround = true;
            range = 120;
            reload = 80;
            shootY = 4;
            recoil = 1.5f;
            velocityRnd = 0;
            inaccuracy = 0;
            shoot.shots = 3;
            shoot.shotDelay = 6;
            ammo(
                    ArikothItems.rhenium, new BasicBulletType(){{
                        collidesAir = false;
                        width = 8;
                        height = 12;
                        speed = 12;
                        damage = 40;
                        pierceCap = 2;
                        lifetime = 10;
                        hitColor = backColor = trailColor = ArikothTurretPal.rheniumTrail;
                        frontColor = ArikothTurretPal.rheniumLight;
                        trailWidth = 2;
                        trailLength = 6;
                        shootEffect = new Effect(25, e -> {
                            color(ArikothTurretPal.rheniumLight, ArikothTurretPal.rheniumTrail, e.fin());
                            float w = 2f + 2 * e.fout();
                            Drawf.tri(e.x, e.y, w, 36f * e.fout(), e.rotation);
                            Drawf.tri(e.x, e.y, w, 6f * e.fout(), e.rotation + 180f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + 45f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + -45f);
                        });
                        trailChance = 0.01f;
                        trailEffect = new ParticleEffect(){{
                            strokeFrom = 4;
                            strokeTo = 0;
                            lenFrom = 7;
                            lenTo = 0;
                            baseRotation = 180;
                            cone = 20;
                            lifetime = 25;
                            interp = Interp.circleOut;
                            colorFrom = ArikothTurretPal.rheniumLight;
                            colorTo = ArikothTurretPal.rheniumTrail;
                            line = true;
                        }};
                        trailInterval = 2;
                        trailRotation = true;
                    }}
            );
            drawer = new DrawTurret("fortified-"){{
                parts.addAll(
                new RegionPart("-mid"){{
                progress = PartProgress.recoil;
                mirror = false;
                moveRot = 0;
                x = 0;
                y = 0f;
                moveX = 0;
                moveY = -2f;
                under = true;
                heatColor = Pal.turretHeat;
                }});
            }};
        }};
        seeker = new ItemTurret("seeker"){{
            requirements(Category.turret, with(ArikothItems.rhenium, 10, ArikothItems.nickel, 30));
            size = 2;
            health = 600;
            squareSprite = false;
            shootSound = Sounds.missile;
            minWarmup = 0.95f;
            shootWarmupSpeed = 0.1f;
            outlineColor = ArikothUnitPal.turretOutline;
            targetAir = true;
            targetGround = false;
            range = 240;
            reload = 120;
            shootY = 4;
            recoil = 1.5f;
            velocityRnd = 0.25f;
            inaccuracy = 3;
            shoot = new ShootSpread(6,3){{
                shotDelay = 10;
            }};
            ammo(
                    ArikothItems.rhenium, new BasicBulletType(){{
                        sprite = "arikoth-rocket";
                        width = 8;
                        weaveRandom = false;
                        height = 12;
                        speed = 4;
                        damage = 20;
                        lifetime = 60;
                        weaveScale = 6;
                        weaveMag = 2;
                        collidesGround = false;
                        collidesAir = true;
                        hitColor = backColor = trailColor = ArikothTurretPal.rheniumTrail;
                        frontColor = ArikothTurretPal.rheniumLight;
                        trailWidth = 2;
                        trailLength = 6;
                        shootEffect = Fx.shootSmallColor;
                        trailChance = 1;
                        trailEffect = Fx.missileTrailShort;
                        trailInterval = 3;
                        trailRotation = false;
                    }},
                    ArikothItems.quartz, new BasicBulletType(){{
                        sprite = "arikoth-rocket";
                        width = 12;
                        weaveRandom = false;
                        height = 12;
                        reloadMultiplier = 1.2f;
                        speed = 8;
                        damage = 35;
                        pierceCap = 3;
                        lifetime = 40;
                        rangeChange = 80;
                        collidesGround = false;
                        collidesAir = true;
                        hitColor = backColor = trailColor = ArikothTurretPal.quartzTrail;
                        frontColor = ArikothTurretPal.quartzLight;
                        trailWidth = 2;
                        trailLength = 6;
                        shootEffect = Fx.shootSmallColor;
                        trailChance = 0.5f;
                        trailEffect = Fx.disperseTrail;
                        trailInterval = 3;
                        trailRotation = true;
                    }}
            );
            drawer = new DrawTurret("fortified-"){{
                parts.addAll(
                        new RegionPart("-undersect"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 10f / 4;
                            moveX = 0;
                            moveY = -10f / 4f;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }});
            }};
        }};
        calefex = new ItemTurret("calefex"){{
            requirements(Category.turret, with(ArikothItems.rhenium, 25, ArikothItems.nickel, 10, ArikothItems.strontium, 20));
            size = 2;
            health = 600;
            squareSprite = false;
            shootSound = Sounds.artillery;
            outlineColor = ArikothUnitPal.turretOutline;
            targetAir = false;
            targetGround = true;
            range = 270;
            reload = 80;
            shootY = 4;
            recoil = 1.5f;
            velocityRnd = 0.4f;
            inaccuracy = 3;
            shoot.shots = 3;
            ammo(
                    ArikothItems.strontium, new ArtilleryBulletType(){{
                        collidesAir = false;
                        width = 12;
                        height = 12;
                        speed = 6;
                        incendAmount = 4;
                        incendSpread = 8;
                        incendChance = 1;
                        makeFire = true;
                        splashDamage = 40;
                        splashDamageRadius = 32;
                        status = StatusEffects.melting;
                        lifetime = 90;
                        hitColor = backColor = trailColor = ArikothTurretPal.strontiumTrail;
                        frontColor = ArikothTurretPal.strontiumLight;
                        trailWidth = 2;
                        trailLength = 6;
                        shootEffect = new Effect(25, e -> {
                            color(ArikothTurretPal.strontiumLight, ArikothTurretPal.strontiumTrail, e.fin());
                            float w = 2.5f + 2 * e.fout();
                            Drawf.tri(e.x, e.y, w, 36f * e.fout(), e.rotation);
                            Drawf.tri(e.x, e.y, w, 6f * e.fout(), e.rotation + 180f);
                            Drawf.tri(e.x, e.y, w - 1, 18f * e.fout(), e.rotation + 90);
                            Drawf.tri(e.x, e.y, w - 1, 18f * e.fout(), e.rotation + -90);
                        });
                        trailEffect = Fx.artilleryTrail;
                        trailInterval = 4;
                    }}
            );
            drawer = new DrawTurret("fortified-"){{
                parts.addAll(
                        new RegionPart("-front"){{
                            progress = PartProgress.recoil;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = -3.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }});
            }};
        }};
        glint = new ItemTurret("glint"){{
            requirements(Category.turret, with(ArikothItems.amalgam, 60, ArikothItems.nickel, 45,  ArikothItems.quartz, 20));
            size = 3;
            health = 900;
            squareSprite = false;
            shootSound = Sounds.railgun;
            soundPitchMax = 3.2f;
            soundPitchMin = 2.8f;
            minWarmup = 0.95f;
            shootWarmupSpeed = 0.08f;
            outlineColor = ArikothUnitPal.turretOutline;
            targetAir = true;
            targetGround = true;
            range = 270;
            reload = 100;
            shootY = 4;
            recoil = 2f;
            velocityRnd = 0;
            inaccuracy = 1;
            shoot.shotDelay = 60;
            consumePower(1.5f);
            ammo(
                    ArikothItems.quartz, new BasicBulletType(){{
                        sprite = "arikoth-rocket";
                        width = 12;
                        height = 18;
                        speed = 6;
                        lightningDamage = 10;
                        lightningCone = 10;
                        lightningLength = 8;
                        lightningLengthRand = 4;
                        lightningColor = ArikothTurretPal.quartzLight;
                        lightning = 4;
                        damage = 80;
                        lifetime = 30;
                        collidesGround = true;
                        collidesAir = true;
                        hitColor = backColor = trailColor = ArikothTurretPal.quartzTrail;
                        frontColor = ArikothTurretPal.quartzLight;
                        trailWidth = 2.5f;
                        trailLength = 12;
                        shootEffect = Fx.colorSparkBig;
                        smokeEffect = Fx.shootSmokeSquareSparse;
                        trailChance = 1;
                        trailEffect = Fx.disperseTrail;
                        trailInterval = 3;
                        trailRotation = true;
                    }}
            );
            drawer = new DrawTurret("fortified-"){{
                parts.addAll(
                new RegionPart("-wing"){{
                    progress = PartProgress.warmup;
                    mirror = false;
                    moveRot = 0;
                    x = 0;
                    y = 0;
                    moveX = 0;
                    moveY = -2;
                    under = false;
                    heatColor = Pal.turretHeat;
                }},
                new RegionPart("-sink"){{
                    progress = PartProgress.warmup;
                    mirror = true;
                    moveRot = 0;
                    x = 0;
                    y = 0;
                    moveX = 3;
                    moveY = -3;
                    under = true;
                    layerOffset = -0.0002f;
                    heatLayerOffset = -0.0001f;
                    heatColor = Pal.turretHeat;
                }}
                );
            }};
        }};
        aperture = new ItemTurret("aperture"){{
            requirements(Category.turret, with(ArikothItems.rhenium, 20, ArikothItems.amalgam, 80, ArikothItems.nickel, 120));
            size = 3;
            health = 900;
            squareSprite = false;
            shootSound = Sounds.bang;
            outlineColor = ArikothUnitPal.turretOutline;
            targetAir = true;
            targetGround = true;
            range = 256;
            reload = 130;
            shootY = 4;
            recoil = 1.5f;
            shake = 4;
            velocityRnd = 0;
            inaccuracy = 0;
            ammoUseEffect = Fx.casing2Double;
            ammoEjectBack = 2;
            ammo(
                    ArikothItems.nickel, new BasicBulletType(){{
                        collidesAir = true;
                        despawnShake = 2;
                        width = 18;
                        height = 18;
                        speed = 8;
                        damage = 170;
                        pierceCap = 4;
                        lifetime = 32;
                        hitColor = backColor = trailColor = ArikothTurretPal.nickelLight;
                        frontColor = ArikothTurretPal.nickelLight;
                        trailWidth = 3.5f;
                        trailLength = 6;
                        hitEffect = Fx.randLifeSpark;
                        despawnEffect = ArikothFx.spikyBoom;
                        shootEffect = new Effect(25, e -> {
                            color(Color.white, ArikothTurretPal.nickelLight, e.fin());
                            float w = 2f + 2 * e.fout();
                            Drawf.tri(e.x, e.y, w, 36f * e.fout(), e.rotation);
                            Drawf.tri(e.x, e.y, w, 6f * e.fout(), e.rotation + 180f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + 45f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + -45f);
                        });
                        trailEffect = Fx.none;
                    }},
                    ArikothItems.amalgam, new BasicBulletType(){{
                        collidesAir = true;
                        despawnShake = 2;
                        width = 18;
                        height = 18;
                        speed = 8;
                        damage = 220;
                        pierceCap = 4;
                        lifetime = 38;
                        rangeChange = 48;
                        hitColor = backColor = trailColor = ArikothTurretPal.amalgamLight;
                        frontColor = ArikothTurretPal.amalgamLight;
                        trailWidth = 3.5f;
                        trailLength = 6;
                        hitEffect = Fx.randLifeSpark;
                        despawnEffect = ArikothFx.spikyBoom;
                        shootEffect = new Effect(25, e -> {
                            color(Color.white, ArikothTurretPal.amalgamLight, e.fin());
                            float w = 2f + 2 * e.fout();
                            Drawf.tri(e.x, e.y, w, 36f * e.fout(), e.rotation);
                            Drawf.tri(e.x, e.y, w, 6f * e.fout(), e.rotation + 180f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + 45f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + -45f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + 135f);
                            Drawf.tri(e.x, e.y, w - 2, 18f * e.fout(), e.rotation + -135f);
                        });
                        trailEffect = Fx.disperseTrail;
                        trailRotation = true;
                        trailInterval = 4;
                    }}
            );
            drawer = new DrawTurret("fortified-"){{
                parts.addAll(
                        new RegionPart("-mid"){{
                            progress = PartProgress.recoil;
                            mirror = false;
                            moveRot = 0;
                            x = 0;
                            y = 0f;
                            moveX = 0;
                            moveY = -3.5f;
                            under = true;
                            heatColor = Pal.turretHeat;
                        }});
            }};
        }};
        //drill
        plasmaDriller = new BeamDrill("plasma-driller"){{
            requirements(Category.production, with(ArikothItems.rhenium, 45));
            consumePower(0.25f);

            drillTime = 210f;
            health = 120;
            tier = 1;
            size = 2;
            range = 3;
            fogRadius = 3;
            researchCost = with(ArikothItems.rhenium, 30);

            consumeLiquid(ArikothLiquids.icher, 0.25f / 60f).boost();
        }};
        advPlasmaDriller = new BeamDrill("advanced-plasma-driller"){{
            requirements(Category.production, with(ArikothItems.rhenium, 90, ArikothItems.nickel, 45, ArikothItems.strontium, 30));
            consumePower(0.5f);

            drillTime = 140f;
            health = 120;
            tier = 1;
            size = 3;
            range = 6;
            fogRadius = 3;
            researchCostMultiplier = 10;

            consumeLiquid(ArikothLiquids.icher, 0.25f / 60f).boost();
        }};
        impactQuarry = new RotaryImpactDrill("impact-quarry"){{
            size = 4;
            health = 3210;
            requirements(Category.production, with(ArikothItems.rhenium, 110, ArikothItems.nickel, 125));
            researchCost = with(ArikothItems.rhenium, 220, ArikothItems.nickel, 250);
            itemCapacity = 32;
            arrows = 18;
            arrowColor = Color.valueOf("ff9a99");
            baseArrowColor = Color.valueOf("ffffff00");
            tier = 7;
            blockedItem = Items.thorium;
            drillMultipliers.put(ArikothItems.quartz, 1.5f);
            drillEffect = new MultiEffect(
              new ParticleEffect(){{
                  particles = 18;
                  line = true;
                  lenFrom = 7;
                  lenTo = 0;
                  strokeFrom = 4;
                  strokeTo = 0;
                  length = 40;
                  colorTo = arrowColor;
                  cone = 360;
                  interp = Interp.circleOut;
                  lifetime = 30;
              }},
              new WaveEffect(){{
                  sizeTo = 40;
                  sizeFrom = 0;
                  strokeFrom = 4;
                  strokeTo = 0;
                  colorTo = arrowColor;
                  interp = Interp.circleOut;
                  lifetime = 30;
              }},
              new WaveEffect(){{
                  sizeTo = 80;
                  sizeFrom = 0;
                  strokeFrom = 4;
                  strokeTo = 0;
                  layer = Layer.debris;
                  colorTo = arrowColor;
                  interp = Interp.circleOut;
                  lifetime = 30;
              }}
            );

            consumePower(6);
            drillTime = 720;
            hardnessDrillMultiplier = 1;
        }};
        //dist
        vaccumShaft = new Duct("vaccum-shaft"){{
            requirements(Category.distribution, with(ArikothItems.rhenium, 1));
            health = 100;
            speed = 5f;
            researchCost = with(ArikothItems.rhenium, 1);
            bridgeReplacement = ArikothBlocks.vaccumShaftOverpass;
        }};
        vaccumShaftRouter = new DuctRouter("vaccum-shaft-router"){{
            requirements(Category.distribution, with(ArikothItems.rhenium, 10));
            health = 120;
            speed = 5f;
            regionRotated1 = 1;
            solid = false;
            researchCost = with(ArikothItems.rhenium, 30);
        }};
        vaccumShaftOverpass = new DuctBridge("vaccum-shaft-overpass"){{
            requirements(Category.distribution, with(ArikothItems.rhenium, 15, ArikothItems.nickel, 6));
            health = 120;
            speed = 4f;
            range = 6;
            researchCostMultiplier = 2f;
        }};
        overflowSwitch = new OverflowGate("overflow-switch"){{
            requirements(Category.distribution, with(ArikothItems.rhenium, 5, ArikothItems.nickel, 5));
            health = 120;
            speed = 5f;
            researchCost = with(ArikothItems.rhenium, 15, ArikothItems.nickel, 15);
        }};
        underflowSwitch = new OverflowGate("underflow-switch"){{
            requirements(Category.distribution, with(ArikothItems.rhenium, 5, ArikothItems.nickel, 5));
            health = 120;
            invert = true;
            speed = 5f;
            researchCost = with(ArikothItems.rhenium, 15, ArikothItems.nickel, 15);
        }};
        //liq
        fluidChannel = new Conduit("fluid-channel"){{
            requirements(liquid, with(ArikothItems.nickel, 1));
            health = 100;
            botColor = Color.valueOf("1f1917");
            rotBridgeReplacement = ArikothBlocks.fluidChannelOverpass;
        }};
        fluidChannelRouter = new LiquidRouter("fluid-channel-router"){{
            requirements(liquid, with(ArikothItems.nickel, 6));
            health = 100;
            liquidCapacity = 20;
            squareSprite = false;
            liquidPadding = 0.5f;
        }};
        fluidChannelOverpass = new DirectionLiquidBridge("fluid-channel-overpass"){{
            requirements(liquid, with(ArikothItems.nickel, 20));
            health = 100;
            liquidCapacity = 10;
            range = 6;
            squareSprite = false;
        }};
        hydraulicPump = new Pump("hydraulic-pump"){{
            requirements(Category.liquid, with(ArikothItems.rhenium, 10, ArikothItems.strontium, 12));
            pumpAmount = 0.1f;
            consumePower(1.5f);
            liquidCapacity = 60f;
            hasPower = true;
            size = 2;
            drawer = new DrawMulti(
               new DrawDefault(),
               new DrawPistons(){{
                   sides = 4;
                   angleOffset = 45;
                   sinMag = 2;
                   sinScl = 4;
               }},
               new DrawLiquidTile(){{
                   drawLiquid = ArikothLiquids.moltenSalt;
                   alpha = 0.5f;
               }},
               new DrawRegion("-top"),
               new DrawPumpLiquid()
            );
        }};
        //power
        helioPanel = new SolarGenerator("helio-panel"){{
            requirements(Category.power, with(ArikothItems.rhenium, 10, ArikothItems.nickel, 8));
            powerProduction = 0.75f;
            size = 2;
        }};
        teslaNode = new BeamPylon("tesla-node"){{
            requirements(Category.power, with(ArikothItems.rhenium, 8));
            consumesPower = outputsPower = true;
            health = 90;
            range = 10;
            fogRadius = 1;
            buildCostMultiplier = 2.5f;
            consumePowerBuffered(1000f);
        }};
        advancedTeslaNode = new BeamPylon("advanced-tesla-node"){{
            requirements(Category.power, with(ArikothItems.rhenium, 60, ArikothItems.nickel, 60, ArikothItems.quartz, 120));
            consumesPower = outputsPower = true;
            health = 900;
            range = 20;
            size = 3;
            fogRadius = 1;
            buildCostMultiplier = 2.5f;
            consumePowerBuffered(1000f);
        }};
        strontiumGen = new ConsumeGenerator("strontium-burner"){{
            requirements(Category.power, with(Items.silicon, 40, ArikothItems.nickel, 50, ArikothItems.amalgam, 30));
            powerProduction = 360f / 60f;
            consumeItem(ArikothItems.strontium);
            consumeLiquid(ArikothLiquids.moltenSalt, 0.5f);
            itemDuration = 6 * 60;
            size = 3;
            drawer = new DrawMulti(
            new DrawRegion("-lower"),
            new DrawPistons(){{
               sinMag = 2.25f;
               sinScl = 3f;
                sideOffset = Mathf.PI / 2f;
            }},
            new DrawRegion("-mid"),
            new DrawLiquidTile(){{
                drawLiquid = ArikothLiquids.moltenSalt;
                padding = 36f / 4f;
            }},
            new DrawDefault(),
            new DrawGlowRegion(){{
                alpha = 0.5f;
                glowScale = 10f;
                color = Color.valueOf("ff0000");
            }}
            );
            generateEffect = Fx.none;

            itemCapacity = 10;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;
        }};
        //def
        int ArikothWallMult = 4;
        nickelWall = new Wall("nickel-wall"){{
            requirements(Category.defense, with(ArikothItems.nickel, 8));
            health = 100 * ArikothWallMult;
            researchCostMultiplier = 1;
        }};
        nickelWallLarge = new Wall("nickel-wall-large"){{
            requirements(Category.defense, with(ArikothItems.nickel, 32));
            health = 100 * 4 * ArikothWallMult;
            researchCostMultiplier = 1;
            size = 2;
        }};
        amalgamWall = new Wall("amalgam-wall"){{
            requirements(Category.defense, with(ArikothItems.amalgam, 8));
            health = 170 * ArikothWallMult;
            researchCostMultiplier = 10;
            size = 1;
        }};
        amalgamWallLarge = new Wall("amalgam-wall-large"){{
            requirements(Category.defense, with(ArikothItems.amalgam, 32));
            health = 170 * 4 * ArikothWallMult;
            researchCostMultiplier = 1;
            size = 2;
            variants = 8;
        }};
        //craft

        mercurySynthesizer = new GenericCrafter("mercury-synthesizer"){{
            requirements(Category.crafting, with(ArikothItems.nickel, 60, ArikothItems.strontium, 10, Items.silicon, 60));

            size = 2;
            squareSprite = false;

            craftTime = 60f * 1f;
            liquidCapacity = 10;
            hasLiquids = true;
            hasItems = true;
            hasPower = true;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.9f;

            consumeLiquid(ArikothLiquids.moltenSalt, 0.06666666666f);
            consumeItems(with(ArikothItems.strontium, 1));
            consumePower(180f / 60f);

            outputLiquid = new LiquidStack(ArikothLiquids.mercury, 0.1f);

            updateEffectSpread = 1.5f;
            updateEffectChance = 0.16f;
            updateEffect = new ParticleEffect(){{
                sizeFrom = 1;
                sizeTo = 0;
                particles = 2;
                length = 10;
                lifetime = 25;
                interp = Interp.pow3Out;
                colorTo = Color.valueOf("#7c726200");
            }};
            drawer = new DrawMulti(
                    new DrawRegion("-lower"),
                    new DrawLiquidTile(ArikothLiquids.mercury){{
                        padding = 1;
                    }},
                    new DrawCircles(){{
                        color = Color.valueOf("ffffff40");
                        strokeMax = 2.5f;
                        radius = 7f;
                        amount = 3;
                    }},
                    new DrawCrucibleFlame(){{
                        flameColor = Color.valueOf("cebee8");
                        particles = 10;
                        particleSize = 2;
                    }},
                    new DrawDefault(),
                    new DrawGlowRegion("-glow"){{
                        glowScale = 10;
                        alpha = 0.5f;
                        color = Color.valueOf("ff0000");
                    }}
            );
        }};

        amalgamFoundry = new GenericCrafter("amalgam-foundry"){{
            requirements(Category.crafting, with(ArikothItems.rhenium, 120, ArikothItems.nickel, 90, Items.silicon, 60, ArikothItems.quartz, 120));

            size = 3;
            squareSprite = false;

            itemCapacity = 40;
            craftTime = 60f * 3f;
            liquidCapacity = 20;
            hasLiquids = true;
            hasItems = true;
            hasPower = true;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.9f;

            consumeLiquid(ArikothLiquids.mercury, 0.2f);
            consumeItems(with(ArikothItems.nickel, 2, Items.silicon, 4));
            consumePower(360f / 60f);

            outputItem = new ItemStack(ArikothItems.amalgam, 4);

            craftEffect = new MultiEffect(
                    new RadialEffect(new MultiEffect(
                            new ParticleEffect(){{
                                sizeFrom = 1.5f;
                                sizeTo = 0;
                                colorFrom = ArikothTurretPal.amalgamLight;
                                colorTo = Color.valueOf("#7760a600");
                                length = 20;
                                baseLength = 4;
                                sizeInterp = Interp.linear;
                                interp = Interp.pow3Out;
                                lifetime = 30;
                                startDelay = 0;
                                particles = 2;
                                cone = 4;
                            }},
                            new ParticleEffect(){{
                                sizeFrom = 1.5f;
                                sizeTo = 0;
                                colorFrom = ArikothTurretPal.amalgamLight;
                                colorTo = Color.valueOf("#7760a600");
                                length = 20;
                                baseLength = 4;
                                sizeInterp = Interp.linear;
                                interp = Interp.pow3Out;
                                lifetime = 30;
                                startDelay = 1;
                                particles = 2;
                                cone = 4;
                            }},
                            new ParticleEffect(){{
                                sizeFrom = 1.5f;
                                sizeTo = 0;
                                colorFrom = ArikothTurretPal.amalgamLight;
                                colorTo = Color.valueOf("#7760a600");
                                length = 20;
                                baseLength = 4;
                                sizeInterp = Interp.linear;
                                interp = Interp.pow3Out;
                                lifetime = 30;
                                startDelay = 2;
                                particles = 2;
                                cone = 4;
                            }},
                            new ParticleEffect(){{
                                sizeFrom = 1.5f;
                                sizeTo = 0;
                                colorFrom = ArikothTurretPal.amalgamLight;
                                colorTo = Color.valueOf("#7760a600");
                                length = 20;
                                baseLength = 4;
                                sizeInterp = Interp.linear;
                                interp = Interp.pow3Out;
                                lifetime = 30;
                                startDelay = 3;
                                particles = 2;
                                cone = 4;
                            }},
                            new ParticleEffect(){{
                                sizeFrom = 1.5f;
                                sizeTo = 0;
                                colorFrom = ArikothTurretPal.amalgamLight;
                                colorTo = Color.valueOf("#7760a600");
                                length = 20;
                                baseLength = 4;
                                sizeInterp = Interp.linear;
                                interp = Interp.pow3Out;
                                lifetime = 30;
                                startDelay = 4;
                                particles = 2;
                                cone = 4;
                            }},
                            new ParticleEffect(){{
                                sizeFrom = 1.5f;
                                sizeTo = 0;
                                colorFrom = ArikothTurretPal.amalgamLight;
                                colorTo = Color.valueOf("#7760a600");
                                length = 20;
                                baseLength = 4;
                                sizeInterp = Interp.linear;
                                interp = Interp.pow3Out;
                                lifetime = 30;
                                startDelay = 5;
                                particles = 2;
                                cone = 4;
                            }}
                    ),8, 45f, 5f)
                    );

            drawer = new DrawMulti(
            new DrawRegion("-lower"),
            new DrawLiquidTile(ArikothLiquids.mercury){{
                padding = 3;
            }},
            new DrawCircles(){{
                color = Color.valueOf("cebee8").a(0.24f);
                strokeMax = 2.5f;
                radius = 10f;
                amount = 3;
            }},
            new DrawCrucibleFlame(){{
                flameColor = Color.valueOf("cebee8");
                particles = 10;
                particleSize = 2;
            }},
            new DrawDefault(),
            new DrawGlowRegion("-glow"){{
                color = Color.valueOf("cebee8");
                glowScale = 10;
                alpha = 0.5f;
            }}
            );
        }};

        tyriumWeaver = new HeatCrafter("tyrium-weaver"){{
            requirements(Category.crafting, with(ArikothItems.rhenium, 120, ArikothItems.nickel, 90, Items.silicon, 60));

            size = 4;
            squareSprite = false;

            itemCapacity = 40;
            craftTime = 60f * 6f;
            liquidCapacity = 10;
            hasLiquids = true;
            hasItems = true;
            hasPower = true;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.9f;

            consumeLiquid(ArikothLiquids.moltenSalt, 0.8f);
            consumeItems(with(ArikothItems.irium, 8, Items.thorium, 4));
            consumePower(720f / 60f);

            outputItem = new ItemStack(ArikothItems.tyrium, 8);

            craftEffect = Fx.none;

            drawer = new DrawMulti(
                    new DrawRegion("-lower"),
                    new DrawMultiWeave(){{
                        weaveColor = Color.valueOf("417778");
                        rotateSpeed2 = -.5f;
                        rotateSpeed = .5f;
                        glowColor = weaveColor;
                    }},
                    new DrawWeaveModif(){{
                        color = Color.valueOf("cefff2");
                        line2Speed = line2AltSpeed = 5;
                    }},
                    new DrawDefault(),
                    new DrawGlowRegion("-glow"){{
                        color = Color.valueOf("8bbeb0");
                        glowScale = 10;
                        alpha = 0.5f;
                    }}
            );
        }};
        burnerHeater = new HeatProducer("burner-heater"){{
            requirements(Category.crafting, with(ArikothItems.nickel, 40, Items.silicon, 20));

            researchCostMultiplier = 4f;

            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            rotateDraw = false;
            hasItems = true;
            itemCapacity = 10;
            size = 2;
            heatOutput = 3f;
            regionRotated1 = 1;
            ambientSound = Sounds.hum;

            consume(new ConsumeItemFlammable());
        }};
        heatConductor = new HeatConductor("heat-conductor"){{
            requirements(Category.crafting, with(ArikothItems.nickel, 30, ArikothItems.rhenium, 10, Items.silicon, 20));

            researchCostMultiplier = 4f;

            group = BlockGroup.heat;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
            conductivePower = true;
            consumePower(5f / 60f);
        }};
        //units
        assaultForge = new UnitFactoryModif("assault-forge"){{
            requirements(Category.units, with(ArikothItems.nickel, 220, ArikothItems.strontium, 120));

            size = 3;
            configurable = true;
            plans = Seq.with(
                new UnitPlan(ArikothUnitTypes.kindle, 60f * 60f, with(ArikothItems.rhenium, 40, Items.silicon, 30)),
                new UnitPlan(ArikothUnitTypes.bolt, 60f * 60f, with(ArikothItems.nickel, 60, Items.silicon, 20))
            );
            regionSuffix = "-desert";
            fogRadius = 3;
            researchCostMultiplier = 1.5f;
            consumePower(320 / 60);
            color = ArikothUnitPal.assaultGold;
        }};
        assaultReforger = new UnitReforger("assault-reforger"){{
            requirements(Category.units, with(ArikothItems.amalgam, 130, Items.silicon, 120, ArikothItems.nickel, 220, ArikothItems.rhenium, 120));

            size = 4;
            consumePower(440 / 60);
            consumeItems(with(ArikothItems.amalgam, 60, ArikothItems.irium, 80));

            constructTime = 80f * 60f;

            upgrades.addAll(
                    new UnitType[]{ArikothUnitTypes.kindle, ArikothUnitTypes.calxel},
                    new UnitType[]{ArikothUnitTypes.bolt, ArikothUnitTypes.hasp}
            );
            color = ArikothUnitPal.assaultGold;
        }};
        //effect
        coreSerenity = new CoreBlock("core-serenity"){{
            requirements(effect, with(ArikothItems.rhenium, 1200, ArikothItems.nickel, 800));
            alwaysUnlocked = true;
            isFirstTier = true;
            unitType = ArikothUnitTypes.vision;
            health = 4500;
            armor = 5;
            itemCapacity = 2000;
            size = 4;
            buildCostMultiplier = 2f;
            squareSprite = false;
            unitCapModifier = 15;
            thrusterLength = 34/4f;
        }};
        }
};