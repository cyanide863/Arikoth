package arikoth;

import arc.*;
import arc.math.*;
import arc.util.*;
import arikoth.content.otherPlanets.*;
import arikoth.graphics.ArikothShaderf;
import arikoth.graphics.ArikothShaders;
import arikoth.graphics.EnvRenderer;
import mindustry.game.*;
import mindustry.mod.*;
import arikoth.content.*;
import arikoth.ui.*;

public class Arikoth extends Mod{

    public Arikoth(){
        Log.info("Its Arikoth Time!");

        Events.on(EventType.ClientLoadEvent.class, (event) -> {
            EditorUIModifier.modify();
        });
    }

    @Override
    public void loadContent(){
        ArikothShaders.load();
        ArikothInnerBlocks.load();
        ArikothTeams.load();
        ArikothSounds.load();
        ArikothAttribute.load();
        ArikothLiquids.load();
        ArikothItems.load();
        ArikothUnitTypes.load();
        ArikothBlocks.load();
        ArikothPlanets.load();
        ArikothSectors.load();
        ArikothTechTree.load();

        VanillaStatusEffects.load();
        SerpuloItems.load();
        SerpuloBlocks.load();
        TechTreeContent.load();
        GhereonTech.load();

        EnvRenderer.init();
    }

}