package premiumapp.org.retrook.test_UI_espresso;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;





import premiumapp.org.retrook.R;
import premiumapp.org.retrook.activities.WeatherActivity;

@RunWith(AndroidJUnit4.class)
public class TestWeatherActivityUI {

    @Rule
    public ActivityTestRule<WeatherActivity> weatherActivityActivityTestRule =
            new ActivityTestRule<>(WeatherActivity.class);

    @Test
    public void testShowWeather() {

        onView(withId(R.id.tvWeather))
                .perform(replaceText("Hello"));

        onView(withId(R.id.tvWeather))
                .check(matches(withText("Hello")));




    }
}
