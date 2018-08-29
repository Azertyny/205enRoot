package e.clement.a205enroot;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Récupère le Layout du menu de la Toolbar et l'affiche
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Gère les actions sur les items du menu de la Toolbar
        switch (item.getItemId()){
            case R.id.menu_activity_main_cafe:
                // Faire un don, ouverture du navigateur web par défaut du téléphone
                String url_cagnote = "https://www.leetchi.com//c/association-205-en-root";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_cagnote));
                startActivity(intent);
                Toast.makeText(this, getString(R.string.cafe), Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
