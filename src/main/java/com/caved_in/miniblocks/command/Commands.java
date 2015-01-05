package com.caved_in.miniblocks.command;

import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.command.Arg;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.command.Wildcard;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.menu.ItemFormat;
import com.caved_in.commons.menu.Menus;
import com.caved_in.commons.menu.PageDisplay;
import com.caved_in.commons.player.Players;
import com.caved_in.miniblocks.MiniBlocks;
import com.caved_in.miniblocks.config.Miniblock;
import com.caved_in.miniblocks.gadgets.MiniblocksWand;
import com.caved_in.miniblocks.menu.MiniblocksMenu;
import com.caved_in.miniblocks.menu.Pages;
import com.caved_in.miniblocks.players.Builder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Commands {
    private static MiniBlocks plugin = MiniBlocks.getInstance();

    private HelpScreen help = Menus.generateHelpScreen("Miniblocks Help", PageDisplay.DEFAULT, ItemFormat.SINGLE_DASH, ChatColor.YELLOW, ChatColor.GOLD);

    @Command(identifier = "miniblocks", onlyPlayers = true)
    public void onMiniblocksCommand(Player p) {
        Builder builder = plugin.getUser(p);

        int lastPage = builder.getPage();
        new MiniblocksMenu(lastPage).openMenu(p);
    }

    @Command(identifier = "miniblocks add", description = "Add a new miniblock to the selection!")
    public void onMiniblocksAddCommand(Player p, @Arg(name = "player-name", description = "Owner of the skin to make a miniblock of") String name, @Arg(name = "title", description = "What the miniblock will be shown as in the menu") String title) {
        Miniblock block = new Miniblock(name, title, "");

        /* Add the block to the list of blocks */
        Pages.addMiniblock(block);
        /* Save it to a file, for later loading! */
        Pages.saveBlock(block);
        /* And generate the menu pages; Just to be safe! */
        Pages.generateMenuItems();
        Chat.message(p, String.format("&aYou've added &e%s&a's skull as a miniblock!", name));
    }

    @Command(identifier = "miniblocks ?", description = "Display Miniblocks help menu")
    public void onMiniblocksHelpCommand(Player p, @Arg(name = "page") int page) {
        help.sendTo(p, page, 7);
    }

    @Command(identifier = "miniblocks wand", description = "Give yourself the miniblocks wand")
    public void onMiniblocksWandCommand(Player p) {
        Players.giveItem(p, MiniblocksWand.getInstance().getItem());
    }
}
