package hw_lesson_6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.io.IOException;

public class JsonWeather {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("474212_PC")
                .addQueryParameter("apikey", "O1nIDDovynb163tsCibiWHz4gcTpsKFi")
                .addQueryParameter("language", "ru")
                .build();

        System.out.println(url);

        Request requestHttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        String jsonWeather = client.newCall(requestHttp).execute().body().string();
        System.out.println(jsonWeather);
    }
}
