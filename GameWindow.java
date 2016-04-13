package qwerty;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 0 on 022 22.03.16.
 */

// Класс окна расстановки кораблей
public class GameWindow extends JFrame implements Defines{
    JButton oneField[][];
    JButton twoField[][];

    int saveOneField[][];                                                                                              // В НОВЫЙ КЛАСС. массив, в котором будет хранится состояние поля
    int saveTwoField[][];                                                                                             // 0 - пусто, 1 - корабль, 2 - использованная ячейка

    int numberOfMyShips = 0;                                                                                            // в класс
    int numberOfRivalShips = 0;

    public GameWindow() {
        super("Морской бой - Игра");
        setContentPane(new JLabel(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\gameBackground.jpg")));
        setLayout(new BorderLayout());
        saveOneField = new int[TEN][TEN];

        for (int i = 0; i < TEN; i++) {                                                                                  // зануление массива для кораблей
            for (int j = 0; j < TEN; j++) {
                saveOneField[i][j] = 0;
            }
        }

        JPanel fieldForButton = new JPanel();
        fieldForButton.setOpaque(false);
        
        // Кнопка начала игры
        final JButton Play = new JButton(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\play.png"));
        Play.setContentAreaFilled(false);
        fieldForButton.add(Play);

        // Текстовое уведомление и настройки шрифта
        final JLabel startText = new JLabel("Расставьте свои корабли");
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

        // Поле для расстановки кораблей игрока 1
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

        // обработчики событий для кнопок расстановки кораблей
        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                final int ifinal = i;
                final int jfinal = j;

                oneField[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int i = ifinal;
                        int j = jfinal;

                        if (numberOfMyShips < 8) {
                            if (saveOneField[i][j] != SHIP) {
                                if (isCellAvailable(i, j)) {                                                            // проверка на ближайшие корабли
                                    oneField[i][j].setContentAreaFilled(false);
                                    oneField[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                                    saveOneField[i][j] = SHIP;
                                    numberOfMyShips++;
                                }
                            } else {
                                oneField[i][j].setBackground(Color.LIGHT_GRAY);
                                saveOneField[i][j] = CLEAR;
                                numberOfMyShips--;
                            }

                        } else {
                            if (saveOneField[i][j] == SHIP) {
                                oneField[i][j].setBackground(Color.LIGHT_GRAY);
                                saveOneField[i][j] = CLEAR;
                                numberOfMyShips--;
                            }
                        }
                    }

                });
            }
        }

        // Поле  кораблей противника
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
                twoField[i][j].setEnabled(false);
                twoFieldPanel.add(twoField[i][j]);
            }
        }

        // обработчик событий кнопки "НАЧАТЬ ИГРАТЬ"
        Play.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       if (numberOfMyShips == 8) {
                                           startText.setText("Игра началась!");
                                           saveTwoField = new int[TEN][TEN];
                                           saveTwoField = generateShips();

                                           for (int i = 0; i < TEN; i++) {
                                               for (int j = 0; j < TEN; j++) {
                                                   twoField[i][j].setEnabled(true);
                                                   ActionListener[] listeners = oneField[i][j].getActionListeners();
                                                   for (int pos = 0; pos < listeners.length; pos++) {
                                                       oneField[i][j].removeActionListener(listeners[pos]);
                                                   }
                                               }
                                           }

                                           Play.addActionListener(new ActionListener() {
                                               @Override
                                               public void actionPerformed(ActionEvent e) {
                                                   GameWindow gameWindow = new GameWindow();
                                                   gameWindow.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
                                                   gameWindow.setResizable(false);
                                                   gameWindow.setLocationRelativeTo(null);
                                                   gameWindow.setVisible(true);
                                                   dispose();
                                               }
                                           });

                                       }


                                   }
                               }

        );

        numberOfRivalShips = 8;

        // обработчик событий для поля соперника
        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                final int ifinal = i;
                final int jfinal = j;

                twoField[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int i = ifinal;
                        int j = jfinal;

                        if (saveTwoField[i][j] == 1) {
                            saveTwoField[i][j] = 2;
                            twoField[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\mirrorFireship.png"));
                            twoField[i][j].setContentAreaFilled(false);
                            ActionListener[] listeners = twoField[i][j].getActionListeners();
                            for (int pos = 0; pos < listeners.length; pos++) {
                                twoField[i][j].removeActionListener(listeners[pos]);
                            }

                            numberOfRivalShips--;
                            if (i > 0) {
                                twoField[i - 1][j].setEnabled(false);
                                if (j < 9) twoField[i - 1][j + 1].setEnabled(false);
                                if (j > 0) twoField[i - 1][j - 1].setEnabled(false);
                            }
                            if (i < 9) {
                                twoField[i + 1][j].setEnabled(false);
                                if (j < 9) twoField[i + 1][j + 1].setEnabled(false);
                                if (j > 0) twoField[i + 1][j - 1].setEnabled(false);
                            }
                            if (j < 9) {
                                twoField[i][j + 1].setEnabled(false);
                                if (i < 9) twoField[i + 1][j + 1].setEnabled(false);
                                if (i > 0) twoField[i - 1][j + 1].setEnabled(false);
                            }
                            if (j > 0) {
                                twoField[i][j - 1].setEnabled(false);
                                if (i < 0) twoField[i - 1][j - 1].setEnabled(false);
                                if (i > 9) twoField[i + 1][j - 1].setEnabled(false);
                            }
                        }

                        if (saveTwoField[i][j] == 0) {
                            twoField[i][j].setContentAreaFilled(true);
                            twoField[i][j].setBackground(Color.LIGHT_GRAY);
                            twoField[i][j].setEnabled(false);
                            saveTwoField[i][j] = USED;
                        }

                        BotAttack();

                        if (numberOfRivalShips == 0) {
                            startText.setText("Вы выиграли!");
                            for (int k = 0; k < TEN; k++) {
                                for (int l = 0; l < TEN; l++) {
                                    twoField[k][l].setEnabled(false);                                                   // ЗАБЛОЧИТЬ КНОПКУ "НАЧАЛО ИГРЫ", ЛИБО ЗАМЕНИТЬ ЕЕ НА "РЕПЛЕЙ"
                                }
                            }
                        }
                        if (numberOfMyShips == 0) {
                            startText.setText("Вы проиграли!");
                            for (int k = 0; k < TEN; k++) {
                                for (int l = 0; l < TEN; l++) {
                                    twoField[k][l].setEnabled(false);
                                }
                            }
                        }
                    }
                });
            }
        }

        add(startText, BorderLayout.NORTH);
        add(twoFieldPanel, BorderLayout.EAST);
        add(fieldForButton, BorderLayout.SOUTH);
        add(oneFieldPanel, BorderLayout.WEST);
    }

    private boolean isCellAvailable(int i, int j) {
        ArrayList<Integer> firstList = new ArrayList<Integer>();
        firstList.add(i - 1);
        firstList.add(i);
        firstList.add(i + 1);
        firstList.add(i);
        firstList.add(i + 1);
        firstList.add(i + 1);
        firstList.add(i - 1);
        firstList.add(i - 1);

        ArrayList<Integer> secondList = new ArrayList<Integer>();
        secondList.add(j);
        secondList.add(j - 1);
        secondList.add(j);
        secondList.add(j + 1);
        secondList.add(j + 1);
        secondList.add(j - 1);
        secondList.add(j - 1);
        secondList.add(j + 1);

        for (int counter = 0; counter < firstList.size(); counter++) {
            try {
                if (saveOneField[firstList.get(counter)][secondList.get(counter)] == 1) {
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return true;
    }


    public void BotAttack() {
        Random attackI = new Random();
        int i = attackI.nextInt(TEN);

        Random attackJ = new Random();
        int j = attackJ.nextInt(TEN);

        if (saveOneField[i][j] == USED) {                                                                                  // Если рандом попал в ту же точку
            BotAttack();                                                                                                // выстрел в новую точку
        }

        if (saveOneField[i][j] == SHIP) {                                                                                  // Если попал
            oneField[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
            saveOneField[i][j] = USED;                                                                                    // использованная кнопка
            numberOfMyShips--;                                                                                          // на один корабль стало меньше
            if (i > 0) {                                                                                                // ВСЕ РЯДОМ НАХОДЯЩИЕСЯ ЯЧЕЙКИ СТАНОВЯТСЯ НЕДЕЙСТВИТЕЛЬНЫМИ
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
            BotAttack();                                                                                                // Если попал, повторный выстрел
        }

        if (saveOneField[i][j] == CLEAR) {                                                                                  // МИМО
            saveOneField[i][j] = USED;
            oneField[i][j].setContentAreaFilled(true);
            oneField[i][j].setBackground(Color.LIGHT_GRAY);
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
        int n = 0;
        while (n != 9) {
            Random ri = new Random();
            int k = ri.nextInt(TEN);
            Random rj = new Random();
            int l = rj.nextInt(TEN);
            if (k > 0) {
                if (fieldForRequired[k - 1][l] == SHIP) continue;

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

            n++;
            fieldForRequired[k][l] = SHIP;
        }
        return fieldForRequired;
    }
}
