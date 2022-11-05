package hw_lesson_7.Project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private WeatherModel weatherModel = new AccuweatherModel();
    private Map <Integer, Period> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Period.NOW);
        variants.put(5, Period.FIVE_DAYS);
    }
    public void getWeather(String userInput, String cityName) throws IOException {
        Integer command = Integer.parseInt(userInput);

        switch (variants.get(command)) {
            case NOW:
                weatherModel.getWeather(cityName, Period.NOW);
                break;
            case FIVE_DAYS:
                weatherModel.getWeather(cityName, Period.FIVE_DAYS);
        }
    }
}
