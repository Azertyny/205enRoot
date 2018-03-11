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
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;


import static java.lang.Boolean.FALSE;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    protected TabLayout tabs;

    protected LinearLayout.LayoutParams layoutParamsSelected, layoutParamsDefault;
    protected View viewHome, viewEvent, viewGallery, viewMaps, viewSponsors;

    private int[] tabIcons = {
            R.layout.ic_tab_home,
            R.layout.ic_tab_event,
            R.layout.ic_tab_gallery,
            R.layout.ic_tab_maps,
            R.layout.ic_tab_sponsors

    };

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
            case R.id.menu_activity_main_cafe:

                // ... Code pour les paramètres

                Toast.makeText(this, getString(R.string.cafe), Toast.LENGTH_SHORT).show();
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
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()){});
        pager.setCurrentItem(0);

        // Récupère le Tablayout du Layout
        tabs = (TabLayout) findViewById(R.id.activity_main_tablayout);

        // Colle le TabLayout et le ViewPager
        tabs.setupWithViewPager(pager);

        /*for(int i = 0; i <tabs.getTabCount();i++){
        tabs.getTabAt(i).setIcon(R.drawable.ic_home_white_24dp);}*/

        // Design
        tabs.setTabMode(TabLayout.MODE_FIXED);

        //
        initialiseLayoutParams();
        setupTabIcons();


    }
    private void initialiseLayoutParams() {
        layoutParamsSelected = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_32dp), getResources().getDimensionPixelSize(R.dimen.value_32dp));
        layoutParamsDefault = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_30dp), getResources().getDimensionPixelSize(R.dimen.value_30dp));
    }

    protected void changeTabSelected(View view)
    {
        //view.LayoutParameters = layoutParamsSelected;
        view.setLayoutParams(layoutParamsSelected);
    }

    private void changeTabDefault(View view)
    {
        //view.LayoutParameters = layoutParamsDefault;
        view.setLayoutParams(layoutParamsDefault);
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons() {
        // Etape 1 : On génère les vues de nos différents composants de TabLayout, à partir du fichier custom_tab_icon
        viewHome = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabs, false);
        viewHome.findViewById(R.id.icon).setBackgroundResource(tabIcons[0]);
        viewHome.setLayoutParams(layoutParamsSelected);

        viewEvent = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabs, false);
        viewEvent.findViewById(R.id.icon).setBackgroundResource(tabIcons[1]);
        viewEvent.setLayoutParams(layoutParamsDefault);

        viewGallery = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabs, false);
        viewGallery.findViewById(R.id.icon).setBackgroundResource(tabIcons[2]);
        viewGallery.setLayoutParams(layoutParamsDefault);

        viewMaps = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabs, false);
        viewMaps.findViewById(R.id.icon).setBackgroundResource(tabIcons[3]);
        viewMaps.setLayoutParams(layoutParamsDefault);

        viewSponsors = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabs, false);
        viewSponsors.findViewById(R.id.icon).setBackgroundResource(tabIcons[4]);
        viewSponsors.setLayoutParams(layoutParamsDefault);

        // Etape 2 : On ajoute les différentes vues créées ci-dessus dans notre TabLayout
        if (tabs != null) {
            tabs.getTabAt(0).setCustomView(viewHome);
            tabs.getTabAt(1).setCustomView(viewEvent);
            tabs.getTabAt(2).setCustomView(viewGallery);
            tabs.getTabAt(3).setCustomView(viewMaps);
            tabs.getTabAt(4).setCustomView(viewSponsors);
        }
        // Etape 3 : On crée le listener pour gérer les interactions avec les utilisateurs
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelected(tab.getCustomView());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabDefault(tab.getCustomView());
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
