package e.clement.a205enroot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Clément on 02/04/2018.
 */

// Modèle de traitement JSON pour les articles

public class NewsArticles implements Parcelable{
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("legend")
    @Expose
    private String legend;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(content);
        dest.writeValue(date);
        dest.writeValue(url);
        dest.writeValue(description);
        dest.writeValue(legend);
    }

    private NewsArticles(Parcel in){
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.content = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((Date) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.legend = ((String) in.readValue((String.class.getClassLoader())));
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