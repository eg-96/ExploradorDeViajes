package com.example.exploradordeviajes.Adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exploradordeviajes.R;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class OneWayFlightAdapter extends RecyclerView.Adapter<OneWayFlightAdapter.OneWayFlightViewHolder> {
    private QuerySnapshot mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class OneWayFlightViewHolder extends RecyclerView.ViewHolder {
        public TextView departureTimeFly;
        public TextView departureCodeFly;
        public TextView destinationTimeFly;
        public TextView destinationCodeFly;
        public TextView airline;

        public OneWayFlightViewHolder(ConstraintLayout v) {
            super(v);
            departureTimeFly = v.findViewById(R.id.departureTimeFly);
            departureCodeFly = v.findViewById(R.id.departureCodeFly);
            destinationTimeFly = v.findViewById(R.id.destinationTimeFly);
            destinationCodeFly = v.findViewById(R.id.destinationCodeFly);
            airline = v.findViewById(R.id.airline);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OneWayFlightAdapter(QuerySnapshot myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OneWayFlightAdapter.OneWayFlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_flight, parent, false);

        OneWayFlightViewHolder vh = new OneWayFlightViewHolder(v);
        return vh;
    }

         // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(OneWayFlightViewHolder holder, int position) {
            SimpleDateFormat timeFlyFormat = new SimpleDateFormat("HH:mm");

            String departureTimeFly = timeFlyFormat.format(((Timestamp)mDataset.getDocuments().get(position).get("DepartureDateTime")).getSeconds());
            holder.departureTimeFly.setText(departureTimeFly);

            holder.departureCodeFly.setText(mDataset.getDocuments().get(position).get("DepartureCity").toString());

            String destinationTimeFly = timeFlyFormat.format(((Timestamp)mDataset.getDocuments().get(position).get("DestinationDateTime")).getSeconds());
            holder.destinationTimeFly.setText(destinationTimeFly);

            holder.destinationCodeFly.setText(mDataset.getDocuments().get(position).get("DestinationCity").toString());

            holder.airline.setText(mDataset.getDocuments().get(position).get("Airline").toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}