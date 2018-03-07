package e.clement.a205enroot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView imageView205;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    //private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Configuration de la Toolbar
        this.configureToolbar();
        // Configuration du Drawer Layout
        this.configureDrawerLayout();
        // Configuration de la navigation View
        this.configureNavigationView();
        // Configuration de l'image View
            //this.configureImageView();
        // Configuration du TabLayout
            //this.configureTabLayout();
        // Configuration du ViewPager
        this.configureViewPagerAndTabs();
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

    /*private void configureImageView(){
        // Sérialise l'ImageView
        this.imageView205 = (ImageView) this.findViewById(R.id.imageView);
        // Attache d'un listener
        imageView205.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Démarre la détail activity
                launchDetailActivity();
            }
        });
    }

    private void launchDetailActivity(){
        Intent myIntent = new Intent(MainActivity.this,DetailActivity.class);
        this.startActivity(myIntent);
    }*/

    private void launchActivity(int tab){
        Intent myIntent = new Intent(MainActivity.this,MainActivity.class);;
        switch (tab){

            case 0:
                myIntent = new Intent(MainActivity.this,MainActivity.class);
                break;
            case 1:

                break;
            case 2:

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




    /*
    // Méthode faisant le lien entre les différentes classes ("Coller" le viewpager et le fragment)
    private void configureViewPager(){
        // Obtention du ViewPager à partir du Layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        // Configure l'adapteur et les "colle"
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),getResources().getIntArray(R.array.colorPagesViewPager)){});
    }*/

    // Méthode faisant le lien entre le ViewPager et le TabLayout
    private void configureViewPagerAndTabs(){
        // Obtention du ViewPager à partir du Layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        // Configure l'adapteur et les "colle"
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),getResources().getIntArray(R.array.colorPagesViewPager)){});

        // Récupère le Tablayout du Layout
        TabLayout tabs = (TabLayout) findViewById(R.id.activity_main_tablayout);
        // Colle le TabLayout et le ViewPager
        tabs.setupWithViewPager(pager);
        // Design
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
