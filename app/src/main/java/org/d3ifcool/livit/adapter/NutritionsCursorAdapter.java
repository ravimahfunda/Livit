package org.d3ifcool.livit.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.livit.R;
import org.d3ifcool.livit.data.LivitContract;


public class NutritionsCursorAdapter extends CursorAdapter {
    public NutritionsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.log_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        int carbsData = cursor.getInt(cursor.getColumnIndex(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS));
        String carbsString;
        switch (carbsData){
            case 0:
                carbsString = context.getString(R.string.nutritions_carbs_rice);
                break;
            case 1:
                carbsString = context.getString(R.string.nutritions_carbs_bread);
                break;
            case 2:
                carbsString = context.getString(R.string.nutritions_carbs_potato);
                break;
            default:
                carbsString = context.getString(R.string.nutritions_carbs_other);
                break;

        }

        int proteinData = cursor.getInt(cursor.getColumnIndex(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN));
        String proteinString;
        switch (proteinData){
            case 0:
                proteinString = context.getString(R.string.nutritions_protein_lamb);
                break;
            case 1:
                proteinString = context.getString(R.string.nutritions_protein_meat);
                break;
            case 2:
                proteinString = context.getString(R.string.nutritions_protein_chicken);
                break;
            case 3:
                proteinString = context.getString(R.string.nutritions_protein_fish);
                break;
            case 4:
                proteinString = context.getString(R.string.nutritions_protein_egg);
                break;
            default:
                proteinString = context.getString(R.string.nutritions_protein_other);
                break;
        }

        String timeData = cursor.getString(cursor.getColumnIndex(LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_TIME));
        TextView timeTextView = (TextView) view.findViewById(R.id.time_textview);
        timeTextView.setText(timeData);

        TextView descriptionTextView= (TextView) view.findViewById(R.id.description_textview);
        descriptionTextView.setText("A meal of "+carbsString+" with "+proteinString);
    }
}
