package hw_lesson_3;

import java.util.Arrays;

public class ChangeArrayElements {
    public static void main(String[] args) {
        Integer[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] strArr = {" one ", " two ", " three ", " four ", " five ", " six ", " seven "};
        changeElements(strArr, 0, 5);
        changeElements(intArr, 2, 3);
        System.out.println("New strArr:" + Arrays.toString(strArr));
        System.out.println("New intArr:" + Arrays.toString(intArr));
    }
    private static void changeElements (Object[] arr, int a, int b) {
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
