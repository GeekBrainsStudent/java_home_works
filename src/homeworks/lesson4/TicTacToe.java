package homeworks.lesson4;

import java.util.Random;
import java.util.Scanner;

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

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    static int lastMoveRow, lastMoveCol;    // последний ход
    /**/    static int numbSymbolVictory;           // кол-ство фишек для победы
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

    public static void main(String[] args) {
        turnGame();
    }

    private static void turnGame() {
        initMap();
        printMap();
        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
        /**/    calcNumbSymbolVictory();    // вычисляет кол-стов фишек для победы
        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
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

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    private static void calcNumbSymbolVictory() {
    /**/        switch(SIZE) {
    /**/            case 3:
    /**/            case 4:
    /**/            case 5:
    /**/            case 6: { numbSymbolVictory = 3; break; }
    /**/            case 7:
    /**/            case 8:
    /**/            case 9:
    /**/            case 10: { numbSymbolVictory = 4; break; }
    /**/            default: { numbSymbolVictory = 5; }
    /**/        }
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

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

        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
        /**/    saveLastMove(rowNumber - 1, colNumber - 1);
        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
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

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    private static void saveLastMove(int row, int col) {
    /**/        lastMoveRow = row;
    /**/        lastMoveCol = col;
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

    private static boolean isCellValid(int rowNumber, int colNumber) {
        return isCellValid(rowNumber, colNumber, false);
    }

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

        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
        /**/    if(checkWinHorizontal(symbol))
        /**/        return true;
        /**/
        /**/    if(checkWinVertical(symbol))
        /**/        return true;
        /**/
        /**/    if(checkWinLeftToRightDown(symbol))
        /**/        return true;
        /**/
        /**/    if(checkWinLeftToRightUp(symbol))
        /**/        return true;
        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
        return false;
    }

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    static private boolean checkWinHorizontal(char symbol) {
    /**/        int numbSymbol = 0;
    /**/        for(int col = 0; col < SIZE; col++) {
    /**/            if(map[lastMoveRow][col] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[lastMoveRow][col] != symbol) {
    /**/                if(numbSymbol >= numbSymbolVictory)
    /**/                    return true;
    /**/                numbSymbol = 0;
    /**/            }
    /**/        }
    /**/        return numbSymbol >= numbSymbolVictory;
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    static private boolean checkWinVertical(char symbol) {
    /**/        int numbSymbol = 0;
    /**/        for(int row = 0; row < SIZE; row++) {
    /**/            if(map[row][lastMoveCol] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[row][lastMoveCol] != symbol) {
    /**/                if(numbSymbol >= numbSymbolVictory)
    /**/                    return true;
    /**/                numbSymbol = 0;
    /**/            }
    /**/        }
    /**/        return numbSymbol >= numbSymbolVictory;
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    static private boolean checkWinLeftToRightDown(char symbol) {
    /**/        int numbSymbol = 0;
    /**/        int rowStart = lastMoveRow - Math.min(lastMoveRow, lastMoveCol);
    /**/        int colStart = lastMoveCol - Math.min(lastMoveRow, lastMoveCol);
    /**/        if((colStart + numbSymbolVictory) > SIZE)
    /**/            return false;
    /**/        if((rowStart + numbSymbolVictory) > SIZE)
    /**/            return false;
    /**/        int row = rowStart;
    /**/        int col = colStart;
    /**/        while (true){
    /**/            if(map[row][col] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[row][col] != symbol) {
    /**/                if(numbSymbol >= numbSymbolVictory)
    /**/                    return true;
    /**/                numbSymbol = 0;
    /**/            }
    /**/            row++;
    /**/            col++;
    /**/            if((row >= SIZE) || (col >= SIZE))
    /**/                break;
    /**/        }
    /**/        return numbSymbol >= numbSymbolVictory;
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    static private boolean checkWinLeftToRightUp(char symbol) {
    /**/        int numbSymbol = 0;
    /**/        int rowStart = lastMoveRow + Math.min(lastMoveCol, (SIZE - 1) - lastMoveRow);
    /**/        int colStart = lastMoveCol - Math.min(lastMoveCol, (SIZE - 1) - lastMoveRow);
    /**/        if((colStart + numbSymbolVictory) > SIZE)
    /**/            return false;
    /**/        if((rowStart - (numbSymbolVictory - 1)) < 0)
    /**/            return false;
    /**/        int row = rowStart;
    /**/        int col = colStart;
    /**/        while (true){
    /**/            if(map[row][col] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[row][col] != symbol) {
    /**/                if(numbSymbol >= numbSymbolVictory)
    /**/                    return true;
    /**/                numbSymbol = 0;
    /**/            }
    /**/            row--;
    /**/            col++;
    /**/            if((row < 0) || (col >= SIZE))
    /**/                break;
    /**/        }
    /**/        return numbSymbol >= numbSymbolVictory;
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

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
        do {
            /* ++++++++++++++++++++++++++++++++++++++++++++++ */
            /**/    int[] aiTurn = getAiTurn();
            /**/    rowNumber = aiTurn[0];
            /**/    colNumber = aiTurn[1];
            /* ++++++++++++++++++++++++++++++++++++++++++++++ */
        } while (!isCellValid(rowNumber + 1, colNumber + 1, true));

        map[rowNumber][colNumber] = DOT_AI;

        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
        /**/    saveLastMove(rowNumber, colNumber);
        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    }

    // К сожалению, не получилось реализовать более-менее "умную"
    // систему ходов для блокировки и победы
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    private static int[] getAiTurn() {
    /**/        int[] aiTurn = new int[2];
    /**/        aiTurn[0] = random.nextInt(SIZE);
    /**/        aiTurn[1] = random.nextInt(SIZE);
    /**/        return aiTurn;
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
}

