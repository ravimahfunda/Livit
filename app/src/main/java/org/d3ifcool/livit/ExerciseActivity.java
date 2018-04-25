package org.d3ifcool.livit;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.livit.entity.Weather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Weather> {

    private TextView timerTextView, milisTextView, dayTextView,
            dateTextView, humidityTextView, tempTextView;
    private ImageView resetButton, playButton, pauseButton;

    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    private Handler handler;

    private int Seconds, Minutes, Hours, MilliSeconds ;

    private Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        timerTextView = (TextView) findViewById(R.id.exercises_timer_textview);
        milisTextView= (TextView) findViewById(R.id.exercises_milis_textview);
        dayTextView= (TextView) findViewById(R.id.exercises_day_textview);
        dateTextView= (TextView) findViewById(R.id.exercises_date_textview);
        resetButton = (ImageView) findViewById(R.id.exercises_reset_button);
        playButton = (ImageView) findViewById(R.id.exercises_play_button);
        pauseButton= (ImageView) findViewById(R.id.exercises_pause_button);

        humidityTextView = (TextView) findViewById(R.id.exercise_humidity_textview);
        tempTextView = (TextView) findViewById(R.id.exercise_temp_textview);

        currentTime = Calendar.getInstance().getTime();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+currentTime);
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM y");
        dayTextView.setText(dayFormatter.format(currentTime));
        dateTextView.setText(dateFormatter.format(currentTime));

        handler = new Handler();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                resetButton.setEnabled(false);

            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                resetButton.setEnabled(true);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                Hours = 0 ;
                MilliSeconds = 0 ;

                timerTextView.setText("00:00:00");
                milisTextView.setText("000");
            }
        });


        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (isConnected){
            getSupportLoaderManager().initLoader(1, null,this);
        }else{
            Toast.makeText(this, "Network unavailable", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Weather data unavailable", Toast.LENGTH_SHORT).show();
        }

    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Hours = Seconds / 3600;

            Minutes = Seconds % 3600 / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timerTextView.setText(String.format("%02d", Hours)+":" + String.format("%02d", Minutes)+ ":"
                    + String.format("%02d", Seconds));

            milisTextView.setText(String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    @Override
    public Loader<Weather> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
//        Uri baseUri = Uri.parse("https://earthquake.usgs.gov/fdsnws/event/1/query");
        Uri.Builder uriBuilder = baseUri.buildUpon();
//        uriBuilder.appendQueryParameter("format","geojson");
//        uriBuilder.appendQueryParameter("limit","10");

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        uriBuilder.appendQueryParameter("minmag",preferences.getString(
//                getString(R.string.settings_minimum_magnitude_key),
//                getString(R.string.settings_minimum_magnitude_default)
//        ));
//
//        uriBuilder.appendQueryParameter("orderby",preferences.getString(
//                getString(R.string.settings_order_by_key),
//                getString(R.string.settings_order_by_default)
//        ));

        return new WeatherLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<Weather> loader, Weather data) {
//        TODO: Setting up temp to local unit
        humidityTextView.setText(data.getHumidity()+"");
        tempTextView.setText((data.getTemp())+"'C");
    }

    @Override
    public void onLoaderReset(Loader<Weather> loader) {

    }
}
