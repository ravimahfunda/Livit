package org.d3ifcool.livit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.d3ifcool.livit.R;
import org.d3ifcool.livit.entity.Log;

import java.util.List;

/**
 * Managa how data shown and adapting Log Custom Class to Log List Item
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
