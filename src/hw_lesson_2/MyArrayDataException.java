package hw_lesson_2;

public class MyArrayDataException extends MyCustomExeption {
    MyArrayDataException (int i, int j) {
        super("Заданное значение в ячейке с координатами "
                + "[" + i + "]" + "[" + j+ "]" + " не число");
    }
}
