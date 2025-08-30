package arikoth.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.*;
import mindustry.Vars;

public class ArikothSounds {

    public static Sound
            laserShoot = new Sound(),
            weakGun = new Sound(),
            weakGunTwo = new Sound(),
            rotorWhir = new Sound(),
            smallShot = new Sound();


    public static void load(){
        laserShoot = loadSound("laserShoot");
        weakGun = loadSound("weakGun");
        weakGunTwo = loadSound("weakGunTwo");
        rotorWhir = loadSound("rotorWhir");
        smallShot = loadSound("smallShot");
    }


    public static Sound loadSound(String soundName){
        //taken from Omaloon, please support this mod -> https://github.com/xstabux/Omaloon/blob/master/src/omaloon/content/OlSounds.java
        //making sure it doesn't load serverside
        if(!Vars.headless) {
            String name = "sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        } else {
            return new Sound();
        }
    }
}