package dev.dan.mcutils.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import dev.dan.mcutils.item.ItemCreator;
import org.bukkit.Material;

public class BasicItems {
    public static GuiItem BLACK_BORDER = new GuiItem(new ItemCreator(Material.BLACK_STAINED_GLASS_PANE, " ").getStack(), event -> event.setCancelled(true));

}
