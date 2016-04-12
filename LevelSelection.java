package qwerty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 0 on 013 13.04.16.
 */
public class LevelSelection extends JFrame {
    public LevelSelection() {
        super("Морской бой - Режим игры");
        setLayout(new FlowLayout());
        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");

        add(easy);
        add(medium);
        add(hard);

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setSize(1000, 700);
                gameWindow.setResizable(false);
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);
                dispose();
            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setSize(1000, 700);
                gameWindow.setResizable(false);
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);
                dispose();
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setSize(1000, 700);
                gameWindow.setResizable(false);
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);
                dispose();
            }
        });
    }
}


