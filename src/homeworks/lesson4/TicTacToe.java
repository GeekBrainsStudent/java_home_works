package homeworks.lesson4;

import java.util.Random;
import java.util.Scanner;

/* ++++++++++++++++++++++++++++++++++++++++++++++ */
/**/    // изменения в коде обрамлены таким образом
/* ++++++++++++++++++++++++++++++++++++++++++++++ */

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
    /**/    static int lastMoveHumanRow, lastMoveHumanCol;      // последний ход человека
    /**/    static int lastMoveAIRow, lastMoveAICol;            // последний ход компьютера
    /**/    static int numbSymbolVictory;                       // кол-ство фишек для победы
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
        /**/    saveHumanLastMove(rowNumber - 1, colNumber - 1);
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
    /**/    private static void saveHumanLastMove(int row, int col) {
    /**/        lastMoveHumanRow = row;
    /**/        lastMoveHumanCol = col;
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
    /**/        int row = symbol == DOT_HUMAN ? lastMoveHumanRow : lastMoveAIRow;
    /**/        int numbSymbol = 0;
    /**/        for(int col = 0; col < SIZE; col++) {
    /**/            if(map[row][col] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[row][col] != symbol) {
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
    /**/        int col = symbol == DOT_HUMAN ? lastMoveHumanCol : lastMoveAICol;
    /**/        int numbSymbol = 0;
    /**/        for(int row = 0; row < SIZE; row++) {
    /**/            if(map[row][col] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[row][col] != symbol) {
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
    /**/        int rowInit = symbol == DOT_HUMAN ? lastMoveHumanRow : lastMoveAIRow;
    /**/        int colInit = symbol == DOT_HUMAN ? lastMoveHumanCol : lastMoveAICol;
    /**/        int rowStart = rowInit - Math.min(rowInit, colInit);
    /**/        int colStart = colInit - Math.min(rowInit, colInit);
    /**/        if((colStart + numbSymbolVictory) > SIZE)
    /**/            return false;
    /**/        if((rowStart + numbSymbolVictory) > SIZE)
    /**/            return false;
    /**/
    /**/        for(int row = rowStart, col = colStart; row < SIZE && col < SIZE; row++, col++) {
    /**/            if(map[row][col] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[row][col] != symbol) {
    /**/                if(numbSymbol >= numbSymbolVictory)
    /**/                    return true;
    /**/                numbSymbol = 0;
    /**/            }
    /**/        }
    /**/        return numbSymbol >= numbSymbolVictory;
    /**/    }
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    static private boolean checkWinLeftToRightUp(char symbol) {
    /**/        int numbSymbol = 0;
    /**/        int rowInit = (symbol == DOT_HUMAN) ? lastMoveHumanRow : lastMoveAIRow;
    /**/        int colInit = (symbol == DOT_HUMAN) ? lastMoveHumanCol : lastMoveAICol;
    /**/        int rowStart = rowInit + Math.min(colInit, (SIZE - 1) - rowInit);
    /**/        int colStart = colInit - Math.min(colInit, (SIZE - 1) - rowInit);
    /**/        if((colStart + numbSymbolVictory) > SIZE)
    /**/            return false;
    /**/        if((rowStart - (numbSymbolVictory - 1)) < 0)
    /**/            return false;
    /**/        for(int row = rowStart, col = colStart; row >= 0 && col < SIZE; row--, col++){
    /**/            if(map[row][col] == symbol) {
    /**/                numbSymbol++;
    /**/            }
    /**/            if(map[row][col] != symbol) {
    /**/                if(numbSymbol >= numbSymbolVictory)
    /**/                    return true;
    /**/                numbSymbol = 0;
    /**/            }
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
        /**/    saveAILastMove(rowNumber, colNumber);
        /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    }

    /* ++++++++++++++++++++++++++++++++++++++++++++++ */
    /**/    // далее идет код добавленный от меня
    /* ++++++++++++++++++++++++++++++++++++++++++++++ */

    private static void saveAILastMove(int row, int col) {
        lastMoveAIRow = row;
        lastMoveAICol = col;
    }

    private static int[] getAiTurn() {
        int[] aiTurn = {-1,-1};

        aiTurn = getAiTurnVictory(DOT_AI, lastMoveAIRow, lastMoveAICol);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnBlock(DOT_HUMAN, lastMoveHumanRow, lastMoveHumanCol);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnAroundLastTurn();
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnRandom();
        return aiTurn;
    }

    private static int[] getAiTurnVictory(char dot, int rowInit, int colInit) {
        int[] aiTurn = {-1,-1};

        aiTurn = getAiTurnHorizontal(dot, rowInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnVertical(dot, colInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnLeftToRightDown(dot, rowInit, colInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnLeftToRightUp(dot, rowInit, colInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        return new int[] {-1,-1};
    }

    private static int[] getAiTurnBlock(char dot, int rowInit, int colInit) {
        int[] aiTurn = {-1,-1};

        aiTurn = getAiTurnHorizontal(dot, rowInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnVertical(dot, colInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnLeftToRightDown(dot, rowInit, colInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        aiTurn = getAiTurnLeftToRightUp(dot, rowInit, colInit);
        if(aiTurn[0] != -1)
            return aiTurn;

        return new int[] {-1,-1};
    }

    private static int[] getAiTurnAroundLastTurn() {
        // Цикл вокруг последнего хода
        for(int i = lastMoveHumanRow - 1; i <= lastMoveHumanRow + 1; i++) {
            for(int j = lastMoveHumanCol - 1; j <= lastMoveHumanCol + 1; j++) {
                // пропускаем, если индексы выходят за пределы поля
                if(i < 0 || i >= SIZE)
                    break;
                if(j < 0 || j >= SIZE)
                    continue;

                // возвращаем индексы первого встреченного "пустого" поля
                if(map[i][j] == DOT_EMPTY) {
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {-1,-1};
    }

    private static int[] getAiTurnRandom() {
        return new int[] {random.nextInt(SIZE), random.nextInt(SIZE)};
    }

    static private int[] getAiTurnVertical(char dot, final int colInit) {
        int countDot = 0;
        for (int row = 0; row < SIZE; row++) {
            if (map[row][colInit] == DOT_EMPTY) { // если встретиться пустая ячейка
                int countDot2 = 0;
                int pos = 0;
                for (pos = row + 1; pos < SIZE; pos++) { // идем циклом до встречи с не dot
                    if (map[pos][colInit] == dot)
                        countDot2++;
                    else
                        break;
                }

                if ((countDot + countDot2) == numbSymbolVictory - 1) { // угроза поражения (возможность сделать победный ход)
                    return new int[]{row, colInit};
                } else if ((countDot + countDot2) == numbSymbolVictory - 2) { // возможная угроза (либо возможность сделать победный ход)
                    if (dot == DOT_HUMAN) { // если мы высчитываем блокировочный ход (не победный)
                        if (pos < SIZE) {
                            if (map[pos][colInit] == DOT_EMPTY) { // если крайняя ячейка пуста
                                if ((pos + 1) < SIZE) { // и ячейка по соседству тоже пуста
                                    if (map[pos + 1][colInit] == DOT_EMPTY)
                                        return new int[]{row, colInit};
                                }
                                if ((row - 1) >= 0) {   // если ячейка из главного цикла также пуста
                                    if (map[row - 1][colInit] == DOT_EMPTY)
                                        return new int[]{row, colInit};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[row][colInit] == dot) {
                countDot++;
            }
            if (map[row][colInit] != dot && map[row][colInit] != DOT_EMPTY) {
                countDot = 0;
            }
        }
        return new int[]{-1, -1};
    }

    static private int[] getAiTurnHorizontal(char dot, final int rowInit) {
        int countDot = 0;
        for (int col = 0; col < SIZE; col++) {
            if (map[rowInit][col] == DOT_EMPTY) {
                int countDot2 = 0;
                int pos = 0;
                for (pos = col + 1; pos < SIZE; pos++) {
                    if (map[rowInit][pos] == dot)
                        countDot2++;
                    else
                        break;
                }
                if ((countDot + countDot2) == numbSymbolVictory - 1) {
                    return new int[]{rowInit, col};
                } else if ((countDot + countDot2) == numbSymbolVictory - 2) {
                    if (dot == DOT_HUMAN) {
                        if (pos < SIZE) {
                            if (map[rowInit][pos] == DOT_EMPTY) {
                                if ((pos + 1) < SIZE) {
                                    if (map[rowInit][pos + 1] == DOT_EMPTY)
                                        return new int[]{rowInit, col};
                                }
                                if ((col - 1) >= 0) {
                                    if (map[rowInit][col - 1] == DOT_EMPTY)
                                        return new int[]{rowInit, col};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[rowInit][col] == dot) {
                countDot++;
            }
            if (map[rowInit][col] != dot && map[rowInit][col] != DOT_EMPTY) {
                countDot = 0;
            }
        }

        return new int[]{-1, -1};
    }

    static private int[] getAiTurnLeftToRightDown(char dot, int rowInit, int colInit) {
        int countDot = 0;
        int rowStart = rowInit - Math.min(rowInit, colInit);
        int colStart = colInit - Math.min(rowInit, colInit);
        for (int row = rowStart, col = colStart; (row < SIZE) && (col < SIZE); row++, col++) {
            if (map[row][col] == DOT_EMPTY) {
                int countDot2 = 0;
                int i, j;
                for (i = row + 1, j = col + 1; (i < SIZE) && (j < SIZE); i++, j++) {
                    if (map[i][j] == dot)
                        countDot2++;
                    else
                        break;
                }
                if ((countDot + countDot2) == numbSymbolVictory - 1) {
                    return new int[]{row, col};
                } else if ((countDot + countDot2) == numbSymbolVictory - 2) {
                    if (dot == DOT_HUMAN) {
                        if (i < SIZE && j < SIZE) {
                            if (map[i][j] == DOT_EMPTY) {
                                if (i + 1 < SIZE && j + 1 < SIZE) {
                                    if (map[i + 1][j + 1] == DOT_EMPTY)
                                        return new int[]{row, col};
                                }
                                if (row - 1 >= 0 && col - 1 >= 0) {
                                    if (map[row - 1][col - 1] == DOT_EMPTY)
                                        return new int[]{row, col};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[row][col] == dot) {
                countDot++;
            }
            if (map[row][col] != dot && map[row][col] != DOT_EMPTY) {
                countDot = 0;
            }
        }
        return new int[]{-1, -1};
    }

    static private int[] getAiTurnLeftToRightUp(char dot, int rowInit, int colInit) {
        int countDot = 0;
        int rowStart = rowInit + Math.min(colInit, (SIZE - 1) - rowInit);
        int colStart = colInit - Math.min(colInit, (SIZE - 1) - rowInit);
        for (int row = rowStart, col = colStart; (row >= 0) && (col < SIZE); row--, col++) {
            if (map[row][col] == DOT_EMPTY) {
                int countDot2 = 0;
                int i, j;
                for (i = row - 1, j = col + 1; (i >= 0) && (j < SIZE); i--, j++) {
                    if (map[i][j] == dot)
                        countDot2++;
                    else
                        break;
                }
                if ((countDot + countDot2) == numbSymbolVictory - 1) {
                    return new int[]{row, col};
                } else if ((countDot + countDot2) == numbSymbolVictory - 2) {
                    if (dot == DOT_HUMAN) {
                        if (i >= 0 && j < SIZE) {
                            if (map[i][j] == DOT_EMPTY) {
                                if (i - 1 >= 0 && j + 1 < SIZE) {
                                    if (map[i - 1][j + 1] == DOT_EMPTY)
                                        return new int[]{row, col};
                                }
                                if (row + 1 < SIZE && col - 1 >= 0) {
                                    if (map[row + 1][col - 1] == DOT_EMPTY)
                                        return new int[]{row, col};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[row][col] == dot) {
                countDot++;
            }
            if (map[row][col] != dot && map[row][col] != DOT_EMPTY) {
                countDot = 0;
            }
        }
        return new int[]{-1, -1};
    }
}