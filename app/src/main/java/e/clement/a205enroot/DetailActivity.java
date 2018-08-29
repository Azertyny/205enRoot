package e.clement.a205enroot;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// -------------------------------------------------------------------------------------------------
// Activité permettant de voir les détails d'un article
// Elle contient un fragment "DetailFragment" pour une future adaptation au format tablette du
// fragment News de l'application
// -------------------------------------------------------------------------------------------------
public class DetailActivity extends AppCompatActivity {

    // Déclaration de la variable du fragment
    private DetailFragment detailFragment;

    // Transfert par intent Extra de l'Objet NewsArticles sélectionné :
    public static final String ITEM_NEWS = "e.clement.a205enroot.DetailActivity.ITEM_NEWS";

    //
    public ActionBar actionBar;

    // Lors de la création de l'activité ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // ...Appel des méthodes de configuration et initialisation des différents éléments de l'UI :
        // Configuration de la Toolbar
        this.configureToolbar();
        // Configuration du fragment
        this.configureandShowFragment();
    }

    // ---------------------------------------------------------------------------------------------
    // TOOLBAR
    // ---------------------------------------------------------------------------------------------
    private void configureToolbar(){
        // Obtention de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Set la Toolbar
        setSupportActionBar(toolbar);
        // Obtention d'un support ActionBar correspondant à cette Toolbar
        actionBar = getSupportActionBar();
        // Rendre disponible le bouton Up de retour à la MainActivity
        actionBar.setDisplayHomeAsUpEnabled(false);
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // FRAGMENT
    // ---------------------------------------------------------------------------------------------
    private void configureandShowFragment(){

        // Appel du Layout "type" pour l'affichage des détails de la news
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);
        // Si le fragment n'est déjà pas existant, alors création d'un nouveau :
        if(detailFragment == null){
            detailFragment = new DetailFragment();
            // Ajout du fragment dans le frame layout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail,detailFragment)
                    .commit();
            Log.e(getClass().getSimpleName(),"Fragment de détail de la news.");
        }
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


    @Override
    public void onResume(){
        super.onResume();
        // Une fois l'activité créée et le fragment implémenté, màj de celui-ci :
        this.updateDetailFragment();
    }

    private void updateDetailFragment() {
        NewsArticles news = getIntent().getParcelableExtra(ITEM_NEWS);
        Log.e(getClass().getSimpleName(), news.getTitle() );
        String langue = this.getResources().getConfiguration().locale.getDisplayLanguage();
        String date;
        if (langue.contentEquals("English")){
            date = new SimpleDateFormat("EEE, d MMM yyyy ", Locale.ENGLISH).format(news.getDate());
        }
        else{
            date = new SimpleDateFormat("EEE, d MMM yyyy ", Locale.FRANCE).format(news.getDate());
        }

        actionBar.setTitle(date);
        // Appel de la méthode de mise à jour de l'affichage du fragment :
        detailFragment.updateUI(news);
    }
}
