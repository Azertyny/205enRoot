package e.clement.a205enroot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsPageFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback{


    public MapsPageFragment() {
        // Required empty public constructor
    }

    // Varaibles de gestion des données
    private DisposableObserver<List<Coordinates>> disposable;
    Coordinates init = new Coordinates();
    private List<Coordinates> coordinates = new ArrayList<>();
    //SupportMapFragment mapFragment;
    SupportMapFragment mapFragment;


    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static MapsPageFragment newInstance(){
        return (new MapsPageFragment());
    }

    private MapView mapView;
    private GoogleMap googleMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mapView = inflater.inflate(R.layout.fragment_maps_page, container, false);
                //inflater.inflate(R.layout.fragment_maps_page, container, false);
        //Inflate the layout for this fragment
        //coordinates.add(init);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        excecuteHttpRetrofit();

        return mapView;
    }




    private void excecuteHttpRetrofit() {
        String langue = this.getResources().getConfiguration().locale.getDisplayLanguage();
        String code;
        if (langue.contentEquals("English")){
            code = "en";
        }
        else{code = "fr";}
        Log.e("LANGAGE",langue);
        this.disposable = HttpStreams.streamFetchCoordinatesFollowing(code).subscribeWith(new DisposableObserver<List<Coordinates>>(){
            @Override
            public void onNext(List<Coordinates> m_coordinates){
                Log.e("TAG","On Next");

                coordinates = m_coordinates;
            }
            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
            }
            @Override
            public void onComplete() {
                Log.e("TAG","On Complete !!");
                Log.e("TAG",""+coordinates.get(0).getLatitude() + coordinates.get(0).getLatitude());
                updateUI();
            }
        });

    }
    private void updateUI(){
        int i;
        for ( i = 0; i<coordinates.size()-1; i++) {

            googleMap.addMarker(new MarkerOptions().position(new LatLng(coordinates.get(i).getLatitude(), coordinates.get(i).getLongitude())).title(coordinates.get(i).getInfo()));
        }
        googleMap.addMarker(new MarkerOptions().position(new LatLng(coordinates.get(i).getLatitude(), coordinates.get(i).getLongitude())).title(coordinates.get(i).getInfo()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(coordinates.get(i).getLatitude(),coordinates.get(i).getLongitude()),(float)7.6));

        //Toast.makeText(this.getContext(),getString(R.string.balise),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onMapReady(GoogleMap m_googleMap){
        googleMap = m_googleMap;
        /*googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 10));
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);*/
        //setUpMap();
    }



    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}
