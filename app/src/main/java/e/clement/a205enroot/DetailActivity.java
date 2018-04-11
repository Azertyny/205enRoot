package e.clement.a205enroot;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment;

    // Transfert par intent Extra de la position
    public static final String ITEM_NEWS = "e.clement.a205enroot.DetailActivity.ITEM_NEWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Configuration de la Toolbar
        this.configureToolbar();
        // Configuration du fragment
        this.configureandShowFragment();
    }

    private void configureToolbar(){
        // Obtention de la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Set la Toolbar
        setSupportActionBar(toolbar);
        // Obtention d'un support ActionBar correspondant à cette Toolbar
        ActionBar ac = getSupportActionBar();
        // Rendre disponible le bouton Up
        ac.setDisplayHomeAsUpEnabled(true);
    }

    private void configureandShowFragment(){
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);

        if(detailFragment == null){
            detailFragment = new DetailFragment();
            // Ajout du fragment dans le frame layout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail,detailFragment)
                    .commit();
            Log.e(getClass().getSimpleName(),"Coucou !");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        this.updateDetailFragment();
    }

    private void updateDetailFragment() {
        NewsArticles news = getIntent().getParcelableExtra(ITEM_NEWS);
        //NewsArticles news = getIntent().getSerializableExtra(ITEM_NEWS);
        Log.e(getClass().getSimpleName(),news.getTitle() );
        detailFragment.updateUI(news);

    }


    // ################# Gestion de l'affichage en mode tablette à faire
}
