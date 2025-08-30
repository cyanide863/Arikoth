
package arikoth.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.geom.Vec2;
import arc.struct.*;
import arc.util.*;
import mindustry.game.Team;
import mindustry.ui.Fonts;
//yoinked code from FOS credits go to slotterleet.
public class ArikothTeams {
    public static Team luxis, conquisitoris, vagus;

    public static void load() {
        luxis = newTeam(3, "Luxis", Color.valueOf("#8875ff"),
                Color.valueOf("8875ff"),
                Color.valueOf("594dd0"),
                Color.valueOf("3524a3")
        );
        conquisitoris = newTeam(5, "Emperix", Color.valueOf("#a0c4de"),
                Color.valueOf("a0c4de"),
                Color.valueOf("7499ba"),
                Color.valueOf("495d75")
        );
        vagus = newTeam(4,"Vagus", Color.valueOf("#f1af4b"),
                Color.valueOf("f1af4b"),
                Color.valueOf("cc7f36"),
                Color.valueOf("a35224")
        );
    }

    //modify any of 256 teams' properties
    private static Team newTeam(int id, String name, Color color, Color c1, Color c2, Color c3) {
        Team team = Team.get(id);
        team.name = name;
        team.color.set(color);
        team.palette[0] = c1;
        team.palette[1] = c2;
        team.palette[2] = c3;

        for(int i = 0; i < 3; i++){
            team.palettei[i] = team.palette[i].rgba();
        }
                return team;
    }
}
