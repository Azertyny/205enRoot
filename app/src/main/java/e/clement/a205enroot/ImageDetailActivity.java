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

public class ImageDetailActivity extends AppCompatActivity {

    private ImageDetailFragment imageDetailFragment;
    public Toolbar toolbar;
    public ActionBar ab;
    // Transfert par intent Extra de la position
    public static final String ITEM_IMAGE = "e.clement.a205enroot.ImageDetailActivity.ITEM_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
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
        imageDetailFragment = (ImageDetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_image_detail);

        if(imageDetailFragment == null){
            imageDetailFragment = new ImageDetailFragment();
            // Ajout du fragment dans le frame layout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_image_detail,imageDetailFragment)
                    .commit();
            Log.e(getClass().getSimpleName(),"Coucou !");
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
        this.updateDetailFragment();
    }

    private void updateDetailFragment() {
        Image images = getIntent().getParcelableExtra(ITEM_IMAGE);
        //Log.e(getClass().getSimpleName(),sponsors.getName() );
        ab.setTitle(images.getLegend());
        imageDetailFragment.updateUI(images);

    }

}
