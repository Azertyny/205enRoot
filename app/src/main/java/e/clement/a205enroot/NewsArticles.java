package e.clement.a205enroot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Clément on 02/04/2018.
 */

// Modèle de traitement JSON pour les articles

public class NewsArticles implements Parcelable{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("titre")
    @Expose
    private String titre;
    @SerializedName("contenu")
    @Expose
    private String contenu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titre);
        dest.writeString(contenu);
    }

    private NewsArticles(Parcel in){
        this.id = in.readInt();
        this.titre = in.readString();
        this.contenu = in.readString();
    }

    public static final Parcelable.Creator<NewsArticles> CREATOR = new Parcelable.Creator<NewsArticles>() {

        @Override
        public NewsArticles createFromParcel(Parcel source) {
            return new NewsArticles(source);
        }

        @Override
        public NewsArticles[] newArray(int size) {
            return new NewsArticles[size];
        }
    };

}