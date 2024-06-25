package dev.dan.mcutils.gui.pane;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;

public class SimpleStaticPane extends StaticPane {

    public SimpleStaticPane(Slot slot, int length, int height) {
        super(slot, length, height);
    }

    public SimpleStaticPane(int x, int y, int length, int height) {
        super(x, y, length, height);
    }


    /**
     * Sets the background of the pane with the specified GUI item.
     *
     * @param item the GUI item to use as the background
     */
    public void setBackground(GuiItem item) {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                addItem(item, x, y);
            }
        }
    }

    /**
     * Sets the border of a GUI item around the edges of the pane.
     *
     * @param item the GUI item to use as the border
     */
    public void setBorder(GuiItem item) {
        for (int i = 0; i < length; i++) {
            addItem(item, i, 0);
            addItem(item, i, height - 1);
        }
        for (int j = 0; j < height; j++) {
            addItem(item, 0, j);
            addItem(item, length - 1, j);
        }
    }
}
