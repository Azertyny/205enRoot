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
    @GET("application/{code}/news.php")
    io.reactivex.Observable<List<NewsArticles>> getFollowing(@Path("code") String news);
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.205enroot.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("application/{code}/sponsors.php")
    io.reactivex.Observable<List<SponsorsArticles>> getSponsors(@Path("code") String sponsors);


    @GET("application/{code}/map.php")
    io.reactivex.Observable<List<Coordinates>> getCoordinates(@Path("code") String sponsors);

    @GET("application/{code}/gallery.php")
    io.reactivex.Observable<List<Image>> getImages(@Path("code") String image);
}
