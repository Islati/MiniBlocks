package com.caved_in.miniblocks.menu;

import com.caved_in.commons.menu.ItemMenu;
import com.caved_in.commons.menu.Menus;
import com.caved_in.miniblocks.MiniBlocks;

import java.util.List;

public class MiniblocksMenu extends ItemMenu {
    private static int START_INDEX = 0;
    private static int END_INDEX = 54;

    public MiniblocksMenu(int page) {
        super(String.format("&cBlock Selection &e(Page %s)", page), Menus.getRows(END_INDEX));

        setExitOnClickOutside(true);

        //Add the next-page menu item at the bottom right slot
        addMenuItem(new PageSwitchMenuItem(PageSwitchMenuItem.Direction.NEXT, page), MiniBlocks.NEXT_PAGE_INDEX);

        //Add the previous-page menu item at the bottom left slot
        addMenuItem(new PageSwitchMenuItem(PageSwitchMenuItem.Direction.PREVIOUS, page), MiniBlocks.PREVIOUS_INDEX);

        List<MiniblockMenuItem> items = Pages.getPage(page);

        int index = 0;

        boolean pastPrevious = false;
        for (MiniblockMenuItem item : items) {
            if (index > MiniBlocks.PREVIOUS_INDEX && !pastPrevious) {
                index++;
                pastPrevious = true;
            }

            if (index == MiniBlocks.NEXT_PAGE_INDEX) {
                break;
            }

            addMenuItem(item, index);
            index++;
        }
    }
}
