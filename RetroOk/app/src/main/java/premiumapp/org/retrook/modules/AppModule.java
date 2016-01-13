package premiumapp.org.retrook.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import premiumapp.org.retrook.model.weather.DayForecast;
import premiumapp.org.retrook.mvp_views.WeatherView;
import premiumapp.org.retrook.presenters.WeatherPresenter;
import premiumapp.org.retrook.presenters.WeatherPresenterImpl;
import premiumapp.org.retrook.rest_api.ServiceFactory;
import rx.Observable;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Observable<DayForecast> providesDayForecastObservable() {
        return ServiceFactory.createDayForecastObservable();
    }
}
