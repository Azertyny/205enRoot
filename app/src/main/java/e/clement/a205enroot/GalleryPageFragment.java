package e.clement.a205enroot;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryPageFragment extends Fragment {


    @BindView(R.id.fragment_gallery_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView
    @BindView(R.id.fragment_gallery_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

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
        View view = inflater.inflate(R.layout.fragment_gallery_page, container, false);
        ButterKnife.bind(this, view);

        this.configureRecyclerView();
        this.excecuteHttpRetrofit();
        this.configureSwipeRefreshLayout();
        this.configureOnclickRecyclerView();

        // Inflate the layout for this fragment
        return view;
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


        // 3.1 - Reset list
        this.images = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new GalleryAdapter(this.images);


        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void excecuteHttpRetrofit() {
        //this.updateUIWhenStartingHTTPRequest();

        this.disposable = HttpStreams.streamFetchImagesFollowing("gallery.php").subscribeWith(new DisposableObserver<List<Image>>(){
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
    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                excecuteHttpRetrofit();
            }
        });
    }

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
        swipeRefreshLayout.setRefreshing(false);
        images.clear();
        images.addAll(mImages);
        adapter.notifyDataSetChanged();
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
