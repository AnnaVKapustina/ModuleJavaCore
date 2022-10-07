package hw_lesson_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruit>{
    public List<T> getList() {

        return list;
    }
    private List<T> list;

    public Box(T...obj) {

        list = Arrays.asList(obj);
    }
    public Box() {

        list = new ArrayList<T>();
    }
    void add(T obj) {

        list.add(obj);
    }
    void moveToBox(Box<T> box) {
        box.getList().addAll(list);
        list.clear();
    }
    void boxInfo() {
        if (list.isEmpty()) {
            System.out.println("Box is empty");
        } else {
            System.out.println(list.size() + " " + list.get(0).toString() + " in the box");
        }
    }
    float getWeight() {
        if (list.isEmpty()) {
            return 0;
        } else {
            return list.size() * list.get(0).getWeight();
        }
    }
    boolean compare (Box<?> box) {

        return this.getWeight() == box.getWeight();
    }
}
