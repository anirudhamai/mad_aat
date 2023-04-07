package com.example.hosp_sign_up;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import java.util.ArrayList;

public class gridadapter extends ArrayAdapter<bookings> {

    public gridadapter(@NonNull Context context, ArrayList<bookings> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_grid_view, parent, false);
        }

        bookings emrs = getItem(position);
        TextView emr = listitemView.findViewById(R.id.emr);
        TextView eta = listitemView.findViewById(R.id.eta);

        emr.setText(emrs.getemr());
        eta.setText(emrs.geteta());
        return listitemView;
    }
}

