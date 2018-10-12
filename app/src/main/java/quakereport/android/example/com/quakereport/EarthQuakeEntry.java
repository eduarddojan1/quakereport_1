package quakereport.android.example.com.quakereport;

public class EarthQuakeEntry {

    private double mMagnitude;
    private String mLocationName;
    private long mTimeInMilliseconds;
    private String mDetailURL;

    public EarthQuakeEntry(double magnitude, String location, long timeInMilliseconds, String url){
        mMagnitude = magnitude;
        mLocationName = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mDetailURL = url;
    }

    public String getLocationName() {
        return mLocationName;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getDetailURL() {
        return mDetailURL;
    }
}
