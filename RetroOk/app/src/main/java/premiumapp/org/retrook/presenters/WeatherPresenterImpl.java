package premiumapp.org.retrook.presenters;

import premiumapp.org.retrook.model.weather.DayForecast;
import premiumapp.org.retrook.mvp_views.WeatherView;
import rx.Observable;
import rx.Observer;
import timber.log.Timber;

public class WeatherPresenterImpl implements WeatherPresenter {

    WeatherView mvpView;
    Observable<DayForecast> dayForecastObservable;

    public WeatherPresenterImpl(WeatherView mvpView, Observable<DayForecast> observable) {
        Timber.i("presenter constructor!!!!!!!!!!!!!!!!!");
        this.mvpView = mvpView;
        this.dayForecastObservable = observable;
    }

    @Override
    public void showWeather() {

        dayForecastObservable
                .subscribe(new Observer<DayForecast>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                    }

                    @Override
                    public void onNext(DayForecast dayForecast) {
                        mvpView.showWeather(dayForecast.name + " "
                                + dayForecast.main.get("temp"));
                    }
                });

    }
}
