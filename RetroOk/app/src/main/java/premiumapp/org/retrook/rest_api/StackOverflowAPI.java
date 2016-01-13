package premiumapp.org.retrook.rest_api;

import premiumapp.org.retrook.model.stack_overflow.StackOverflowQuestions;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface StackOverflowAPI {

    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);

}
