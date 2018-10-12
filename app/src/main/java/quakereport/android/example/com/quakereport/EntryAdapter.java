package quakereport.android.example.com.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntryAdapter extends ArrayAdapter<EarthQuakeEntry> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EntryAdapter(@NonNull Activity context, ArrayList<EarthQuakeEntry> entries) {
        super(context, 0, entries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        EarthQuakeEntry currentEntry = getItem(position);

        TextView magnituteTextView = listItemView.findViewById(R.id.magnitude);
        double magnitude = currentEntry.getMagnitude();
        magnituteTextView.setText(formatMagnitude(magnitude));

        GradientDrawable magnitudeCircle = (GradientDrawable)magnituteTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEntry.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        TextView locationTextView = listItemView.findViewById(R.id.primary_location);
        TextView offsetTextView = listItemView.findViewById(R.id.location_offset);

        String originalLocation = currentEntry.getLocationName();

        String primaryLocation;
        String locationOffset;

        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        offsetTextView.setText(locationOffset);
        locationTextView.setText(primaryLocation);

        TextView dateTextView = listItemView.findViewById(R.id.date);
        TextView timeTextView = listItemView.findViewById(R.id.time);

        long time = currentEntry.getmTimeInMilliseconds();

        Date dateObject = new Date(time);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String formattedDate = dateFormatter.format(dateObject);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormatter.format(dateObject);

        dateTextView.setText(formattedDate);
        timeTextView.setText(formattedTime);


        return listItemView;

    }

    public String formatMagnitude(double magnitude){

        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);

    }

    private int getMagnitudeColor(double magnitude){

        int magnitudeColorResourceID;
        int flooredMagnitude = (int) Math.floor(magnitude);

        switch (flooredMagnitude){
            case 0:
            case 1:
                magnitudeColorResourceID = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceID = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceID = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceID = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceID = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceID = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceID = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceID = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceID = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceID = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceID);
    }
}
