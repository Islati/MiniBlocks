package com.caved_in.miniblocks.menu;

import com.caved_in.commons.menu.MenuItem;
import com.caved_in.miniblocks.MiniBlocks;
import com.caved_in.miniblocks.config.Miniblock;
import com.caved_in.miniblocks.players.Builder;
import org.bukkit.entity.Player;

public class PageSwitchMenuItem extends MenuItem {
    private static MiniBlocks plugin = MiniBlocks.getInstance();

    public static enum Direction {
        PREVIOUS,
        NEXT
    }

    private int page;
    private Direction direction;

    public PageSwitchMenuItem(Direction direction, int page) {
        this.page = page;
        this.direction = direction;
        setText(direction == Direction.PREVIOUS ? "&aPrevious Page" : "&aNext Page");
    }

    @Override
    public void onClick(Player player) {
        Builder builder = plugin.getUser(player);

        int pagesCount = Pages.getPageCount();

        int nextPage = 0;
        if (direction == Direction.NEXT) {

            nextPage = page + 1;

            /*
            If they're cycling ahead and are already at the last page, then we'll
            send them to the first page.
             */
            if (nextPage >= pagesCount) {
                nextPage = 1;
            }
        } else {
            nextPage = page - 1;

            /*
            If they're cycling all the way back (from first page) then
            We're gonna go to the final page available.
            */
            if (nextPage <= 0) {
                nextPage = pagesCount;
            }
        }

        builder.setPage(nextPage);

        /* Switch the player to the next page. */
        getMenu().switchMenu(player, new MiniblocksMenu(nextPage));
    }
}
