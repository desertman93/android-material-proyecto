package mauroxdev.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mauricio on 21-05-2015.
 */
public class post implements Parcelable {
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String titulo;
    private String url;
    private String img;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(url);
        parcel.writeString(img);

    }

    private post(Parcel in){

        titulo = in.readString();
        url = in.readString();
        img = in.readString();

    }
    public post(){

    }


    public static final Parcelable.Creator<post> CREATOR = new Parcelable.Creator<post>() {
        public post createFromParcel(Parcel in) {
            return new post(in);
        }

        public post[] newArray(int size) {
            return new post[size];
        }
    };
}
