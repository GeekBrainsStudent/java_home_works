package homeworks2.lesson2.Exceptions;

public class MyArraySizeException extends IndexOutOfBoundsException {

    public MyArraySizeException(String message) {
        super(message);
    }

    public MyArraySizeException() {
        this("Неверный размер массива.");
    }
}
