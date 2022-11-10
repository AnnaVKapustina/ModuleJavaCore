package hw_lesson_8_Project_with_DB;

import hw_lesson_8_Project_with_DB.entity.Weather;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepositorySQLite {
    private static String createTable = "CREATE TABLE IF NOT EXISTS weather (id INTEGER PRIMARY KEY AUTOINCREMENT, city TEXT NOT NULL, date TEXT NOT NULL, temp_max REAL NOT NULL, temp_min REAL NOT NULL);";
    private static String insertWeather = "INSERT into weather (city, date, temp_max, temp_min) values (?, ?, ?, ?);";
    private static String getWeather = "SELECT * FROM weather;";
    private static String DB_PATH = "jdbc:sqlite:lesson9.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection () throws SQLException {
        Connection connection = DriverManager.getConnection(DB_PATH);
        return connection;
    }

    static void CreateTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(DB_PATH)){
            connection.createStatement().execute(createTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean saveToDB (Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)){
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getDate());
            saveWeather.setDouble(3, weather.getTemperatureMax());
            saveWeather.setDouble(4, weather.getTemperaruteMin());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException ("Погода не сохранилась в базу");
    }

    public static void saveToDB (List<Weather> weatherList) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            for (Weather weather : weatherList) {
                saveWeather.setString(1, weather.getCity());
                saveWeather.setString(2, weather.getDate());
                saveWeather.setDouble(3, weather.getTemperatureMax());
                saveWeather.setDouble(4, weather.getTemperaruteMin());
                saveWeather.addBatch();
            }
            saveWeather.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException ("Погода не сохранилась в базу");
    }

    public static List<Weather> getAllToDB() {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.println(" ");
                System.out.print(resultSet.getString("city"));
                System.out.println(" ");
                System.out.print(resultSet.getString("date"));
                System.out.println(" ");
                System.out.print(resultSet.getDouble("temp_max"));
                System.out.println(" ");
                System.out.print(resultSet.getDouble("temp_min"));
                System.out.println(" ");
                weathers.add(new Weather(
                        resultSet.getString("city"),
                        resultSet.getString("date"),
                        resultSet.getDouble("temp_max"),
                        resultSet.getDouble("temp_min")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weathers;
    }

}
