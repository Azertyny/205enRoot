package e.clement.a205enroot;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryPageFragment extends android.support.v4.app.Fragment {


    @BindView(R.id.fragment_gallery_recycler_view) RecyclerView recyclerView; // 1 - Declare RecyclerView
    //@BindView(R.id.fragment_gallery_swipe_container) android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;
    //SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.galleryIndeterminateBar)
    ProgressBar progressBar;

    // Varaibles de gestion des données
    private DisposableObserver<List<Image>> disposable;
    private List<Image> images;
    public GalleryAdapter adapter;

    public GalleryPageFragment() {
        // Required empty public constructor
    }

    private OnImageClickedListener mImageCallback;

    public interface OnImageClickedListener{
        void onImageClicked(Image image);
    }

    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static GalleryPageFragment newInstance(){
        return (new GalleryPageFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View galleryView = inflater.inflate(R.layout.fragment_gallery_page, container, false);
        ButterKnife.bind(this, galleryView);

        this.configureRecyclerView();
        this.excecuteHttpRetrofit();
        //this.configureSwipeRefreshLayout();
        this.configureOnclickRecyclerView();

        Log.e("create","galleryView");
        // Inflate the layout for this fragment
        return galleryView;
    }

    // ###
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void configureRecyclerView(){

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);



        this.images = new ArrayList<>();

        this.adapter = new GalleryAdapter(this.images);



        this.recyclerView.setAdapter(this.adapter);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void excecuteHttpRetrofit() {
        progressBar.setVisibility(View.VISIBLE);
        //this.updateUIWhenStartingHTTPRequest();
        String langue = this.getResources().getConfiguration().locale.getDisplayLanguage();
        String code;
        if (langue.contentEquals("English")){
            code = "en";
        }
        else{code = "fr";}
        Log.e("LANGAGE",langue);
        this.disposable = HttpStreams.streamFetchImagesFollowing(code).subscribeWith(new DisposableObserver<List<Image>>(){
            @Override
            public void onNext(List<Image> images){
                Log.e("TAG","On Next");
                updateUI(images);
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


    /*private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                excecuteHttpRetrofit();
            }
        });
    }*/

    private void configureOnclickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.gallery_page_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                        Image image = adapter.getImage(position);
                        mImageCallback.onImageClicked(image);
                    }
                });
    }

    private void updateUI(List<Image> mImages){
        //swipeRefreshLayout.setRefreshing(false);
        images.clear();
        images.addAll(mImages);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    private void createCallbackToParentActivity(){
        try{
            mImageCallback = (OnImageClickedListener) getActivity();

        }catch (ClassCastException e){
            throw new ClassCastException(e.toString()+ " implementer in listener");
        }
    }
}
