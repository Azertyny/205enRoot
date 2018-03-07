package e.clement.a205enroot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    // Création des clés pour le Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";

    public PageFragment() {
        // Required empty public constructor
    }

    // Méthode de création d'une nouvelle instance de PageFragment, ajoute les datas au bundle
    public static PageFragment newInstance(int position, int color){
        // Création du nouveau fragment
        PageFragment frag = new PageFragment();

        // Création du bundle et ajout des datas
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR,color);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);

        FrameLayout rootView = (FrameLayout) result.findViewById(R.id.fragment_page_rootview);
        TextView textView = (TextView) result.findViewById(R.id.fragment_page_title);

        // Récupère les données du Bundle ...
        int position = getArguments().getInt(KEY_POSITION,-1);
        int color = getArguments().getInt(KEY_COLOR,-1);
        // ... et met à jour les widgets avec :
        rootView.setBackgroundColor(color);
        textView.setText("Page numéro " + position);

        Log.e(getClass().getSimpleName(),"onCreateView called for fragment number " + position);

        return result;
    }

}
