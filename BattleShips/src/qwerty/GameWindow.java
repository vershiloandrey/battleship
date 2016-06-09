package qwerty;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 0 on 022 22.03.16.
 */

// Класс окна расстановки кораблей
public class GameWindow extends JFrame implements Defines {
    JButton oneField[][];
    JButton twoField[][];

    JLabel numberFirst;
    JLabel numberSecond;

    int saveOneField[][];                                                                                               // В НОВЫЙ КЛАСС. массив, в котором будет хранится состояние поля
    int saveTwoField[][];                                                                                             // 0 - пусто, 1 - корабль, 2 - использованная ячейка

    int numberOfMyShips = 20;                                                                                            // в класс
    int numberOfRivalShips = 20;

    public GameWindow() {
        super("Морской бой - Игра");
        setContentPane(new JLabel(new ImageIcon("E:\\BattleShips\\src\\qwerty\\gameBackground.jpg")));
        setLayout(new BorderLayout());
        saveOneField = new int[TEN][TEN];
        saveTwoField = new int[TEN][TEN];

        for (int i = 0; i < TEN; i++) {                                                                                  // зануление массива для кораблей
            for (int j = 0; j < TEN; j++) {
                saveOneField[i][j] = CLEAR;
            }
        }

        JPanel fieldForButton = new JPanel();
        fieldForButton.setOpaque(false);

        // Кнопка начала игры
        final JButton Play = new JButton(new ImageIcon("E:\\BattleShips\\src\\qwerty\\play.png"));
        Play.setContentAreaFilled(false);
        fieldForButton.add(Play);

        // Кнопка реплея
        final JButton Replay = new JButton(new ImageIcon("E:\\BattleShips\\src\\qwerty\\Repeat.png"));
        Replay.setContentAreaFilled(false);
        fieldForButton.add(Replay);


        JPanel fieldForLabel = new JPanel();
        fieldForLabel.setLayout(new GridLayout(1, 3));
        fieldForLabel.setOpaque(false);
        // Для счета
        numberFirst = new JLabel(Integer.toString(numberOfMyShips));
        numberSecond = new JLabel(Integer.toString(numberOfRivalShips));

        // Текстовое уведомление и настройки шрифта
        final JLabel startText = new JLabel("Нажмите play для игры");
        Icon icon = UIManager.getIcon("OptionPane.informationIcon");

        Font font = new Font("Century Gothic", Font.BOLD, SIZE_FONT);
        Border solidBorder = BorderFactory.createLineBorder(Color.WHITE, 5);
        startText.setIcon(icon);
        Dimension labelSize = new Dimension(SIZE_LABEL, SIZE_LABEL);

        startText.setVerticalAlignment(JLabel.CENTER);
        startText.setHorizontalAlignment(JLabel.CENTER);
        startText.setForeground(Color.BLACK);
        startText.setPreferredSize(labelSize);
        startText.setFont(font);

        numberFirst.setHorizontalAlignment(JLabel.CENTER);
        numberFirst.setForeground(Color.BLACK);
        numberFirst.setPreferredSize(labelSize);
        numberFirst.setFont(font);

        numberSecond.setHorizontalAlignment(JLabel.CENTER);
        numberSecond.setForeground(Color.BLACK);
        numberSecond.setPreferredSize(labelSize);
        numberSecond.setFont(font);

        fieldForLabel.add(numberFirst);
        fieldForLabel.add(startText);
        fieldForLabel.add(numberSecond);

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

        Random random = new Random();
        int randomForShips = random.nextInt(6);
        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                saveOneField[i][j] = ARRAY_SHIPS[randomForShips][i][j];
            }
        }


        // отображение кораблей
        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                if (saveOneField[i][j] == SHIP) {
                    oneField[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                }
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
                                       startText.setText("Игра началась!");

                                       try {                                                                            // Очистка содержимого файлов
                                          /* FileWriter fstream1 = new FileWriter("savesBattleShips1");
                                           BufferedWriter out1 = new BufferedWriter(fstream1);
                                           out1.write("");
                                           out1.close();

                                           FileWriter fstream2 = new FileWriter("savesBattleShips2");
                                           BufferedWriter out2 = new BufferedWriter(fstream2);
                                           out2.write("");
                                           out2.close();*/
                                       } catch (Exception exception) {
                                           System.err.println("Error in file cleaning: " + exception.getMessage());
                                       }

                                       Random random = new Random();
                                       int randomForShips = random.nextInt(6);
                                       for (int i = 0; i < TEN; i++) {
                                           for (int j = 0; j < TEN; j++) {
                                               saveTwoField[i][j] = ARRAY_SHIPS[randomForShips][i][j];
                                           }
                                       }

                                       for (int i = 0; i < TEN; i++) {
                                           for (int j = 0; j < TEN; j++) {
                                               twoField[i][j].setEnabled(true);
                                               ActionListener[] listeners = oneField[i][j].getActionListeners();
                                               for (int pos = 0; pos < listeners.length; pos++) {
                                                   oneField[i][j].removeActionListener(listeners[pos]);
                                               }
                                           }
                                       }

                                       String root = System.getProperty("user.dir");
                                       File listFile = new File(root + "\\saves");

                                       File folder = new File("saves\\" +  (listFile.listFiles().length+1));
                                       folder.mkdir();

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
        );

        Replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (startText.getText() != "Игра началась!") {
                        startText.setText("Игра возобновлена.");
                        for (int i = 0; i < TEN; i++) {
                            for (int j = 0; j < TEN; j++) {
                                twoField[i][j].setEnabled(true);
                            }
                        }

                        int[][][] savesArraysFirst = read(1);
                        int[][][] savesArraysTwo = read(2);
                        numberOfMyShips = readNum()[0];
                        numberOfRivalShips = readNum()[1];


                        numberFirst.setText(Integer.toString(numberOfMyShips));
                        numberSecond.setText(Integer.toString(numberOfRivalShips));

                        for (int k = 0; k < savesArraysFirst.length; k++) {
                            for (int i = 0; i < TEN; i++) {
                                for (int j = 0; j < TEN; j++) {
                                    saveOneField[i][j] = savesArraysFirst[k][i][j];
                                    saveTwoField[i][j] = savesArraysTwo[k][i][j];

                                    oneField[i][j].setIcon(null);
                                    if (saveOneField[i][j] != CLEAR) {
                                        if (saveOneField[i][j] == SHIP) {
                                            oneField[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                                        }
                                        if (saveOneField[i][j] == USED) {
                                            oneField[i][j].setContentAreaFilled(true);
                                            oneField[i][j].setBackground(Color.LIGHT_GRAY);
                                        }
                                        if (saveOneField[i][j] == FIRE) {
                                            oneField[i][j].setIcon(new ImageIcon("E:\\BattleShips\\src\\qwerty\\fireship.png"));
                                            oneField[i][j].setBackground(Color.LIGHT_GRAY);
                                        }
                                    }
                                    twoField[i][j].setIcon(null);
                                    if (saveTwoField[i][j] != CLEAR) {
                                        if (saveTwoField[i][j] == USED) {
                                            twoField[i][j].setContentAreaFilled(true);
                                            twoField[i][j].setBackground(Color.LIGHT_GRAY);
                                            twoField[i][j].setEnabled(false);
                                        }
                                        if (saveTwoField[i][j] == FIRE) {
                                            twoField[i][j].setIcon(new ImageIcon("E:\\BattleShips\\src\\qwerty\\fireship.png"));
                                            twoField[i][j].setBackground(Color.LIGHT_GRAY);
                                            twoField[i][j].setEnabled(false);
                                        }
                                    }
                                }
                            }
                        }

                        write(saveOneField, 1, numberOfMyShips, numberOfRivalShips);
                        write(saveTwoField, 2, numberOfMyShips, numberOfRivalShips);
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
                            write(saveOneField, 1, numberOfMyShips, numberOfRivalShips);
                            write(saveTwoField, 2, numberOfMyShips, numberOfRivalShips);
                        }
                    });

                } catch(FileNotFoundException exception){}
            }
        });

        numberOfRivalShips = 20;
        numberOfMyShips = 20;

        System.out.println(numberOfMyShips+" "+numberOfRivalShips);
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

                        if (saveTwoField[i][j] == SHIP) {
                            saveTwoField[i][j] = FIRE;
                            numberOfRivalShips--;
                            twoField[i][j].setIcon(new ImageIcon("E:\\BattleShips\\src\\qwerty\\fireship.png"));
                            twoField[i][j].setContentAreaFilled(false);
                            ActionListener[] listeners = twoField[i][j].getActionListeners();
                            for (int pos = 0; pos < listeners.length; pos++) {
                                twoField[i][j].removeActionListener(listeners[pos]);
                            }
                            validateShip(i, j);
                        }

                        if (saveTwoField[i][j] == CLEAR) {
                            twoField[i][j].setContentAreaFilled(true);
                            twoField[i][j].setBackground(Color.LIGHT_GRAY);
                            twoField[i][j].setEnabled(false);
                            saveTwoField[i][j] = USED;
                            Random attackI = new Random();
                            int randomI = attackI.nextInt(TEN);

                            Random attackJ = new Random();
                            int randomJ = attackJ.nextInt(TEN);
                            try {
                                Thread.sleep(1000);
                            }catch(InterruptedException exception){
                            }
                            BotAttack(randomI, randomJ);

                        }


                        numberFirst.setText(Integer.toString(numberOfMyShips));
                        numberSecond.setText(Integer.toString(numberOfRivalShips));

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


                        write(saveOneField, 1, numberOfMyShips, numberOfRivalShips);
                        write(saveTwoField, 2, numberOfMyShips, numberOfRivalShips);
                    }
                });
            }
        }

        add(fieldForLabel, BorderLayout.NORTH);
        add(twoFieldPanel, BorderLayout.EAST);
        add(fieldForButton, BorderLayout.SOUTH);
        add(oneFieldPanel, BorderLayout.WEST);
    }

    void validateShip(int i, int j) {
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                try {
                    if (saveTwoField[k][l] == SHIP) {
                        return;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                }
            }
        }
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                try {
                    if (saveTwoField[k][l] == FIRE) {
                        if (k == i && l == j) {
                            continue;
                        }
                        saveTwoField[i][j] = USED;
                        validateShip(k, l);
                    }
                    twoField[k][l].setContentAreaFilled(true);
                    twoField[k][l].setBackground(Color.LIGHT_GRAY);
                    saveTwoField[k][l] = USED;
                    twoField[k][l].setEnabled(false);
                    twoField[i][j].setEnabled(false);
                } catch (ArrayIndexOutOfBoundsException exception) {
                }
            }
        }

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


    public void BotAttack(int i, int j) {

        if (saveOneField[i][j] == USED) {                                                                                  // Если рандом попал в ту же точку
            Random attackI = new Random();
            int secondAttackI = attackI.nextInt(TEN);

            Random attackJ = new Random();
            int secondAttackJ = attackJ.nextInt(TEN);
            BotAttack(secondAttackI, secondAttackJ);                                                                                                // выстрел в новую точку
        }

        if (saveOneField[i][j] == SHIP) {                                                                                  // Если попал
            oneField[i][j].setIcon(new ImageIcon("E:\\BattleShips\\src\\qwerty\\fireship.png"));
            saveOneField[i][j] = USED;                                                                                    // использованная кнопка
            numberOfMyShips--;                                                                                          // на один корабль стало меньше

            for (int k = i - 1; k <= i + 1; k++) {
                for (int l = j - 1; l <= j + 1; l++) {
                    try {
                        if (saveOneField[k][l] == SHIP) {
                            BotAttack(k, l);
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                    }
                }
            }

            for (int k = i - 1; k <= i + 1; k++) {
                for (int l = j - 1; l <= j + 1; l++) {
                    try {
                        oneField[k][l].setContentAreaFilled(true);
                        oneField[k][l].setBackground(Color.LIGHT_GRAY);
                        saveOneField[k][l] = USED;
                    } catch (ArrayIndexOutOfBoundsException exception) {
                    }
                }
            }
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


    // запись в файл
    public static void write(int[][] ms, int flag, int num1, int num2) {
        String root = System.getProperty("user.dir");
        File listFile = new File(root + "\\saves");

        try {
            FileWriter fstream1 = new FileWriter("saves\\" +  (listFile.listFiles().length) + "\\savesNum");
            BufferedWriter out1 = new BufferedWriter(fstream1);
            out1.write("");
            out1.close();
        } catch (Exception e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }

        File file;
        file = new File("saves\\" +  (listFile.listFiles().length) + "\\savesBattleShips" + flag);
        File fileForNum = new File("saves\\" +  (listFile.listFiles().length) + "\\savesNum");

        try {
            if (!file.exists()){
                file.createNewFile();
            }

            if (!fileForNum.exists()){
                fileForNum.createNewFile();
            }
            
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile(), true), "UTF-8"));
            PrintWriter outForNum = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileForNum.getAbsoluteFile(), true), "UTF-8"));
            try {
                for (int i = 0; i < TEN; i++) {
                    for (int j = 0; j < TEN; j++) {
                        out.print(ms[i][j]);
                        if (j < 9)
                            out.print(" ");
                    }
                    out.println("");
                }
            } finally {
                out.close();
            }

            try {
                outForNum.print(num1 + " " + num2);
            } finally {
                outForNum.close();
            }
        }catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }


