package homeworks.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/* +++++++++++++++++++++++++++++++++++++++++++++++ */
/**/    //Изменения в коде, обрамлены таким образом
/* +++++++++++++++++++++++++++++++++++++++++++++++ */

public class TicTacToe {

    static final int SIZE = 3;

    static final char DOT_EMPTY = '•';
    static final char DOT_HUMAN = 'X';
    static final char DOT_AI = 'O';

    static final String HEADER_FIRST_EMPTY = "♥";
    static final String EMPTY = " ";

    static char[][] map = new char[SIZE][SIZE];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    /* +++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    static int lastMoveRow, lastMoveCol;    // последний ход по строке и столбцу
    /* +++++++++++++++++++++++++++++++++++++++++++++++ */

    public static void main(String[] args) {
        //turnGame();


        int[][] m ={{3,1}, {6,3}, {1,0}, {4,5}, {6,2}};

        for(int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(m[i]));
        }
    }

    private static void turnGame() {
        initMap();
        printMap();
        playGame();
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        printMapHeader();
        printMapRow();
    }

    private static void printMapHeader() {
        System.out.print(HEADER_FIRST_EMPTY + EMPTY);
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
        }
        System.out.println();
    }

    private static void printMapNumber(int i) {
        System.out.print(i + 1 + EMPTY);
    }

    private static void printMapRow() {
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY);
            }
            System.out.println();
        }
    }


    private static void playGame() {

        while (true) {
            humanTurn();
            printMap();
            checkEnd(DOT_HUMAN);

            aiTurn();
            printMap();
            checkEnd(DOT_AI);
        }
    }

    private static void humanTurn() {
        int rowNumber;
        int colNumber;

        System.out.println("\nХод человека! Введите номер строки и столбца!");
        do {
            System.out.print("Строка = ");
            rowNumber = scanner.nextInt();
            System.out.print("Столбец = ");
            colNumber = scanner.nextInt();
        } while (!isCellValid(rowNumber, colNumber));

        map[rowNumber - 1][colNumber - 1] = DOT_HUMAN;

        /* +++++++++++++++++++++++++++++++++++++++++++++++ */
        /**/    saveLastMove(rowNumber -1, colNumber - 1); // сохраняем последний ход
        /* +++++++++++++++++++++++++++++++++++++++++++++++ */
    }

    private static boolean isCellValid(int rowNumber, int colNumber, boolean isAI) {

        if (!isAI && ((rowNumber < 1) || (rowNumber > SIZE) || (colNumber < 1) || (colNumber > SIZE))) {
            System.out.println("\nПроверьте значения строки и столбца");
            return false;
        }

        if (map[rowNumber - 1][colNumber - 1] != DOT_EMPTY) {
            if (!isAI) {
                System.out.println("\nВы выбрали занятую ячейку");
            }
            return false;
        }

        return true;
    }

    private static boolean isCellValid(int rowNumber, int colNumber) {
        return isCellValid(rowNumber, colNumber, false);
    }

    /* +++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    private static void saveLastMove(int row, int col) {
    /**/        lastMoveRow = row;
    /**/        lastMoveCol = col;
    /**/    }
    /* +++++++++++++++++++++++++++++++++++++++++++++++ */

    private static void checkEnd(char symbol) {

        boolean isEnd = false;

        if(checkWin(symbol)) {
            String winMessage;

            if(symbol == DOT_HUMAN) {
                winMessage = "I`ll be back!";
            }
            else {
                winMessage = "Восстание близко! AI победил";
            }

            isEnd = true;
            System.out.println(winMessage);
        }

        if(!isEnd && isMapFull()) {
            System.out.println("Ничья!");
            isEnd = true;
        }

        if(isEnd) {
            System.exit(0);
        }
    }

    private static boolean checkWin(char symbol) {

        /* +++++++++++++++++++++++++++++++++++++++++++++++ */
        /**/        int numbCharsVictory = calcCharsVictory();  // кол-ство символов для победы
        /**/        int numbChars = 0;
        /**/
        /**/        // Считываем по горизонтали
        /**/        numbChars = countChars(numbCharsVictory, symbol, lastMoveRow, lastMoveCol);
        /**/        if(numbChars == numbCharsVictory)
        /**/            return true;
        /**/
        /**/        // Считываем по вертикали
        /**/        numbChars = countChars(numbCharsVictory, symbol, lastMoveCol, lastMoveRow);
        /**/        if(numbChars == numbCharsVictory)
        /**/            return true;
        /**/
        /**/        // Считываем по диоганали справа налево вниз
        /**/        numbChars = countChars(numbCharsVictory, symbol, true);
        /**/        if(numbChars == numbCharsVictory)
        /**/            return true;
        /**/
        /**/        // Считываем по диоганали слева направо вниз
        /**/        numbChars = countChars(numbCharsVictory, symbol, false);
        /**/        if(numbChars == numbCharsVictory)
        /**/            return true;
        /* +++++++++++++++++++++++++++++++++++++++++++++++ */

        return false;
    }

    /* +++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    private static int calcCharsVictory() {
    /**/        switch(SIZE) {
    /**/            case 3:
    /**/            case 4:
    /**/            case 5:
    /**/            case 6: return 3;
    /**/            case 7:
    /**/            case 8:
    /**/            case 9:
    /**/            case 10: return 4;
    /**/            default: return 5;
    /**/        }
    /**/    }
    /* +++++++++++++++++++++++++++++++++++++++++++++++ */

    /* +++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    // Считывает символы по горизонтали и вертикали
    /**/    private static int countChars(int numbCharsVictory, char symbol,
    /**/                                      int countIndex, int constantIndex) {
    /**/        // результат
    /**/        int numbChars = 0;
    /**/        // Начало цикла, совпадает с индексом последнего хода - длина победных символов
    /**/        int start = countIndex - (numbCharsVictory - 1);
    /**/        // Конец цикла, совпадает с индексом последнего хода + длина победных символов
    /**/        int limit = countIndex + (numbCharsVictory - 1);
    /**/
    /**/        for(int i = start; i <= limit; i++) {
    /**/
    /**/            if(i < 0)
    /**/                continue;
    /**/            if(i >= SIZE)
    /**/                break;
    /**/
    /**/            numbChars = (map[i][constantIndex] == symbol) ?
    /**/                ++numbChars : 0;
    /**/
    /**/            if(numbChars == numbCharsVictory)
    /**/                return numbChars;
    /**/        }
    /**/
    /**/        return numbChars;
    /**/    }
    /* +++++++++++++++++++++++++++++++++++++++++++++++ */

    /* +++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    // Считывает символы по диоганали
    /**/    private static int countChars(int numbCharsVictory, char symbol, boolean rightToLeftDown) {
    /**/        int numbChars = 0;
    /**/        int row = lastMoveRow - (numbCharsVictory - 1);
    /**/        int limit = lastMoveRow + (numbCharsVictory - 1);
    /**/        int stepRow = 1;
    /**/        int col, stepCol;
    /**/        if(rightToLeftDown) {
    /**/            col = lastMoveCol - (numbCharsVictory - 1);
    /**/            stepCol = 1;
    /**/        } else {
    /**/            col = lastMoveCol + (numbCharsVictory - 1);
    /**/            stepCol = (-1);
    /**/        }
    /**/
    /**/        for(int i = row, j = col; i <= limit; i += stepRow, j += stepCol) {
    /**/
    /**/            if(i < 0 || j < 0)
    /**/                continue;
    /**/            if(i >= SIZE || j >= SIZE)
    /**/                break;
    /**/
    /**/            numbChars = (map[i][j] == symbol) ? ++numbChars : 0;
    /**/
    /**/            if(numbChars == numbCharsVictory)
    /**/                return numbChars;
    /**/        }
    /**/
    /**/        return numbChars;
    /**/    }
    /* +++++++++++++++++++++++++++++++++++++++++++++++ */

    private static boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if(aChar == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    private static void aiTurn() {
        int rowNumber;
        int colNumber;
        System.out.println("\nХод компьютера!\n");
        //do {
            /* +++++++++++++++++++++++++++++++++++++++++++++++ */
            /**/    int[] aiTurn = getAITurn();
            /**/    rowNumber = aiTurn[0];
            /**/    colNumber = aiTurn[1];
            /* +++++++++++++++++++++++++++++++++++++++++++++++ */
        //} while (!isCellValid(rowNumber, colNumber, true));

        map[rowNumber][colNumber] = DOT_AI;

        /* +++++++++++++++++++++++++++++++++++++++++++++++ */
        /**/    saveLastMove(rowNumber -1, colNumber - 1); // сохраняем последний ход
        /* +++++++++++++++++++++++++++++++++++++++++++++++ */
    }

    private static int[] getAITurn() {

        int[] aiTurn = new int[2];

        for(int i = lastMoveRow - 1; i <= lastMoveRow + 1; i ++) {
            for(int j = lastMoveCol - 1; j <= lastMoveCol + 1; j++) {

                if(i < 0 || i >= SIZE)
                    break;
                if(j == lastMoveCol || j < 0 || j >= SIZE)
                    continue;
                if(i == lastMoveRow && j == lastMoveCol )
                    continue;

                System.out.println("i = " + i + ", j = " + j);

                if(map[i][j] == DOT_HUMAN) {
                    int rowOffset = i - lastMoveRow;
                    int colOffset = j - lastMoveCol;


                    if((i + rowOffset) >= 0 && (i + rowOffset) < SIZE && (j + colOffset) > 0 && (j + colOffset) < SIZE) {
                        if(isCellValid((i + rowOffset), (j + colOffset), true)) {
                            return new int[] {i + rowOffset, j + colOffset};
                        }
                    }
                    if((lastMoveRow + rowOffset * (-1)) > 0 && (lastMoveRow + rowOffset * (-1)) < SIZE &&
                            (lastMoveCol + colOffset * (-1)) > 0 && (lastMoveCol + colOffset * (-1)) < SIZE) {
                        if(isCellValid((lastMoveRow + rowOffset), (lastMoveCol + colOffset), true)) {
                            return new int[] {i - rowOffset, j - colOffset};
                        }
                    }
                }

                if(map[i][j] == DOT_EMPTY) {
                    aiTurn[0] = i;
                    aiTurn[1] = j;
                }
            }
        }

        return aiTurn;
    }
}

