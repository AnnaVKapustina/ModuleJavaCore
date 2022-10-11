package hw_lesson_5;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AppData appData = new AppData();

        appData.readFile("src/hw_lesson_5/test.csv");

        System.out.println(Arrays.toString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));

        appData.writeFile("src/hw_lesson_5/test1.csv");




    }
}
