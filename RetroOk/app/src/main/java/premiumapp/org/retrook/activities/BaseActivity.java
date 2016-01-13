package premiumapp.org.retrook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
        Timber.i("");

    }

    protected abstract void setupActivityComponent();
}

