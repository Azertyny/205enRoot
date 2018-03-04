package e.clement.a205enroot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SponsorsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        // Configuration de la Toolbar
        this.configureToolbar();
        // Configuration du Drawer Layout
        this.configureDrawerLayout();
        // Configuration de la navigation View
        this.configureNavigationView();

        // Configuration du TabLayout
        this.configureTabLayout();
    }

    @Override
    public void onBackPressed(){
        // Gère la fermeture du menu
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Gère la sélection d'un item dans le menu
        int id = item.getItemId();
        switch (id){
            case R.id.activity_main_drawer_games:
                // Code de lancement du/des jeu(x)
                break;
            case R.id.activity_main_drawer_settings:
                // Code de lancement de gestion des paramètres
                break;
            case R.id.activity_main_drawer_contact:
                // Code de lancement d'une application tierce (SMS,Appel,Mail)
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    // Affiche le menu (Icônes) et l'ajoute à la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Gère les actions sur les items du menu contenu dans le fichier XML "menu_activity_main"
        switch (item.getItemId()){
            case R.id.menu_activity_main_params:

                // ... Code pour les paramètres

                Toast.makeText(this, "Il n'ya rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:

                // ... Code pour la recherche

                Toast.makeText(this, "Aucune recherche possible", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Méthodes de configuration appelée dans le "onCreate" de l'activité
    private void configureToolbar(){
        // Récupère la Toolbar View à partir du Layout
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Active la Toolbar
        setSupportActionBar(toolbar);
    }

    private void launchActivity(int tab){
        Intent myIntent = new Intent(SponsorsActivity.this,MainActivity.class);;
        switch (tab){

            case 0:
                myIntent = new Intent(SponsorsActivity.this,MainActivity.class);
                break;
            case 1:
                myIntent = new Intent(SponsorsActivity.this,TrophyActivity.class);
                break;
            case 2:
                myIntent = new Intent(SponsorsActivity.this,SponsorsActivity.class);
                break;
            case 3:
                break;
            case 4:
                break;

        }
        this.startActivity(myIntent);
    }

    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout)findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureTabLayout(){
        this.tabLayout = (TabLayout) findViewById(R.id.activity_main_tablayout);
        /*tabLayout.addTab(tabLayout.newTab().setText("1"));
        tabLayout.addTab(tabLayout.newTab().setText("2"));
        tabLayout.addTab(tabLayout.newTab().setText("3"));
        tabLayout.addTab(tabLayout.newTab().setText("4"));
        tabLayout.addTab(tabLayout.newTab().setText("5"));*/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                //switch (pos){
                //case 0:
                launchActivity(pos);
                        /*Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),"5",Toast.LENGTH_SHORT).show();
                        break;*/
                //}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}