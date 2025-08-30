
package arikoth.world.blocks;

import arc.Core;
import arc.func.Cons;
import arc.func.Func;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.PayloadSeq;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.consumers.Consume;
import mindustry.world.meta.BuildVisibility;
import arikoth.world.blocks.*;

/**
 * Inner building that are linked to a specific building.
 * Handle items, liquids, damage and so on a passed to the main building to handle.
 * NEVER SUPPOSED TO USE OUTSIDE MULTI BLOCK!
 */
//author @Lao_Hua-Ji
public class LinkBlock extends Block {
    public LinkBlock(String name) {
        super(name);

        update = true;
        squareSprite = false;

        destructible = true;
        breakable = false;
        solid = true;
        rebuildable = false;
        canOverdrive = false;

        instantDeconstruct = true;

        hasItems = true;
        hasLiquids = true;
        hasPower = false;

        buildVisibility = BuildVisibility.hidden;
        inEditor = false;

        drawCracks = false;
        drawArrow = false;
        drawTeamOverlay = false;

        ambientSound = Sounds.none;
        breakSound = Sounds.none;
        destroySound = Sounds.none;
        placeSound = Sounds.none;

        acceptsItems = true;
        acceptsPayload = true;
        acceptsUnitPayloads = true;
        outputsPayload = false;

        localizedName = Core.bundle.get(getContentType() + ".new-horizon-inner-entity.name", this.name);
        description = Core.bundle.getOrNull(getContentType() + ".new-horizon-inner-entity.description");
        details = Core.bundle.getOrNull(getContentType() + ".new-horizon-inner-entity.details");
    }

    public boolean canBreak(Tile tile) {
        return false;
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public void load() {
        super.load();
        region = Core.atlas.find("status-blasted");
    }

    @Override
    public void loadIcon() {
        fullIcon = uiIcon = Core.atlas.find("status-blasted");
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class LinkBuild extends Building {
        public Building linkBuild;

        public void updateLink(Building link) {
            if (link instanceof MultiBlockEntity) {
                linkBuild = link;
                items = link.items;
                liquids = link.liquids;
                //might not a good idea if do so
                //block = link.block;
            } else {
                linkBuild = null;
                tile.remove();
            }
        }

        @Override
        public void updateTile() {
            if (linkBuild == null || !linkBuild.isValid()) {
                kill();
            }
        }

        //skip draw
        @Override
        public void draw() {
        }

        @Override
        public void drawSelect() {
            if (linkBuild != null) {
                linkBuild.drawSelect();
            }
        }

        @Override
        public TextureRegion getDisplayIcon() {
            return linkBuild == null ? this.block.uiIcon : linkBuild.getDisplayIcon();
        }

        @Override
        public String getDisplayName() {
            String name = linkBuild == null ? this.block.localizedName : linkBuild.block.localizedName;
            return this.team == Team.derelict ? name + "\n" + Core.bundle.get("block.derelict") : name + (this.team != Vars.player.team() && !this.team.emoji.isEmpty() ? " " + this.team.emoji : "");
        }

        @Override
        public void displayBars(Table table) {
            if (linkBuild != null) {
                for (Func<Building, Bar> buildingBarFunc : linkBuild.block.listBars()) {
                    Bar result = buildingBarFunc.get(linkBuild);
                    if (result != null) {
                        table.add(result).growX();
                        table.row();
                    }
                }
            }
        }

        @Override
        public void displayConsumption(Table table) {
            if (linkBuild != null) {
                table.left();
                for (Consume cons : linkBuild.block.consumers) {
                    if (cons.optional && cons.booster) continue;
                    cons.build(linkBuild, table);
                }
            }
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            return linkBuild != null && linkBuild.acceptItem(source, item);
        }

        @Override
        public int acceptStack(Item item, int amount, Teamc source) {
            if (linkBuild != null) {
                return linkBuild.acceptStack(item, amount, source);
            } else {
                return 0;
            }
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            return linkBuild != null && linkBuild.acceptLiquid(source, liquid);
        }

        @Override
        public boolean acceptPayload(Building source, Payload payload) {
            return linkBuild != null && linkBuild.acceptPayload(source, payload);
        }

        @Override
        public float handleDamage(float amount) {
            if (linkBuild != null) {
                return linkBuild.handleDamage(amount);
            } else {
                return 0;
            }
        }

        @Override
        public void damage(float damage) {
            if (linkBuild != null) {
                linkBuild.damage(damage);
            }
        }

        @Override
        public void handleItem(Building source, Item item) {
            if (linkBuild != null) {
                linkBuild.handleItem(source, item);
            }
        }

        @Override
        public void handleStack(Item item, int amount, Teamc source) {
            if (linkBuild != null) {
                linkBuild.handleStack(item, amount, source);
            }
        }

        @Override
        public void handleLiquid(Building source, Liquid liquid, float amount) {
            if (linkBuild != null) {
                linkBuild.handleLiquid(source, liquid, amount);
            }
        }

        @Override
        public void handlePayload(Building source, Payload payload) {
            if (linkBuild != null) {
                linkBuild.handlePayload(source, payload);
            }
        }

        @Override
        public void handleString(Object value) {
            if (linkBuild != null) {
                linkBuild.handleString(value);
            }
        }

        @Override
        public Payload getPayload() {
            return linkBuild == null ? null : linkBuild.getPayload();
        }

        @Override
        public Payload takePayload() {
            return linkBuild == null ? null : linkBuild.takePayload();
        }

        @Override
        public PayloadSeq getPayloads() {
            return linkBuild == null ? null : linkBuild.getPayloads();
        }

        @Override
        public void handleUnitPayload(Unit unit, Cons<Payload> grabber) {
            if (linkBuild != null) {
                linkBuild.handleUnitPayload(unit, grabber);
            }
        }

        @Override
        public void onProximityUpdate() {
            if (linkBuild != null) ((MultiBlockEntity) linkBuild).updateLinkProximity();
            super.onProximityUpdate();
        }

        @Override
        public void onDestroyed() {
        }

        @Override
        public void remove() {
            super.remove();
        }

        @Override
        public boolean canPickup() {
            return false;
        }
    }
}
