package arikoth.ui;

import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arikoth.Arikoth;
import arikoth.content.ArikothTeams;
import mindustry.content.Blocks;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.world.Tile;
import arikoth.world.blocks.env.ACliff;
import arikoth.world.blocks.env.ACliffHelper;

import static mindustry.Vars.*;

//author @Andromeda

public class EditorUIModifier {
    public static void modify(){
        ui.editor.shown(() -> {
            Table cont = (Table) ui.editor.getChildren().get(0);
            Table mid = (Table) cont.getChildren().get(0);

            // Adds cliffs button
            mid.row();
            mid.table(t -> {
                t.button("@editor.arikoth-cliffs", Icon.terrain, Styles.flatt, EditorUIModifier::processCliffs).growX().margin(9f);
            }).growX().top();
        });
    }

    public static void addTeamButton(Table table, Team team){
        ImageButton button = new ImageButton(Tex.whiteui, Styles.clearNoneTogglei);
        button.margin(4f);
        button.getImageCell().grow();
        button.getStyle().imageUpColor = team.color;
        button.clicked(() -> editor.drawTeam = team);
        button.update(() -> button.setChecked(editor.drawTeam == team));
        table.add(button);
    }
    public static void processCliffs(){
        for(Tile tile : world.tiles){
            if(tile.block() instanceof ACliff cliff){
                cliff.process(tile);
            }
        }
        for(Tile tile : world.tiles){
            if(tile.block() instanceof ACliffHelper){
                tile.setNet(Blocks.air);
            }
        }
    }
}
