package e.clement.a205enroot;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Clément on 02/04/2018.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    //private TextView textView;


    @BindView(R.id.news_page_item_title) TextView title;
    @BindView(R.id.news_page_item_content) TextView content;
    public NewsViewHolder(View itemView) {
        super(itemView);
        //itemView = (TextView) itemView.findViewById(R.id.news_page_item_title);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNewsTitle(NewsArticles news){
        this.title.setText(news.getTitre());
        this.content.setText(news.getContenu());
    }
}
