package hw_lesson_4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Task1 {
    public static void main (String[] args) {
        String arr [] = {"one", "two", "one", "four", "five", "two", "one", "six", "seven", "two"};
        unique(arr);
        wordsCount(arr);

    }
    public static void unique(String[] arr){
        Set<String> set = new HashSet<>();
        for(String str: arr) {
            set.add(str);
        }
        System.out.println(set);
    }
    public static void wordsCount(String[] arr) {
        HashMap<String, Integer> hmArr = new HashMap<>();
        for (String str: arr) {
            hmArr.put(str, hmArr.getOrDefault(str, 0) + 1);
        }
        System.out.println(hmArr);
    }
}
