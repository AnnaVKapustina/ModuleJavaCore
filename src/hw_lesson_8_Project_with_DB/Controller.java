package hw_lesson_8_Project_with_DB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private  AccuweatherModel weatherModel = new AccuweatherModel();
    private Map <Integer, Period> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Period.NOW);
        variants.put(5, Period.FIVE_DAYS);
        variants.put(2, Period.DB);
    }
    public void getWeather(String userInput, String cityName) throws IOException, SQLException {
        Integer command = Integer.parseInt(userInput);

        switch (variants.get(command)) {
            case NOW:
                weatherModel.getWeather(cityName, Period.NOW);
                break;
            case FIVE_DAYS:
                weatherModel.getWeather(cityName, Period.FIVE_DAYS);
            case DB:
                weatherModel.getWeather(cityName, Period.DB);
        }
    }
}
