package e.clement.a205enroot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Coordinates implements Parcelable{

    @SerializedName("id_coordinate")
    @Expose
    private int idCoordinate;
    @SerializedName("latitude")
    @Expose
    private float latitude;
    @SerializedName("longitude")
    @Expose
    private float longitude;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("place")
    @Expose
    private String place;

    public Coordinates() {
    }

    public int getIdCoordinate() {
        return idCoordinate;
    }

    public void setIdCoordinate(int idCoordinate) {
        this.idCoordinate = idCoordinate;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idCoordinate);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(date);
        dest.writeValue(info);
        dest.writeValue(place);
    }

    public final static Parcelable.Creator<Coordinates> CREATOR = new Creator<Coordinates>() {

        @Override
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        public Coordinates[] newArray(int size) {
            return (new Coordinates[size]);
        }

    }
            ;

    protected Coordinates(Parcel in) {
        this.idCoordinate = ((int) in.readValue((String.class.getClassLoader())));
        this.latitude = ((float) in.readValue((String.class.getClassLoader())));
        this.longitude = ((float) in.readValue((String.class.getClassLoader())));
        this.date = ((Date) in.readValue((String.class.getClassLoader())));
        this.info = ((String) in.readValue((String.class.getClassLoader())));
        this.place = ((String) in.readValue((String.class.getClassLoader())));
    }






}
