package com.example.exploradordeviajes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.example.exploradordeviajes.Adapters.OneWayFlightAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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

        //Default search
        executeSearch("", "");

        SearchView searchByDeparture = findViewById(R.id.searchByDestination);
        searchByDeparture.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String value) {
                executeSearch("DepartureCityName", value);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String value) {
                executeSearch("DepartureCityName", value);
                return true;
            }
        });

        SearchView searchByDestination = findViewById(R.id.searchByDestination);
        searchByDestination.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String value) {
                executeSearch("DestinationCityName", value);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String value) {
                executeSearch("DestinationCityName", value);
                return true;
            }
        });
    }

    // Read from the database
    private void executeSearch(String field, String value){
        Query query;

        if (field.isEmpty())
            query = db.collection("Flights");
        else
            query = db.collection("Flights").whereEqualTo(field, value);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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