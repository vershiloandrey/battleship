package qwerty;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Created by 0 on 009 09.06.16.
 */
public class Decoder extends JFrame implements Defines {
public Decoder() {
    super("Морской бой - Расшифровка сохранения");
    setContentPane(new JLabel(new ImageIcon("E:\\BattleShips\\src\\qwerty\\gameBackground.jpg")));
    setLayout(new GridLayout(3, 1));

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



