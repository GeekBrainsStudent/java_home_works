package homeworks.lesson8.view;

import homeworks.lesson8.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    static final String TITLE = "Крестики - Нолики";
    private int size;
    private Controller controller;

    public View(int size, Controller controller) {

        this.size = size;
        this.controller = controller;

        setTitle(TITLE);
        setSize(300,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout gridLayout = new GridLayout(size,size);
        JPanel jpanel = new JPanel(gridLayout);
        add(jpanel, BorderLayout.CENTER);

        JButton[] buttons = new JButton[9];
        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton("# " + i);
            jpanel.add(buttons[i]);
        }

        setVisible(true);
    }

}
