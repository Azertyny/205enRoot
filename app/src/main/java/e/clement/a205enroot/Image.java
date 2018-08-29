package e.clement.a205enroot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("legend")
    @Expose
    private String legend;

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


    public final static Parcelable.Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image[] newArray(int size) {
            return (new Image[size]);
        }

        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

    };

    protected Image(Parcel in) {
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.legend = ((String) in.readValue((String.class.getClassLoader())));
    }




    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(description);
        dest.writeValue(legend);
    }



    public int describeContents() {
        return 0;
    }

    public Image() {
    }

}

