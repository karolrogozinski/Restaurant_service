package com.example.desktop.ui;

import com.example.desktop.entities.Order;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Class creating and facilitating view of panel enabling us to
 * f. e. see placed order or order in progress.
 */
public class ItemView {

    private final JPanel panel;
    private final JLabel label;
    private final JButton button1;
    private JButton button2;

    private Order order;

    public ItemView() {

        Border blackLine = BorderFactory.createLineBorder(Color.black);

        panel = new JPanel();
        panel.setBorder(blackLine);
        panel.setPreferredSize(new Dimension(200, 28));
        panel.setMaximumSize(new Dimension(5000, 28));
        panel.setMinimumSize(new Dimension(100, 28));
        panel.setLayout(null);

        label = new JLabel();
        label.setBounds(25, 0, 250, 28);

        button1 = new JButton();
        button1.setBounds(-300, 0, 90, 28);

        panel.add(label);
        panel.add(button1);
    }

    public ItemView(String buttonName) {

        Border blackLine = BorderFactory.createLineBorder(Color.black);

        panel = new JPanel();
        panel.setBorder(blackLine);
        panel.setPreferredSize(new Dimension(200, 28));
        panel.setMaximumSize(new Dimension(5000, 28));
        panel.setMinimumSize(new Dimension(100, 28));
        panel.setLayout(new BorderLayout());

        label = new JLabel();
        label.setBounds(25, 0, 250, 28);

        button1 = new JButton(buttonName);
        button1.setPreferredSize(new Dimension(90, 28));
        button1.setBounds(200, 0, 90, 28);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 0));
        buttons.add(button1);

        panel.add(label);
        panel.add(buttons, BorderLayout.LINE_END);
    }

    public ItemView(String firstButtonName, String secondButtonName) {

        Border blackLine = BorderFactory.createLineBorder(Color.black);

        panel = new JPanel();
        panel.setBorder(blackLine);
        panel.setPreferredSize(new Dimension(200, 28));
        panel.setMaximumSize(new Dimension(5000, 28));
        panel.setMinimumSize(new Dimension(100, 28));
        panel.setLayout(new BorderLayout());

        label = new JLabel();
        label.setBounds(25, 0, 250, 28);

        button1 = new JButton(firstButtonName);
        button1.setPreferredSize(new Dimension(90, 28));

        button2 = new JButton(secondButtonName);
        button2.setPreferredSize(new Dimension(90, 28));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 0));
        buttons.add(button1);
        buttons.add(button2);

        panel.add(label);
        panel.add(buttons, BorderLayout.LINE_END);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
