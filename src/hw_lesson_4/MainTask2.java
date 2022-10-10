package hw_lesson_4;

public class MainTask2 {
    public static void main (String[] args) {

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("+7-999-99-99", "Sidorov");
        phoneBook.add("+7-888-88-88", "Petrov");
        phoneBook.add("+7-777-77-77", "Ivanov");
        phoneBook.add("+7-666-66-66", "Turin");
        phoneBook.add("+7-555-55-55", "Sidorov");
        phoneBook.add("+7-444-44-44", "Karpov");
        phoneBook.add("+7-333-33-33", "Savin");
        phoneBook.add("+7-222-22-22", "Turin");

        phoneBook.get("Sidorov");
        phoneBook.get("Ivanov");

    }
}
