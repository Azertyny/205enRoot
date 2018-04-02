package e.clement.a205enroot;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //ProgressBar progressBar;
    private Toolbar toolbar;                // Barre supérieure
    private DrawerLayout drawerLayout;      // Page latérale déroulante
    private NavigationView navigationView;  // Menu du DrawerLayout
    protected TabLayout tabs;               // Barre de navigation inférieure

    protected LinearLayout.LayoutParams layoutParamsSelected, layoutParamsDefault; // Gestion de la selection ou non des icônes de la barre de navigation
    protected View viewHome, viewEvent, viewGallery, viewMaps, viewSponsors; // Vue correspondant à chaque icônes de la barre de navigation

    private int[] tabIcons = {          // Layout de chaque icône (Selector permettant la gestion d'état)
            R.layout.ic_tab_home,
            R.layout.ic_tab_event,
            R.layout.ic_tab_gallery,
            R.layout.ic_tab_maps,
            R.layout.ic_tab_sponsors
    };

    protected String[] mails = {"foyart@et.esiea.fr","hanna@et.esiea.fr"};

    protected void onCreate(Bundle savedInstanceState) {
        // Toutes les méthodes de configuration des éléments graphique sont appelées ici :
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuration de la Toolbar
        this.configureToolbar();
        // Configuration du Drawer Layout
        this.configureDrawerLayout();
        // Configuration de la navigation View
        this.configureNavigationView();
        // Configuration du ViewPager
        this.configureViewPagerAndTabs();
        // Appels réseaux
        //this.excecuteHttpRetrofit();
    }

    @Override
    public void onBackPressed(){
        // Gère la fermeture du DrawerLayout
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Gère la sélection d'un item dans le menu du DrawerLayout
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
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, mails);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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

    private void configureToolbar(){
        // Récupère la Toolbar View à partir du Layout
        this.toolbar = findViewById(R.id.toolbar);
        // Active la Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureDrawerLayout() {
        // Récupère le layout du DrawerLayout
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        // Instancie la gestion du listener sur le drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureViewPagerAndTabs(){
        // Obtention du ViewPager à partir du Layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);

        // Récupération de l'adapteur et définit le fragment à afficher au démarrage
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()){});
        pager.setCurrentItem(0);

        // Récupère le layout du TanLayout
        tabs = findViewById(R.id.activity_main_tablayout);

        // "Colle" le TabLayout et le ViewPager ensemble
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        // Initialise les icônes et leur gestion
        initialiseLayoutParams();
        setupTabIcons();
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons() {
        // Création des vues des différents icônes destinés à être dans le TabLayout
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

        // Ajout des vues dans le TabLayout
        if (tabs != null) {
            tabs.getTabAt(0).setCustomView(viewHome);
            tabs.getTabAt(1).setCustomView(viewEvent);
            tabs.getTabAt(2).setCustomView(viewGallery);
            tabs.getTabAt(3).setCustomView(viewMaps);
            tabs.getTabAt(4).setCustomView(viewSponsors);
        }
        // Listeners du tabLayout
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelected(tab.getCustomView());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {changeTabDefault(tab.getCustomView());}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void initialiseLayoutParams() {
        // Définit la taille des icônes en fonction de leur état
        layoutParamsSelected = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_32dp), getResources().getDimensionPixelSize(R.dimen.value_32dp));
        layoutParamsDefault = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_30dp), getResources().getDimensionPixelSize(R.dimen.value_30dp));
    }

    protected void changeTabSelected(View view)
            // Gestion de l'état de l'icône lorsqu'il est sélectionné
    {view.setLayoutParams(layoutParamsSelected);}

    private void changeTabDefault(View view)
            // Gestion de l'état de l'icône lorsqu'il est désélectionné
    {view.setLayoutParams(layoutParamsDefault);}


    //##################################
    // Appels réseaux




}
