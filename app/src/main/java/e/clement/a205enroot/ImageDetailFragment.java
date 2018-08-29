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
public class ImageDetailFragment extends Fragment {

    TextView legend, description;
    ImageView image;

    public ImageDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        //this.legend = view.findViewById(R.id.image_item_detail_fragment_name);
        //this.description = view.findViewById(R.id.image_item_detail_fragment_description);
        this.image = view.findViewById(R.id.image_item_detail_fragment_image);
        return (view);
    }
    public void updateUI(Image mImage){
        //this.legend.setText(mImage.getLegend());
        //this.description.setText(mImage.getDescription());
        Glide.with(this).load(mImage.getUrl()).into(image);
    }
}
