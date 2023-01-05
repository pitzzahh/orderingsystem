package com.mycompany.programmingpt.components;

import com.mycompany.programmingpt.model.MenuItem;

import javax.swing.*;

public class MenuItemButton extends JButton {
    private final MenuItem menuItem;

    public MenuItemButton(MenuItem menuItem) {
        super(menuItem.getShortName() + " - " + menuItem.getFormattedPrice());
        setToolTipText(menuItem.getName() + " - " + menuItem.getFormattedPrice());
        this.menuItem = menuItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
