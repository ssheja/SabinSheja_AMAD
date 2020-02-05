package com.example.lab1sabinsheja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    private List<Students> mStudents;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClick(int position);
    }

    public StudentsAdapter(List<Students> mStudents, Context mContext, ItemClickListener mItemClickListener) {
        this.mStudents = mStudents;
        this.mContext = mContext;
        this.mItemClickListener = mItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView studentFirstNTextView;
        TextView moreInfo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentFirstNTextView = itemView.findViewById(R.id.firstNameTextView);
            moreInfo= itemView.findViewById(R.id.moreInfoTextView);
            moreInfo.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public StudentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.student_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.ViewHolder holder, int position) {
        Students student = mStudents.get(position);
        holder.studentFirstNTextView.setText(student.getFirstName()+" "+student.getLastName());
    }


    @Override
    public int getItemCount() {
        return mStudents.size();
    }
}
