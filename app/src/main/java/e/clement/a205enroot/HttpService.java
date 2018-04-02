package e.clement.a205enroot;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Clément on 02/04/2018.
 */

public interface HttpService {
    @GET("test_php/{script_php}")
    io.reactivex.Observable<List<NewsArticles>> getFollowing(@Path("script_php") String news);
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.15/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("test_php/{games_php}")
    io.reactivex.Observable<List<SponsorsList>> getSponsors(@Path("games_php") String sponsors);

}
