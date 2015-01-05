package com.caved_in.miniblocks.players;

import com.caved_in.commons.player.User;
import org.bukkit.entity.Player;

public class Builder extends User {

    private int page = 1;

    public Builder(Player p) {
        super(p);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
