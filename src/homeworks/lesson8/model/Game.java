package homeworks.lesson8.model;

import java.util.Random;

public class Game {

    final private int size;
    private char[][] map;

    private int lastMoveRowUser;
    private int lastMoveColUser;
    private int lastMoveRowComp;
    private int lastMoveColComp;

    private final char userMark = 'X';
    private final char compMark = 'O';
    private final char emptyMark = '*';


    private int numbCharsToWin;

    private String message;

    private Random random = new Random();

    public Game(int size) {
        this.size = size;
        map = new char[this.size][this.size];
        initMap();
        calcNumbCharsToWin();

        lastMoveRowUser = -1;
        lastMoveColUser = -1;

        lastMoveRowComp = -1;
        lastMoveColComp = -1;
    }

    private void initMap() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                map[i][j] = emptyMark;
            }
        }
    }

    private boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if(aChar == emptyMark) {
                    return false;
                }
            }
        }
        return true;
    }

    private void calcNumbCharsToWin() {
        switch(size) {
            case 3:
            case 4:
            case 5:
            case 6: { numbCharsToWin = 3; break; }
            case 7:
            case 8:
            case 9:
            case 10: { numbCharsToWin = 4; break; }
            default: { numbCharsToWin = 5; }
        }
    }

    public boolean userMove(int row, int col) {
        if(!isCellValid(row, col)) {
            return false;
        }
        map[row][col] = userMark;
        saveLastMoveUser(row, col);
        return true;
    }

    private boolean isCellValid(int row, int col) {
        if ((row < 0) || (row > size) || (col < 0) || (col > size) || map[row][col] != emptyMark)
            return false;
        return true;
    }

    private void saveLastMoveUser(int row, int col) {
        lastMoveRowUser = row;
        lastMoveColUser = col;
    }

    public boolean checkEnd(char mark) {
        if(checkWin(mark)) {
            if(mark == userMark)
                message = "I`ll be back!";
            else
                message = "Восстание близко! AI победил";
            return true;
        }

        if(isMapFull()) {
            message = "Ничья!";
            return true;
        }
        return false;
    }

    private boolean checkWin(char mark) {

        if(checkWinHorizontal(mark))
            return true;
        if(checkWinVertical(mark))
            return true;
        if(checkWinLeftToRightDown(mark))
            return true;
        if(checkWinLeftToRightUp(mark))
            return true;
        return false;
    }

    private boolean checkWinHorizontal(char mark) {
        int row = mark == userMark ? lastMoveRowUser : lastMoveRowComp;
        int numbSymbol = 0;
        for(int col = 0; col < size; col++) {
            if(map[row][col] == mark) {
                numbSymbol++;
            }
            if(map[row][col] != mark) {
                if(numbSymbol >= numbCharsToWin)
                    return true;
                numbSymbol = 0;
            }
        }
        return numbSymbol >= numbCharsToWin;
    }

    private boolean checkWinVertical(char mark) {
        int col = mark == userMark ? lastMoveColUser : lastMoveColComp;
        int numbSymbol = 0;
        for(int row = 0; row < size; row++) {
            if(map[row][col] == mark) {
                numbSymbol++;
            }
            if(map[row][col] != mark) {
                if(numbSymbol >= numbCharsToWin)
                    return true;
                numbSymbol = 0;
            }
        }
        return numbSymbol >= numbCharsToWin;
    }

    private boolean checkWinLeftToRightDown(char mark) {
        int numbSymbol = 0;
        int rowInit = (mark == userMark) ? lastMoveRowUser : lastMoveRowComp;
        int colInit = (mark == userMark) ? lastMoveColUser : lastMoveColComp;
        int rowStart = rowInit - Math.min(rowInit, colInit);
        int colStart = colInit - Math.min(rowInit, colInit);
        if((colStart + numbCharsToWin) > size)
            return false;
        if((rowStart + numbCharsToWin) > size)
            return false;
        for(int row = rowStart, col = colStart; row < size && col < size; row++, col++) {
            if(map[row][col] == mark) {
                numbSymbol++;
            }
            if(map[row][col] != mark) {
                if(numbSymbol >= numbCharsToWin)
                    return true;
                numbSymbol = 0;
            }
        }
        return numbSymbol >= numbCharsToWin;
    }

    private boolean checkWinLeftToRightUp(char mark) {
        int numbSymbol = 0;
        int rowInit = (mark == userMark) ? lastMoveRowUser : lastMoveRowComp;
        int colInit = (mark == userMark) ? lastMoveColUser : lastMoveColComp;
        int rowStart = rowInit + Math.min(colInit, (size - 1) - rowInit);
        int colStart = colInit - Math.min(colInit, (size - 1) - rowInit);
        if((colStart + numbCharsToWin) > size)
            return false;
        if((rowStart - (numbCharsToWin - 1)) < 0)
            return false;
        for(int row = rowStart, col = colStart; row >= 0 && col < size; row--, col++){
            if(map[row][col] == mark) {
                numbSymbol++;
            }
            if(map[row][col] != mark) {
                if(numbSymbol >= numbCharsToWin)
                    return true;
                numbSymbol = 0;
            }
        }
        return numbSymbol >= numbCharsToWin;
    }


    private void compMove() {
        int[] move = getCompMove();
        while (!isCellValid(move[0], move[1]));
        map[move[0]][move[1]] = compMark;
        saveLastMoveComp(move[0], move[1]);
    }

    private void saveLastMoveComp(int row, int col) {
        lastMoveRowComp = row;
        lastMoveColComp = col;
    }

    private int[] getCompMove() {
        int[] move = null;

        move = getCompMoveToWin(compMark, lastMoveRowComp, lastMoveColComp);
        if(move != null)
            return move;

        move = getAiTurnBlock(userMark, lastMoveRowUser, lastMoveColUser);
        if(move != null)
            return move;

        move = getAiTurnAroundLastTurn();
        if(move != null)
            return move;

        move = getCompMoveRand();
        return move;
    }

    private int[] getCompMoveToWin(char mark, int rowInit, int colInit) {
        int[] aiTurn = null;

        aiTurn = getCompMoveHor(mark, rowInit);
        if(aiTurn != null)
            return aiTurn;

        aiTurn = getCompMoveVer(mark, colInit);
        if(aiTurn != null)
            return aiTurn;

        aiTurn = getCompMoveDiagDw(mark, rowInit, colInit);
        if(aiTurn != null)
            return aiTurn;

        aiTurn = getCompMoveDiagUp(mark, rowInit, colInit);
        if(aiTurn != null)
            return aiTurn;

        return null;
    }

    private int[] getAiTurnBlock(char dot, int rowInit, int colInit) {
        int[] aiTurn = {-1,-1};

        aiTurn = getCompMoveHor(dot, rowInit);
        if(aiTurn != null)
            return aiTurn;

        aiTurn = getCompMoveVer(dot, colInit);
        if(aiTurn != null)
            return aiTurn;

        aiTurn = getCompMoveDiagDw(dot, rowInit, colInit);
        if(aiTurn != null)
            return aiTurn;

        aiTurn = getCompMoveDiagUp(dot, rowInit, colInit);
        if(aiTurn != null)
            return aiTurn;

        return null;
    }

    private int[] getAiTurnAroundLastTurn() {
        // Цикл вокруг последнего хода
        for(int i = lastMoveRowUser - 1; i <= lastMoveRowUser + 1; i++) {
            for(int j = lastMoveColUser - 1; j <= lastMoveColUser + 1; j++) {
                // пропускаем, если индексы выходят за пределы поля
                if(i < 0 || i >= size)
                    break;
                if(j < 0 || j >= size)
                    continue;

                // возвращаем индексы первого встреченного "пустого" поля
                if(map[i][j] == emptyMark) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    private int[] getCompMoveRand() {
        return new int[] {random.nextInt(size), random.nextInt(size)};
    }

    private int[] getCompMoveVer(char mark, final int colInit) {
        int countDot = 0;
        for (int row = 0; row < size; row++) {
            if (map[row][colInit] == emptyMark) { // если встретиться пустая ячейка
                int countDot2 = 0;
                int pos = 0;
                for (pos = row + 1; pos < size; pos++) { // идем циклом до встречи с не dot
                    if (map[pos][colInit] == mark)
                        countDot2++;
                    else
                        break;
                }

                if ((countDot + countDot2) == numbCharsToWin - 1) { // угроза поражения (возможность сделать победный ход)
                    return new int[]{row, colInit};
                } else if ((countDot + countDot2) == numbCharsToWin - 2) { // возможная угроза (либо возможность сделать победный ход)
                    if (mark == userMark) { // если мы высчитываем блокировочный ход (не победный)
                        if (pos < size) {
                            if (map[pos][colInit] == emptyMark) { // если крайняя ячейка пуста
                                if ((pos + 1) < size) { // и ячейка по соседству тоже пуста
                                    if (map[pos + 1][colInit] == emptyMark)
                                        return new int[]{row, colInit};
                                }
                                if ((row - 1) >= 0) {   // если ячейка из главного цикла также пуста
                                    if (map[row - 1][colInit] == emptyMark)
                                        return new int[]{row, colInit};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[row][colInit] == mark) {
                countDot++;
            }
            if (map[row][colInit] != mark && map[row][colInit] != emptyMark) {
                countDot = 0;
            }
        }
        return null;
    }

    private int[] getCompMoveHor(char mark, final int rowInit) {
        int countDot = 0;
        for (int col = 0; col < size; col++) {
            if (map[rowInit][col] == emptyMark) {
                int countDot2 = 0;
                int pos = 0;
                for (pos = col + 1; pos < size; pos++) {
                    if (map[rowInit][pos] == mark)
                        countDot2++;
                    else
                        break;
                }
                if ((countDot + countDot2) == numbCharsToWin - 1) {
                    return new int[]{rowInit, col};
                } else if ((countDot + countDot2) == numbCharsToWin - 2) {
                    if (mark == userMark) {
                        if (pos < size) {
                            if (map[rowInit][pos] == emptyMark) {
                                if ((pos + 1) < size) {
                                    if (map[rowInit][pos + 1] == emptyMark)
                                        return new int[]{rowInit, col};
                                }
                                if ((col - 1) >= 0) {
                                    if (map[rowInit][col - 1] == emptyMark)
                                        return new int[]{rowInit, col};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[rowInit][col] == mark) {
                countDot++;
            }
            if (map[rowInit][col] != mark && map[rowInit][col] != emptyMark) {
                countDot = 0;
            }
        }

        return null;
    }

    private int[] getCompMoveDiagDw(char mark, int rowInit, int colInit) {
        int countDot = 0;
        int rowStart = rowInit - Math.min(rowInit, colInit);
        int colStart = colInit - Math.min(rowInit, colInit);
        for (int row = rowStart, col = colStart; (row < size) && (col < size); row++, col++) {
            if (map[row][col] == emptyMark) {
                int countDot2 = 0;
                int i, j;
                for (i = row + 1, j = col + 1; (i < size) && (j < size); i++, j++) {
                    if (map[i][j] == mark)
                        countDot2++;
                    else
                        break;
                }
                if ((countDot + countDot2) == numbCharsToWin - 1) {
                    return new int[]{row, col};
                } else if ((countDot + countDot2) == numbCharsToWin - 2) {
                    if (mark == userMark) {
                        if (i < size && j < size) {
                            if (map[i][j] == emptyMark) {
                                if (i + 1 < size && j + 1 < size) {
                                    if (map[i + 1][j + 1] == emptyMark)
                                        return new int[]{row, col};
                                }
                                if (row - 1 >= 0 && col - 1 >= 0) {
                                    if (map[row - 1][col - 1] == emptyMark)
                                        return new int[]{row, col};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[row][col] == mark) {
                countDot++;
            }
            if (map[row][col] != mark && map[row][col] != emptyMark) {
                countDot = 0;
            }
        }
        return null;
    }

    private int[] getCompMoveDiagUp(char mark, int rowInit, int colInit) {
        int countDot = 0;
        int rowStart = rowInit + Math.min(colInit, (size - 1) - rowInit);
        int colStart = colInit - Math.min(colInit, (size - 1) - rowInit);
        for (int row = rowStart, col = colStart; (row >= 0) && (col < size); row--, col++) {
            if (map[row][col] == emptyMark) {
                int countDot2 = 0;
                int i, j;
                for (i = row - 1, j = col + 1; (i >= 0) && (j < size); i--, j++) {
                    if (map[i][j] == mark)
                        countDot2++;
                    else
                        break;
                }
                if ((countDot + countDot2) == numbCharsToWin - 1) {
                    return new int[]{row, col};
                } else if ((countDot + countDot2) == numbCharsToWin - 2) {
                    if (mark == userMark) {
                        if (i >= 0 && j < size) {
                            if (map[i][j] == emptyMark) {
                                if (i - 1 >= 0 && j + 1 < size) {
                                    if (map[i - 1][j + 1] == emptyMark)
                                        return new int[]{row, col};
                                }
                                if (row + 1 < size && col - 1 >= 0) {
                                    if (map[row + 1][col - 1] == emptyMark)
                                        return new int[]{row, col};
                                }
                            }
                        }
                    }
                } else {
                    countDot = 0;
                }
            }
            if (map[row][col] == mark) {
                countDot++;
            }
            if (map[row][col] != mark && map[row][col] != emptyMark) {
                countDot = 0;
            }
        }
        return null;
    }
}
