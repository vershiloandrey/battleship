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
public class BotsGame extends JFrame implements Defines{
    JButton oneField[][];
    JButton twoField[][];

    int saveOneField[][];                                                                                              // В НОВЫЙ КЛАСС. массив, в котором будет хранится состояние поля
    int saveTwoField[][];                                                                                             // 0 - пусто, 1 - корабль, 2 - использованная ячейка

    int numberOfMyShips = 8;                                                                                              // в класс
    int numberOfRivalShips = 8;

    public BotsGame() {
        super("Морской бой - Игра");
        setContentPane(new JLabel(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\gameBackground.jpg")));
        setLayout(new BorderLayout());

        saveOneField = new int[TEN][TEN];
        saveTwoField = new int[TEN][TEN];
        saveOneField = generateShips();
        saveTwoField = generateShips();

        JPanel fieldForButton = new JPanel();
        fieldForButton.setOpaque(false);
        // Кнопка начала игры
        final JButton Play = new JButton(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\play.png"));
        Play.setContentAreaFilled(false);
        fieldForButton.add(Play);

        // Текстовое уведомление и настройки шрифта
        final JLabel startText = new JLabel("Начните игру");
        Icon icon = UIManager.getIcon("OptionPane.informationIcon");

        Font font = new Font("Century Gothic", Font.BOLD, SIZE_FONT);
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
        startText.setIcon(icon);
        Dimension labelSize = new Dimension(SIZE_LABEL, SIZE_LABEL);

        startText.setVerticalAlignment(JLabel.CENTER);
        startText.setHorizontalAlignment(JLabel.CENTER);
        startText.setForeground(Color.BLACK);
        startText.setPreferredSize(labelSize);
        startText.setBorder(solidBorder);
        startText.setFont(font);


        // Поле кораблей 1 бота
        JPanel oneFieldPanel = new JPanel();
        oneFieldPanel.setLayout(new GridLayout(TEN, TEN));
        oneFieldPanel.setOpaque(false);

        oneField = new JButton[TEN][TEN];

        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                oneField[i][j] = new JButton();
                Dimension sizeButton = new Dimension(SIZE_BUTTON, SIZE_BUTTON);
                oneField[i][j].setPreferredSize(sizeButton);
                Border solidBorderForField = BorderFactory.createLineBorder(Color.WHITE, 1);
                oneField[i][j].setBorder(solidBorderForField);
                oneField[i][j].setContentAreaFilled(false);
                oneFieldPanel.add(oneField[i][j]);
            }
        }


        // Поле  кораблей 2 бота
        JPanel twoFieldPanel = new JPanel();
        twoFieldPanel.setLayout(new GridLayout(TEN, TEN));
        twoFieldPanel.setOpaque(false);
        twoField = new JButton[TEN][TEN];

        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                twoField[i][j] = new JButton();
                Dimension sizeButton = new Dimension(SIZE_BUTTON, SIZE_BUTTON);
                twoField[i][j].setPreferredSize(sizeButton);
                Border solidBorderForField = BorderFactory.createLineBorder(Color.WHITE, 1);
                twoField[i][j].setBorder(solidBorderForField);
                twoField[i][j].setContentAreaFilled(false);
                twoFieldPanel.add(twoField[i][j]);
            }
        }


        // Обработчик событий кнопки "НАЧАТЬ ИГРАТЬ"
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (numberOfMyShips != 0 && numberOfRivalShips != 0) {
                    BotAttack(SHOT_FIRST_BOT);
                    BotAttack(SHOT_SECOND_BOT);
                }
                if (numberOfMyShips == 0) {
                    startText.setText("Выиграл 2 бот");
                } else {
                    startText.setText("Выиграл 1 бот");
                }

                Play.addActionListener(new ActionListener() {
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
        });

