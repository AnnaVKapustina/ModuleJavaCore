package hw_lesson_7.Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class AccuweatherModel implements WeatherModel {
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

    public void getWeather (String city, Period period) throws IOException {
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
                WeatherResponse.parse(oneDayWeatherResponse, Period.NOW);
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
                System.out.println("В городе " + city);
                WeatherResponse.parse(fiveDayWeatherResponse, Period.FIVE_DAYS);
                break;
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
