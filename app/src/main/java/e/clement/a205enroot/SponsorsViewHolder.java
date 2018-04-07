package e.clement.a205enroot;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SponsorsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.sponsors_page_item_name) TextView name;
    @BindView(R.id.sponsors_page_item_description) TextView description;
    @BindView(R.id.sponsors_page_item_image) ImageView image;
    public SponsorsViewHolder(View itemView) {
        super(itemView);
        //itemView = (TextView) itemView.findViewById(R.id.news_page_item_title);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithSponsorsTitle(SponsorsArticles sponsors){
        this.name.setText(sponsors.getName());
        this.description.setText(sponsors.getSponsorDescription());
        Glide.with(this.image).load(sponsors.getUrl()).into(image);
    }

}
