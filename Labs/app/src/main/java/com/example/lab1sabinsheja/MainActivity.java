package com.example.lab1sabinsheja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentsAdapter.ItemClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Students> studentsList = SampleData.studentList;
        System.out.println(studentsList);

        //get the recycler view
        RecyclerView recyclerView = findViewById(R.id.studentsRecycleView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //define an adapter
        StudentsAdapter adapter = new StudentsAdapter(studentsList, this, this);

        //assign the adapter to the recycle view
        recyclerView.setAdapter(adapter);

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, StudentDetails.class);
        intent.putExtra("studentNumber", position);
        startActivity(intent);
    }
}
