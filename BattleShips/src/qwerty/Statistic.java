package qwerty;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by 0 on 013 13.04.16.
 */
public class Statistic extends JFrame implements Defines {
    public Statistic() {
        super("Морской бой - Режим игры");
        setContentPane(new JLabel(new ImageIcon("E:\\BattleShips\\src\\qwerty\\gameBackground.jpg")));
        String root = System.getProperty("user.dir");
        File listFile = new File(root+"\\saves");
        setLayout(new GridLayout(3, 1));

        int sumsub = 0;
        int max = 0;
        int[] num;
        for (int i = 1; i < listFile.listFiles().length; i++) {
            num = readNum(i);
            int sub = Math.abs(num[0] - num[1]);
            max =+ Math.max(num[0], num[1]);
            sumsub =+ sub;
        }
        sumsub = sumsub / listFile.listFiles().length;
        max = max / listFile.listFiles().length;


        Font font = new Font("Century Gothic", Font.BOLD, SIZE_FONT);
        Dimension labelSize = new Dimension(SIZE_LABEL, SIZE_LABEL);



        JLabel numGames = new JLabel("Игр сыграно:   " + listFile.listFiles().length);
        JLabel avagareSub = new JLabel("Средняя разница количества кораблей:   " + sumsub);
        JLabel maxShip = new JLabel("Среднее количество оставшихся кораблей выигрывающего игрока:   " + max);

        numGames.setVerticalAlignment(JLabel.CENTER);
        numGames.setHorizontalAlignment(JLabel.CENTER);
        numGames.setForeground(Color.BLACK);
        numGames.setPreferredSize(labelSize);
        numGames.setFont(font);

        avagareSub.setVerticalAlignment(JLabel.CENTER);
        avagareSub.setHorizontalAlignment(JLabel.CENTER);
        avagareSub.setForeground(Color.BLACK);
        avagareSub.setPreferredSize(labelSize);
        avagareSub.setFont(font);

        maxShip.setVerticalAlignment(JLabel.CENTER);
        maxShip.setHorizontalAlignment(JLabel.CENTER);
        maxShip.setForeground(Color.BLACK);
        maxShip.setPreferredSize(labelSize);
        maxShip.setFont(font);

        add(numGames);
        add(avagareSub);
        add(maxShip);
    }




    public static int[] readNum(int i){
        int[] nums = new int[2];
        String s;
        String[] arrayNums = new String[2];
        BufferedReader readerForNum;
        try {
            String root = System.getProperty("user.dir");
            File listFile = new File(root + "\\saves");

            readerForNum = new BufferedReader(new FileReader("saves\\" +  i + "\\savesNum"));
            while ((s = readerForNum.readLine()) != null) {                                                               // считывание файла в массив строк lines
                arrayNums = s.split(" ");
            }
            nums[0] = Integer.parseInt(arrayNums[0]);
            nums[1] = Integer.parseInt(arrayNums[1]);
            return nums;
        }catch (IOException e){}
        return null;
    }




}
