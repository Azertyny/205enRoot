package e.clement.a205enroot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        View view = inflater.inflate(R.layout.fragment_maps_page, container, false);
                //inflater.inflate(R.layout.fragment_maps_page, container, false);
        //Inflate the layout for this fragment
        //coordinates.add(init);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        excecuteHttpRetrofit();
        return view;
    }




    private void excecuteHttpRetrofit() {
        this.disposable = HttpStreams.streamFetchCoordinatesFollowing("map.php").subscribeWith(new DisposableObserver<List<Coordinates>>(){
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

        for (int i = 0; i<= 3; i++) {
            /*float lat = ;
            /*float lat = ;
            float longi = coordinates.get(0).getLongitude();*/


            //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, longi), 10));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(coordinates.get(i).getLatitude(), coordinates.get(i).getLongitude())));
        }
        //mapFragment.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,longi , 10));

        //updateUIWhenStopingHTTPRequest(lat,longi);
    }

    private void updateUIWhenStopingHTTPRequest(float lat, float longi) {
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
