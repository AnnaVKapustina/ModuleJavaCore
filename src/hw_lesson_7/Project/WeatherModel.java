package hw_lesson_7.Project;

import java.io.IOException;

public interface WeatherModel {

    void getWeather(String city, Period period) throws IOException;
}
