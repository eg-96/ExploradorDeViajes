package com.example.exploradordeviajes;

import android.animation.ArgbEvaluator;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.exploradordeviajes.Adapters.TickerAdapter;
import com.example.exploradordeviajes.Modelos.Ticket;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Compra extends AppCompatActivity {

    ViewPager viewPager;
    TickerAdapter adapter;
    ArrayList<Ticket> tickets;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tickets = new ArrayList<>();
        final ImageView imageContainer = findViewById(R.id.imageContainer);
        FirebaseFirestore.getInstance().collection("Compra").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                    tickets.add(snapshot.toObject(Ticket.class));
                }

                adapter = new TickerAdapter(Compra.this,tickets);
                viewPager = findViewById(R.id.viewPager);
                viewPager.setAdapter(adapter);



//                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int i1) {
//
//
//                        System.out.println("==================IMAGE ===================");
//                        System.out.println(tickets.get(position).getImage());
//                        System.out.println("==================IMAGE ===================");
//                        Picasso.get().load(tickets.get(position).getImage()).fit().centerCrop().into(imageContainer);
//
////                        System.out.println("===========================");
////                        System.out.println("=============POSITION==============");
////                        System.out.println(position);
////                        System.out.println("===========POSITION OFFSET================");
////                        System.out.println(positionOffset);
////                        System.out.println("=============il=============");
////                        System.out.println(i1);
////                        System.out.println("===========================");
////                        if (position < (adapter.getCount() - 1) && position < (tickets.size() -1)){
////                            Picasso.get().load(tickets.get(position).getImage()).into(imageContainer);
////                        }else{
////                            Picasso.get().load(tickets.get(position - 1).getImage()).into(imageContainer);
////                        }
//                    }
//
//                    @Override
//                    public void onPageSelected(int i) {
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int i) {
//
//                    }
//                });
            }
        });






    }
}
