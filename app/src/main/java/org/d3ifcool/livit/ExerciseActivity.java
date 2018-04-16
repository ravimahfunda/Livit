package org.d3ifcool.livit;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExerciseActivity extends AppCompatActivity {

    private TextView timerTextView, milisTextView, dayTextView, dateTextView;
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

}
