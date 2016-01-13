package premiumapp.org.retrook.components;

import javax.inject.Singleton;

import dagger.Component;
import premiumapp.org.retrook.modules.AppModule;
import premiumapp.org.retrook.modules.WeatherActivityModule;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    WeatherActivityComponent plus(WeatherActivityModule m);
}
