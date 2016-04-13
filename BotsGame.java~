package qwerty;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by 0 on 008 08.04.16.
 */
public class BotsGame extends JFrame {
    JButton field_ms1player[][];
    JButton field_ms2player[][];

    int msPlayerField[][];                                                                                              // В НОВЫЙ КЛАСС. массив, в котором будет хранится состояние поля
    int msPlayer2Field[][];                                                                                             // 0 - пусто, 1 - корабль, 2 - использованная ячейка

    int numberOfMyShips = 8;                                                                                              // в класс
    int numberOfRivalShips = 8;

    public BotsGame() {
        super("Морской бой - Игра");
        setContentPane(new JLabel(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\background2.jpg")));
        setLayout(new BorderLayout());

        msPlayerField = new int[10][10];
        msPlayer2Field = new int[10][10];
        msPlayerField = generateShips();
        msPlayer2Field = generateShips();

        JPanel fieldForButton = new JPanel();
        fieldForButton.setOpaque(false);
        // Кнопка начала игры
        final JButton Play = new JButton(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\play.png"));
        Play.setContentAreaFilled(false);
        fieldForButton.add(Play);

        // Текстовое уведомление и настройки шрифта
        final JLabel startText1 = new JLabel("Начните игру");
        Icon icon = UIManager.getIcon("OptionPane.informationIcon");

        Font font = new Font("Century Gothic", Font.BOLD, 20);
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
        startText1.setIcon(icon);
        Dimension labelSize = new Dimension(50, 50);

        startText1.setVerticalAlignment(JLabel.CENTER);
        startText1.setHorizontalAlignment(JLabel.CENTER);
        startText1.setForeground(Color.BLACK);
        startText1.setPreferredSize(labelSize);
        startText1.setBorder(solidBorder);
        startText1.setFont(font);


        // Поле кораблей 1 бота
        JPanel field1 = new JPanel();
        field1.setLayout(new GridLayout(10, 10));
        field1.setOpaque(false);

        field_ms1player = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field_ms1player[i][j] = new JButton();
                Dimension sizeButton = new Dimension(45, 45);
                field_ms1player[i][j].setPreferredSize(sizeButton);
                field_ms1player[i][j].setContentAreaFilled(false);
                field_ms1player[i][j].setEnabled(false);
                field1.add(field_ms1player[i][j]);
            }
        }


        // Поле  кораблей 2 бота
        JPanel field2 = new JPanel();
        field2.setLayout(new GridLayout(10, 10));
        field2.setOpaque(false);
        field_ms2player = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field_ms2player[i][j] = new JButton();
                Dimension sizeButton = new Dimension(45, 45);
                field_ms2player[i][j].setPreferredSize(sizeButton);
                field_ms2player[i][j].setContentAreaFilled(false);
                field_ms2player[i][j].setEnabled(false);
                field2.add(field_ms2player[i][j]);
            }
        }


        // Обработчик событий кнопки "НАЧАТЬ ИГРАТЬ"
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (numberOfMyShips != 0 && numberOfRivalShips != 0) {
                    BotAttack(1);
                    BotAttack(2);
                    //TimeUnit.SECONDS.sleep(1);
                }
                if (numberOfMyShips == 0) {
                    startText1.setText("Выиграл 2 бот");
                } else {
                    startText1.setText("Выиграл 1 бот");
                }

