package com.example.studentgrades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentgrades.model.Grade;
import com.example.studentgrades.model.GradeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Grade> mGradeList = new ArrayList<>();
    private GradeViewModel gradeViewModel;
    private MyGradesAdapter gradeAdapter;
    private RecyclerView recyclerView;
    public String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 //       Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //create edit text


                LinearLayout layout = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setLayoutParams(parms);

                layout.setGravity(Gravity.CLIP_VERTICAL);
                layout.setPadding(2, 2, 2, 2);

                final EditText editText = new EditText(getApplicationContext());
                editText.setText("Enter Course name here ");
                editText.setPadding(40, 40, 40, 40);
                editText.setGravity(Gravity.CENTER);
                editText.setTextSize(10);

                final EditText editText2 = new EditText(getApplicationContext());
                editText2.setText("Enter Leeter grade here ");
                editText2.setPadding(40, 40, 40, 40);
                editText2.setGravity(Gravity.CENTER);
                editText2.setTextSize(12);


                LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                tv1Params.bottomMargin = 5;
                layout.addView(editText,tv1Params);
                layout.addView(editText2, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                dialog.setView(layout);

                dialog.setTitle("Add the Course Name and Grade");
                //sets OK action
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //get item entered
                        courseName = editText.getText().toString();
                        String courseLetter = editText2.getText().toString();
                        if (!courseName.isEmpty() && !courseLetter.isEmpty()) {
                            //call insert method
                            gradeViewModel.insertItem(courseName,courseLetter);
                        }
                        Snackbar.make(view, "Item added", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
                //sets cancel action
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // cancel
                    }
                });
                //present alert dialog
                dialog.show();

            }
        });

        //create a viewmodel
        gradeViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        //get the recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the observer
        final Observer<List<Grade>> itemObserver = new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                mGradeList.clear();
                mGradeList.addAll(grades);
                if (gradeAdapter == null) {
                    //define the adapter
                    gradeAdapter = new MyGradesAdapter(gradeViewModel, MainActivity.this);
                    //assign the adapter to the recycle view
                    recyclerView.setAdapter(gradeAdapter);
                } else {
                    gradeAdapter.setItems(mGradeList);
                    gradeAdapter.notifyDataSetChanged();
                }
            }
        };

        //set the observer
        gradeViewModel.getItemList().observe(this, itemObserver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            mGradeList.clear();
            gradeViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
