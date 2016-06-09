package qwerty;

import java.util.Random;

/**
 * Created by 0 on 007 07.06.16.
 */
public class JavaSort {
    public int[] sort(int[] array, int begin, int end){
        int [] index;
        index = qSort(array, begin, end);
        return index;

    }
    static Random rand = new Random();
    public int[] qSort(int[] array, int begin, int end) {
        int [] index = new int[array.length];
        int i = begin;
        int j = end;
        int temp = array[begin + rand.nextInt(end - begin + 1)];
        while (i <= j) {
            while (array[i] > temp) {
                i++;
            }
            while (array[j] < temp) {
                j--;
            }
            if (i <= j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }
        if (begin < j) {
            qSort(array, begin, j);
        }
        if (i < end) {
            qSort(array, i, end);
        }
        return index;
    }

}
