package com.mycompany.programmingpt.model;

public class OrderItem {
    private MenuItem menuItem;
    private int qty;

    public MenuItem getMenu() {
        return menuItem;
    }

    public void setMenu(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void incrementQty() {
        this.qty++;
    }

    public double getSubTotal() {
        return menuItem.getPrice() * qty;
    }
}
