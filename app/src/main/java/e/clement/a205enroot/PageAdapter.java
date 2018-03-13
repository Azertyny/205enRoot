package e.clement.a205enroot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Clément on 07/03/2018.
 */

public class PageAdapter extends FragmentPagerAdapter{


    // Constructeur par défaut
    public PageAdapter(FragmentManager mgr){
        super(mgr);
    }

    @Override
    public int getCount(){
        return(5); // Nombre de page à montrer
    }

    @Override
    public Fragment getItem(int position){
        // Page à renvoyer
        switch (position){
            case 0:
                return NewsPageFragment.newInstance();
            case 1:
                return EventPageFragment.newInstance();
            case 2:
                return GalleryPageFragment.newInstance();
            case 3:
                return MapsPageFragment.newInstance();
            case 4:
                return SponsorsPageFragment.newInstance();
            default:
                return null;
        }
    }

}