        add(startText, BorderLayout.NORTH);
        add(twoFieldPanel, BorderLayout.EAST);
        add(fieldForButton, BorderLayout.SOUTH);
        add(oneFieldPanel, BorderLayout.WEST);

    }


    public void BotAttack(int variable) {
        if (numberOfMyShips == 0 || numberOfRivalShips == 0) {
            return;
        }
        if (variable == 1) {
            Random attackI = new Random();
            int i = attackI.nextInt(TEN);

            Random attackJ = new Random();
            int j = attackJ.nextInt(TEN);

            if (saveOneField[i][j] == USED) {                                                                             // Если рандом попал в ту же точку
                if (numberOfMyShips != 0)
                    BotAttack(SHOT_FIRST_BOT);                                                                                       // выстрел в новую точку
            }


            if (saveOneField[i][j] == SHIP) {                                                                             // Если попал
                oneField[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
                saveOneField[i][j] = USED;                                                                                // использованная кнопка
                numberOfMyShips--;                                                                                      // на один корабль стало меньше
                if (i > 0) {                                                                                            // ВСЕ РЯДОМ НАХОДЯЩИЕСЯ ЯЧЕЙКИ СТАНОВЯТСЯ НЕДЕЙСТВИТЕЛЬНЫМИ
                    oneField[i - 1][j].setContentAreaFilled(true);
                    oneField[i - 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        oneField[i - 1][j + 1].setContentAreaFilled(true);
                        oneField[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i - 1][j + 1] = USED;
                    }
                    if (j > 0) {
                        oneField[i - 1][j - 1].setContentAreaFilled(true);
                        oneField[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i - 1][j - 1] = USED;
                    }
                }
                if (i < 9) {
                    oneField[i + 1][j].setContentAreaFilled(true);
                    oneField[i + 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        oneField[i + 1][j + 1].setContentAreaFilled(true);
                        oneField[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i + 1][j + 1] = USED;
                    }
                    if (j > 0) {
                        oneField[i + 1][j - 1].setContentAreaFilled(true);
                        oneField[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i + 1][j - 1] = USED;
                    }
                }
                if (j < 9) {
                    oneField[i][j + 1].setContentAreaFilled(true);
                    oneField[i][j + 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 9) {
                        oneField[i + 1][j + 1].setContentAreaFilled(true);
                        oneField[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i + 1][j + 1] = USED;
                    }
                    if (i > 0) {
                        oneField[i - 1][j + 1].setContentAreaFilled(true);
                        oneField[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i - 1][j + 1] = USED;
                    }
                }
                if (j > 0) {
                    oneField[i][j - 1].setContentAreaFilled(true);
                    oneField[i][j - 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 0) {
                        oneField[i - 1][j - 1].setContentAreaFilled(true);
                        oneField[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i - 1][j - 1] = USED;
                    }
                    if (i > 9) {
                        oneField[i + 1][j - 1].setContentAreaFilled(true);
                        oneField[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveOneField[i + 1][j - 1] = USED;
                    }
                }
                BotAttack(variable);                                                                                    // Если попал, повторный выстрел
            }

            if (saveOneField[i][j] == CLEAR) {                                                                             // МИМО
                saveOneField[i][j] = USED;
                oneField[i][j].setContentAreaFilled(true);
                oneField[i][j].setBackground(Color.LIGHT_GRAY);
            }
        } else {
            Random attackI = new Random();
            int i = attackI.nextInt(TEN);

            Random attackJ = new Random();
            int j = attackJ.nextInt(TEN);

            if (saveTwoField[i][j] == USED) {                                                                            // Если рандом попал в ту же точку
                if (numberOfRivalShips != 0)
                    BotAttack(SHOT_SECOND_BOT);                                                                                       // выстрел в новую точку
            }

            if (saveTwoField[i][j] == SHIP) {                                                                            // Если попал
                twoField[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
                saveTwoField[i][j] = USED;                                                                               // использованная кнопка
                numberOfRivalShips--;                                                                                   // на один корабль стало меньше
                if (i > 0) {                                                                                            // ВСЕ РЯДОМ НАХОДЯЩИЕСЯ ЯЧЕЙКИ СТАНОВЯТСЯ НЕДЕЙСТВИТЕЛЬНЫМИ
                    twoField[i - 1][j].setContentAreaFilled(true);
                    twoField[i - 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        twoField[i - 1][j + 1].setContentAreaFilled(true);
                        twoField[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i - 1][j + 1] = USED;
                    }
                    if (j > 0) {
                        twoField[i - 1][j - 1].setContentAreaFilled(true);
                        twoField[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i - 1][j - 1] = USED;
                    }
                }
                if (i < 9) {
                    twoField[i + 1][j].setContentAreaFilled(true);
                    twoField[i + 1][j].setBackground(Color.LIGHT_GRAY);
                    if (j < 9) {
                        twoField[i + 1][j + 1].setContentAreaFilled(true);
                        twoField[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i + 1][j + 1] = USED;
                    }
                    if (j > 0) {
                        twoField[i + 1][j - 1].setContentAreaFilled(true);
                        twoField[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i + 1][j - 1] = USED;
                    }
                }
                if (j < 9) {
                    twoField[i][j + 1].setContentAreaFilled(true);
                    twoField[i][j + 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 9) {
                        twoField[i + 1][j + 1].setContentAreaFilled(true);
                        twoField[i + 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i + 1][j + 1] = USED;
                    }
                    if (i > 0) {
                        twoField[i - 1][j + 1].setContentAreaFilled(true);
                        twoField[i - 1][j + 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i - 1][j + 1] = USED;
                    }
                }
                if (j > 0) {
                    twoField[i][j - 1].setContentAreaFilled(true);
                    twoField[i][j - 1].setBackground(Color.LIGHT_GRAY);
                    if (i < 0) {
                        twoField[i - 1][j - 1].setContentAreaFilled(true);
                        twoField[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i - 1][j - 1] = USED;
                    }
                    if (i > 9) {
                        twoField[i + 1][j - 1].setContentAreaFilled(true);
                        twoField[i + 1][j - 1].setBackground(Color.LIGHT_GRAY);
                        saveTwoField[i + 1][j - 1] = USED;
                    }
                }
                BotAttack(variable);                                                                                    // Если попал, повторный выстрел
            }

            if (saveTwoField[i][j] == CLEAR) {                                                                            // МИМО
                saveTwoField[i][j] = USED;
                twoField[i][j].setContentAreaFilled(true);
                twoField[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }
    }


    int[][] generateShips() {
        int fieldForRequired[][];

        fieldForRequired = new int[TEN][TEN];
        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                fieldForRequired[i][j] = CLEAR;
            }
        }
        int numberShip = 0;
        while (numberShip != 9) {
            Random plaseToI = new Random();
            int k = plaseToI.nextInt(TEN);
            Random plaseToJ = new Random();
            int l = plaseToJ.nextInt(TEN);
            if (k > 0) {
                if (fieldForRequired[k - 1][l] == 1) continue;

                if (l > 0)
                    if (fieldForRequired[k - 1][l - 1] == SHIP) continue;
                if (l < 9)
                    if (fieldForRequired[k - 1][l + 1] == SHIP) continue;

            }

            if (k < 9) {
                if (fieldForRequired[k + 1][l] == SHIP) continue;

                if (l > 0)
                    if (fieldForRequired[k + 1][l - 1] == SHIP) continue;
                if (l < 9)
                    if (fieldForRequired[k + 1][l + 1] == SHIP) continue;

            }

            if (l < 9) {
                if (fieldForRequired[k][l + 1] == SHIP) continue;

                if (k > 0)
                    if (fieldForRequired[k - 1][l + 1] == SHIP) continue;
                if (k < 9)
                    if (fieldForRequired[k + 1][l + 1] == SHIP) continue;

            }

            if (l > 0) {
                if (fieldForRequired[k][l - 1] == SHIP) continue;

                if (k > 0)
                    if (fieldForRequired[k - 1][l - 1] == SHIP) continue;
                if (k < 9)
                    if (fieldForRequired[k + 1][l - 1] == SHIP) continue;
            }

           numberShip++;
            fieldForRequired[k][l] = SHIP;
        }
        return fieldForRequired;
    }
}



