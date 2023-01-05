package com.mycompany.programmingpt.components;

import com.mycompany.programmingpt.model.MenuItem;
import com.mycompany.programmingpt.model.OrderItem;
import com.mycompany.programmingpt.util.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderFrame extends JFrame {
    private Map<Integer, OrderItem> menuItemIdToOrderItemMap = new HashMap<>();
    private List<MenuItem> menuItems;
    private Object[][] orderItemsData = {
        {"foo", "2x", "200.00"},
        {"bar", "2x", "200.00"},
    };

    public MyOrderFrame() {
        create();
        setSize(1000, 500);
        setTitle("Tapsihan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void create() {
        Container container = getContentPane();
        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        container.add(top, BorderLayout.NORTH);
        top.add(new JLabel("Cashier Name: Foo Bar"));
        JButton logout = new JButton("Logout");
        top.add(logout);
        JPanel center = new JPanel(new GridLayout(0, 3));
        container.add(center, BorderLayout.CENTER);
        center.add(getMenuItemButtons());
        center.add(getOrderItems());
    }

    private Component getMenuItemButtons() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Menu"));
        panel.setLayout(new GridLayout(0, 2));
        menuItems = DbUtils.getMenuItems();
        for (MenuItem menuItem : menuItems) {
            String text = menuItem.getName() + " - " + menuItem.getFormattedPrice();
            JButton menuItemBtn = new JButton(menuItem.getShortName() + " - " + menuItem.getFormattedPrice());
            menuItemBtn.setPreferredSize(new Dimension(150, 50));
            menuItemBtn.setToolTipText(text);
            menuItemBtn.addActionListener(e -> {
                System.out.println(menuItem.getName() + " " + menuItem.getPrice());
                OrderItem orderItem = new OrderItem();
                orderItem.setMenu(menuItem);
                orderItem.setQty(0);
                menuItemIdToOrderItemMap.putIfAbsent(menuItem.getId(), orderItem);
                menuItemIdToOrderItemMap.get(menuItem.getId()).incrementQty();
                refreshTableData();
                table.setModel(new DefaultTableModel());
            });
            panel.add(menuItemBtn);
        }

        return panel;
    }

    private JTable table;

    private Component getOrderItems() {
        table = new JTable(orderItemsData, new String[] {"Item", "Qty", "Subtotal"});
        JScrollPane pane = new JScrollPane(table);
        pane.setBorder(BorderFactory.createTitledBorder("Order"));
        return pane;
    }

    private void refreshTableData() {
        String[][] data = new String[4][3];
        int i = 0;
        for (OrderItem orderItem : menuItemIdToOrderItemMap.values()) {
            data[i++] = new String[]{orderItem.getMenu().getName(), orderItem.getQty() + "x", String.valueOf(orderItem.getSubTotal())};
        }
        orderItemsData = data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyOrderFrame());
    }
}
