package e.clement.a205enroot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Clément on 07/04/2018.
 */


public class SponsorsArticles implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sponsorDescription")
    @Expose
    private String sponsorDescription;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSponsorDescription() {
        return sponsorDescription;
    }

    public void setSponsorDescription(String sponsorDescription) {
        this.sponsorDescription = sponsorDescription;
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
        dest.writeValue(name);
        dest.writeValue(sponsorDescription);
        dest.writeValue(date);
        dest.writeValue(url);
        dest.writeValue(description);
        dest.writeValue(legend);
    }

    protected SponsorsArticles(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.sponsorDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((Date) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.legend = ((String) in.readValue((String.class.getClassLoader())));
    }

    public static final Parcelable.Creator<SponsorsArticles> CREATOR = new Parcelable.Creator<SponsorsArticles>() {

        @Override
        public SponsorsArticles createFromParcel(Parcel source) {
            return new SponsorsArticles(source);
        }

        @Override
        public SponsorsArticles[] newArray(int size) {
            return new SponsorsArticles[size];
        }
    };

    public SponsorsArticles() {
    }
}
