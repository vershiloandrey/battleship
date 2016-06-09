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
public class Result extends JFrame implements Defines {
    public Result() {
        super("Морской бой - Статистика");
        setContentPane(new JLabel(new ImageIcon("E:\\BattleShips\\src\\qwerty\\menuBackground.jpg")));
        String root = System.getProperty("user.dir");
        final File listFile = new File(root + "\\saves");

        JButton buttonSortJava = new JButton("sort java");
        JButton buttonSortScala = new JButton("sort scala");

        setLayout(new BorderLayout());


        Font font = new Font("Century Gothic", Font.BOLD, SIZE_FONT);
        Dimension labelSize = new Dimension(SIZE_LABEL, SIZE_LABEL);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(listFile.listFiles().length, 1));
        panel.setOpaque(false);

        JPanel panelForButton = new JPanel();
        panelForButton.setOpaque(false);

        final int[] arraySub = new int[listFile.listFiles().length];
        final int[] arrayN = new int[listFile.listFiles().length];
        JLabel[] namePlay = new JLabel[listFile.listFiles().length];
        JLabel north = new JLabel("№          sub  N");
        north.setVerticalAlignment(JLabel.CENTER);
        north.setForeground(Color.BLACK);
        north.setPreferredSize(labelSize);
        north.setFont(font);

        final Res[] res = new Res[listFile.listFiles().length];

        for (int i = 0; i < listFile.listFiles().length; i++) {
            res[i] = new Res();
            int[] num = readNum(i + 1);
            int sub = Math.abs(num[0] - num[1]);
            int n = 0;

            if (num[0] == 0) {
                n = num[0];
            }
            if (num[1] == 0) {
                n = num[1];
            }
            if (num[0] != 0 && num[1] != 0) {
                n = Math.max(num[0], num[1]);
            }

            res[i].seti(i);
            res[i].setn(n);
            res[i].setSub(sub);

            namePlay[i] = new JLabel((i + 1) + " game   " + sub + "   " + n);
            namePlay[i].setVerticalAlignment(JLabel.CENTER);
            namePlay[i].setForeground(Color.BLACK);
            namePlay[i].setPreferredSize(labelSize);
            namePlay[i].setFont(font);
            panel.add(namePlay[i]);
        }

        panelForButton.add(buttonSortJava);
        panelForButton.add(buttonSortScala);
        add(north, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panelForButton, BorderLayout.SOUTH);

        buttonSortJava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaSort sort = new JavaSort();
                long time = System.currentTimeMillis();
                for (int i = 0; i < listFile.listFiles().length; i++) {
                    arrayN[i] = res[i].getn();
                }

                sort.sort(arrayN, 0, arrayN.length - 1);
                time = System.currentTimeMillis() - time;
                System.out.println("Time for java sort: " + time);

            }
        });


        buttonSortScala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scala scala = new Scala();
                long time = System.currentTimeMillis();
                scala.sort(arraySub);
                time = System.currentTimeMillis() - time;
                System.out.println("Time for scala sort: " + time);
            }
        });
    }


    // загрузка количества подбитых кораблей
    public static int[] readNum(int i) {
        int[] nums = new int[2];
        String s;
        String[] arrayNums = new String[2];
        BufferedReader readerForNum;
        try {
            String root = System.getProperty("user.dir");
            File listFile = new File(root + "\\saves");

            readerForNum = new BufferedReader(new FileReader("saves\\" + i + "\\savesNum"));
            while ((s = readerForNum.readLine()) != null) {                                                               // считывание файла в массив строк lines
                arrayNums = s.split(" ");
            }
            nums[0] = Integer.parseInt(arrayNums[0]);
            nums[1] = Integer.parseInt(arrayNums[1]);
            return nums;
        } catch (IOException e) {
        }
        return null;
    }
}

class Res{
    private int i;
    private int sub;
    private int n;

    public void seti(int temp){
        i = temp;
    }
    public int geti(){
        return i;
    }
    public void setSub(int temp){
        sub = temp;
    }
    public int getSub(){
        return sub;
    }
    public void setn(int temp){
        n = temp;
    }
    public int getn(){
        return n;
    }
}
