package dev.dan.mcutils.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import dev.dan.mcutils.item.ItemCreator;
import dev.dan.mcutils.utils.StringUtils;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class MultiPagePane extends SimpleStaticPane {

    public PaginatedPane paginatedPane;

    private int currentPane;

    private GuiItem back = new GuiItem(new ItemCreator(Material.RED_STAINED_GLASS_PANE, StringUtils.altColor("&c&lBack")).getStack());
    private GuiItem next = new GuiItem(new ItemCreator(Material.GREEN_STAINED_GLASS_PANE, StringUtils.altColor("&e&lNext")).getStack());

    public MultiPagePane(Slot slot, int length, int height, @NotNull Pane.Priority priority) {
        super(slot, length, height, priority);
        setup();
    }

    public MultiPagePane(int x, int y, int length, int height, @NotNull Pane.Priority priority) {
        super(x, y, length, height, priority);
        setup();
    }

    public MultiPagePane(Slot slot, int length, int height) {
        super(slot, length, height);
        setup();
    }

    public MultiPagePane(int x, int y, int length, int height) {
        super(x, y, length, height);
        setup();
    }

    public MultiPagePane(int length, int height) {
        super(length, height);
        setup();
    }


    public void addSubPane(Pane pane) {
        paginatedPane.addPage(pane);
    }


    private void setup() {
        paginatedPane = new PaginatedPane(length, height - 1);
        this.addSubPane(paginatedPane);

        back.setAction(event -> {
            currentPane++;
            paginatedPane.setPage(currentPane);
        });
        next.setAction(event -> {
            currentPane--;
            paginatedPane.setPage(currentPane);
        });

        this.addItem(back, 0, height);
        this.addItem(next, length, height);
    }

}
