package arikoth.content;

import arc.audio.Music;
import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arikoth.planet.maps.ColorPass;
import arikoth.planet.maps.HeightPass;
import arikoth.world.meta.ArikothEnv;
import mindustry.content.Blocks;
import mindustry.content.Weathers;
import mindustry.game.Gamemode;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import arikoth.planet.*;;

public class ArikothPlanets {
    public static Planet acithar, mesarius, halchyin, arikoth, zealor, hypheru, kargithar, tauix, eres, kelthir, derath;

    public static void load(){
        acithar = new Planet("Acithar", null, 8f){{
            bloom = true;
            accessible = false;
            solarSystem = this;
            meshLoader = () -> new SunMesh(
                    this, 5,
                    5, 0.3, 2.7, 1.2, 1,
                    1.15f,
                    Color.valueOf("#d5a762"),
                    Color.valueOf("#debb7f"),
                    Color.valueOf("#e8cf9c"),
                    Color.valueOf("#f3e3bb"),
                    Color.valueOf("#fff6db")
            );
        }};
        mesarius = new Planet("mesarius", ArikothPlanets.acithar, 4f){{
            bloom = true;
            accessible = false;
            solarSystem = acithar;
            orbitRadius = 60;
            meshLoader = () -> new SunMesh(
                    this, 5,
                    5, 0.3, 2.7, 1.2, 1,
                    1f,
                    Color.valueOf("#c8142a"),
                    Color.valueOf("#d9484a"),
                    Color.valueOf("#e86b6b"),
                    Color.valueOf("#f58d8c"),
                    Color.valueOf("#ffadad")
            );
        }};
        arikoth = new Planet("Arikoth", ArikothPlanets.acithar, 1.4f, 3){{
            generator = new ArikothPlanetGenerator();

            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 42, 2f, 0.13f, 5, Color.valueOf("d2ae8d").a(0.75f), 3, 0.7f, 1f, 0.43f),
                    new HexSkyMesh(this, 69, 2.4f, 0.12f, 5, Color.valueOf("f7cba4").a(0.75f), 3, 0.7f, 1f, 0.45f)
            );

            alwaysUnlocked = true;
            accessible = true;
            allowLaunchToNumbered = false;
            allowLaunchLoadout = false;
            allowSectorInvasion = false;
            startSector = 15;
            allowWaveSimulation = true;
            clearSectorOnLose = true;
            allowWaves = true;
            prebuildBase = false;
            defaultCore = ArikothBlocks.coreSerenity;

            defaultEnv = Env.oxygen | Env.terrestrial | Env.groundOil;

            orbitSpacing = 1;
            drawOrbit = true;
            orbitRadius = 35;
            rotateTime = 12 * 60;

            atmosphereRadIn = 0;
            atmosphereRadOut = 0.3f;
            sectorSeed = 1204;
            bloom = false;
            visible = true;
            atmosphereColor = Color.valueOf("1c513aaa");
            iconColor = Color.valueOf("31ffa4");
            hasAtmosphere = true;

            ruleSetter = r -> {
                r.waveTeam = ArikothTeams.conquisitoris;
                r.showSpawns = true;
                r.defaultTeam = ArikothTeams.luxis;
                r.teams.get(r.waveTeam).rtsAi = false;

                r.weather.addAll(
                        new Weather.WeatherEntry(Weathers.sandstorm){{
                            always = true;
                        }}
                );

                if(r.mode() == Gamemode.attack || r.mode() == Gamemode.pvp){
                }
            };
        }};
        zealor = makeAsteroid("zealor", ArikothPlanets.arikoth, Blocks.ferricStoneWall, ArikothBlocks.weatheredRheniumWall, -2, 0.55f, 6, 1.3f, gen -> {
            gen.berylChance = 0.8f;
            gen.iceChance = 0f;
            gen.carbonChance = 0.01f;
            gen.max += 2;
        });
        hypheru = makeAsteroid("hypheru", ArikothPlanets.arikoth, Blocks.ferricStoneWall, ArikothBlocks.weatheredRheniumWall, 11, 0.55f, 12, 1.3f, gen -> {
            gen.berylChance = 0.8f;
            gen.iceChance = 0f;
            gen.carbonChance = 0.01f;
            gen.max += 2;
        });
    }
    private static Planet makeAsteroid(String name, Planet parent, Block base, Block tint, int seed, float tintThresh, int pieces, float scale, Cons<AsteroidGenerator> cgen){
        return new Planet(name, parent, 0.12f){{
            hasAtmosphere = false;
            updateLighting = false;
            sectors.add(new Sector(this, Ptile.empty));
            camRadius = 0.68f * scale;
            minZoom = 0.6f;
            drawOrbit = false;
            accessible = false;
            clipRadius = 2f;
            defaultEnv = Env.space;
            icon = "commandRally";
            generator = new AsteroidGenerator();
            cgen.get((AsteroidGenerator)generator);

            meshLoader = () -> {
                iconColor = tint.mapColor;
                Color tinted = tint.mapColor.cpy().a(1f - tint.mapColor.a);
                Seq<GenericMesh> meshes = new Seq<>();
                Color color = base.mapColor;
                Rand rand = new Rand(id + 2);

                meshes.add(new NoiseMesh(
                        this, seed, 2, radius, 2, 0.55f, 0.45f, 14f,
                        color, tinted, 3, 0.6f, 0.38f, tintThresh
                ));

                for(int j = 0; j < pieces; j++){
                    meshes.add(new MatMesh(
                            new NoiseMesh(this, seed + j + 1, 1, 0.022f + rand.random(0.039f) * scale, 2, 0.6f, 0.38f, 20f,
                                    color, tinted, 3, 0.6f, 0.38f, tintThresh),
                            new Mat3D().setToTranslation(Tmp.v31.setToRandomDirection(rand).setLength(rand.random(0.44f, 1.4f) * scale)))
                    );
                }

                return new MultiMesh(meshes.toArray(GenericMesh.class));
            };
        }};
    }
}