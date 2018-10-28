package wiatrzyk.com.exchangerateapp;

public class ListItem {

    private String mKey;
    private String mValue;
    private String mDate;
    //0 - currency rate 1-date
    private int mType;

    public ListItem(String key, String value, int type, String date) {
        mKey = key;
        mValue = value;
        mType = type;
        mDate=date;
    }

    public String getmKey() {
        return mKey;
    }

    public String getmValue() {
        return mValue;
    }

    public int getmType() {
        return mType;
    }

    public String getmDate() {
        return mDate;
    }
}