// загрузка количества подбитых кораблей
    public static int[] readNum(){
        int[] nums = new int[2];
        String s;
        String[] arrayNums = new String[2];
        BufferedReader readerForNum;
        try {
            String root = System.getProperty("user.dir");
            File listFile = new File(root + "\\saves");

            readerForNum = new BufferedReader(new FileReader("saves\\" +  (listFile.listFiles().length) + "\\savesNum"));
            while ((s = readerForNum.readLine()) != null) {                                                               // считывание файла в массив строк lines
                arrayNums = s.split(" ");
            }
            nums[0] = Integer.parseInt(arrayNums[0]);
            nums[1] = Integer.parseInt(arrayNums[1]);
            return nums;
        }catch (IOException e){}
        return null;
    }



// загрузка из файла
    public static int[][][] read(int flag) throws FileNotFoundException {
        BufferedReader reader;
        int[][] intResult;
        ArrayList<String[]> arrayLines = new ArrayList<String[]>();


        String root = System.getProperty("user.dir");
        File listFile = new File(root + "\\saves");

        reader = new BufferedReader(new FileReader("saves\\" +  (listFile.listFiles().length) + "\\savesBattleShips" + flag));

        String s;
        int i = 0;
        try {
            while(i < TEN) {
                while ((s = reader.readLine()) != null) {                                                               // считывание файла в массив строк lines
                    arrayLines.add(s.split(" "));
                    i++;
                }
            }

            intResult = new int[arrayLines.size()][TEN];

            for (int j = 0; j < arrayLines.size(); j++) {
                for (int k = 0; k < TEN; k++) {
                    intResult[j][k] = Integer.parseInt(arrayLines.get(j)[k]);                                                   // String to int
                }
            }

            int sizeResult = arrayLines.size() / TEN;
            int[][][] resultArrays = splitArray(intResult, sizeResult);

            return resultArrays;
        }catch (IOException e){}
        return null;
    }


    // разбиение массива на 10х10
    public static int[][][] splitArray(int[][] firstArray, int size){
        int[][][] resultArray = new int[size][TEN][TEN];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < TEN; j++) {
                for (int k = 0; k < TEN; k++) {
                    resultArray[i][j][k] = firstArray[i*TEN + j][k];
                }
            }
        }
        return resultArray;
    }
}
