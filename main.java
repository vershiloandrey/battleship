package qwerty;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by 0 on 015 15.03.16.
 */
public class main extends JPanel implements Defines{
    public main() {
        setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Морской бой - меню");
        final JButton buttonNewGame = new JButton("New Game");

        buttonNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModeSelection modeSelection = new ModeSelection();
                modeSelection.setSize(300, 200);
                modeSelection.setResizable(false);
                modeSelection.setLocationRelativeTo(null);
                modeSelection.setVisible(true);
                modeSelection.getContentPane().setBackground(Color.WHITE);
            }
        });

        JButton buttonInfo = new JButton("Information");
        buttonInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = new JFrame("Морской бой - Информация");
                mainFrame.setLayout(new GridLayout(3, 1));

                JLabel aboutGame = new JLabel("ОБ ИГРЕ");
                JLabel gameAutor = new JLabel("Игра <<Морской бой>> разработана студентом БГУИР, Вершило Андреем, в качестве лабораторной работы. ^_^");
                JLabel endInfo = new JLabel("Играйте на здоровье! :)");

                Font font = new Font("Century Gothic", Font.BOLD, SIZE_FONT);
                Icon icon = UIManager.getIcon("OptionPane.informationIcon");
                aboutGame.setIcon(icon);
                Dimension labelSize = new Dimension(SIZE_LABEL, SIZE_LABEL);

                aboutGame.setVerticalAlignment(JLabel.CENTER);
                aboutGame.setHorizontalAlignment(JLabel.CENTER);
                aboutGame.setForeground(Color.BLACK);
                aboutGame.setPreferredSize(labelSize);
                aboutGame.setFont(font);

                Font fontForText = new Font("Century Gothic", Font.BOLD, TEN);
                gameAutor.setVerticalAlignment(JLabel.CENTER);
                gameAutor.setHorizontalAlignment(JLabel.CENTER);
                gameAutor.setForeground(Color.BLACK);
                gameAutor.setPreferredSize(labelSize);
                gameAutor.setFont(fontForText);


                endInfo.setVerticalAlignment(JLabel.CENTER);
                endInfo.setHorizontalAlignment(JLabel.CENTER);
                endInfo.setForeground(Color.BLACK);
                endInfo.setPreferredSize(labelSize);
                endInfo.setFont(font);

                mainFrame.add(aboutGame);
                mainFrame.add(gameAutor);
                mainFrame.add(endInfo);

                mainFrame.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
                mainFrame.setResizable(false);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        });

        JButton buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        main menu = new main();
        menu.add(buttonNewGame);
        menu.add(buttonInfo);
        menu.add(buttonExit);
        mainFrame.add(menu);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
    }

    public void paint(Graphics g) {
        Image a = Toolkit.getDefaultToolkit().getImage("D:\\qwe\\out\\production\\qwe\\qwerty\\menuBackground.jpg");
        g.drawImage(a, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }
}
