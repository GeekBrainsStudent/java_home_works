package homeworks.lesson2;

public class Main {

    public static void main(String[] args) {

//        Task #1
        int[] intArr = {0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1};
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = intArr[i] == 0 ? 1 : 0;
        }

//        Task #2
        int[] intArr2 = new int[8];
        for (int i = 0; i < intArr2.length; i++) {
            intArr2[i] = i * 3;
        }

//        Task #3
        int[] intArr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < intArr3.length; i++) {
            if(intArr3[i] < 6)
                intArr3[i] *= 2;
        }

//        Task #4
        int size = 10;
        int[][] intArr4 = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                intArr4[i][j] = (i == j) ? 1 : 0;
            }
        }

//        Task #5
        double[] doubleArr = {34.05, 2.0, 83.8, 100.0, 43,21, -342.0, 0.0};
        double min = doubleArr[0];
        double max = doubleArr[0];
        for(double d : doubleArr) {
            if(d < min)
                min = d;
            if(d > max)
                max = d;
        }
    }

//    Task #6
    static boolean checkBalance(int[] intArr) {
        if(intArr.length == 0) {
            System.out.println("Array is empty");
            return false;
        }

        int sumFull = 0;
        for(int i : intArr)
            sumFull += i;

        int sum1 = 0;
        for(int i : intArr) {
            sum1 += i;
            if(sum1 == (sumFull - sum1))
                return true;
        }

        return false;
    }

//    Task #7
    static void shiftArray(int[] array, int shift) {
        int saveSignShift = shift;
        shift = shift < 0 ? shift * (-1) : shift;
        for(int i = 0; i < shift; i++) {
            if(saveSignShift < 0)
                oneStepLeft(array);
            else
                oneStepRight(array);
        }
    }

    static void oneStepRight(int[] array) {
        int lastElement = array[array.length - 1];
        for(int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = lastElement;
    }

    static void oneStepLeft(int[] array) {
        int firstElement = array[0];
        for(int i = 0; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = firstElement;
    }
}