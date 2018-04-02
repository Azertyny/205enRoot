package e.clement.a205enroot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsPageFragment extends Fragment implements /*MyAsyncTask.Listeners,*/ HttpCalls.Callbacks{

    /*public NewsPageFragment() {
        // Required empty public constructor
    }*/

    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static NewsPageFragment newInstance(){
        return (new NewsPageFragment());
    }



    //ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.excecuteHttpRetrofit();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_page, container, false);
    }

    private void excecuteHttpRetrofit() {
        //this.updateUIWhenStartingHTTPRequest();
        HttpCalls.fetchNewsFollowing(this,"mysql_connect.php");
    }

    @Override
    public void onResponse(@Nullable List<NewsArticles> users) {
        // 2.1 - When getting response, we update UI
        if (users != null) this.updateUIWithListOfUsers(users);
    }

    @Override
    public void onFailure() {
        // 2.2 - When getting error, we update UI
        this.updateUIWhenStopingHTTPRequest("An error happened !");
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    // 3 - Update UI showing only name of users

    private void updateUIWithListOfUsers(List<NewsArticles> news){
        StringBuilder stringBuilder = new StringBuilder();
        for (NewsArticles titre : news){
            stringBuilder.append("-"+titre.getTitre()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }

    private void updateUIWhenStopingHTTPRequest(String s) {
        TextView textView = (TextView) getView().findViewById(R.id.fragment_page_news_title);
        textView.setText(s);
    }


}

    // ------
// Implémentation avec l'AsyncTask
/*

    // 3 - We create and start our AsyncTask

    private void startAsyncTask() {
        new MyAsyncTask(this).execute("http://192.168.1.15/test_php/mysql_connect.php");
    }

    // 2 - Override methods of callback
    @Override
    public void onPreExecute() {
        // 2.1 - We update our UI before task (starting ProgressBar)
        this.updateUIBeforeTask();
    }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(String json) {
        // 2.2 - We update our UI before task (stopping ProgressBar)
        this.updateUIAfterTask(json);
    }

    // -----------------
    // UPDATE UI
    // -----------------

    public void updateUIBeforeTask(){
        //progressBar.setVisibility(View.VISIBLE);
    }

    public void updateUIAfterTask(String taskEnd){
        //progressBar.setVisibility(View.GONE);
        //Toast.makeText(super.getContext(), "Task is finally finished at : "+taskEnd+" !", Toast.LENGTH_SHORT).show();
        TextView textView = (TextView) getView().findViewById(R.id.fragment_page_news_title);
        textView.setText(taskEnd);
    }*/




