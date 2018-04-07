package e.clement.a205enroot;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class SponsorsDetailActivity extends AppCompatActivity {

    private SponsorsDetailFragment sponsorsDetailFragment;

    // Transfert par intent Extra de la position
    public static final String ITEM_SPONSOR = "e.clement.a205enroot.DetailActivity.ITEM_SPONSOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors_detail);
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
        ActionBar ab = getSupportActionBar();
        // Rendre disponible le bouton Up
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void configureandShowFragment(){
        sponsorsDetailFragment = (SponsorsDetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_sponsors_detail);

        if(sponsorsDetailFragment == null){
            sponsorsDetailFragment = new SponsorsDetailFragment();
            // Ajout du fragment dans le frame layout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_sponsors_detail,sponsorsDetailFragment)
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
        SponsorsArticles sponsors = getIntent().getParcelableExtra(ITEM_SPONSOR);
        //NewsArticles news = getIntent().getSerializableExtra(ITEM_NEWS);
        //Log.e(getClass().getSimpleName(),sponsors.getName() );
        sponsorsDetailFragment.updateUI(sponsors);

    }


}
