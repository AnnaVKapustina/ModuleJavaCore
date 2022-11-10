package hw_lesson_8_Project_with_DB;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw_lesson_8_Project_with_DB.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccuweatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727

    private static final String PTOTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAY = "5day";
    private static final String API_KEY = "O1nIDDovynb163tsCibiWHz4gcTpsKFi";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final String METRIC = "metric";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    static final ObjectMapper objectMapper = new ObjectMapper();
    private Weather weather;

    public void getWeather (String city, Period period) throws IOException, SQLException {
        switch (period) {
            case NOW:
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PTOTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(city))
                        .addQueryParameter(API_KEY_QUERY_PARAM,API_KEY)
                        .addQueryParameter(METRIC, "true")
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response oneDayResponse = okHttpClient.newCall(request).execute();
                String oneDayWeatherResponse = oneDayResponse.body().string();
                System.out.println("В городе " + city);
                String day = objectMapper.readTree(oneDayWeatherResponse)
                        .get("DailyForecasts")
                        .get(0).get("Date").asText();
                System.out.println(day + " ожидается:");
                Double minimum = Double.valueOf(objectMapper.readTree(oneDayWeatherResponse)
                        .get("DailyForecasts")
                        .get(0).get("Temperature")
                        .get("Minimum")
                        .get("Value").asText());
                System.out.println("мин. температура " + minimum + "C");
                Double maximum = Double.valueOf(objectMapper.readTree(oneDayWeatherResponse)
                        .get("DailyForecasts")
                        .get(0).get("Temperature")
                        .get("Maximum")
                        .get("Value").asText());
                System.out.println("макс. температура: " + maximum + "C");
                Weather weatherResponse = new Weather(city, day, maximum, minimum);
                DBRepositorySQLite.CreateTableIfNotExists();
                DBRepositorySQLite.saveToDB (weatherResponse);
                break;

            case FIVE_DAYS:
                HttpUrl urlFive = new HttpUrl.Builder()
                        .scheme(PTOTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAY)
                        .addPathSegment(detectCityKey(city))
                        .addQueryParameter(API_KEY_QUERY_PARAM,API_KEY)
                        .addQueryParameter(METRIC, "true")
                        .build();
                Request requestFive = new Request.Builder()
                        .url(urlFive)
                        .build();
                Response fiveDayResponse = okHttpClient.newCall(requestFive).execute();
                String fiveDayWeatherResponse = fiveDayResponse.body().string();
                List<Weather> fiveWeatherResponse = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    System.out.println("В городе " + city);
                    String fiveDay = objectMapper.readTree(fiveDayWeatherResponse)
                            .get("DailyForecasts")
                            .get(i).get("Date").asText();
                    System.out.println(fiveDay + " ожидается:");
                    Double fiveMin = Double.valueOf(objectMapper.readTree(fiveDayWeatherResponse)
                            .get("DailyForecasts")
                            .get(i).get("Temperature")
                            .get("Minimum")
                            .get("Value").asText());
                    System.out.println("мин. температура " + fiveMin + "C");
                    Double fiveMax = Double.valueOf(objectMapper.readTree(fiveDayWeatherResponse)
                            .get("DailyForecasts")
                            .get(i).get("Temperature")
                            .get("Maximum")
                            .get("Value").asText());
                    System.out.println("макс. температура: " + fiveMax + "C");
                    fiveWeatherResponse.add(new Weather(city, fiveDay, fiveMax, fiveMin));
                }
                DBRepositorySQLite.CreateTableIfNotExists();
                DBRepositorySQLite.saveToDB(fiveWeatherResponse);
                break;
            case DB:
                DBRepositorySQLite.getAllToDB();
        }

    }

    private String detectCityKey(String city) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PTOTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", city)
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}
