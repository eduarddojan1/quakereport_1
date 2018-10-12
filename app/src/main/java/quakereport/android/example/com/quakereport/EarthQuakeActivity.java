package quakereport.android.example.com.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthQuakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthQuakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake);

        ArrayList<EarthQuakeEntry> earthquakes = QueryUtils.extractEarthquakes();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EntryAdapter adapter = new EntryAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // get list item that was clicked on an call web browser intent with its URL

                EarthQuakeEntry clickedEntry = (EarthQuakeEntry) adapterView.getItemAtPosition(position);

                String url = clickedEntry.getDetailURL();

                Uri webpage = Uri.parse(url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, webpage);

                if(browserIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(browserIntent);
                }

            }
        });
    }
}
