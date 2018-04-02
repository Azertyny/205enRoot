package e.clement.a205enroot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsPageFragment extends Fragment /*implements MyAsyncTask.Listeners,*/{

    /*public NewsPageFragment() {
        // Required empty public constructor
    }*/
    //RecyclerView recyclerView;
    @BindView(R.id.fragment_news_recycler_view) RecyclerView recyclerView; // 1 - Declare RecyclerView
    // Varaibles de gestion des données
    private DisposableObserver<List<NewsArticles>> disposable;
    private List<NewsArticles> newsArticles;
    private NewsAdapter adapter;

    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static NewsPageFragment newInstance(){
        return (new NewsPageFragment());
    }


    // ###


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ###
        View view = inflater.inflate(R.layout.fragment_news_page, container, false);
        ButterKnife.bind(this, view);

        //recyclerView = (RecyclerView) view.findViewById(R.id.fragment_news_recycler_view);


        this.configureRecyclerView();
        this.excecuteHttpRetrofit();

        // Inflate the layout for this fragment
        return view; //inflater.inflate(R.layout.fragment_news_page, container, false);
    }

    // ###
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void configureRecyclerView(){

        // 3.1 - Reset list
        this.newsArticles = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new NewsAdapter(this.newsArticles);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    private void excecuteHttpRetrofit() {
        //this.updateUIWhenStartingHTTPRequest();

        this.disposable = HttpStreams.streamFetchNewsFollowing("mysql_connect.php").subscribeWith(new DisposableObserver<List<NewsArticles>>(){
           @Override
            public void onNext(List<NewsArticles> news){
               Log.e("TAG","On Next");
               updateUI(news);
           }
            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
                //TextView textView = (TextView) getView().findViewById(R.id.news_page_item_title);
                //textView.setText("Pas de réseau");
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

    //private void updateUIWhenStartingHTTPRequest(){
        //TextView textView = (TextView) getView().findViewById(R.id.fragment_page_news_title);
        //textView.setText("Downloading...");
    //}

    //private void updateUIWhenStopingHTTPRequest(String response){
        //TextView textView = (TextView) getView().findViewById(R.id.news_page_item_title);
        //textView.setText(response);
    //}

    private void updateUI(List<NewsArticles> articles){
        newsArticles.addAll(articles);
        adapter.notifyDataSetChanged();

        /*StringBuilder stringBuilder = new StringBuilder();
        for (NewsArticles titre : news){
            stringBuilder.append("-"+titre.getTitre()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());*/
    }
/*
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
    }*/


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




