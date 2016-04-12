package qwerty;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import sun.audio.*;

/**
 * Created by 0 on 015 15.03.16.
 */
public class main extends JPanel {
    public main() {
        setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Морской бой - меню");
        final JButton buttonNewGame = new JButton("New Game");

        buttonNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModeSelection ms = new ModeSelection();
                ms.setSize(300, 200);
                ms.setResizable(false);
                ms.setLocationRelativeTo(null);
                ms.setVisible(true);
                ms.getContentPane().setBackground(Color.WHITE);
            }
        });

        JButton buttonInfo = new JButton("Information");
        buttonInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Морской бой - Информация");
                frame.setLayout(new GridLayout(3, 1));

                JLabel info1 = new JLabel("ОБ ИГРЕ");
                JLabel info2 = new JLabel("Игра <<Морской бой>> разработана студентом БГУИР, Вершило Андреем, в качестве лабораторной работы. ^_^");
                JLabel info3 = new JLabel("Играйте на здоровье! :)");

                Font font = new Font("Century Gothic", Font.BOLD, 20);
                Icon icon = UIManager.getIcon("OptionPane.informationIcon");
                info1.setIcon(icon);
                Dimension labelSize = new Dimension(50, 50);

                info1.setVerticalAlignment(JLabel.CENTER);
                info1.setHorizontalAlignment(JLabel.CENTER);
                info1.setForeground(Color.BLACK);
                info1.setPreferredSize(labelSize);
                info1.setFont(font);

                Font fontForText = new Font("Century Gothic", Font.BOLD, 10);
                info2.setVerticalAlignment(JLabel.CENTER);
                info2.setHorizontalAlignment(JLabel.CENTER);
                info2.setForeground(Color.BLACK);
                info2.setPreferredSize(labelSize);
                info2.setFont(fontForText);


                info3.setVerticalAlignment(JLabel.CENTER);
                info3.setHorizontalAlignment(JLabel.CENTER);
                info3.setForeground(Color.BLACK);
                info3.setPreferredSize(labelSize);
                info3.setFont(font);

                frame.add(info1);
                frame.add(info2);
                frame.add(info3);

                frame.setSize(1000, 700);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
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
        frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void paint(Graphics g) {
        Image a = Toolkit.getDefaultToolkit().getImage("D:\\qwe\\out\\production\\qwe\\qwerty\\background.jpg");
        g.drawImage(a, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }
}
