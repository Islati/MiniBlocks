package com.caved_in.miniblocks.menu;

import com.caved_in.commons.menu.MenuItem;
import com.caved_in.commons.player.Players;
import com.caved_in.miniblocks.MiniBlocks;
import com.caved_in.miniblocks.config.Miniblock;
import org.bukkit.entity.Player;

public class MiniblockMenuItem extends MenuItem {

    private Miniblock miniblock;

    public MiniblockMenuItem(Miniblock block) {
        super(block.getTitle(), block.getMaterialData());
        this.miniblock = block;

        if (block.getDescription() != null) {
            setDescriptions(block.getDescription());
        }
    }

    @Override
    public void onClick(Player player) {
        Players.giveItem(player, miniblock.getSkull());
    }
}
