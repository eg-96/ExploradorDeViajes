package com.example.exploradordeviajes;

import android.graphics.Interpolator;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class FlySearching extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly_searching);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Read from the database
        db.collection("Flights")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot results = task.getResult();

                            for (QueryDocumentSnapshot document : results) {
//                                Log.d("Data", document.getId() + " => " + document.getData());

                                String flightCount = (Integer.toString(results.size())) + " of " + (Integer.toString(results.size()))  + " results shown sorted by Price";
                                ((TextView)findViewById(R.id.flightCount)).setText(flightCount);

                                ConstraintLayout flightWrapper = findViewById(R.id.wrapFlight);

                                SimpleDateFormat timeFlyFormat = new SimpleDateFormat("HH:mm");

                                String departureTimeFly1 = timeFlyFormat.format(((Timestamp)document.get("DepartureDateTime")).getSeconds());
                                ((TextView) flightWrapper.findViewById(R.id.departureTimeFly1)).setText(departureTimeFly1);

                                String destinationTimeFly1 = timeFlyFormat.format(((Timestamp)document.get("DestinationDateTime")).getSeconds());
                                ((TextView) flightWrapper.findViewById(R.id.destinationTimeFly1)).setText(destinationTimeFly1);

                                ((TextView) flightWrapper.findViewById(R.id.departureCodeFly1)).setText(document.get("DepartureCity").toString());
                                ((TextView) flightWrapper.findViewById(R.id.destinationCodeFly1)).setText(document.get("DestinationCity").toString());

                                ((TextView) flightWrapper.findViewById(R.id.airlineFly1)).setText(document.get("Airline").toString());
                            }
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}