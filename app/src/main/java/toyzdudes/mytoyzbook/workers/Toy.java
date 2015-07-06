package toyzdudes.mytoyzbook.workers;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Toy implements Parcelable {

    private int id;
    private String titre, description, imageURI, tags, caracteristiques;

    public static final String JSON_FIELD_ID = "id_product";
    public static final String JSON_FIELD_TITRE = "name";
    public static final String JSON_FIELD_DESCRIPTION = "description_short";
    public static final String JSON_FIELD_IMAGE_URI = "image";
    public static final String JSON_FIELD_TAGS = "tags";
    public static final String JSON_FIELD_CARACTERISTIQUES = "description";
    public static final String JSON_FIELD_SHOPS = "shops";

    public Toy(JSONObject object) throws JSONException {

        this.id = object.getInt(Toy.JSON_FIELD_ID);
        this.titre = object.getString(Toy.JSON_FIELD_TITRE);
        this.imageURI = object.optString(Toy.JSON_FIELD_IMAGE_URI);
        this.description = object.getString(Toy.JSON_FIELD_DESCRIPTION);
        this.caracteristiques = object.getString(Toy.JSON_FIELD_CARACTERISTIQUES);

    }

    public Toy(Parcel in) {
        this.id = in.readInt();
        this.titre = in.readString();
        this.imageURI = in.readString();
        this.description = in.readString();
        this.caracteristiques = in.readString();
    }

    // region GET/SET

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    //endregion

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.titre);
        dest.writeString(this.imageURI);
        dest.writeString(this.description);
        dest.writeString(this.caracteristiques);
        //dest.writeString(this.tags);
        //dest.writeString(this.caracteristiques);
    }

    public static final Parcelable.Creator<Toy> CREATOR = new Parcelable.Creator<Toy>()
    {
        @Override
        public Toy createFromParcel(Parcel source)
        {
            return new Toy(source);
        }

        @Override
        public Toy[] newArray(int size)
        {
            return new Toy[size];
        }
    };

}
