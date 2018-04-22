package e.clement.a205enroot;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;

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
