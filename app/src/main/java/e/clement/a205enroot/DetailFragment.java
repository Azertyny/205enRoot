package e.clement.a205enroot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TextView title, content;
    ImageView image;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        this.title = (TextView) view.findViewById(R.id.item_detail_fragment_title);
        this.content = view.findViewById(R.id.item_detail_fragment_content);
        this.image = view.findViewById(R.id.item_detail_fragment_image);
        return (view);
    }

    public void updateUI(NewsArticles news){
        this.title.setText(news.getTitle());
        this.content.setText(news.getContent());
        Glide.with(this).load(news.getUrl()).into(image);
    }

}
