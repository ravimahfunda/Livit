package org.d3ifcool.livit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by haaniifaa on 13/03/2018.
 */

public class LogAdapter extends ArrayAdapter<Log> {
    public LogAdapter(@NonNull Context context, @NonNull List<Log> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.log_list_item, parent, false);
        }

        Log currentLog = getItem(position);

        TextView timesTextView = (TextView) convertView.findViewById(R.id.times_textview);
        timesTextView.setText(currentLog.getTime());

        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.description_textview);
        descriptionTextView.setText(currentLog.getDescription());

        return convertView;

    }
}
