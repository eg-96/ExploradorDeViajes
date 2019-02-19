package com.example.exploradordeviajes.Adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exploradordeviajes.R;
import com.google.firebase.firestore.QuerySnapshot;

public class OneWayFlightAdapter extends RecyclerView.Adapter<OneWayFlightAdapter.OneWayFlightViewHolder> {
    private QuerySnapshot mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class OneWayFlightViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView destinationCodeFly1;
        public OneWayFlightViewHolder(ConstraintLayout v) {
            super(v);
            destinationCodeFly1 = v.findViewById(R.id.destinationCodeFly1);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OneWayFlightAdapter(QuerySnapshot myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OneWayFlightAdapter.OneWayFlightViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_flight, parent, false);

        OneWayFlightViewHolder vh = new OneWayFlightViewHolder(v);
        return vh;
    }

         // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(OneWayFlightViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.destinationCodeFly1.setText(mDataset.getDocuments().get(position).get("DestinationCity").toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}