package homeworks2.lesson2;

import homeworks2.lesson2.Exceptions.MyArrayDataException;
import homeworks2.lesson2.Exceptions.MyArraySizeException;

public class Main {
    public static void main(String[] args) {
        String[][] arr = { {"1","2","3","4"}, {"1","2","3","4"},
                {"1","2","3","4"}, {"1","2","3","4"} };

        try {
            System.out.println(sumToIntArray(arr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static int sumToIntArray(String[][] arr)
            throws MyArraySizeException, MyArrayDataException {

        int sum = 0;
        final int validSize = 4;

        if(arr.length != validSize && arr[0].length != validSize)
            throw new MyArraySizeException();

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i,j);
                }
            }
        }

        return sum;
    }
}
