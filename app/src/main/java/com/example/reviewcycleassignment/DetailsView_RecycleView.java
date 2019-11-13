package com.example.reviewcycleassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.reviewcycleassignment.Model.User;

import java.util.List;

public class DetailsView_RecycleView extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsview__recycle_view);


       recyclerView=findViewById(R.id.rvusers);
        Intent intent = getIntent();

        final List<User> users = (List<User>)intent.getSerializableExtra("userlist");


        RecyclevAdapter adapter = new RecyclevAdapter(users,this);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
