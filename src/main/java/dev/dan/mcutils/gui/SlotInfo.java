package dev.dan.mcutils.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;

public class SlotInfo {
    private int x;
    private int y;
    private GuiItem item;

    public SlotInfo(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setItem(GuiItem item){
        this.item = item;
    }


    public GuiItem getItem(){
        return this.item;
    }


    public int getX(){
        return this.x;
    }


    public int getY(){
        return this.y;
    }

}
