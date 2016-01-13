package premiumapp.org.retrook.modules;

import dagger.Module;
import dagger.Provides;
import premiumapp.org.retrook.activities.WeatherActivity;
import premiumapp.org.retrook.components.ActivityScope;
import premiumapp.org.retrook.model.weather.DayForecast;
import premiumapp.org.retrook.presenters.WeatherPresenter;
import premiumapp.org.retrook.presenters.WeatherPresenterImpl;
import rx.Observable;

@Module
public class WeatherActivityModule {

    private WeatherActivity weatherActivity;

    public WeatherActivityModule(WeatherActivity weatherActivity) {
        this.weatherActivity = weatherActivity;
    }

    @Provides
    @ActivityScope
    WeatherActivity providesWeatherActivity() {
        return weatherActivity;
    }

    @Provides
    @ActivityScope
    WeatherPresenter providesWeatherPresenter(Observable<DayForecast> dfo) {
        return new WeatherPresenterImpl(weatherActivity, dfo);
    }
}
