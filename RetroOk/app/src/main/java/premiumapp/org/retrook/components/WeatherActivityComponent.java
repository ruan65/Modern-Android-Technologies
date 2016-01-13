package premiumapp.org.retrook.components;

import dagger.Subcomponent;
import premiumapp.org.retrook.activities.WeatherActivity;
import premiumapp.org.retrook.modules.WeatherActivityModule;

@ActivityScope
@Subcomponent(modules = WeatherActivityModule.class)
public interface WeatherActivityComponent {
    WeatherActivity inject(WeatherActivity a);
}
