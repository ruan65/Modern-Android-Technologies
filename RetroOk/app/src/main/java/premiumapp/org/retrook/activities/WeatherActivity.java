package premiumapp.org.retrook.activities;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import premiumapp.org.retrook.OkApp;
import premiumapp.org.retrook.R;
import premiumapp.org.retrook.modules.WeatherActivityModule;
import premiumapp.org.retrook.mvp_views.WeatherView;
import premiumapp.org.retrook.presenters.WeatherPresenter;
import timber.log.Timber;

public class WeatherActivity extends BaseActivity implements WeatherView {

    @Bind(R.id.tvWeather) TextView tvWeather;

    @Inject WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {
        Timber.i("zz");
        OkApp.get(this)
                .getAppComponent()
                .plus(new WeatherActivityModule(this))
                .inject(this);
    }

    @Override
    public void showWeather(String weather) {
        tvWeather.setText(weather);
    }

    @OnClick(R.id.btn_get_weather)
    public void getWeather() {
        presenter.showWeather();
    }
}