                Play.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BotsGame botsGame = new BotsGame();
                        botsGame.setSize(1000, 700);
                        botsGame.setResizable(false);
                        botsGame.setLocationRelativeTo(null);
                        botsGame.setVisible(true);
                        dispose();
                    }
                });
            }
        });

        add(startText1, BorderLayout.NORTH);
        add(field2, BorderLayout.EAST);
        add(fieldForButton, BorderLayout.SOUTH);
        add(field1, BorderLayout.WEST);

    }


    public void BotAttack(int variable) {
        if (numberOfMyShips == 0 || numberOfRivalShips == 0) {
            return;
        }
        if (variable == 1) {
            Random randomi = new Random();
            int i = randomi.nextInt(10);

            Random randomj = new Random();
            int j = randomj.nextInt(10);

            if (msPlayerField[i][j] == 2) {                                                                             // Если рандом попал в ту же точку
                if (numberOfMyShips != 0)
                    BotAttack(1);                                                                                       // выстрел в новую точку
            }


            if (msPlayerField[i][j] == 1) {                                                                             // Если попал
                field_ms1player[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
                msPlayerField[i][j] = 2;                                                                                // использованная кнопка
                numberOfMyShips--;                                                                                      // на один корабль стало меньше
                if (i > 0) {                                                                                            // ВСЕ РЯДОМ НАХОДЯЩИЕСЯ ЯЧЕЙКИ СТАНОВЯТСЯ НЕДЕЙСТВИТЕЛЬНЫМИ
                    field_ms1player[i - 1][j].setContentAreaFilled(true);
                    field_ms1player[i - 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        field_ms1player[i - 1][j + 1].setContentAreaFilled(true);
                        field_ms1player[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i - 1][j + 1] = 2;
                    }
                    if (j > 0) {
                        field_ms1player[i - 1][j - 1].setContentAreaFilled(true);
                        field_ms1player[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i - 1][j - 1] = 2;
                    }
                }
                if (i < 9) {
                    field_ms1player[i + 1][j].setContentAreaFilled(true);
                    field_ms1player[i + 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        field_ms1player[i + 1][j + 1].setContentAreaFilled(true);
                        field_ms1player[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i + 1][j + 1] = 2;
                    }
                    if (j > 0) {
                        field_ms1player[i + 1][j - 1].setContentAreaFilled(true);
                        field_ms1player[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i + 1][j - 1] = 2;
                    }
                }
                if (j < 9) {
                    field_ms1player[i][j + 1].setContentAreaFilled(true);
                    field_ms1player[i][j + 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 9) {
                        field_ms1player[i + 1][j + 1].setContentAreaFilled(true);
                        field_ms1player[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i + 1][j + 1] = 2;
                    }
                    if (i > 0) {
                        field_ms1player[i - 1][j + 1].setContentAreaFilled(true);
                        field_ms1player[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i - 1][j + 1] = 2;
                    }
                }
                if (j > 0) {
                    field_ms1player[i][j - 1].setContentAreaFilled(true);
                    field_ms1player[i][j - 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 0) {
                        field_ms1player[i - 1][j - 1].setContentAreaFilled(true);
                        field_ms1player[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i - 1][j - 1] = 2;
                    }
                    if (i > 9) {
                        field_ms1player[i + 1][j - 1].setContentAreaFilled(true);
                        field_ms1player[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayerField[i + 1][j - 1] = 2;
                    }
                }
                BotAttack(variable);                                                                                    // Если попал, повторный выстрел
            }

            if (msPlayerField[i][j] == 0) {                                                                             // МИМО
                msPlayerField[i][j] = 2;
                field_ms1player[i][j].setContentAreaFilled(true);
                field_ms1player[i][j].setBackground(Color.LIGHT_GRAY);
            }
        } else {
            Random randomi = new Random();
            int i = randomi.nextInt(10);

            Random randomj = new Random();
            int j = randomj.nextInt(10);

            if (msPlayer2Field[i][j] == 2) {                                                                            // Если рандом попал в ту же точку
                if (numberOfRivalShips != 0)
                    BotAttack(2);                                                                                       // выстрел в новую точку
            }

            if (msPlayer2Field[i][j] == 1) {                                                                            // Если попал
                field_ms2player[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
                msPlayer2Field[i][j] = 2;                                                                               // использованная кнопка
                numberOfRivalShips--;                                                                                   // на один корабль стало меньше
                if (i > 0) {                                                                                            // ВСЕ РЯДОМ НАХОДЯЩИЕСЯ ЯЧЕЙКИ СТАНОВЯТСЯ НЕДЕЙСТВИТЕЛЬНЫМИ
                    field_ms2player[i - 1][j].setContentAreaFilled(true);
                    field_ms2player[i - 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        field_ms2player[i - 1][j + 1].setContentAreaFilled(true);
                        field_ms2player[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i - 1][j + 1] = 2;
                    }
                    if (j > 0) {
                        field_ms2player[i - 1][j - 1].setContentAreaFilled(true);
                        field_ms2player[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i - 1][j - 1] = 2;
                    }
                }
                if (i < 9) {
                    field_ms2player[i + 1][j].setContentAreaFilled(true);
                    field_ms2player[i + 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        field_ms2player[i + 1][j + 1].setContentAreaFilled(true);
                        field_ms2player[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i + 1][j + 1] = 2;
                    }
                    if (j > 0) {
                        field_ms2player[i + 1][j - 1].setContentAreaFilled(true);
                        field_ms2player[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i + 1][j - 1] = 2;
                    }
                }
                if (j < 9) {
                    field_ms2player[i][j + 1].setContentAreaFilled(true);
                    field_ms2player[i][j + 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 9) {
                        field_ms2player[i + 1][j + 1].setContentAreaFilled(true);
                        field_ms2player[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i + 1][j + 1] = 2;
                    }
                    if (i > 0) {
                        field_ms2player[i - 1][j + 1].setContentAreaFilled(true);
                        field_ms2player[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i - 1][j + 1] = 2;
                    }
                }
                if (j > 0) {
                    field_ms2player[i][j - 1].setContentAreaFilled(true);
                    field_ms2player[i][j - 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 0) {
                        field_ms2player[i - 1][j - 1].setContentAreaFilled(true);
                        field_ms2player[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i - 1][j - 1] = 2;
                    }
                    if (i > 9) {
                        field_ms2player[i + 1][j - 1].setContentAreaFilled(true);
                        field_ms2player[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        msPlayer2Field[i + 1][j - 1] = 2;
                    }
                }
                BotAttack(variable);                                                                                    // Если попал, повторный выстрел
            }

            if (msPlayer2Field[i][j] == 0) {                                                                            // МИМО
                msPlayer2Field[i][j] = 2;
                field_ms2player[i][j].setContentAreaFilled(true);
                field_ms2player[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }
    }


    int[][] generateShips() {
        int ms[][];

        ms = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ms[i][j] = 0;
            }
        }
        int n = 0;
        while (n != 8) {
            Random ri = new Random();
            int k = ri.nextInt(10);
            Random rj = new Random();
            int l = rj.nextInt(10);
            if (k > 0) {
                if (ms[k - 1][l] == 1) continue;

                if (l > 0)
                    if (ms[k - 1][l - 1] == 1) continue;
                if (l < 9)
                    if (ms[k - 1][l + 1] == 1) continue;

            }

            if (k < 9) {
                if (ms[k + 1][l] == 1) continue;

                if (l > 0)
                    if (ms[k + 1][l - 1] == 1) continue;
                if (l < 9)
                    if (ms[k + 1][l + 1] == 1) continue;

            }

            if (l < 9) {
                if (ms[k][l + 1] == 1) continue;

                if (k > 0)
                    if (ms[k - 1][l + 1] == 1) continue;
                if (k < 9)
                    if (ms[k + 1][l + 1] == 1) continue;

            }

            if (l > 0) {
                if (ms[k][l - 1] == 1) continue;

                if (k > 0)
                    if (ms[k - 1][l - 1] == 1) continue;
                if (k < 9)
                    if (ms[k + 1][l - 1] == 1) continue;
            }

            n++;
            ms[k][l] = 1;
        }
        return ms;
    }
}



