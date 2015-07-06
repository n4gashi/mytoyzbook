package toyzdudes.mytoyzbook.workers;

import android.os.Parcel;
import android.os.Parcelable;

public class FilterPage implements Parcelable {

    private String title, constValue;
    private int drawable, btnIndex;

    // region GENERATED STUFF

    public FilterPage(String title, String constName, int drawable, String constValue, int btnIndex)
    {
        this.title = title;
        this.drawable = drawable;
        this.constValue = constValue;
        this.btnIndex = btnIndex;
    }

    public FilterPage(Parcel in) {
        this.title = in.readString();
        this.drawable = in.readInt();
        this.constValue = in.readString();
        this.btnIndex = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getConstValue() {
        return constValue;
    }

    public void setConstValue(String constValue) {
        this.constValue = constValue;
    }

    public int getBtnIndex() {
        return btnIndex;
    }

    public void setBtnIndex(int btnIndex) {
        this.btnIndex = btnIndex;
    }

    // endregion

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.drawable);
        dest.writeString(this.constValue);
        dest.writeInt(this.btnIndex);
    }

    public static final Parcelable.Creator<FilterPage> CREATOR = new Parcelable.Creator<FilterPage>()
    {
        @Override
        public FilterPage createFromParcel(Parcel source)
        {
            return new FilterPage(source);
        }

        @Override
        public FilterPage[] newArray(int size)
        {
            return new FilterPage[size];
        }
    };

}
