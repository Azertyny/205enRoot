package e.clement.a205enroot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsViewHolder> {

    private List<SponsorsArticles> sponsors;

    public SponsorsAdapter(List<SponsorsArticles> sponsors){
        this.sponsors = sponsors;
    }

    @Override
    public SponsorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sponsors_page_item, parent, false);

        return new SponsorsViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(SponsorsViewHolder viewHolder, int position) {
        viewHolder.updateWithSponsorsTitle(this.sponsors.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.sponsors.size();
    }

    // Méthode publique permettant de gérer le numéro de la news sur laquelle l'utilisateur a cliqué
    public SponsorsArticles getSponsors(int position){
        return this.sponsors.get(position);
    }
}
