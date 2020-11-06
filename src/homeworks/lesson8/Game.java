package homeworks.lesson8;

import java.util.Random;

// имеются некоторые недочеты в рассчете хода компьютером
// то есть компьютер не всегда ходит единственно верным образом

class Game {

    private final int size;

    private final char[][] map;

    private int lastMoveRowUser;
    private int lastMoveColUser;
    private int lastMoveRowComp;
    private int lastMoveColComp;

    private int numbMarksToWin;
    private final Random random = new Random();

    Game(int size) {
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
                map[i][j] = Resource.EMPTY_MARK;
            }
        }
    }

    private boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if(aChar == Resource.EMPTY_MARK) {
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
            case 6: { numbMarksToWin = 3; break; }
            case 7:
            case 8:
            case 9:
            case 10: { numbMarksToWin = 4; break; }
            default: { numbMarksToWin = 5; }
        }
    }

    public boolean userMove(int row, int col) {
        if(isCellValid(row, col))
            return false;
        map[row][col] = Resource.USER_MARK;
        saveLastMoveUser(row, col);
        return true;
    }

    private boolean isCellValid(int row, int col) {
        return (row < 0) || (row >= size) || (col < 0) || (col >= size) || map[row][col] != Resource.EMPTY_MARK;
    }

    private void saveLastMoveUser(int row, int col) {
        lastMoveRowUser = row;
        lastMoveColUser = col;
    }

    public int getSize() {
        return size;
    }


    public boolean checkDraw() {
        return isMapFull();
    }

    public boolean checkWin(char mark) {

        if(checkWinHorizontal(mark))
            return true;
        if(checkWinVertical(mark))
            return true;
        if(checkWinLeftToRightDown(mark))
            return true;
        return checkWinLeftToRightUp(mark);
    }

    private boolean checkWinHorizontal(char mark) {
        int row = (mark == Resource.USER_MARK) ? lastMoveRowUser : lastMoveRowComp;
        int countMark = 0;
        for(int col = 0; col < size; col++) {
            if(map[row][col] == mark) {
                countMark++;
            }
            if(map[row][col] != mark) {
                if(countMark >= numbMarksToWin)
                    return true;
                countMark = 0;
            }
        }
        return countMark >= numbMarksToWin;
    }

    private boolean checkWinVertical(char mark) {
        int col = (mark == Resource.USER_MARK) ? lastMoveColUser : lastMoveColComp;
        int countMarks = 0;
        for(int row = 0; row < size; row++) {
            if(map[row][col] == mark) {
                countMarks++;
            }
            if(map[row][col] != mark) {
                if(countMarks >= numbMarksToWin)
                    return true;
                countMarks = 0;
            }
        }
        return countMarks >= numbMarksToWin;
    }

    private boolean checkWinLeftToRightDown(char mark) {
        int countMarks = 0;
        int rowInit = (mark == Resource.USER_MARK) ? lastMoveRowUser : lastMoveRowComp;
        int colInit = (mark == Resource.USER_MARK) ? lastMoveColUser : lastMoveColComp;
        int rowStart = rowInit - Math.min(rowInit, colInit);
        int colStart = colInit - Math.min(rowInit, colInit);
        if((colStart + numbMarksToWin) > size)
            return false;
        if((rowStart + numbMarksToWin) > size)
            return false;
        for(int row = rowStart, col = colStart; row < size && col < size; row++, col++) {
            if(map[row][col] == mark) {
                countMarks++;
            }
            if(map[row][col] != mark) {
                if(countMarks >= numbMarksToWin)
                    return true;
                countMarks = 0;
            }
        }
        return countMarks >= numbMarksToWin;
    }

    private boolean checkWinLeftToRightUp(char mark) {
        int countMarks = 0;
        int rowInit = (mark == Resource.USER_MARK) ? lastMoveRowUser : lastMoveRowComp;
        int colInit = (mark == Resource.USER_MARK) ? lastMoveColUser : lastMoveColComp;
        final int min = Math.min(colInit, (size - 1) - rowInit);
        int rowStart = rowInit + min;
        int colStart = colInit - min;
        if((colStart + numbMarksToWin) > size)
            return false;
        if((rowStart - (numbMarksToWin - 1)) < 0)
            return false;
        for(int row = rowStart, col = colStart; row >= 0 && col < size; row--, col++){
            if(map[row][col] == mark) {
                countMarks++;
            }
            if(map[row][col] != mark) {
                if(countMarks >= numbMarksToWin)
                    return true;
                countMarks = 0;
            }
        }
        return countMarks >= numbMarksToWin;
    }

    public int[] compMove() {
        int[] move;
        do {
            move = getCompMove();
        }
        while (isCellValid(move[0], move[1]));
        map[move[0]][move[1]] = Resource.COMP_MARK;
        saveLastMoveComp(move[0], move[1]);
        return move;
    }

    private void saveLastMoveComp(int row, int col) {
        lastMoveRowComp = row;
        lastMoveColComp = col;
    }

    private int[] getCompMove() {
        int[] move;

        if(lastMoveRowComp == -1)
            return getCompMoveRand();

        move = getCompMoveToWin(lastMoveRowComp, lastMoveColComp);
        if(move != null)
            return move;

        move = getCompMoveBlock(lastMoveRowUser, lastMoveColUser);
        if(move != null)
            return move;

        move = getCompMoveAroundLastTurn();
        if(move != null)
            return move;

        move = getCompMoveRand();
        return move;
    }

    private int[] getCompMoveToWin(int rowInit, int colInit) {
        int[] move;

        move = getCompMoveHor(Resource.COMP_MARK, rowInit);
        if(move != null)
            return move;

        move = getCompMoveVer(Resource.COMP_MARK, colInit);
        if(move != null)
            return move;

        move = getCompMoveDiagonalDw(Resource.COMP_MARK, rowInit, colInit);
        if(move != null)
            return move;

        move = getCompMoveDiagonalUp(Resource.COMP_MARK, rowInit, colInit);
        return move;
    }

    private int[] getCompMoveBlock(int rowInit, int colInit) {
        int[] move;

        move = getCompMoveHor(Resource.USER_MARK, rowInit);
        if(move != null)
            return move;

        move = getCompMoveVer(Resource.USER_MARK, colInit);
        if(move != null)
            return move;

        move = getCompMoveDiagonalDw(Resource.USER_MARK, rowInit, colInit);
        if(move != null)
            return move;

        move = getCompMoveDiagonalUp(Resource.USER_MARK, rowInit, colInit);
        return move;
    }

    private int[] getCompMoveAroundLastTurn() {
        // Цикл вокруг последнего хода
        for(int i = lastMoveRowUser - 1; i <= lastMoveRowUser + 1; i++) {
            for(int j = lastMoveColUser - 1; j <= lastMoveColUser + 1; j++) {
                // пропускаем, если индексы выходят за пределы поля
                if(i < 0 || i >= size)
                    break;
                if(j < 0 || j >= size)
                    continue;

                // возвращаем индексы первого встреченного "пустого" поля
                if(map[i][j] == Resource.EMPTY_MARK) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    private int[] getCompMoveRand() {
        return new int[] {random.nextInt(size), random.nextInt(size)};
    }

    private int[] getCompMoveHor(char mark, final int rowInit) {
        int countMark = 0;
        for (int col = 0; col < size; col++) {
            if (map[rowInit][col] == Resource.EMPTY_MARK) {
                int countMark2 = 0;
                int pos;
                for (pos = col + 1; pos < size; pos++) {
                    if (map[rowInit][pos] == mark)
                        countMark2++;
                    else
                        break;
                }
                if ((countMark + countMark2) == numbMarksToWin - 1) {
                    return new int[]{rowInit, col};
                } else if ((countMark + countMark2) == numbMarksToWin - 2) {
                    if (mark == Resource.USER_MARK) {
                        if (pos < size) {
                            if (map[rowInit][pos] == Resource.EMPTY_MARK) {
                                if ((pos + 1) < size) {
                                    if (map[rowInit][pos + 1] == Resource.EMPTY_MARK)
                                        return new int[]{rowInit, col};
                                }
                                if ((col - 1) >= 0) {
                                    if (map[rowInit][col - 1] == Resource.EMPTY_MARK)
                                        return new int[]{rowInit, col};
                                }
                            }
                        }
                    }
                } else {
                    countMark = 0;
                }
            }
            if (map[rowInit][col] == mark) {
                countMark++;
            }
            if (map[rowInit][col] != mark && map[rowInit][col] != Resource.EMPTY_MARK) {
                countMark = 0;
            }
        }

        return null;
    }

    private int[] getCompMoveVer(char mark, final int colInit) {
        int countMark = 0;
        for (int row = 0; row < size; row++) {
            if (map[row][colInit] == Resource.EMPTY_MARK) { // если встретиться пустая ячейка
                int countMark2 = 0;
                int pos;
                for (pos = row + 1; pos < size; pos++) { // идем циклом до встречи с не mark
                    if (map[pos][colInit] == mark)
                        countMark2++;
                    else
                        break;
                }

                if ((countMark + countMark2) == numbMarksToWin - 1) { // угроза поражения (возможность сделать победный ход)
                    return new int[]{row, colInit};
                } else if ((countMark + countMark2) == numbMarksToWin - 2) { // возможная угроза (либо возможность сделать победный ход)
                    if (mark == Resource.USER_MARK) { // если мы высчитываем блокировочный ход (не победный)
                        if (pos < size) {
                            if (map[pos][colInit] == Resource.EMPTY_MARK) { // если крайняя ячейка пуста
                                if ((pos + 1) < size) { // и ячейка по соседству тоже пуста
                                    if (map[pos + 1][colInit] == Resource.EMPTY_MARK)
                                        return new int[]{row, colInit};
                                }
                                if ((row - 1) >= 0) {   // если ячейка из главного цикла также пуста
                                    if (map[row - 1][colInit] == Resource.EMPTY_MARK)
                                        return new int[]{row, colInit};
                                }
                            }
                        }
                    }
                } else {
                    countMark = 0;
                }
            }
            if (map[row][colInit] == mark) {
                countMark++;
            }
            if (map[row][colInit] != mark && map[row][colInit] != Resource.EMPTY_MARK) {
                countMark = 0;
            }
        }
        return null;
    }

    private int[] getCompMoveDiagonalDw(char mark, int rowInit, int colInit) {
        int countMark = 0;
        int rowStart = rowInit - Math.min(rowInit, colInit);
        int colStart = colInit - Math.min(rowInit, colInit);
        for (int row = rowStart, col = colStart; (row < size) && (col < size); row++, col++) {
            if (map[row][col] == Resource.EMPTY_MARK) {
                int countMark2 = 0;
                int i, j;
                for (i = row + 1, j = col + 1; (i < size) && (j < size); i++, j++) {
                    if (map[i][j] == mark)
                        countMark2++;
                    else
                        break;
                }
                if ((countMark + countMark2) == numbMarksToWin - 1) {
                    return new int[]{row, col};
                } else if ((countMark + countMark2) == numbMarksToWin - 2) {
                    if (mark == Resource.USER_MARK) {
                        if (i < size && j < size) {
                            if (map[i][j] == Resource.EMPTY_MARK) {
                                if (i + 1 < size && j + 1 < size) {
                                    if (map[i + 1][j + 1] == Resource.EMPTY_MARK)
                                        return new int[]{row, col};
                                }
                                if (row - 1 >= 0 && col - 1 >= 0) {
                                    if (map[row - 1][col - 1] == Resource.EMPTY_MARK)
                                        return new int[]{row, col};
                                }
                            }
                        }
                    }
                } else {
                    countMark = 0;
                }
            }
            if (map[row][col] == mark) {
                countMark++;
            }
            if (map[row][col] != mark && map[row][col] != Resource.EMPTY_MARK) {
                countMark = 0;
            }
        }
        return null;
    }

    private int[] getCompMoveDiagonalUp(char mark, int rowInit, int colInit) {
        int countMark = 0;
        final int min = Math.min(colInit, (size - 1) - rowInit);
        int rowStart = rowInit + min;
        int colStart = colInit - min;
        for (int row = rowStart, col = colStart; (row >= 0) && (col < size); row--, col++) {
            if (map[row][col] == Resource.EMPTY_MARK) {
                int countMark2 = 0;
                int i, j;
                for (i = row - 1, j = col + 1; (i >= 0) && (j < size); i--, j++) {
                    if (map[i][j] == mark)
                        countMark2++;
                    else
                        break;
                }
                if ((countMark + countMark2) == numbMarksToWin - 1) {
                    return new int[]{row, col};
                } else if ((countMark + countMark2) == numbMarksToWin - 2) {
                    if (mark == Resource.USER_MARK) {
                        if (i >= 0 && j < size) {
                            if (map[i][j] == Resource.EMPTY_MARK) {
                                if (i - 1 >= 0 && j + 1 < size) {
                                    if (map[i - 1][j + 1] == Resource.EMPTY_MARK)
                                        return new int[]{row, col};
                                }
                                if (row + 1 < size && col - 1 >= 0) {
                                    if (map[row + 1][col - 1] == Resource.EMPTY_MARK)
                                        return new int[]{row, col};
                                }
                            }
                        }
                    }
                } else {
                    countMark = 0;
                }
            }
            if (map[row][col] == mark) {
                countMark++;
            }
            if (map[row][col] != mark && map[row][col] != Resource.EMPTY_MARK) {
                countMark = 0;
            }
        }
        return null;
    }
}
