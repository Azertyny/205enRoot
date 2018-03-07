package e.clement.a205enroot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Clément on 07/03/2018.
 */

public class PageAdapter extends FragmentPagerAdapter{
    // Tableau des couleurs passant par PageFragment
    private int[] colors;


    // Constructeur par défaut
    public PageAdapter(FragmentManager mgr, int[] colors){
        super(mgr);
        this.colors = colors;

    }

    @Override
    public int getCount(){
        return(5); // Nombre de page à montrer
    }

    @Override
    public Fragment getItem(int position){
        // Page à renvoyer
        return(PageFragment.newInstance(position,this.colors[position]));
    }

    // Retourne le "titre" de chaque page du viewPager
    @Override
    public CharSequence getPageTitle(int position){
        return "" + position;
    }
}
