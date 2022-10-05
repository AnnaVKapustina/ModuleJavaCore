package hw_lesson_3;

public class AppFruitBoxes {
    public static void main(String[] args) {

        Orange orange = new Orange();
        Apple apple = new Apple();

        Box<Orange> orangeBox1 = new Box();
        Box<Orange> orangeBox2 = new Box();
        Box<Apple> appleBox = new Box();

        for (int i = 0; i < 3; i++) {
            orangeBox1.add(new Orange());
        }

        for (int i = 0; i < 4; i++) {
            orangeBox2.add(new Orange());
        }
        for (int i = 0; i < 6; i++) {
            appleBox.add(new Apple());
        }

        orangeBox1.boxInfo();
        orangeBox2.boxInfo();
        appleBox.boxInfo();

        Float orange1Weigth = orangeBox1.getWeight();
        System.out.println("OrangeBox1 weight: " + orange1Weigth);
        Float orange2Weigth = orangeBox2.getWeight();
        System.out.println("OrangeBox2 weight: " + orange2Weigth);
        Float appleWeigth = appleBox.getWeight();
        System.out.println("AppleBox weight: " + appleWeigth);


        System.out.println("Weight comparison orangeBox1 и appleBox: " + orangeBox1.compare(appleBox));
        System.out.println("Weight comparison orangeBox2 и appleBox: " + orangeBox2.compare(appleBox));

        orangeBox1.moveToBox(orangeBox2);
//        orangeBox1.moveToBox(appleBox);   Нельзя пересыпать апельсины в коробку с яблоками

        orangeBox1.boxInfo();
        orangeBox2.boxInfo();
        appleBox.boxInfo();
    }
}
