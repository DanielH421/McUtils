package dev.dan.mcutils.gui.pane;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import dev.dan.mcutils.item.ItemCreator;
import dev.dan.mcutils.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class MultiPagePane extends SimpleStaticPane {
    public PaginatedPane paginatedPane;
    private int currentPane = 0;
    private final ChestGui gui;
    int maxIndex = -1;
    private final GuiItem back = new GuiItem(new ItemCreator(Material.RED_STAINED_GLASS_PANE, StringUtils.altColor("&c&lBack")).getStack());
    private final GuiItem next = new GuiItem(new ItemCreator(Material.GREEN_STAINED_GLASS_PANE, StringUtils.altColor("&e&lNext")).getStack());


    public MultiPagePane(Slot slot, int length, int height, @NotNull ChestGui gui) {
        super(slot, length, height);
        this.gui = gui;
        setup();
    }

    public MultiPagePane(int x, int y, int length, int height, @NotNull ChestGui gui) {
        super(x, y, length, height);
        this.gui = gui;
        setup();
    }


    /**
     * Adds a page to the MultiPagePane.
     *
     * @param pane the Pane to be added as a page
     * @throws IllegalArgumentException if the pane parameter is null
     */
    public void addPage(Pane pane) {
        if (pane == null) throw new IllegalArgumentException("Pane cannot be null");
        paginatedPane.addPage(pane);
        maxIndex++;
        if (paginatedPane.getPages() == 1) {
            paginatedPane.setPage(currentPane);
            gui.update();
        }
    }

    /**
     * Set up the MultiPagePane by creating the PaginatedPane and adding it to the GUI,
     * setting up the back and next buttons, and updating the GUI. This method is private
     * and does not return any value.
     */
    private void setup() {
        paginatedPane = new PaginatedPane(0, 0, this.length, this.height - 1);
        gui.addPane(paginatedPane);
        back.setAction(event -> resetPane(event, -1));
        next.setAction(event -> resetPane(event, 1));
        addItem(back, 0, height - 1);
        addItem(next, length - 1, height - 1);
        gui.update();
    }

    /**
     * Resets the pane to display the previous or next page based on the direction.
     *
     * @param event     the cancellation event
     * @param direction the direction to move the page (-1 for previous, 1 for next)
     */
    private void resetPane(Cancellable event, int direction) {
        event.setCancelled(true);
        if ((direction == -1 && currentPane > 0) || (direction == 1 && currentPane < maxIndex)) {
            currentPane += direction;
            paginatedPane.setPage(currentPane);
            gui.update();
        }
    }

    @Override
    public void setBackground(GuiItem item) {
        super.setBackground(item);
        addItem(back, 0, height - 1);
        addItem(next, length - 1, height - 1);
    }

    @Override
    public void setBorder(GuiItem item) {
        super.setBorder(item);
        addItem(back, 0, height - 1);
        addItem(next, length - 1, height - 1);
    }
}
