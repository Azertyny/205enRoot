package e.clement.a205enroot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Clément on 02/04/2018.
 */

// Modèle de traitement JSON pour les articles

public class NewsArticles {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("titre")
    @Expose
    private String titre;
    @SerializedName("contenu")
    @Expose
    private String contenu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

}