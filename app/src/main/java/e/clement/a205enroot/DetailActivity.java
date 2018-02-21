package e.clement.a205enroot;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Configuration de la Toolbar
        this.configureToolbar();

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
}
