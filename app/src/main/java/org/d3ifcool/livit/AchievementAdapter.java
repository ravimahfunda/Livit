package org.d3ifcool.livit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Multimedia on 13/03/2018.
 */

public class AchievementAdapter extends ArrayAdapter<Achievement> {
    public AchievementAdapter(@NonNull Context context, int resource, @NonNull List<Achievement> objects) {
        super(context, resource, objects);
    }
}
