package com.zahra.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.zahra.ecommerceapp.R;
import com.zahra.ecommerceapp.adapters.ShowAllAdapter;
import com.zahra.ecommerceapp.models.ShowAllModels;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModels> showAllModelsList;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        String type = getIntent().getStringExtra("type");

        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelsList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this,showAllModelsList);
        recyclerView.setAdapter(showAllAdapter);

        
        if(type == null || type.isEmpty()){
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModels showAllModels = doc.toObject(ShowAllModels.class);
                            showAllModelsList.add(showAllModels);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("men")){

            firestore.collection("ShowAll").whereEqualTo("type","men")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModels showAllModels = doc.toObject(ShowAllModels.class);
                            showAllModelsList.add(showAllModels);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }


        if(type != null && type.equalsIgnoreCase("woman")){

            firestore.collection("ShowAll").whereEqualTo("type","woman")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModels showAllModels = doc.toObject(ShowAllModels.class);
                            showAllModelsList.add(showAllModels);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

        }

        if(type != null && type.equalsIgnoreCase("watch")){

            firestore.collection("ShowAll").whereEqualTo("type","watch")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModels showAllModels = doc.toObject(ShowAllModels.class);
                            showAllModelsList.add(showAllModels);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

        }

        if(type != null && type.equalsIgnoreCase("kids")){

            firestore.collection("ShowAll").whereEqualTo("type","kids")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModels showAllModels = doc.toObject(ShowAllModels.class);
                            showAllModelsList.add(showAllModels);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

        }

        if(type != null && type.equalsIgnoreCase("camera")){

            firestore.collection("ShowAll").whereEqualTo("type","camera")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModels showAllModels = doc.toObject(ShowAllModels.class);
                            showAllModelsList.add(showAllModels);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

        }

        if(type != null && type.equalsIgnoreCase("shoes")){

            firestore.collection("ShowAll").whereEqualTo("type","shoes")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModels showAllModels = doc.toObject(ShowAllModels.class);
                            showAllModelsList.add(showAllModels);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        }
    }
