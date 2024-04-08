package dev.dan.mcutils.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import dev.dan.mcutils.item.ItemCreator;
import dev.dan.mcutils.utils.StringUtils;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class MultiPagePane extends SimpleStaticPane {

    public PaginatedPane paginatedPane;

    private int currentPane = 0;

    private ChestGui gui;

    int maxIndex = -1;

    private GuiItem back = new GuiItem(new ItemCreator(Material.RED_STAINED_GLASS_PANE, StringUtils.altColor("&c&lBack")).getStack());
    private GuiItem next = new GuiItem(new ItemCreator(Material.GREEN_STAINED_GLASS_PANE, StringUtils.altColor("&e&lNext")).getStack());

    public MultiPagePane(Slot slot, int length, int height, @NotNull Pane.Priority priority, ChestGui gui) {
        super(slot, length, height, priority);
        this.gui = gui;
        this.setup();
    }

    public MultiPagePane(int x, int y, int length, int height, @NotNull Pane.Priority priority, ChestGui gui) {
        super(x, y, length, height, priority);
        this.gui = gui;
        this.setup();
    }

    public MultiPagePane(Slot slot, int length, int height, ChestGui gui) {
        super(slot, length, height);
        this.gui = gui;
        this.setup();
    }

    public MultiPagePane(int x, int y, int length, int height, ChestGui gui) {
        super(x, y, length, height);
        this.gui = gui;
        this.setup();
    }

    public MultiPagePane(int length, int height, ChestGui gui) {
        super(length, height);
        this.gui = gui;
        this.setup();
    }


    public void addPage(Pane pane) {
        paginatedPane.addPage(pane);
        maxIndex++;
        if(paginatedPane.getPages() == 1) {
            paginatedPane.setPage(currentPane);
            gui.update();
        }
    }


    private void setup() {
        paginatedPane = new PaginatedPane(0, 0, this.length, this.height - 1);
        gui.addPane(paginatedPane);

        back.setAction(event -> {
            event.setCancelled(true);
            if(currentPane > 0)
                currentPane--;
            paginatedPane.setPage(currentPane);
            gui.update();
        });

        next.setAction(event -> {
            event.setCancelled(true);
            if(currentPane < maxIndex)
                currentPane++;
            paginatedPane.setPage(currentPane);
            gui.update();
        });
        addItem(back, 0, height-1);
        addItem(next, length-1, height-1);
        gui.update();
    }


    @Override
    public void setBackground(GuiItem item){
        super.setBackground(item);
        addItem(back, 0, height-1);
        addItem(next, length-1, height-1);
    }


    @Override
    public void addBorder(GuiItem item) {
        super.addBorder(item);
        addItem(back, 0, height-1);
        addItem(next, length-1, height-1);
    }

}
