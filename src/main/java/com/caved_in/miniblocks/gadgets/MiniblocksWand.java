package com.caved_in.miniblocks.gadgets;

import com.caved_in.commons.game.gadget.ItemGadget;
import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.miniblocks.MiniBlocks;
import com.caved_in.miniblocks.menu.MiniblocksMenu;
import com.caved_in.miniblocks.players.Builder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MiniblocksWand extends ItemGadget {
    private static MiniBlocks plugin = MiniBlocks.getInstance();

    private static MiniblocksWand instance;

    public static MiniblocksWand getInstance() {
        if (instance == null) {
            instance = new MiniblocksWand();
        }

        return instance;
    }

    protected MiniblocksWand() {
        super(ItemBuilder.of(Material.BLAZE_ROD).name("&6Miniblocks Wand").lore("&eUsed to easily access the &6miniblocks", "&emenu without commands").item());
    }

    @Override
    public void perform(Player player) {
        Builder builder = plugin.getUser(player);

        //Open the menu for the player
        new MiniblocksMenu(builder.getPage()).openMenu(player);
    }

    @Override
    public int id() {
        return 1;
    }
}
