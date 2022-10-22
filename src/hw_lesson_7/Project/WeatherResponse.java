package hw_lesson_7.Project;

import com.fasterxml.jackson.core.JsonProcessingException;

import static hw_lesson_7.Project.AccuweatherModel.objectMapper;

public class WeatherResponse {

    public static void parse(String stringJSON, int countDays) throws JsonProcessingException {
            for (int i = 0; i < countDays; i++) {
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
        }
    }
