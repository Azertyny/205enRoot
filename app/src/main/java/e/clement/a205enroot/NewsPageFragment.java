package e.clement.a205enroot;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

// -------------------------------------------------------------------------------------------------
// Fragment correspondant à la page de News (Position 0 du ViewPager
// Des éléments graphiques et de données sont aussi implémentés au sein du fragment :
//      - RecyclerView
//      - SwipeRefreshLayout
//      - Requête réseau
// -------------------------------------------------------------------------------------------------
public class NewsPageFragment extends android.support.v4.app.Fragment /*implements MyAsyncTask.Listeners,*/{

    // Récupération de la RecyclerView dans le Layout et affectation à une variable
    @BindView(R.id.fragment_news_recycler_view) RecyclerView recyclerView;
    public NewsAdapter adapter;                                 // Adapteur pour la RecyclerView
    // Récupération du SwipeRefreshLayout dans le Layout et affectation à une variable
    @BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.newsIndeterminateBar) ProgressBar progressBar;

    // Variables de gestion des données récupérer sur le server
    private DisposableObserver<List<NewsArticles>> disposable;  // Observer de HttpStreams
    private List<NewsArticles> newsArticles;                    // Liste des objets NewsArticles

    // Variable de l'interface de Callback (cf. MainActivity.java)
    private OnItemClickedListener mCallback;
    // Déclaration de l'interface de Callback lors du clic sur un item de la RecyclerView
    public interface OnItemClickedListener{
        void onItemClicked(NewsArticles news);
    }

    // Méthode de création d'une nouvelle instance de PageFragment appelée dans "PageAdapter"
    public static NewsPageFragment newInstance(){
        return (new NewsPageFragment());
    }

    // Lors de la création du fragment ...
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_page, container, false);
        ButterKnife.bind(this, view);
        // ...Appel des méthodes de configuration et initialisation des différents éléments du fragment :


        this.configureRecyclerView();
        this.excecuteHttpRetrofit();
        this.configureSwipeRefreshLayout();
        this.configureOnclickRecyclerView();
        Log.e("create","newsView");
        return view;
    }

    // ---------------------------------------------------------------------------------------------
    // RECYCLERVIEW
    // ---------------------------------------------------------------------------------------------
    private void configureRecyclerView(){
        // Initialisation de la liste d'articles
        this.newsArticles = new ArrayList<>();
        // Remplissage de la RecyclerView avec la liste d'articles par l'adaptateur
        this.adapter = new NewsAdapter(this.newsArticles);
        this.recyclerView.setAdapter(this.adapter);
        // Placement des différents items de la RecyclerView par le LayoutManager
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // ---------------------------------------------------------------------------------------------
    // NETWORK REQUEST
    // ---------------------------------------------------------------------------------------------
    private void excecuteHttpRetrofit() {
        progressBar.setVisibility(View.VISIBLE);
        // Vérification de la langue utilisée sur le téléphone afin de faire la requête sur la BDD associée :
        String langue = this.getResources().getConfiguration().locale.getDisplayLanguage();
        String code;
        if (langue.contentEquals("English")){
            code = "en";
        }
        else{code = "fr";}
        Log.e("LANGAGE",langue);
        // Inscription de l'Observer au stream
        this.disposable = HttpStreams.streamFetchNewsFollowing(code).subscribeWith(new DisposableObserver<List<NewsArticles>>(){
            @Override
            public void onNext(List<NewsArticles> news){
                Log.e("NEWS","On Next");
                // Une fois la requête réseau effectuée, mise à jour de la RecyclerView :

                updateUI(news);
            }
            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
            }
            @Override
            public void onComplete() {
                Log.e("NEWS","On Complete");
            }
        });
    }
    private void updateUI(List<NewsArticles> articles){

        // Désactive la possibilité de rafraîchissment lors de la mis à jour des données :
        swipeRefreshLayout.setRefreshing(false);
        // Reset de la RecyclerView :
        newsArticles.clear();
        // Ajout des articles :
        newsArticles.addAll(articles);
        // Notification à l'adapter du changement de données :
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }
    // Méthodes de désinscription du stream lors de la destruction du fragment
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }
    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // SWIPE REFRESH LAYOUT
    // ---------------------------------------------------------------------------------------------
    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Lors d'un rafraîchissment, on refait une requête http :
                excecuteHttpRetrofit();
            }
        });
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // CALLBACK
    // ---------------------------------------------------------------------------------------------
    private void configureOnclickRecyclerView(){
        // Ajout d'un listener pour chaque élément de la RecyclerView :
        ItemClickSupport.addTo(recyclerView, R.layout.news_page_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                        NewsArticles news = adapter.getNews(position);
                        mCallback.onItemClicked(news);
                    }
                });
    }

    // Méthodes permettant de faire le lien avec MainActivity.java :
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }
    private void createCallbackToParentActivity(){
        try{
            mCallback = (OnItemClickedListener) getActivity();

        }catch (ClassCastException e){
            throw new ClassCastException(e.toString()+ " implementer in listener");
        }
    }
    // ---------------------------------------------------------------------------------------------
}