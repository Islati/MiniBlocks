package com.caved_in.miniblocks.listener;

import com.caved_in.miniblocks.MiniBlocks;
import com.caved_in.miniblocks.config.Miniblock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {
    private static MiniBlocks plugin = MiniBlocks.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.addUser(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        plugin.removeUser(e.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        plugin.removeUser(e.getPlayer());
    }
}
