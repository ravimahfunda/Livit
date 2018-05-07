package org.d3ifcool.livit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.d3ifcool.livit.entity.Recommendation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 06/05/2018.
 */

public class RecommendationAdapter extends ArrayAdapter {

    public RecommendationAdapter(@NonNull Context context, List<Recommendation> data) {
        super(context, 0, data);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

            Recommendation recommendation = (Recommendation) getItem(position);

            TextView namaTextView = convertView.findViewById(R.id.nama);
            namaTextView.setText(String.valueOf(recommendation.getNama()));

            TextView tipeTextView = convertView.findViewById(R.id.tipe);
            tipeTextView.setText(String.valueOf(recommendation.getTipe()));

//            if (recommendation.getLocation().contains(" of ")) {
//                String parts[] = earthquake.getLocation().split(" of ");
//                nearTextView.setText(parts[0] + " of ");
//                locationTextView.setText(parts[1]);
//
//            } else {
//                nearTextView.setText("Near of ");
//                locationTextView.setText(earthquake.getLocation());
//            }

//            locationTextView.setText(earthquake.getLocation());

//            Date date = new Date(earthquake.getTimeInMillis());
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
//            TextView dateTextView = convertView.findViewById(R.id.date_textview);
//            dateTextView.setText(dateFormat.format(date));
//
//            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
//            TextView timeTextView = convertView.findViewById(R.id.time_textview);
//            timeTextView.setText(timeFormat.format(date));

        }return convertView;
    }
}
