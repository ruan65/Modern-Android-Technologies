package premiumapp.org.retrook.rest_api;

import premiumapp.org.retrook.model.weather.DayForecast;
import retrofit.http.GET;
import rx.Observable;

public interface OpenWeatherAPI {

    @GET("/data/2.5/weather?lat=55.4&lon=35.5&APPID=15c74eaabe9edd7775661dccf85eb532")
    Observable<DayForecast> loadWeather();
}
