package com.example.desktop.ui;

import com.example.desktop.App;
import com.example.desktop.entities.*;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GUI {
    private Vector<Cook> vecCooks = new Vector<Cook>();
    private List<Recipe> allRecipes;

    private final JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final JPanel panelTop = new JPanel();
    private final Border blackline = BorderFactory.createLineBorder(Color.black);
    private JPanel scrollablePanel = new JPanel();
    private JScrollPane scrollFrame = new JScrollPane(scrollablePanel);
    private JPanel scrollablePanelRight = new JPanel();
    private JScrollPane scrollFrameRight = new JScrollPane(scrollablePanelRight);
    private JButton buttonCooks = new JButton("Cooks");
    private JButton buttonRecipes = new JButton("Recipes");

    public void setButtonCooks(){
        buttonCooks.setBounds(1, 1, 100, 23);
        panelTop.add(buttonCooks);
        buttonCooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonCooksOnClick();
            }
        });
    }

    public void setButtonRecipes(){
        buttonRecipes.setBounds(101, 1, 100, 23);
        panelTop.add(buttonRecipes);
        buttonRecipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllRecipes();
            }
        });
    }

    public void displayAllRecipes(){                                //////////!!!!!!!!!!!!!!!!!!!!!!!!
        JFrame frameAllRecipes = new JFrame("All recipes");
        //JLabel labelTitle = new JLabel("Recipes");
        //frameAllRecipes.add(labelTitle);
        JPanel panelRecipes = new JPanel();
        List<Recipe> undividedRecipes = getUndividedRecipes();

        for (Recipe recipe : undividedRecipes){
            JButton buttonDisplayRecipe = new JButton(Integer.toString(recipe.getDishId()));
            buttonDisplayRecipe.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frameCurrRecipe = new JFrame("Current recipe id: " + Integer.toString(recipe.getDishId()));
                    JTextField field = new JTextField();
                    field.setText(recipe.getRecipe());

                    frameCurrRecipe.add(field);

                    frameCurrRecipe.setMinimumSize(new Dimension(300, 300));
                    frameCurrRecipe.setResizable(false);
                    frameCurrRecipe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    frameCurrRecipe.pack();
                    frameCurrRecipe.setLocationRelativeTo(null);
                    frameCurrRecipe.setVisible(true);
                }
            });
            panelRecipes.add(buttonDisplayRecipe);
        }
        frameAllRecipes.add(panelRecipes);

        frameAllRecipes.setMinimumSize(new Dimension(500, 500));
        frameAllRecipes.setResizable(false);
        frameAllRecipes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAllRecipes.pack();
        frameAllRecipes.setLocationRelativeTo(null);
        frameAllRecipes.setVisible(true);
    }

    public void buttonCooksOnClick() {
        JFrame frameCooks = new JFrame("Cooks");
        JPanel panelCooks = new JPanel();
        JPanel panelOptions = new JPanel();
        JButton buttonAdd = new JButton("Add cook");
        JButton buttonRemove = new JButton("Remove cook");
        JPanel panelTitle = new JPanel();
        JLabel labelTitle = new JLabel("Current Cooks: ");
        JPanel scrollableCooks = new JPanel();
        JScrollPane scrollCooks = new JScrollPane(scrollableCooks);

        scrollCooks.setPreferredSize(new Dimension(300, 240));
        scrollableCooks.setLayout(new BoxLayout(scrollableCooks, BoxLayout.Y_AXIS));
        scrollableCooks.setAutoscrolls(true);

        panelTitle.setBackground(Color.DARK_GRAY);
        panelTitle.setBounds(1, 1, 300, 30);
        labelTitle.setForeground(Color.WHITE);
        panelTitle.add(labelTitle);
        panelCooks.add(panelTitle);
        panelCooks.add(scrollCooks);

        buttonAdd.setBounds(1, 1, 150, 24);
        panelOptions.add(buttonAdd);

        buttonRemove.setBounds(150, 1, 150, 24);
        panelOptions.add(buttonRemove);

        panelCooks.setBorder(blackline);
        panelCooks.setPreferredSize(new Dimension(300, 30));
        panelCooks.setLayout(new BoxLayout(panelCooks, BoxLayout.Y_AXIS));

        panelOptions.setBorder(blackline);
        panelOptions.setPreferredSize(new Dimension(300, 25));
        panelOptions.setBackground(Color.GRAY);
        panelOptions.setLayout(null);

        frameCooks.setMinimumSize(new Dimension(300, 300));
        frameCooks.setResizable(false);
        frameCooks.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameCooks.add(panelOptions, BorderLayout.NORTH);
        frameCooks.add(panelCooks, BorderLayout.LINE_START);

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameAdd = new JFrame("Add cook");
                JTextField textCookName = new JTextField("name");
                JButton buttonAddCook = new JButton("Add");

                textCookName.setPreferredSize(new Dimension(130, 50));
                textCookName.setBorder(blackline);

                buttonAddCook.setPreferredSize(new Dimension(70, 50));
                buttonAddCook.setBorder(blackline);

                frameAdd.setPreferredSize(new Dimension(200, 50));
                frameAdd.setResizable(false);
                frameAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frameAdd.add(textCookName, BorderLayout.LINE_START);
                frameAdd.add(buttonAddCook, BorderLayout.LINE_END);

                buttonAddCook.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int cooksNumber = vecCooks.size();
                        int cookId;
                        if (cooksNumber == 0)
                            cookId = 1;
                        else {
                            int lastCookId = vecCooks.get(cooksNumber - 1).getIdInt();
                            cookId = lastCookId + 1;
                        }
                        String cookName = textCookName.getText();
                        Cook cook = new Cook(cookId, cookName);
                        vecCooks.add(cook);

                        Vector<JPanel> cooks = new Vector<JPanel>();

                        for (Cook c : vecCooks){
                            JPanel panelCook = new JPanel( new FlowLayout(FlowLayout.LEFT));
                            panelCook.setBorder(blackline);
                            panelCook.setMinimumSize(new Dimension(300, 30));
                            panelCook.setMaximumSize(new Dimension(300, 30));

                            JLabel labelCookDescription = new JLabel("Id: " + Integer.toString(c.getIdInt()) + ".    Name: " +c.getFirstName());
                            panelCook.add(labelCookDescription);

                            cooks.add(panelCook);
                        }
                        scrollableCooks.removeAll();
                        for (JPanel ck : cooks) { scrollableCooks.add(ck); }

                        panelCooks.updateUI();

                        frameAdd.dispose();
                    }
                });

                frameAdd.pack();
                frameAdd.setLocationRelativeTo(null);
                frameAdd.setVisible(true);
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameRemove = new JFrame("Remove cook");
                JTextField textCookId = new JTextField("id");
                JButton buttonRemoveCook = new JButton("Remove");

                textCookId.setPreferredSize(new Dimension(130, 50));
                textCookId.setBorder(blackline);

                buttonRemoveCook.setPreferredSize(new Dimension(70, 50));
                buttonRemoveCook.setBorder(blackline);

                frameRemove.setPreferredSize(new Dimension(200, 50));
                frameRemove.setResizable(false);
                frameRemove.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frameRemove.add(textCookId, BorderLayout.LINE_START);
                frameRemove.add(buttonRemoveCook, BorderLayout.LINE_END);

                buttonRemoveCook.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String stringCookId = textCookId.getText();
                        int cookId = 0;
                        int indexToRemove = 0;
                        boolean remove = false;
                        try {
                            cookId = Integer.parseInt(stringCookId);
                        }
                        catch (NumberFormatException ex){
                            frameRemove.dispose();
                            JOptionPane.showMessageDialog(frameRemove, "Its not a number!");
                            return;
                        }

                        for (Cook c : vecCooks){
                            if (c.getId() == cookId) {
                                indexToRemove = vecCooks.indexOf(c);
                                remove = true;
                                break;
                            }
                        }
                        if (remove)
                            vecCooks.remove(indexToRemove);
                        else {
                            frameRemove.dispose();
                            JOptionPane.showMessageDialog(frameRemove, "Id not in the list!");
                            return;
                        }

                        Vector<JPanel> cooks = new Vector<JPanel>();

                        for (Cook c : vecCooks){
                            JPanel panelCook = new JPanel( new FlowLayout(FlowLayout.LEFT));
                            panelCook.setBorder(blackline);
                            panelCook.setMinimumSize(new Dimension(300, 30));
                            panelCook.setMaximumSize(new Dimension(300, 30));

                            JLabel labelCookDescription = new JLabel("Id:" + Integer.toString(c.getIdInt()) + ".    Name: " +c.getFirstName());
                            panelCook.add(labelCookDescription);

                            cooks.add(panelCook);
                        }
                        scrollableCooks.removeAll();
                        for (JPanel ck : cooks) { scrollableCooks.add(ck); }

                        panelCooks.updateUI();

                        frameRemove.dispose();
                    }
                });

                frameRemove.pack();
                frameRemove.setLocationRelativeTo(null);
                frameRemove.setVisible(true);
            }
        });

        frameCooks.pack();
        frameCooks.setLocationRelativeTo(null);
        frameCooks.setVisible(true);
    }

    public void downloadOrders() {
        Call<List<Order>> call = App.interfaceApi.getOrdersStatus1();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    addOrdersToPanels(response);
                }
            }
            @Override
            public void onFailure(Call<List<Order>> call, Throwable throwable) {
            }
        });
    }

    private void addOrdersToPanels(Response<List<Order>> response) {
        JPanel scrollableCooksAssign = new JPanel();
        JScrollPane scrollCooksAssign = new JScrollPane(scrollableCooksAssign);

        Vector<JPanel> orders = new Vector<JPanel>();
        Vector<JPanel> ordersInProgress = new Vector<JPanel>();

        for (Order order : response.body()) {
            JPanel orderPanel = new JPanel();
            orderPanel.setBorder(blackline);
            orderPanel.setPreferredSize(new Dimension(200, 28));
            orderPanel.setLayout(null);
//                JLabel date = new JLabel(order.getDate().toString());
            JLabel dish_name = new JLabel(order.getDish().getName());
            dish_name.setBounds(25, 0, 250, 28);
//                orderPanel.add(date);

            String name = "Name";
            if (order.getEmployee() != null)
                name = order.getEmployee().getFirstName();

            JLabel cooksName = new JLabel("Assigned to: " + name);
            cooksName.setBounds(200, 0, 190, 28);

            JButton buttonAssign = new JButton("Assign");
            JButton buttonRecipe = new JButton("Recipe");

            buttonAssign.setBounds(200, 0, 90, 28);
            buttonRecipe.setBounds(290, 0, 90, 28);

            orderPanel.add(dish_name);

            buttonAssign.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frameAssign = new JFrame("Assign a cook");
                    JPanel panelAssign = new JPanel();

                    panelAssign.setPreferredSize(new Dimension(200, 400));

                    frameAssign.setPreferredSize(new Dimension(200, 400));
                    frameAssign.setResizable(false);
                    frameAssign.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    scrollCooksAssign.setPreferredSize(new Dimension(200, 400));
                    scrollableCooksAssign.setLayout(new BoxLayout(scrollableCooksAssign, BoxLayout.Y_AXIS));
                    scrollableCooksAssign.setAutoscrolls(true);

                    panelAssign.add(scrollCooksAssign);
                    frameAssign.add(panelAssign);

                    Vector<JPanel> cooks = new Vector<JPanel>();

                    for (Cook c : vecCooks){
                        JPanel panelCook = new JPanel( new FlowLayout(FlowLayout.LEFT));
                        panelCook.setBorder(blackline);
                        panelCook.setMinimumSize(new Dimension(200, 30));
                        panelCook.setMaximumSize(new Dimension(200, 30));
                        panelCook.setLayout(null);

                        JButton buttonCookDescription = new JButton("Id:" + Integer.toString(c.getIdInt()) + ".    Name: " +c.getFirstName());
                        buttonCookDescription.setBounds(1, 1, 198, 28);
                        panelCook.add(buttonCookDescription);

                        cooks.add(panelCook);
                    }
                    scrollableCooksAssign.removeAll();
                    for (JPanel ck : cooks) { scrollableCooksAssign.add(ck); }

                    panelAssign.updateUI();;

                    frameAssign.pack();
                    frameAssign.setLocationRelativeTo(null);
                    frameAssign.setVisible(true);
                }
            });

            for (SpecialRequest request: order.getRequests())
                orderPanel.add(new JLabel(request.getRequest()));

            if (order.getEmployee() == null) {
                orderPanel.add(buttonAssign);
                orderPanel.add(buttonRecipe);
                orders.add(orderPanel);
            }
            else {
                orderPanel.add(cooksName);
                ordersInProgress.add(orderPanel);
            }
        }
        scrollablePanel.removeAll();
        scrollablePanelRight.removeAll();

        for (JPanel order: orders)
            scrollablePanel.add(order);
        for (JPanel order : ordersInProgress)
            scrollablePanelRight.add(order);

        panelLeft.updateUI();
    }


    public List<Recipe> downloadAllRecipes(){
        Call<List<Recipe>> call = App.interfaceApi.getRecipe();
        try {
            return call.execute().body();
        } catch (IOException e) {}   //można dodać okienko, że nie udało się
        return new ArrayList<>();
    }


    public List<String> getOneRecipe(int dishId){
        List<String> currentRecipe = new ArrayList<>();
        for (Recipe recipe: this.allRecipes)
            if (recipe.getDishId() == dishId) {
                currentRecipe.add(recipe.getRecipe());
            }
        return currentRecipe;
    }


    public List<Recipe> getUndividedRecipes(){

        Set<Integer> dishIds = new HashSet<Integer>();
        for (Recipe recipe: this.allRecipes){
            dishIds.add(recipe.getDishId());        //zbiór samych pojedynczych dishId
        }

        List<Recipe> undividedRecipes = new ArrayList<>();
        for (int id: dishIds){
            List<String> currentRecipe = getOneRecipe(id);
            Recipe currRecipe = new Recipe(id);
            for (String line: currentRecipe){
                currRecipe.extendRecipe(line);
            }
            undividedRecipes.add(currRecipe);
        }

        return undividedRecipes;

    }




    public void run() {
        SwingUtilities.invokeLater(() -> {

            this.allRecipes = downloadAllRecipes();

            JFrame frame = new JFrame("Kitchen application");
            frame.setMinimumSize(new Dimension(800, 450));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panelLeft.setBorder(blackline);
            panelLeft.setPreferredSize(new Dimension(400, 150));
            panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

            panelRight.setBorder(blackline);
            panelRight.setPreferredSize(new Dimension(400, 150));
            panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

            panelTop.setBorder(blackline);
            panelTop.setPreferredSize(new Dimension(800, 25));
            panelTop.setBackground(Color.GRAY);
            panelTop.setLayout(null);

            JPanel panelLeftTitle = new JPanel();
            panelLeftTitle.setBorder(blackline);
            panelLeftTitle.setBackground(Color.DARK_GRAY);

            JPanel panelRightTitle = new JPanel();
            panelRightTitle.setBorder(blackline);
            panelRightTitle.setBackground(Color.DARK_GRAY);

            JLabel panelLeftTitleText = new JLabel("Orders placed:");
            panelLeftTitleText.setForeground(Color.WHITE);

            panelLeftTitle.add(panelLeftTitleText);
            panelLeft.add(panelLeftTitle);

            JLabel panelRightTitleText = new JLabel("Orders in progress:");
            panelRightTitleText.setForeground(Color.WHITE);

            panelRightTitle.add(panelRightTitleText);
            panelRight.add(panelRightTitle);

            scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
            scrollablePanel.setAutoscrolls(true);
            scrollFrame.setPreferredSize(new Dimension(400, 350));
            panelLeft.add(scrollFrame);

            scrollablePanelRight.setLayout(new BoxLayout(scrollablePanelRight, BoxLayout.Y_AXIS));
            scrollablePanelRight.setAutoscrolls(true);
            scrollFrameRight.setPreferredSize(new Dimension(400, 350));
            panelRight.add(scrollFrameRight);

            downloadOrders();
            Timer t = new Timer(30_000, e -> downloadOrders());
            t.start();

            frame.add(panelLeft, BorderLayout.LINE_START);
            frame.add(panelRight, BorderLayout.LINE_END);
            frame.add(panelTop, BorderLayout.NORTH);

            setButtonRecipes();
            setButtonCooks();

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}