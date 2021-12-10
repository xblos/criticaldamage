package net.xblos.crit.util;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LootTableHelper {

    public enum Chest {
        ABANDONED_MINESHAFT("abandoned_mineshaft"),
        BURIED_TREASURE("buried_treasure"),
        BASTION_BRIDGE("bastion_bridge"),
        BASTION_HOGLIN_STABLE("bastion_hoglin_stable"),
        BASTION_OTHER("bastion_other"),
        BASTION_TREASURE("bastion_treasure"),
        DESERT_PYRAMID("desert_pyramid"),
        END_CITY_TREASURE("end_city_treasure"),
        IGLOO_CHEST("igloo_chest"),
        JUNGLE_TEMPLE("jungle_temple"),
        JUNGLE_TEMPLE_DISPENSER("jungle_temple_dispenser"),
        NETHER_BRIDGE("nether_bridge"),
        PILLAGER_OUTPOST("pillager_outpost"),
        RUINED_PORTAL("ruined_portal"),
        SHIPWRECK_MAP("shipwreck_map"),
        SHIPWRECK_SUPPLY("shipwreck_supply"),
        SHIPWRECK_TREASURE("shipwreck_treasure"),
        SIMPLE_DUNGEON("simple_dungeon"),
        SPAWN_BONUS_CHEST("spawn_bonus_chest"),
        STRONGHOLD_CORRIDOR("stronghold_corridor"),
        STRONGHOLD_CROSSING("stronghold_crossing"),
        STRONGHOLD_LIBRARY("stronghold_library"),
        UNDERWATER_RUIN_BIG("underwater_ruin_big"),
        UNDERWATER_RUIN_SMALL("underwater_ruin_small"),
        WOODLAND_MANSION("woodland_mansion"),

        VILLAGE_ARMORER("village_armorer"),
        VILLAGE_BUTCHER("village_butcher"),
        VILLAGE_CARTOGRAPHER("village_cartographer"),
        VILLAGE_DESERT_HOUSE("village_desert_house"),
        VILLAGE_FISHER("village_fisher"),
        VILLAGE_FLETCHER("village_fletcher"),
        VILLAGE_MASON("village_mason"),
        VILLAGE_PLAINS_HOUSE("village_plains_house"),
        VILLAGE_SAVANNA_HOUSE("village_savanna_house"),
        VILLAGE_SHEPHERD("village_shepherd"),
        VILLAGE_SNOWY_HOUSE("village_snowy_house"),
        VILLAGE_TAIGA_HOUSE("village_taiga_house"),
        VILLAGE_TANNERY("village_tannery"),
        VILLAGE_TEMPLE("village_temple"),
        VILLAGE_TOOLSMITH("village_toolsmith"),
        VILLAGE_WEAPONSMITH("village_weaponsmith");

        private final Identifier id;

        private static final Identifier[] identifiers = Arrays.stream(Chest.values())
            .map(Chest::getId)
            .toArray(Identifier[]::new);

        Chest(String name) {
            id = chestLootId(name);
        }

        public Identifier getId() {
            return id;
        }

        public static Identifier[] identifiers() {
            return identifiers;
        }
    }

    private static class Loot {
        public Item item;
        public LootNumberProvider provider;
        public Identifier[] tableIds;

        public Loot(Item item, LootNumberProvider provider, Identifier[] tableIds) {
            this.item = item;
            this.provider = provider;
            this.tableIds = tableIds;
        }
    }

    private final List<Loot> loots = new ArrayList<>();

    public void registerLoot() {
        LootTableLoadingCallback.EVENT.register(this::doRegisterLoot);
    }

    public void addToAllChests(Item item, LootNumberProvider provider) {
        loots.add(new Loot(item, provider, Chest.identifiers()));
    }

    private void doRegisterLoot(
        ResourceManager resourceManager,
        LootManager manager,
        Identifier id,
        FabricLootSupplierBuilder supplier,
        LootTableLoadingCallback.LootTableSetter setter
    ) {
        for (Loot loot : loots) {
            for (Identifier lootTableId : loot.tableIds) {
                if (id.equals(lootTableId)) {
                    supplier.withPool(
                        FabricLootPoolBuilder.builder()
                            .rolls(loot.provider)
                            .withEntry(ItemEntry.builder(loot.item).build())
                            .build()
                    );
                }
            }
        }
    }

    private static Identifier chestLootId(String chestId) {
        return new Identifier("minecraft", "chests/" + chestId);
    }
}
