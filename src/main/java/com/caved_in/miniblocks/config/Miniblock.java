package com.caved_in.miniblocks.config;

import com.caved_in.commons.config.XmlItemStack;
import com.caved_in.commons.item.Items;
import com.caved_in.miniblocks.menu.MiniblockMenuItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "mini-block")
public class Miniblock {

    @Element(name = "item", type = XmlItemStack.class)
    private XmlItemStack item;

    @Element(name = "menu-title")
    private String title;

    @Element(name = "menu-description", required = false)
    private String description;

    private MiniblockMenuItem menuItem;

    public Miniblock(@Element(name = "item", type = XmlItemStack.class) XmlItemStack item, @Element(name = "menu-title") String title, @Element(name = "menu-description", required = false) String description) {
        this.item = item;
        this.title = title;
        this.description = description;
    }

    public Miniblock(String owner, String title, String description) {
        this.item = XmlItemStack.fromItem(Items.addLore(Items.getSkull(owner), description));
        this.title = title;
        this.description = description;
    }

    public ItemStack getSkull() {
        return item.getItemStack();
    }

    public MaterialData getMaterialData() {
        return item.getItemStack().getData();
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getOwner() {
        return item.getSkullOwner();
    }

    public MiniblockMenuItem getMenuItem() {
        if (menuItem == null) {
            menuItem = new MiniblockMenuItem(this);
        }
        return menuItem;
    }
}
