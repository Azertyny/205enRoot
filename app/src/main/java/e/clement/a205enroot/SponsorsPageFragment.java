package e.clement.a205enroot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorsPageFragment extends Fragment {


    public SponsorsPageFragment() {
        // Required empty public constructor
    }

    private DisposableObserver<List<SponsorsList>> disposable;

    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static SponsorsPageFragment newInstance(){
        return (new SponsorsPageFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.excecuteHttpRetrofit();
        return inflater.inflate(R.layout.fragment_sponsors_page, container, false);
    }
    // ###
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void excecuteHttpRetrofit() {
        //this.updateUIWhenStartingHTTPRequest();

        this.disposable = HttpStreams.streamFetchSponsorsFollowing("games.php").subscribeWith(new DisposableObserver<List<SponsorsList>>(){
            @Override
            public void onNext(List<SponsorsList> sponsors){
                Log.e("TAG","On Next");
                updateUIWithListOfSponsors(sponsors);
            }
            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
                TextView textView = (TextView) getView().findViewById(R.id.fragment_page_sponsors_title);
                textView.setText("Pas de réseau");
            }
            @Override
            public void onComplete() {
                Log.e("TAG","On Complete !!");
            }
        });

    }


    // ###
    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();

    }





    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUIWhenStartingHTTPRequest(){
        //TextView textView = (TextView) getView().findViewById(R.id.fragment_page_news_title);
        //textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        TextView textView = (TextView) getView().findViewById(R.id.fragment_page_sponsors_title);
        textView.setText(response);
    }

    private void updateUIWithListOfSponsors(List<SponsorsList> sponsors){
        StringBuilder stringBuilder = new StringBuilder();
        for (SponsorsList nom : sponsors){
            stringBuilder.append("-"+ nom.getNom()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }
}
