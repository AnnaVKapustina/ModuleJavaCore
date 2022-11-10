package hw_lesson_8_Project_with_DB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterfaceView {

    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите название города");
            String city = scanner.nextLine();
            System.out.println("Введите 1 для получения погоды на сегодня \n" +
                            "5 для получения погоды на 5 дней; \n" +
                            "2 для получения всех записей из Базы данных; \n" +
                            "0 для выхода");
            String command = scanner.nextLine();

            if ("0".equals(command)) break;

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                System.out.println("Ошибка при получении погоды");
            } catch (NumberFormatException e) {
                System.out.println("Введено не число. Введите 1 для получения погоды на сегодня; \n" +
                        "5 для получения погоды на 5 дней; \n" +
                        "2 для получения всех записей из Базы данных; \n" +
                        "0 для выхода");
            } catch (NullPointerException e) {
                System.out.println("Неверно введен город или число для получения погоды. Попробуйте еще раз.");
            } catch (SQLException e) {
                System.out.println("Проблема с БД");
            }
        }
    }
}
