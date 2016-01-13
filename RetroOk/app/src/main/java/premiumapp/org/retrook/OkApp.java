package premiumapp.org.retrook;

import android.app.Application;
import android.content.Context;

import premiumapp.org.retrook.components.AppComponent;
import premiumapp.org.retrook.components.DaggerAppComponent;
import premiumapp.org.retrook.modules.AppModule;
import timber.log.Timber;

public class OkApp extends Application {

    private AppComponent appComponent;

    public static OkApp get(Context context) {
        return (OkApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) +
                            ":mytimber:line=" + element.getLineNumber() +
                            " method: " + element.getMethodName();
                }
            });
        }
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
