package e.clement.a205enroot;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Clément on 02/04/2018.
 */


public class HttpStreams {
    public static Observable<List<NewsArticles>> streamFetchNewsFollowing(String newsArticles){
        HttpService httpService = HttpService.retrofit.create(HttpService.class);
        return httpService.getFollowing(newsArticles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
    public static Observable<List<SponsorsArticles>> streamFetchSponsorsFollowing(String sponsors){
        HttpService httpService = HttpService.retrofit.create(HttpService.class);
        return httpService.getSponsors(sponsors)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
    public static Observable<List<Coordinates>> streamFetchCoordinatesFollowing(String coordinates){
        HttpService httpService = HttpService.retrofit.create(HttpService.class);
        return httpService.getCoordinates(coordinates)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
    public static Observable<List<Image>> streamFetchImagesFollowing(String image){
        HttpService httpService = HttpService.retrofit.create(HttpService.class);
        return httpService.getImages(image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }


}


/*
public class HttpCalls {
    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable List<NewsArticles> users);
        void onFailure();
    }

    // 2 - Public method to start fetching users following by Jake Wharton
    public static void fetchNewsFollowing(Callbacks callbacks, String news){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        HttpService httpService = HttpService.retrofit.create(HttpService.class);

        // 2.3 - Create the call on Github API
        Call<List<NewsArticles>> call = httpService.getFollowing(news);

        // 2.4 - Start the call
        call.enqueue(new Callback<List<NewsArticles>>() {

            @Override
            public void onResponse(Call<List<NewsArticles>> call, Response<List<NewsArticles>> response) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<NewsArticles>> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}*/
