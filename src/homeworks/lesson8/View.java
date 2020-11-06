package homeworks.lesson8;

import javax.swing.*;
import java.awt.*;

class View extends JFrame{

    private Game game;
    private int size;
    private JButton[][] buttons;
    private final JPanel container = new JPanel();
    private GridLayout layout;

    View(Game game) {
        this.game = game;
        size = game.getSize();
        createFrame();
    }

    private void setNewGame(Game game) {
        this.game = game;
        size = game.getSize();
    }

    private void createFrame() {

        setTitle(Resource.TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        container.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        layout = new GridLayout(size,size);
        container.setLayout(layout);


        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(Resource.MENU_FILE);

        JMenuItem newGame = new JMenuItem(Resource.MENU_NEW_GAME);
        newGame.addActionListener(e -> {
            setNewGame(new Game(size));
            resetAllButtons();
        });
        fileMenu.add(newGame);

        JMenuItem exit = new JMenuItem(Resource.MENU_EXIT);
        exit.addActionListener(e -> System.exit(0));
        fileMenu.add(exit);

        menuBar.add(fileMenu);

        JMenu settingsMenu = new JMenu(Resource.MENU_SETTINGS);

        JMenuItem setSize = new JMenuItem(Resource.MENU_SIZE);
        setSize.addActionListener(e -> {
            String res = (String) JOptionPane.showInputDialog(View.this,Resource.ENTER_SIZE,
                    Resource.TITLE,JOptionPane.QUESTION_MESSAGE,
                    null,null,3);
            try{
                int enter_size = Integer.parseInt(res);
                if(enter_size < 3)
                    enter_size = 3;
                setNewGame(new Game(enter_size));
                removeAllButtons();
                layout.setRows(size);
                layout.setColumns(size);
                createButtons();
                container.revalidate();
                container.repaint();
                View.this.pack();
            } catch( NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        });
        settingsMenu.add(setSize);

        menuBar.add(settingsMenu);

        setJMenuBar(menuBar);

        createButtons();

        add(container, BorderLayout.CENTER);
        pack();
        setVisible(true);

    }

    private void disabledAllButtons() {
        for (JButton[] button : buttons) {
            for (JButton jButton : button) {
                jButton.setEnabled(false);
            }
        }
    }

    private void resetAllButtons() {
        for (JButton[] button : buttons) {
            for (JButton jButton : button) {
                jButton.setEnabled(true);
                jButton.setText(String.valueOf(Resource.EMPTY_MARK));
            }
        }
    }

    private void removeAllButtons() {
        for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
                container.remove(buttons[i][j]);
            }
        }
    }

    public void showDialog(String message, JButton[][] buttons) {
        disabledAllButtons();
        int res = JOptionPane.showConfirmDialog(View.this,
                message,Resource.TITLE,
                JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION) {
            setNewGame(new Game(size));
            resetAllButtons();
        }
    }

    private void createButtons() {
        buttons = new JButton[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                buttons[i][j] = new JButton(String.valueOf(Resource.EMPTY_MARK));
                buttons[i][j].setPreferredSize(new Dimension(50,50));
                container.add(buttons[i][j]);
                final int row = i;
                final int col = j;
                buttons[row][col].addActionListener(e -> {
                    if(game.userMove(row,col)) {
                        buttons[row][col].setText(String.valueOf(Resource.USER_MARK));
                        buttons[row][col].setEnabled(false);
                        if(game.checkWin(Resource.USER_MARK)) {
                            showDialog(Resource.WIN_MESSAGE, buttons);
                        } else if(game.checkDraw()) {
                            showDialog(Resource.DRAW_MESSAGE, buttons);
                        } else {
                            int[] compMove = game.compMove();
                            buttons[compMove[0]][compMove[1]].setText(String.valueOf(Resource.COMP_MARK));
                            buttons[compMove[0]][compMove[1]].setEnabled(false);
                            if(game.checkWin(Resource.COMP_MARK)) {
                                showDialog(Resource.LOSS_MESSAGE, buttons);
                            } else if(game.checkDraw()) {
                                showDialog(Resource.DRAW_MESSAGE, buttons);
                            }
                        }
                    }
                });
            }
        }
    }
}