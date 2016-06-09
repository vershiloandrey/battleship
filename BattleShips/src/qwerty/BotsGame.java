package qwerty;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by 0 on 008 08.04.16.
 */
public class BotsGame extends JFrame implements Defines {
    JButton oneField[][];
    JButton twoField[][];

    int saveOneField[][];                                                                                               // В НОВЫЙ КЛАСС. массив, в котором будет хранится состояние поля
    int saveTwoField[][];                                                                                               // 0 - пусто, 1 - корабль, 2 - использованная ячейка

    int numberOfMyShips = 20;                                                                                            // в класс
    int numberOfRivalShips = 20;

    public BotsGame() {
        super("Морской бой - Игра");
        setContentPane(new JLabel(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\gameBackground.jpg")));
        setLayout(new BorderLayout());

        saveOneField = new int[TEN][TEN];
        saveTwoField = new int[TEN][TEN];

        for (int i = 0; i < TEN; i++) {
            for (int j = 0; j < TEN; j++) {
                saveOneField[i][j] = CLEAR;
                saveTwoField[i][j] = CLEAR;
            }
        }

        while (true) {
            Random randomForOneField = new Random();
            int randomForShips = randomForOneField.nextInt(6);
            Random randomForTwoField = new Random();
            int randomForShips2 = randomForTwoField.nextInt(6);
            if (randomForShips != randomForShips2) {
                for (int i = 0; i < TEN; i++) {
                    for (int j = 0; j < TEN; j++) {
                        saveOneField[i][j] = ARRAY_SHIPS[randomForShips][i][j];
                        saveTwoField[i][j] = ARRAY_SHIPS[randomForShips2][i][j];
                    }
                }
                break;
            }
        }

        JPanel fieldForButton = new JPanel();
        fieldForButton.setOpaque(false);
        // Кнопка начала игры
        final JButton Play = new JButton(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\play.png"));
        Play.setContentAreaFilled(false);
        fieldForButton.add(Play);


        // Кнопка реплея
        final JButton Replay = new JButton(new ImageIcon("E:\\BattleShips\\src\\qwerty\\Repeat.png"));
        Replay.setContentAreaFilled(false);
        fieldForButton.add(Replay);

        // Текстовое уведомление и настройки шрифта
        final JLabel startText = new JLabel("Начните игру");
        Icon icon = UIManager.getIcon("OptionPane.informationIcon");

        Font font = new Font("Century Gothic", Font.BOLD, SIZE_FONT);
        Border solidBorder = BorderFactory.createLineBorder(Color.WHITE, 5);
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


                try {                                                                            // Очистка содержимого файлов
                    String root = System.getProperty("user.dir");
                    File listFile = new File(root + "\\saves");

                    FileWriter fstream1 = new FileWriter("saves\\" +  listFile.listFiles().length + "\\savesBattleShips1");
                    BufferedWriter out1 = new BufferedWriter(fstream1);
                    out1.write("");
                    out1.close();

                    FileWriter fstream2 = new FileWriter("saves\\" +  listFile.listFiles().length + "\\savesBattleShips2");
                    BufferedWriter out2 = new BufferedWriter(fstream2);
                    out2.write("");
                    out2.close();
                } catch (Exception exception) {
                    System.err.println("Error in file cleaning: " + exception.getMessage());
                }


                 // отображение кораблей
                for (int i = 0; i < TEN; i++) {
                    for (int j = 0; j < TEN; j++) {
                        if (saveOneField[i][j] == SHIP) {
                            oneField[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                        }
                        if (saveTwoField[i][j] == SHIP) {
                            twoField[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                        }
                    }
                }


                while (numberOfMyShips != 0 && numberOfRivalShips != 0) {
                    Random attackI = new Random();
                    int i = attackI.nextInt(TEN);

                    Random attackJ = new Random();
                    int j = attackJ.nextInt(TEN);

                    BotAttack(SHOT_FIRST_BOT, i, j);

                    attackI = new Random();
                    i = attackI.nextInt(TEN);

                    attackJ = new Random();
                    j = attackJ.nextInt(TEN);
                    BotAttack(SHOT_SECOND_BOT, i, j);
                }
                if (numberOfMyShips == 0) {
                    startText.setText("Выиграл 2 бот");
                    write(saveOneField, 1, numberOfMyShips, numberOfRivalShips);
                    write(saveTwoField, 2, numberOfMyShips, numberOfRivalShips);
                } else {
                    startText.setText("Выиграл 1 бот");
                    write(saveOneField, 1, numberOfMyShips, numberOfRivalShips);
                    write(saveTwoField, 2, numberOfMyShips, numberOfRivalShips);
                }

                for (int i = 0; i < TEN; i++) {
                    for (int j = 0; j < TEN; j++) {
                        saveOneField[i][j] = CLEAR;
                        saveTwoField[i][j] = CLEAR;
                    }
                }

                Play.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BotsGame botsGame = new BotsGame();
                        botsGame.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
                        botsGame.setResizable(false);
                        botsGame.setLocationRelativeTo(null);
                        botsGame.setVisible(true);
                        for (int i = 0; i < TEN; i++) {
                            for (int j = 0; j < TEN; j++) {
                                saveOneField[i][j] = CLEAR;
                                saveTwoField[i][j] = CLEAR;
                            }
                        }
                        dispose();
                    }
                });
            }
        });


        Replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
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


                    // отображение кораблей
                    for (int i = 0; i < TEN; i++) {
                        for (int j = 0; j < TEN; j++) {
                            if (saveOneField[i][j] == SHIP) {
                                oneField[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                            }
                            if (saveTwoField[i][j] == SHIP) {
                                twoField[i][j].setIcon(new ImageIcon(getClass().getResource("Ship.png")));
                            }
                        }
                    }


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
                } catch(FileNotFoundException exception){}
            }
        });

        add(startText, BorderLayout.NORTH);
        add(twoFieldPanel, BorderLayout.EAST);
        add(fieldForButton, BorderLayout.SOUTH);
        add(oneFieldPanel, BorderLayout.WEST);
    }


    public void BotAttack(int variable, int i, int j) {
        if (numberOfMyShips == 0 || numberOfRivalShips == 0) {
            return;
        }
        if (variable == SHOT_FIRST_BOT) {
            if (saveOneField[i][j] == USED) {                                                                                  // Если рандом попал в ту же точку
                Random attackI = new Random();
                int secondAttackI = attackI.nextInt(TEN);

                Random attackJ = new Random();
                int secondAttackJ = attackJ.nextInt(TEN);

                BotAttack(variable, secondAttackI, secondAttackJ);                                                                                                // выстрел в новую точку
            }

            if (saveOneField[i][j] == SHIP) {                                                                                  // Если попал
                oneField[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
                saveOneField[i][j] = USED;                                                                                    // использованная кнопка
                numberOfMyShips--;                                                                                          // на один корабль стало меньше

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        try {
                            if (saveOneField[k][l] == SHIP) {
                                BotAttack(variable, k, l);
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


        if (variable == SHOT_SECOND_BOT) {
            if (saveTwoField[i][j] == USED) {                                                                                  // Если рандом попал в ту же точку
                Random attackI = new Random();
                int secondAttackI = attackI.nextInt(TEN);

                Random attackJ = new Random();
                int secondAttackJ = attackJ.nextInt(TEN);

                BotAttack(variable, secondAttackI, secondAttackJ);                                                                                                // выстрел в новую точку
            }

            if (saveTwoField[i][j] == SHIP) {                                                                                  // Если попал
                twoField[i][j].setIcon(new ImageIcon("D:\\qwe\\out\\production\\qwe\\qwerty\\fireship.png"));
                saveTwoField[i][j] = USED;                                                                                    // использованная кнопка
                numberOfRivalShips--;                                                                                          // на один корабль стало меньше

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        try {
                            if (saveTwoField[k][l] == SHIP) {
                                BotAttack(variable, k, l);
                            }
                        } catch (ArrayIndexOutOfBoundsException exception) {
                        }
                    }
                }

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        try {
                            twoField[k][l].setContentAreaFilled(true);
                            twoField[k][l].setBackground(Color.LIGHT_GRAY);
                            saveTwoField[k][l] = USED;
                        } catch (ArrayIndexOutOfBoundsException exception) {
                        }
                    }
                }


            }

            if (saveTwoField[i][j] == CLEAR) {                                                                                  // МИМО
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
            Random placeToI = new Random();
            int k = placeToI.nextInt(TEN);
            Random placeToJ = new Random();
            int l = placeToJ.nextInt(TEN);
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
            numberShip++;
            fieldForRequired[k][l] = SHIP;
        }
        return fieldForRequired;
    }

    // запись в файл
    public static void write(int[][] ms, int flag, int num1, int num2) {

        try {
            String root = System.getProperty("user.dir");
            File listFile = new File(root + "\\saves");

            FileWriter fstream1 = new FileWriter("saves\\" +  listFile.listFiles().length + "\\savesNum");
            BufferedWriter out1 = new BufferedWriter(fstream1);
            out1.write("");
            out1.close();
        } catch (Exception e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }

        File file;
        String root = System.getProperty("user.dir");
        File listFile = new File(root + "\\saves");

        if(flag == 1) {
            file = new File("saves\\" +  listFile.listFiles().length + "\\savesBattleShips1");
        } else {
            if (flag == 2) {
                file = new File("saves\\" +  listFile.listFiles().length + "\\savesBattleShips2");
            } else {
                return;
            }
        }
        File fileForNum = new File("saves\\" +  listFile.listFiles().length + "\\savesNum");

        try{
            if (!file.exists()){
                file.createNewFile();
            }

            if (!fileForNum.exists()){
                fileForNum.createNewFile();
            }

            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile(), true), "UTF-8"));
            PrintWriter outForNum = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileForNum.getAbsoluteFile(), true), "UTF-8"));
            try{
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

            readerForNum = new BufferedReader(new FileReader("saves\\" +  listFile.listFiles().length + "\\savesNum"));
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

        if (flag == 1) {
            reader = new BufferedReader(new FileReader("saves\\" +  listFile.listFiles().length + "\\savesBattleShips1"));

        } else{
            if(flag == 2){
                reader = new BufferedReader(new FileReader("saves\\" +  listFile.listFiles().length + "\\savesBattleShips2"));
            } else{
                return null;
            }
        }

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






