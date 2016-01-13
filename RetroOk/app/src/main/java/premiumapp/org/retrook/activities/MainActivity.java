package premiumapp.org.retrook.activities;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import premiumapp.org.retrook.R;
import timber.log.Timber;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.tv_info)
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Timber.d("huli");
    }

    @Override
    protected void setupActivityComponent() {
        Timber.d("");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_weather) {
            startActivity(new Intent(this, WeatherActivity.class));
            // Handle the camera action
        } else if (id == R.id.stackOverflow) {
            startActivity(new Intent(this, StackOverflowActivity.class));

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.hello, R.id.hey, R.id.hi})
    public void greetingClicked(Button button) {
        int buttonId = button.getId();
        Timber.i("A button with ID %s was clicked to say '%s'.", buttonId, button.getText());
        Toast.makeText(this, "Check logcat for a greeting!", Toast.LENGTH_SHORT).show();

        if (buttonId == R.id.hello) {
            SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            StringBuffer sb = new StringBuffer();

            List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
            for (Sensor s : sensorList) {
                Timber.i(s.getName());
                sb.append(s.getName()).append(" ").append(" ").append(s.getType()).append("\n");

            }

            tvInfo.setText(sb.toString());

        } else if (buttonId == R.id.hey) {
            final String[] token = {null};
            new Thread(() -> {
                try {
                    token[0] = GoogleAuthUtil.getToken(MainActivity.this, "ruan65@gmail.com",
                            "https://www.googleapis.com/auth/devstorage.read_only");
                    Timber.i("token%s", token[0]);
                    new Handler().post(() -> {
                        Timber.i("token%s ", token[0]);
                    });
                } catch (IOException | GoogleAuthException e) {
                    Timber.i(e.getMessage());
                }
            });

            Timber.i("token%s", token[0]);
        } else if (buttonId == R.id.hi) {
            List<ProviderInfo> providerInfos = returnProviders();
            Timber.i(providerInfos.toString());
            tvInfo.setText(providerInfos.toString());
        }

    }

    public void someTestMethod() throws IOException {

        Settings settings = new Settings();

        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(this, Uri.parse("some uri"));
        mp.prepareAsync();
        mp.start();

        String actionCall = Intent.ACTION_CALL;

        startActivity(new Intent(this, StackOverflowActivity.class));

        BluetoothAdapter ba;

        Message msg;

        requestWindowFeature(Window.FEATURE_RIGHT_ICON);

        ProgressBar pb = new ProgressBar(this);

        ProgressDialog pd = new ProgressDialog(this);

    }

    public List<ProviderInfo> returnProviders() {

        List<ProviderInfo> returnList = new ArrayList<ProviderInfo>();
        for (PackageInfo pack : getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
            ProviderInfo[] providers = pack.providers;
            if (providers != null) {
                returnList.addAll(Arrays.asList(providers));
            }
        }
        return returnList;

    }
}
