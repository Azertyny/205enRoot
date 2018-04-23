package e.clement.a205enroot;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventPageFragment extends android.support.v4.app.Fragment {


    public EventPageFragment() {
        // Required empty public constructor
    }

    // Méthode de création d'une nouvelle instance de PageFragment (ajout des datas au bundle si nécessaire)
    public static EventPageFragment newInstance(){
        return (new EventPageFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("create","eventView");
        return inflater.inflate(R.layout.fragment_event_page, container, false);


    }

    //##############################################################################################
    // Gestion du fragment"l'enfant"
    //##############################################################################################

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager eventViewPager = (ViewPager) view.findViewById(R.id.fragment_event_viewpager);


        eventViewPager.setAdapter(new PageAdapter(getChildFragmentManager()));
        TabLayout eventTabs = (TabLayout) view.findViewById(R.id.fragment_event_tabs);
        eventTabs.setupWithViewPager(eventViewPager);
        eventTabs.setTabMode(TabLayout.MODE_FIXED);

    }




    private class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return (3); // Nombre de page à montrer
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TrophyEventPageFragment.newInstance();
                case 1:
                    return RootEventPageFragment.newInstance();
                case 2:
                    return CharityEventPageFragment.newInstance();
                default:
                    return null;
            }

        }
        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return getString(R.string.trophee);
                case 1:
                    return getString(R.string.assoRoot);
                case 2:
                    return getString(R.string.assoMaroc);

                default:
                    return null;
            }
        }
    }
}
