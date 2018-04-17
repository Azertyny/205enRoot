package e.clement.a205enroot;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;


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
    public void onResume(){
        super.onResume();
        // Une fois l'activité créée et le fragment implémenté, màj de celui-ci :
        this.updateDetailFragment();
    }

    private void updateDetailFragment() {
        NewsArticles news = getIntent().getParcelableExtra(ITEM_NEWS);
        Log.e(getClass().getSimpleName(), news.getTitle() );
        actionBar.setTitle("205 en ROOT");
        // Appel de la méthode de mise à jour de l'affichage du fragment :
        detailFragment.updateUI(news);
    }
}
