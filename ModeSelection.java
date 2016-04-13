package qwerty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 0 on 008 08.04.16.
 */
public class ModeSelection extends JFrame implements Defines{
    public ModeSelection() {
        super("Морской бой - Режим игры");
        setLayout(new FlowLayout());
        Button botVSbot = new Button("botVSbot");
        Button single = new Button("Single");

        add(botVSbot);
        add(single);

        single.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LevelSelection levelSelection = new LevelSelection();
                levelSelection.setSize(300, 200);
                levelSelection.setResizable(false);
                levelSelection.setLocationRelativeTo(null);
                levelSelection.setVisible(true);
                levelSelection.getContentPane().setBackground(Color.WHITE);
                dispose();
            }
        });

        botVSbot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotsGame botsGame = new BotsGame();
                botsGame.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
                botsGame.setResizable(false);
                botsGame.setLocationRelativeTo(null);
                botsGame.setVisible(true);
                dispose();
            }
        });
    }
}
