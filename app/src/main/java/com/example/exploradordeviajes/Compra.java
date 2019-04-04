package com.example.exploradordeviajes;

import android.animation.ArgbEvaluator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.exploradordeviajes.Adapters.TickerAdapter;
import com.example.exploradordeviajes.Modelos.Ticket;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Compra extends AppCompatActivity {

    ViewPager viewPager;
    TickerAdapter adapter;
    ArrayList<Ticket> tickets;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        tickets = new ArrayList<>();

        FirebaseFirestore.getInstance().collection("Compra").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                    tickets.add(snapshot.toObject(Ticket.class));
                }
                for (int i = 0; i < tickets.size() ; i++) {
                    System.out.println("===================================");

                    System.out.println(tickets.get(i).getEmail());

                    System.out.println("===================================");
                }
                adapter = new TickerAdapter(Compra.this,tickets);
                viewPager = findViewById(R.id.viewPager);
                viewPager.setAdapter(adapter);
                viewPager.setPadding(130,0,130,0);

                Integer[] colors_temp = {
                        getResources().getColor(R.color.color1),
                        getResources().getColor(R.color.color2),
                        getResources().getColor(R.color.color3),
                        getResources().getColor(R.color.color4),
                        getResources().getColor(R.color.color5)
                };

                colors = colors_temp;

                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int i1) {
                        if (position < (adapter.getCount() - 1) && position < (colors.length -1)){
                            viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,colors[position], colors[position + 1]));
                        }else{
                            viewPager.setBackgroundColor(colors[colors.length - 1] );
                        }
                    }

                    @Override
                    public void onPageSelected(int i) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
            }
        });






    }
}
