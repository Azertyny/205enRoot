package e.clement.a205enroot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Clément on 02/04/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<NewsArticles> news;

    public NewsAdapter(List<NewsArticles> news){
        this.news = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_page_item, parent, false);

        return new NewsViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(NewsViewHolder viewHolder, int position) {
        viewHolder.updateWithNewsTitle(this.news.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.news.size();
    }

}
