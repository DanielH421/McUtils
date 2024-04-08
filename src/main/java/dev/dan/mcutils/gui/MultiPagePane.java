package dev.dan.mcutils.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import dev.dan.mcutils.item.ItemCreator;
import dev.dan.mcutils.utils.StringUtils;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiPagePane extends StaticPane {

    private HashMap<Integer, Pane> panes = new HashMap<>();

    private List<SlotInfo> reservedSlots = new ArrayList<>();

    private SlotInfo reservedBack = new SlotInfo(0, height);
    private SlotInfo reservedNext = new SlotInfo(length, height);

    private GuiItem back = new GuiItem(new ItemCreator(Material.RED_STAINED_GLASS_PANE, StringUtils.altColor("&c&lBack")).getStack());
    private GuiItem next = new GuiItem(new ItemCreator(Material.GREEN_STAINED_GLASS_PANE, StringUtils.altColor("&e&lNext")).getStack());

    public MultiPagePane(Slot slot, int length, int height, @NotNull Pane.Priority priority) {
        super(slot, length, height, priority);

        reservedBack.setItem(back);
        reservedNext.setItem(next);
        reservedSlots.add(reservedNext);
        reservedSlots.add(reservedBack);
    }

    public MultiPagePane(int x, int y, int length, int height, @NotNull Pane.Priority priority) {
        super(x, y, length, height, priority);

        reservedBack.setItem(back);
        reservedNext.setItem(next);
        reservedSlots.add(reservedNext);
        reservedSlots.add(reservedBack);
    }

    public MultiPagePane(Slot slot, int length, int height) {
        super(slot, length, height);

        reservedBack.setItem(back);
        reservedNext.setItem(next);
        reservedSlots.add(reservedNext);
        reservedSlots.add(reservedBack);
    }

    public MultiPagePane(int x, int y, int length, int height) {
        super(x, y, length, height);

        reservedBack.setItem(back);
        reservedNext.setItem(next);
        reservedSlots.add(reservedNext);
        reservedSlots.add(reservedBack);
    }

    public MultiPagePane(int length, int height) {
        super(length, height);

        reservedBack.setItem(back);
        reservedNext.setItem(next);
        reservedSlots.add(reservedNext);
        reservedSlots.add(reservedBack);
    }


    // TODO - Test
    // This has not been tested.
    public void setBackground(GuiItem item){
        for (int x = 0; x < this.length; x++)   {
            for (int y = 0; y < this.height; y++)   {
                // This part is kind of gross. Sorry.
                for(SlotInfo slotInfo : reservedSlots) {
                    if(x != slotInfo.getX() && y != slotInfo.getY())
                        this.addItem(item, x, y);
                }
            }
        }
    }


    public void addBorder(GuiItem item) {

        // Also kinda gross
        for (int x = 0; x < this.length; x++) {
            this.addItem(item, x, 0);
            for(SlotInfo slotInfo : reservedSlots) {
                if(x != slotInfo.getX() && y != slotInfo.getY())
                    this.addItem(item, x, this.height);
            }
        }

        for (int y = 0; y < this.height; y++) {
            this.addItem(item, 0, y);
            this.addItem(item, 9 - 1, y);
        }
    }
    
}
