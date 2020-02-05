package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lab2.model.Bilionaire;
import com.example.lab2.loader.JSONData;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    List<Bilionaire> bilionaires =JSONData.billionaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (bilionaires.isEmpty()) {
            JSONData.getJSON(this);
        }

        //get the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //define an adapter
        MyListAdapter adapter = new MyListAdapter(bilionaires, this);

        //assign the adapter to the recycler view
        recyclerView.setAdapter(adapter);

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}