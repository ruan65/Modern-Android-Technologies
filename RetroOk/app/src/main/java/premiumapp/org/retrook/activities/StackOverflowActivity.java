package premiumapp.org.retrook.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import premiumapp.org.retrook.R;
import premiumapp.org.retrook.model.stack_overflow.Question;
import premiumapp.org.retrook.model.stack_overflow.StackOverflowQuestions;
import premiumapp.org.retrook.rest_api.StackOverflowAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class StackOverflowActivity extends ListActivity implements Callback<StackOverflowQuestions> {

    ArrayAdapter<Question> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        arrayAdapter = new ArrayAdapter<Question>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<Question>());

        setListAdapter(arrayAdapter);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        setProgressBarIndeterminateVisibility(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StackOverflowAPI api = retrofit.create(StackOverflowAPI.class);
        Call<StackOverflowQuestions> call = api.loadQuestions("android");

        call.enqueue(this);

        return true;
    }

    @Override
    public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {

        setProgressBarIndeterminateVisibility(false);
        arrayAdapter.clear();
        arrayAdapter.addAll(response.body().items);

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
