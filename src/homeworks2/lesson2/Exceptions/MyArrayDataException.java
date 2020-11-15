package homeworks2.lesson2.Exceptions;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(String message) {
        super(message);
    }

    public MyArrayDataException() {
        this("Ошибка конвертации в тип \"int\" в одном из элементов массива.");
    }

    public MyArrayDataException(int dimension1, int dimension2) {
        this("Ошибка конвертации в тип \"int\" элемента массива в ячейке [" +
                dimension1 +"][" + dimension2 + "].");
    }

}
