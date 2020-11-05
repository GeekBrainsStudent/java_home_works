package homeworks.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class View extends JFrame{

    private final Game game;

    View(Game game) {
        this.game = game;
        createFrame();
    }

    private void createFrame() {
        setTitle(Resource.TITLE);
        setSize(300,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int size = game.getSize();
        JPanel panelCenter = new JPanel(new GridLayout(size,size));
        add(panelCenter, BorderLayout.CENTER);

        JButton[][] buttons = new JButton[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                buttons[i][j] = new JButton(String.valueOf(Resource.EMPTY_MARK));
                panelCenter.add(buttons[i][j]);
                final int row = i;
                final int col = j;
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(game.userMove(row,col)) {
                            buttons[row][col].setText(String.valueOf(Resource.USER_MARK));
                            if(game.checkWin(Resource.USER_MARK)) {

                            }
                            if(game.checkDraw()) {

                            }
                            int compMove[] = game.compMove();
                            buttons[compMove[0]][compMove[1]].setText(String.valueOf(Resource.COMP_MARK));
                        }
                    }
                });
            }
        }

        setVisible(true);
    }

}