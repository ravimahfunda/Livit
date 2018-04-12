package org.d3ifcool.livit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //Creating log data
        ArrayList<Log> logs = new ArrayList<>();
        logs.add(new Log("09.00", "Breakfast with bread"));
        logs.add(new Log("16.00", "Run"));
        logs.add(new Log("21.00", "Sleep"));
        logs.add(new Log("09.00", "Breakfast with bread"));
        logs.add(new Log("16.00", "Run"));
        logs.add(new Log("21.00", "Sleep"));
        logs.add(new Log("09.00", "Breakfast with bread"));
        logs.add(new Log("16.00", "Run"));
        logs.add(new Log("21.00", "Sleep"));
        logs.add(new Log("09.00", "Breakfast with bread"));
        logs.add(new Log("16.00", "Run"));
        logs.add(new Log("21.00", "Sleep"));
        logs.add(new Log("09.00", "Breakfast with bread"));
        logs.add(new Log("16.00", "Run"));
        logs.add(new Log("21.00", "Sleep"));

        //Implement log data to log list in layout
        ListView listView = (ListView) findViewById(R.id.log_listview);
        listView.setAdapter(new LogAdapter(this, logs));
    }
}
