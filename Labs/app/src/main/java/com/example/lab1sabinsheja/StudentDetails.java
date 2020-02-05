package com.example.lab1sabinsheja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        //get bulb id from the intent
        final int studentNumber = (Integer) getIntent().getExtras().get("studentNumber");
        final Students student = SampleData.studentList.get(studentNumber);

        TextView firstName = findViewById(R.id.firstNamesTextView);
        TextView lastName = findViewById(R.id.lastNameTextView);
        TextView nickName = findViewById(R.id.nickName_TextView);
        TextView phoneNumber = findViewById(R.id.phone_numberTextView);
        Button send = findViewById(R.id.button);
        Button showImage = findViewById(R.id.viewPictureBtn);

        firstName.setText("First Name: " + student.getFirstName());
        lastName.setText("Last Name:" + student.getLastName());
        nickName.setText("NickName:" + student.getNickName());
        phoneNumber.setText("Phone Number:" + student.getPhoneNumber());
        showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetails.this, students_Image.class);
                intent.putExtra("studentNumber", studentNumber);
                startActivity(intent);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(student.getPhoneNumber());
            }
        });

    }


    public void sendSMS(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("address", phoneNum);
        intent.putExtra("sms_body", "Please enter the message");

        try {
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Error sending SMS" + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.list:
                intent = new Intent(StudentDetails.this, MainActivity.class);
                startActivity(intent);
                return true;

        }
        return false;

    }
}
