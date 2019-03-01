package joke.k.myapplication.login.data.network;

import io.reactivex.Flowable;
import joke.k.myapplication.login.data.RandomJokes;
import retrofit2.http.GET;

public interface Api  {

    String BASE_URL = "https://official-joke-api.appspot.com/";
    @GET("random_ten")
    Flowable<RandomJokes> getTenJokes();
    @GET("random_joke")
    Flowable<RandomJokes> getJokes();


}
