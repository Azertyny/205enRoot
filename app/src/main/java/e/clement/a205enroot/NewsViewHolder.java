package e.clement.a205enroot;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Clément on 02/04/2018.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.news_page_item_title) TextView title;
    @BindView(R.id.news_page_item_content) TextView content;
    @BindView(R.id.news_page_item_image) ImageView image;
    public NewsViewHolder(View itemView) {
        super(itemView);
        //itemView = (TextView) itemView.findViewById(R.id.news_page_item_title);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNews(NewsArticles news){
        this.title.setText(news.getTitle());
        this.content.setText(news.getContent());
        Glide.with(this.image).load(news.getUrl()).into(image);
    }
}
