package qwerty;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * Created by 0 on 015 15.03.16.
 */

class MusicThing implements Runnable {
    public void run(){
        try {
            File soundFile = new File("music.wav");                                                 //Звуковой файл


            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            while(true){
                Clip clip = AudioSystem.getClip();

                //Загружаем наш звуковой поток в Clip
                clip.open(ais);

                clip.setFramePosition(0);                                                           //устанавливаем указатель на старт
                clip.start();                                                                       //Поехали!!!

                Thread.sleep(clip.getMicrosecondLength() / 1000);
                clip.stop();                                                                        //Останавливаем
                clip.close();  //Закрываем
                run();
            }
        } catch(IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        } catch (InterruptedException exc) {}
    }
}




public class main extends JPanel implements Defines {
    public main() {
        setOpaque(false);
    }

    static MusicThing musicThing;

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

        JButton buttonStatistic = new JButton("Statistic");
        buttonStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statistic statistic = new Statistic();
                statistic.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
                statistic.setResizable(false);
                statistic.setLocationRelativeTo(null);
                statistic.setVisible(true);
            }
        });


        JButton buttonResult = new JButton("Result");
        buttonResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Result result = new Result();
                result.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
                result.setResizable(false);
                result.setLocationRelativeTo(null);
                result.setVisible(true);
            }
        });


        JButton buttonSort = new JButton("Test sorting");
        buttonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arrayForScala1 = new int[1000];
                int[] arrayForJava1 = new int[1000];
                int[] arrayForScala2 = new int[10000];
                int[] arrayForJava2 = new int[10000];
                int[] arrayForScala3 = new int[100000];
                int[] arrayForJava3 = new int[100000];

                for (int i = 0; i < 1000; i++) {
                    Random random = new Random();
                    arrayForJava1[i] = random.nextInt(1000);
                    random = new Random();
                    arrayForScala1[i] = random.nextInt(1000);
                }

                for (int i = 0; i < 10000; i++) {
                    Random random = new Random();
                    arrayForJava2[i] = random.nextInt(10000);
                    random = new Random();
                    arrayForScala2[i] = random.nextInt(10000);
                }

                for (int i = 0; i < 100000; i++) {
                    Random random = new Random();
                    arrayForJava3[i] = random.nextInt(100000);
                    random = new Random();
                    arrayForScala3[i] = random.nextInt(100000);
                }
                sortS(arrayForScala1);
                sortS(arrayForScala2);
                sortS(arrayForScala3);
                sortJ(arrayForJava1);
                sortJ(arrayForJava2);
                sortJ(arrayForJava3);
            }
        });


        JButton buttonDecoder = new JButton("Decoder");
        buttonDecoder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfMyShips = 20;
                int numberOfRivalShips = 20;
                String numberFirst = "";
                String numberSecond = "";

                try {
                    int[][][] savesArraysFirst = read(1);
                    int[][][] savesArraysTwo = read(2);
                    numberOfMyShips = readNum()[0];
                    numberOfRivalShips = readNum()[1];

                    numberFirst = Integer.toString(numberOfMyShips);
                    numberSecond = Integer.toString(numberOfRivalShips);

                    for (int k = 0; k < savesArraysFirst.length; k++) {
                        for (int i = 0; i < TEN; i++) {
                            for (int j = 0; j < TEN; j++) {
                                if (k != 0) {
                                    if (savesArraysTwo[k][i][j] != savesArraysTwo[k - 1][i][j]) {
                                        System.out.println("Игрок 1 стреляет по координатам i: " + i + "  j: " + j);
                                    }
                                    if (savesArraysFirst[k][i][j] != savesArraysFirst[k - 1][i][j]){
                                        System.out.println("Игрок 2 стреляет по координатам i: " + i + "  j: " + j);
                                    }
                                }
                            }
                        }
                    }

                    if (numberOfMyShips > numberOfRivalShips){
                        System.out.println("Первый игрок одержал победу!");
                    } else{
                        System.out.println("Второй игрок одержал победу!");
                    }

                }catch(FileNotFoundException exception){}
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
        //menu.add(buttonInfo);
        menu.add(buttonStatistic);
        menu.add(buttonResult);
        //menu.add(buttonSort);
        //menu.add(buttonDecoder);
        menu.add(buttonExit);
        mainFrame.add(menu);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(SIZE_WINDOW_HORIZONTAL, SIZE_WINDOW_VERTICAL);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);


        musicThing = new MusicThing();
        Thread thread = new Thread(musicThing);
        thread.start();
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




    public static void sortS(int[] array){
        Scala scala = new Scala();
        long time = System.currentTimeMillis();
        scala.sort(array);
        time = System.currentTimeMillis() - time;
        System.out.println("Time for scala " + array.length + " array sort: " + time + "ms");
    }
    public static void sortJ(int[] array){
        JavaSort sort = new JavaSort();
        long time = System.currentTimeMillis();
        sort.sort(array, 0, array.length - 1);
        time = System.currentTimeMillis() - time;
        System.out.println("Time for java " + array.length + " array sort: " + time + "ms");
    }

    public void paint(Graphics g) {
        Image a = Toolkit.getDefaultToolkit().getImage("D:\\qwe\\out\\production\\qwe\\qwerty\\menuBackground.jpg");
        g.drawImage(a, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }
}
