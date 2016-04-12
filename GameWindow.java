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
public class GameWindow extends JFrame {
    JButton field_ms1player[][];
    JButton field_ms2player[][];

    int msPlayerField[][];                                                                                              // В НОВЫЙ КЛАСС. массив, в котором будет хранится состояние поля
    int msPlayer2Field[][];                                                                                             // 0 - пусто, 1 - корабль, 2 - использованная ячейка

    int numberOfMyShips = 0;                                                                                            // в класс
    int numberOfRivalShips = 0;

    public GameWindow() {
        super("Морской бой - Игра");
        setContentPane(new JLabel(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\background2.jpg")));
        setLayout(new BorderLayout());
        msPlayerField = new int[10][10];

        for (int i = 0; i < 10; i++) {                                                                                  // зануление массива для кораблей
            for (int j = 0; j < 10; j++) {
                msPlayerField[i][j] = 0;
            }
        }

        JPanel fieldForButton = new JPanel();
        fieldForButton.setOpaque(false);
        // Кнопка начала игры
        final JButton Play = new JButton(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\play.png"));
        Play.setContentAreaFilled(false);
        fieldForButton.add(Play);

        // Текстовое уведомление и настройки шрифта
        final JLabel startText1 = new JLabel("Расставьте свои корабли");
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

        // Поле для расстановки кораблей игрока 1
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
                field1.add(field_ms1player[i][j]);
            }
        }

        // обработчики событий для кнопок расстановки кораблей
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int tempi = i;
                final int tempj = j;

                field_ms1player[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int i = tempi;
                        int j = tempj;

                        if (numberOfMyShips < 8) {
                            if (msPlayerField[i][j] != 1) {
                                if (isCellAvailable(i, j)) {                                                            // проверка на ближайшие корабли
                                    field_ms1player[i][j].setContentAreaFilled(false);
                                    field_ms1player[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                                    msPlayerField[i][j] = 1;
                                    numberOfMyShips++;
                                }
                            } else {
                                field_ms1player[i][j].setBackground(Color.LIGHT_GRAY);
                                msPlayerField[i][j] = 0;
                                numberOfMyShips--;
                            }

                        } else {
                            if (msPlayerField[i][j] == 1) {
                                field_ms1player[i][j].setBackground(Color.LIGHT_GRAY);
                                msPlayerField[i][j] = 0;
                                numberOfMyShips--;
                            }
                        }
                    }

                });
            }
        }

        // Поле  кораблей противника
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

        // обработчик событий кнопки "НАЧАТЬ ИГРАТЬ"
        Play.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       if (numberOfMyShips == 8) {
                                           startText1.setText("Игра началась!");
                                           msPlayer2Field = new int[10][10];
                                           msPlayer2Field = generateShips();

                                           for (int i = 0; i < 10; i++) {
                                               for (int j = 0; j < 10; j++) {
                                                   field_ms2player[i][j].setEnabled(true);
                                                   ActionListener[] listeners = field_ms1player[i][j].getActionListeners();
                                                   for (int pos = 0; pos < listeners.length; pos++) {
                                                       field_ms1player[i][j].removeActionListener(listeners[pos]);
                                                   }
                                               }
                                           }

                                           for (int i = 0; i < 10; i++) {
                                               for (int j = 0; j < 10; j++) {
                                                   System.out.print(msPlayer2Field[i][j]);
                                               }
                                               System.out.println("");
                                           }
                                           Play.addActionListener(new ActionListener() {
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
                               }

        );

        numberOfRivalShips = 8;

        // обработчик событий для поля соперника
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int tempi = i;
                final int tempj = j;

                field_ms2player[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int i = tempi;
                        int j = tempj;

                        if (msPlayer2Field[i][j] == 1) {
                            msPlayer2Field[i][j] = 2;
                            field_ms2player[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship2.png"));
                            field_ms2player[i][j].setContentAreaFilled(false);
                            ActionListener[] listeners = field_ms2player[i][j].getActionListeners();
                            for (int pos = 0; pos < listeners.length; pos++) {
                                field_ms2player[i][j].removeActionListener(listeners[pos]);
                            }

                            numberOfRivalShips--;
                            if (i > 0) {
                                field_ms2player[i - 1][j].setEnabled(false);
                                if (j < 9) field_ms2player[i - 1][j + 1].setEnabled(false);
                                if (j > 0) field_ms2player[i - 1][j - 1].setEnabled(false);
                            }
                            if (i < 9) {
                                field_ms2player[i + 1][j].setEnabled(false);
                                if (j < 9) field_ms2player[i + 1][j + 1].setEnabled(false);
                                if (j > 0) field_ms2player[i + 1][j - 1].setEnabled(false);
                            }
                            if (j < 9) {
                                field_ms2player[i][j + 1].setEnabled(false);
                                if (i < 9) field_ms2player[i + 1][j + 1].setEnabled(false);
                                if (i > 0) field_ms2player[i - 1][j + 1].setEnabled(false);
                            }
                            if (j > 0) {
                                field_ms2player[i][j - 1].setEnabled(false);
                                if (i < 0) field_ms2player[i - 1][j - 1].setEnabled(false);
                                if (i > 9) field_ms2player[i + 1][j - 1].setEnabled(false);
                            }
                        }

                        if (msPlayer2Field[i][j] == 0) {
                            field_ms2player[i][j].setContentAreaFilled(true);
                            field_ms2player[i][j].setBackground(Color.LIGHT_GRAY);
                            field_ms2player[i][j].setEnabled(false);
                            msPlayer2Field[i][j] = 2;
                        }

                        BotAttack();

                        if (numberOfRivalShips == 0) {
                            startText1.setText("Вы выиграли!");
                            for (int k = 0; k < 10; k++) {
                                for (int l = 0; l < 10; l++) {
                                    field_ms2player[k][l].setEnabled(false);
                                    //setEnabled(false);                                                                  // ЗАБЛОЧИТЬ КНОПКУ "НАЧАЛО ИГРЫ", ЛИБО ЗАМЕНИТЬ ЕЕ НА "РЕПЛЕЙ"
                                }
                            }
                        }
                        if (numberOfMyShips == 0) {
                            startText1.setText("Вы проиграли!");
                            for (int k = 0; k < 10; k++) {
                                for (int l = 0; l < 10; l++) {
                                    field_ms2player[k][l].setEnabled(false);
                                }
                            }
                        }
                    }
                });
            }
        }

        add(startText1, BorderLayout.NORTH);
        add(field2, BorderLayout.EAST);
        add(fieldForButton, BorderLayout.SOUTH);
        add(field1, BorderLayout.WEST);
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
                if (msPlayerField[firstList.get(counter)][secondList.get(counter)] == 1) {
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return true;
    }


    public void BotAttack() {
        Random randomi = new Random();
        int i = randomi.nextInt(10);

        Random randomj = new Random();
        int j = randomj.nextInt(10);

        if (msPlayerField[i][j] == 2) {                                                                                  // Если рандом попал в ту же точку
            BotAttack();                                                                                                // выстрел в новую точку
        }

        if (msPlayerField[i][j] == 1) {                                                                                  // Если попал
            field_ms1player[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
            msPlayerField[i][j] = 2;                                                                                    // использованная кнопка
            numberOfMyShips--;                                                                                          // на один корабль стало меньше
            if (i > 0) {                                                                                                // ВСЕ РЯДОМ НАХОДЯЩИЕСЯ ЯЧЕЙКИ СТАНОВЯТСЯ НЕДЕЙСТВИТЕЛЬНЫМИ
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
            BotAttack();                                                                                                // Если попал, повторный выстрел
        }

        if (msPlayerField[i][j] == 0) {                                                                                  // МИМО
            msPlayerField[i][j] = 2;
            field_ms1player[i][j].setContentAreaFilled(true);
            field_ms1player[i][j].setBackground(Color.LIGHT_GRAY);
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
        while (n != 9) {
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
