package e.clement.a205enroot;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class SponsorsDetailActivity extends AppCompatActivity {

    private SponsorsDetailFragment sponsorsDetailFragment;
    public Toolbar toolbar;
    // Transfert par intent Extra de la position
    public static final String ITEM_SPONSOR = "e.clement.a205enroot.SponsorsDetailActivity.ITEM_SPONSOR";
    public ActionBar ab;
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Set la Toolbar
        setSupportActionBar(toolbar);
        // Obtention d'un support ActionBar correspondant à cette Toolbar
        ab = getSupportActionBar();
        // Rendre disponible le bouton Up
        ab.setDisplayHomeAsUpEnabled(false);
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
        String langue = this.getResources().getConfiguration().locale.getDisplayLanguage();
        String date;
        if (langue.contentEquals("English")){
            date = new SimpleDateFormat("EEE, d MMM yyyy ", Locale.ENGLISH).format(sponsors.getDate());
        }
        else{
            date = new SimpleDateFormat("EEE, d MMM yyyy ", Locale.FRANCE).format(sponsors.getDate());
        }

        ab.setTitle(date);
        sponsorsDetailFragment.updateUI(sponsors);
    }

    /*@Override
    public void onBackPressed() {
        if (MainActivity.stackkk.size() > 1) {
            MainActivity.stackkk.pop();
            MainActivity.pager.setCurrentItem(MainActivity.stackkk.lastElement());
            //changeTabSelected(tab.getCustomView());
        } else {
        }
    }*/

}
