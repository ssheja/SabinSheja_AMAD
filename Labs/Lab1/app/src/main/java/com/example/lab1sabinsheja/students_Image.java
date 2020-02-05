package com.example.lab1sabinsheja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class students_Image extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students__image);

        final int studentNumber = (Integer) getIntent().getExtras().get("studentNumber");
        final Students student = SampleData.studentList.get(studentNumber);

        TextView names = findViewById(R.id.namesTextView);
        names.setText(student.getFirstName()+"  "+student.getLastName());


        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(student.getRatings());

        Button submit = findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setRatings(ratingBar.getNumStars());
                Intent intent = new Intent(students_Image.this, StudentDetails.class);
                intent.putExtra("studentNumber", studentNumber);
                startActivity(intent);
            }
        });



    }
}
