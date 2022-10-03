package hw_lesson_2;

public class Main {

    public static void main(String[] args) {

        String[][] arr = new String[][]{{"1", "2", "3", "4"},
                                        {"5", "6", "7", "8"},
                                        {"9", "10", "11", "12"},
                                        {"13", "14", "15", "16"}};

        try {
            int finalArray = convertInInt(arr);
            System.out.println("Сумма элементов массива: " + finalArray);
        }
        catch (MyCustomExeption e) {
                System.out.println(e.getMessage());
            }
        }
    public static int convertInInt (String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}
