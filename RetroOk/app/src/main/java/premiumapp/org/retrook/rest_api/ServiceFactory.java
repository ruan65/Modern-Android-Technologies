package premiumapp.org.retrook.rest_api;

import premiumapp.org.retrook.model.weather.DayForecast;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param endPoint endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        T service = restAdapter.create(clazz);

        return service;
    }

    public static Observable<DayForecast> createDayForecastObservable() {

        OpenWeatherAPI openWeatherAPI = ServiceFactory.createRetrofitService(
                OpenWeatherAPI.class, "http://api.openweathermap.org");

        Observable<DayForecast> dayForecastObservable = openWeatherAPI.loadWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        return dayForecastObservable;
    }
}
