package e.clement.a205enroot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<ImagesViewHolder> {

    private List<Image> images;

    public GalleryAdapter(List<Image> images){
        this.images = images;
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gallery_page_item, parent, false);

        return new ImagesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(ImagesViewHolder viewHolder, int position) {
        viewHolder.updateWithImages(this.images.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.images.size();
    }

    // Méthode publique permettant de gérer le numéro de la news sur laquelle l'utilisateur a cliqué
    public Image getImage(int position){
        return this.images.get(position);
    }
}
