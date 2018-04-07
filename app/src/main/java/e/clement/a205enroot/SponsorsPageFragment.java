package e.clement.a205enroot;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorsPageFragment extends Fragment {

    //RecyclerView recyclerView;
    @BindView(R.id.fragment_sponsors_recycler_view) RecyclerView recyclerView; // 1 - Declare RecyclerView
    @BindView(R.id.fragment_sponsors_swipe_container) SwipeRefreshLayout swipeRefreshLayout;


    private DisposableObserver<List<SponsorsArticles>> disposable;
    private List<SponsorsArticles> sponsorsArticles;
    public SponsorsAdapter adapter;

    // Ajout de l'interface de Callback
    private OnItemClickedListener mCallback;

    public interface OnItemClickedListener{
        void onSponsorsItemClicked(SponsorsArticles sponsors);
    }

    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static SponsorsPageFragment newInstance(){
        return (new SponsorsPageFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ###
        View view = inflater.inflate(R.layout.fragment_sponsors_page, container, false);
        ButterKnife.bind(this, view);

        this.configureRecyclerView();
        this.excecuteHttpRetrofit();
        this.configureSwipeRefreshLayout();
        this.configureOnclickRecyclerView();

        return view;
    }

    // ###
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }


    private void configureRecyclerView(){

        // 3.1 - Reset list
        this.sponsorsArticles = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new SponsorsAdapter(this.sponsorsArticles);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void excecuteHttpRetrofit() {
        //this.updateUIWhenStartingHTTPRequest();

        this.disposable = HttpStreams.streamFetchSponsorsFollowing("sponsors.php").subscribeWith(new DisposableObserver<List<SponsorsArticles>>(){
            @Override
            public void onNext(List<SponsorsArticles> sponsors){
                Log.e("TAG","On Next");
                updateUI(sponsors);
            }
            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
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

    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                excecuteHttpRetrofit();
            }
        });
    }

    private void configureOnclickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.sponsors_page_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                        SponsorsArticles sponsor = adapter.getSponsors(position);
                        mCallback.onSponsorsItemClicked(sponsor);
                    }
                });
    }
    private void updateUI(List<SponsorsArticles> articles){
        swipeRefreshLayout.setRefreshing(false);
        sponsorsArticles.clear();
        sponsorsArticles.addAll(articles);
        adapter.notifyDataSetChanged();
    }

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

}
