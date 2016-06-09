package qwerty;

/**
 * Created by 0 on 013 13.04.16.
 */
public interface Defines {
    int TEN = 10;                                   // константная 10
    int SIZE_BUTTON = 45;                           // размер кнопки на поле
    int SIZE_LABEL = 50;                            // размер label
    int SIZE_FONT = 20;                             // размер шрифта
    int SIZE_WINDOW_HORIZONTAL = 1000;              // размер окна по горизонтали
    int SIZE_WINDOW_VERTICAL = 600;                 // размер окна по вертикали

    // метки для игрового процесса
    int CLEAR = 0;                                  // нет корабля
    int SHIP = 1;                                   // есть корабль
    int USED = 2;                                   // использованная ячейка
    int FIRE = 3;
    int SHOT_SECOND_BOT = 2;                        // будет стрелять 2 бот
    int SHOT_FIRST_BOT = 1;                         // будет стрелять 1 бот


    int[][] SHIPS1 = {
            {1, 0, 0, 0, 0, 1, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}
    };

    int[][] SHIPS2 = {
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 1}
    };

    int[][] SHIPS3 = {
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {1, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 1, 1, 0}
    };

    int[][] SHIPS4 = {
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
    };

    int[][] SHIPS5 = {
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 1, 1, 0, 0, 0}
    };

    int[][] SHIPS6 = {
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 1, 0, 0, 0, 0}
    };

    int[][][] ARRAY_SHIPS = {SHIPS1, SHIPS2, SHIPS3, SHIPS4, SHIPS5, SHIPS6};
}
