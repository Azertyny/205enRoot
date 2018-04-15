package e.clement.a205enroot;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
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
import android.util.Log;
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


import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static java.lang.Boolean.FALSE;

// -------------------------------------------------------------------------------------------------
// Activité principale de l'application sur laquelle tous les éléments graphiques s'apuient :
//      - DrawerLayout
//      - Viewpager
//      - Toolbar
//      - TabLayout
// -------------------------------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NewsPageFragment.OnItemClickedListener,SponsorsPageFragment.OnSponsorClickedListener, GalleryPageFragment.OnImageClickedListener {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;                          // Page latérale déroulante
    private NavigationView navigationView;                      // Menu du DrawerLayout
    protected TabLayout tabs;                                   // Barre de navigation inférieure
    
    // Variables de gestion de la selection dans le TabLayout
    protected LinearLayout.LayoutParams layoutParamsSelected, layoutParamsDefault;
    // Variables correspondant à chaque élément du TabLayout
    protected View viewHome, viewEvent, viewGallery, viewMaps, viewSponsors;
    // Tableau de références aux Layout des icônes du TabLayout
    private int[] tabIcons = {
            R.layout.ic_tab_home,
            R.layout.ic_tab_event,
            R.layout.ic_tab_gallery,
            R.layout.ic_tab_maps,
            R.layout.ic_tab_sponsors
    };

    // Tableau des mails à passer dans l'implicit intent
    protected String[] mails = {"foyart@et.esiea.fr","hanna@et.esiea.fr"};

    // Lors de la création de l'activité ...
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ...Appel des méthodes de configuration et initialisation des différents éléments de l'UI :
        // Configuration de la Toolbar :
        this.configureToolbar();
        // Configuration du Drawer Layout :
        this.configureDrawerLayout();
        // Configuration de la navigation View :
        this.configureNavigationView();
        // Configuration du ViewPager :
        this.configureViewPagerAndTabs();
    }

    // ---------------------------------------------------------------------------------------------
    // TOOLBAR
    // ---------------------------------------------------------------------------------------------
    private void configureToolbar(){
        // Récupère la Toolbar View à partir du Layout
        this.toolbar = findViewById(R.id.toolbar);
        // Active la Toolbar
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Récupère le Layout du menu de la Toolbar et l'affiche
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        // Initialise le texte de la toolbar :
        toolbar.setTitle(getString(R.string.news));
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
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // DRAWERLAYOUT
    // ---------------------------------------------------------------------------------------------
    private void configureDrawerLayout() {
        // Récupère le layout du DrawerLayout
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);

        // Instancie la gestion du listener permettant de déployer le DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
        switch (item.getItemId()){
            case R.id.activity_main_drawer_games:
                // Code de lancement du/des jeu(x) futurs
                break;
            case R.id.activity_main_drawer_settings:
                // Code de lancement de gestion des paramètres
                break;
            case R.id.activity_main_drawer_contact:
                // Code de d'implicit intent (Mail)
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
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // NAVIGATION
    // ---------------------------------------------------------------------------------------------
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void configureViewPagerAndTabs(){
        // Obtention du ViewPager à partir du Layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        // Récupération de l'adapteur "PageAdapter" et définit le fragment à afficher au démarrage
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()){});
        pager.setCurrentItem(0);
        // Récupère le layout du TabLayout
        tabs = findViewById(R.id.activity_main_tablayout);
        // "Colle" le TabLayout et le ViewPager ensemble
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        // Initialise les icônes et leur gestion
        initialiseLayoutParams();
        setupTabIcons();
    }
    private void initialiseLayoutParams() {
        // Définit la taille des icônes en fonction de leur état
        layoutParamsSelected = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_32dp), getResources().getDimensionPixelSize(R.dimen.value_32dp));
        layoutParamsDefault = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_30dp), getResources().getDimensionPixelSize(R.dimen.value_30dp));
    }
    private void setupTabIcons() {
        // Affectation des vues des différents icônes destinés à être dans le TabLayout
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
                int pos = tab.getPosition();
                switch (pos){
                    case 0:
                        toolbar.setTitle(getString(R.string.news));
                        break;
                    case 1:
                        toolbar.setTitle(getString(R.string.event));
                        break;
                    case 2:
                        toolbar.setTitle(getString(R.string.gallery));
                        break;
                    case 3:
                        toolbar.setTitle(getString(R.string.map));
                        break;
                    case 4:
                        toolbar.setTitle(getString(R.string.sponsors));
                        break;
                    default:
                         toolbar.setTitle("205 en ROOT");
                }
                changeTabSelected(tab.getCustomView());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {changeTabDefault(tab.getCustomView());}
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
    protected void changeTabSelected(View view)
    // Gestion de l'état de l'icône lorsqu'il est sélectionné
    {view.setLayoutParams(layoutParamsSelected);}
    private void changeTabDefault(View view)
    // Gestion de l'état de l'icône lorsqu'il est désélectionné
    {view.setLayoutParams(layoutParamsDefault);}
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // CALLBACKS
    // ---------------------------------------------------------------------------------------------
    // Implémebtation des interfaces de Callbacks pour la gestion des interactions avec les
    // fragments du ViewPager
    @Override
    public void onItemClicked(NewsArticles news){
        Log.e(getClass().getSimpleName(),"Article : "+ news.getTitle() +" selectionné.");
        // Lancement de DetailActivity.java lors de la selection d'un article :
        Intent i = new Intent(this, DetailActivity.class);
        // Passage de l'objet de type NewsArticles dans l'intent :
        i.putExtra(DetailActivity.ITEM_NEWS,news);
        startActivity(i);
    }

    @Override
    public void onSponsorsItemClicked(SponsorsArticles sponsors){
        Log.e(getClass().getSimpleName(),"Sponsor : "+sponsors.getName()+" selectionné.");
        // Lancement de SponsorsDetailActivity.java lors de la sélection d'un article :
        Intent i = new Intent(this, SponsorsDetailActivity.class);
        // Passage de l'objet de type SponsorsArticles lors de la sélection d'un article :
        i.putExtra(SponsorsDetailActivity.ITEM_SPONSOR,sponsors);
        startActivity(i);
    }

    @Override
    public void onImageClicked(Image image){
        Log.e(getClass().getSimpleName(),"Image "+image.getLegend()+" sélectionnée.");

        /* Intent i = new Intent(this, ImagesDetailActivity.class);
        i.putExtra(ImagesDetailActivity.ITEM_IMAGE,image);
        startActivity(i);*/
    }
}
