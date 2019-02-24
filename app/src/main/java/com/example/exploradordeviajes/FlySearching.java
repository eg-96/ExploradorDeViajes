package com.example.exploradordeviajes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.exploradordeviajes.Adapters.OneWayFlightAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FlySearching extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView simpleFlightView;
    private RecyclerView.Adapter oneWayFlightAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly_searching);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        simpleFlightView = findViewById(R.id.rvSimpleFlight);
        layoutManager = new LinearLayoutManager(this);
        simpleFlightView.setLayoutManager(layoutManager);

        // Read from the database
        db.collection("Flights")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot results = task.getResult();

                            oneWayFlightAdapter = new OneWayFlightAdapter(results);
                            simpleFlightView.setAdapter(oneWayFlightAdapter);
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}