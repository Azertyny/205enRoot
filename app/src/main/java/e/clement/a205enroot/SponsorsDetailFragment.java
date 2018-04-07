package e.clement.a205enroot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorsDetailFragment extends Fragment {

    TextView name, description;
    ImageView image;

    public SponsorsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsors_detail, container, false);
        this.name = view.findViewById(R.id.sponsors_item_detail_fragment_name);
        this.description = view.findViewById(R.id.sponsors_item_detail_fragment_description);
        this.image = view.findViewById(R.id.sponsors_item_detail_fragment_image);
        return (view);
    }
    public void updateUI(SponsorsArticles sponsors){
        this.name.setText(sponsors.getName());
        this.description.setText(sponsors.getSponsorDescription());
        Glide.with(this).load(sponsors.getUrl()).into(image);
    }

}
