package com.caved_in.miniblocks;

import com.caved_in.commons.game.players.IUserManager;
import com.caved_in.commons.plugin.BukkitPlugin;
import com.caved_in.miniblocks.command.Commands;
import com.caved_in.miniblocks.config.Miniblock;
import com.caved_in.miniblocks.gadgets.MiniblocksWand;
import com.caved_in.miniblocks.listener.PlayerConnectionListener;
import com.caved_in.miniblocks.menu.Pages;
import com.caved_in.miniblocks.players.Builder;
import org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;
import org.simpleframework.xml.Serializer;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MiniBlocks extends BukkitPlugin implements IUserManager<Builder> {
    private static MiniBlocks instance;
    public static int PREVIOUS_INDEX = 45;
    public static int NEXT_PAGE_INDEX = 53;

    private static final Map<UUID, Builder> users = new HashMap<>();

    public static final String blocksFolderLocation = "plugins/MiniBlocks/Blocks/";

    public static MiniBlocks getInstance() {
        return instance;
    }

    @Override
    public void startup() {
        instance = this;

        registerListeners(new PlayerConnectionListener());

        registerCommands(new Commands());

        registerGadgets(MiniblocksWand.getInstance());

        Pages.generateMenuItems();
    }

    @Override
    public void shutdown() {
        Pages.saveToDisk();
    }

    @Override
    public String getVersion() {
        return "1.0.0-ALPHA";
    }

    @Override
    public String getAuthor() {
        return "Brandon Curtis";
    }

    @Override
    public void initConfig() {
        File blockFolder = new File(blocksFolderLocation);

        if (!blockFolder.exists()) {
            if (!blockFolder.mkdirs()) {
                debug("Error creating blocks folder for Miniblocks");
                return;
            } else {
                debug("Created blocks folder for MiniBlocks");
            }
        }

        Collection<File> blockFiles = FileUtils.listFiles(blockFolder, null, false);
        if (blockFiles.size() == 0) {
            //TODO Create default miniblocks data
            debug("No miniblocks to load!");
            return;
        }

        Serializer serializer = getSerializer();

        for (File blockFile : blockFiles) {
            Miniblock miniblock = null;

            try {
                miniblock = serializer.read(Miniblock.class, blockFile);
                /* Add the miniblock to the pages; Used to track all blocks */
                Pages.addMiniblock(miniblock);
                debug("Loaded miniblock of " + miniblock.getOwner());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addUser(Player player) {
        users.put(player.getUniqueId(), new Builder(player));
    }

    @Override
    public Builder getUser(Player player) {
        return getUser(player.getUniqueId());
    }

    @Override
    public void removeUser(Player player) {
        removeUser(player.getUniqueId());
    }

    @Override
    public Builder getUser(UUID id) {
        return users.get(id);
    }

    public void removeUser(UUID id) {
        users.remove(id);
    }
}
