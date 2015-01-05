package com.caved_in.miniblocks.menu;

import com.caved_in.commons.chat.Chat;
import com.caved_in.miniblocks.MiniBlocks;
import com.caved_in.miniblocks.config.Miniblock;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Pages {
    private static List<List<MiniblockMenuItem>> menuPages = new ArrayList<>();
    private static Set<Miniblock> miniblocks = new HashSet<>();

    private static boolean hasChanged = false;

    public static void generateMenuItems() {
        if (!hasChanged) {
            return;
        }

        List<MiniblockMenuItem> menuItems = miniblocks.stream().map(m -> m.getMenuItem()).collect(Collectors.toList());

        //Create the individual pages of the menu by partitioning the pages into sliced-lists of 52 elements.
        menuPages = Lists.partition(menuItems, 52);

        hasChanged = false;
    }

    public static List<MiniblockMenuItem> getPage(int page) {
        if (page < 1) {
            page = 1;
        }
        return menuPages.get(page - 1);
    }

    public static int getPageCount() {
        return menuPages.size();
    }

    public static void addMiniblock(Miniblock miniblock) {
        miniblocks.add(miniblock);
        hasChanged = true;
    }

    public static void saveToDisk() {
        File blockFolder = new File(MiniBlocks.blocksFolderLocation);

        if (!blockFolder.exists()) {

            if (blockFolder.mkdirs()) {
                Chat.debug("Error creating blocks folder when saving to disk");
                return;
            }
        }

        /* Loop through all the miniblocks and save them to disk! */
        for (Miniblock block : miniblocks) {
            saveBlock(block);
        }
    }

    public static void saveBlock(Miniblock block) {
        Serializer serializer = MiniBlocks.getInstance().getSerializer();

        String blockFileLoc = String.format("%s%s.xml", MiniBlocks.blocksFolderLocation, block.getOwner());
        File blockFile = new File(blockFileLoc);

        try {
            serializer.write(block, blockFile);
            Chat.debug("Saved block " + block.getOwner() + " to file @ " + blockFileLoc + " !!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
