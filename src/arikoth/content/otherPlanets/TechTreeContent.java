
package arikoth.content.otherPlanets;

public class TechTreeContent {
    public static TechTreeNode placeholder,

    // Title
    ghereonIcon,

    // SubBranch - blockCategory
    production, crafters, defence, power,

    // SubBranch - turret
    turret, turretMG, turretSG, turretART, turretMSL

            // others
            ;

    public static void load() {
        ghereonIcon = new TechTreeNode("ghereon-icon");
    }
}
