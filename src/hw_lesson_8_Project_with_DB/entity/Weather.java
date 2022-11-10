package hw_lesson_8_Project_with_DB.entity;

public class Weather {
    private String city;
    private String date;
    private double temp_max;
    private double temp_min;

    public Weather(String city, String date, double temp_max, double temp_min) {
        this.city = city;
        this.date = date;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperatureMax() {
        return temp_max;
    }

    public void setTemperatureMax(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getTemperaruteMin() {
        return temp_min;
    }

    public void setTemperaruteMin(double temp_min) {
        this.temp_min = temp_min;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", temp_max=" + temp_max +
                ", temp_min=" + temp_min +
                '}';
    }
}
