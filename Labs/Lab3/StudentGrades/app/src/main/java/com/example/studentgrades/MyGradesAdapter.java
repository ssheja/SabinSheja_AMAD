package com.example.studentgrades;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentgrades.model.Grade;
import com.example.studentgrades.model.GradeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MyGradesAdapter extends RecyclerView.Adapter<MyGradesAdapter.ViewHolder> {
    private List<Grade> mGradeList;
    private GradeViewModel mGradeViewModel;
    private Context mContext;

    public MyGradesAdapter(GradeViewModel mGradeViewModel, Context mContext) {
        this.mGradeViewModel = mGradeViewModel;
        mGradeList = mGradeViewModel.getItemList().getValue();
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_grade, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyGradesAdapter.ViewHolder holder, int position) {
        final Grade grade = mGradeList.get(position);
        holder.mTextView.setText("Course Name:"+grade.getCourseName());
        holder.mTextView2.setText("Course grade: "+grade.getCourseLetterGrade());

        //context menu
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
            @Override
            public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
                //set the menu title
                menu.setHeaderTitle("Delete " + grade.getCourseName());
                //add the choices to the menu
                menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = holder.getAdapterPosition();
                        Grade removeGrade = mGradeList.get(position);
                        mGradeViewModel.deleteItem(removeGrade);
                        Snackbar.make(v, "Item removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return false;
                    }
                });
                menu.add(2, 2, 2, "No");
            }
        });
    }

    @Override
    public int getItemCount() {
        if ( mGradeList != null) {
            return mGradeList.size();
        }
        else return 0;
    }

    public void setItems(List<Grade> items){
        mGradeList = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mTextView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }
}