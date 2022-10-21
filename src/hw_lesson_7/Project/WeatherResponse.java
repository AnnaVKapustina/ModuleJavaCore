package hw_lesson_7.Project;

import com.fasterxml.jackson.core.JsonProcessingException;

import static hw_lesson_7.Project.AccuweatherModel.objectMapper;

public class WeatherResponse {

    public static void parse(String stringJSON, Period period) throws JsonProcessingException {
        if (period == Period.FIVE_DAYS) {
            for (int i = 0; i < 5; i++) {
                String day = objectMapper.readTree(stringJSON)
                        .get("DailyForecasts")
                        .get(i).get("Date").asText();
                System.out.println(day + " ожидается:");
                String minimum = objectMapper.readTree(stringJSON)
                        .get("DailyForecasts")
                        .get(i).get("Temperature")
                        .get("Minimum")
                        .get("Value").asText();
                System.out.println("мин. температура " + minimum + "C");
                String maximum = objectMapper.readTree(stringJSON)
                        .get("DailyForecasts")
                        .get(i).get("Temperature")
                        .get("Maximum")
                        .get("Value").asText();
                System.out.println("макс. температура: " + maximum + "C");
            }
        } else {
            String day = objectMapper.readTree(stringJSON)
                    .get("DailyForecasts")
                    .get(0).get("Date").asText();
            System.out.println(day + " ожидается:");
            String minimum = objectMapper.readTree(stringJSON)
                    .get("DailyForecasts")
                    .get(0).get("Temperature")
                    .get("Minimum")
                    .get("Value").asText();
            System.out.println("мин. температура " + minimum + "C");
            String maximum = objectMapper.readTree(stringJSON)
                    .get("DailyForecasts")
                    .get(0).get("Temperature")
                    .get("Maximum")
                    .get("Value").asText();
            System.out.println("макс. температура: " + maximum + "C");
        }
    }
}
