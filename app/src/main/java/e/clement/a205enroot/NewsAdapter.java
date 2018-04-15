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

    // Instanciation du ViewHolder "NewsViewHolder.java"
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_page_item, parent, false);
        return new NewsViewHolder(view);
    }

    // Appel de la méthode de m.à.j de l'UI par le ViewHolder
    @Override
    public void onBindViewHolder(NewsViewHolder viewHolder, int position) {
        viewHolder.updateWithNews(this.news.get(position));
    }

    // Ajoute les éléments correspondant au nombre d'éléments dans la liste de news
    @Override
    public int getItemCount() {
        return this.news.size();
    }

    // Méthode permettant de gérer le numéro de la news sur laquelle l'utilisateur a cliqué
    public NewsArticles getNews(int position){
        return this.news.get(position);
    }
}
