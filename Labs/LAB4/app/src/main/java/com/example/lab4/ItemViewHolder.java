package com.example.lab4;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder{
    private TextView mTextView;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.textView);
    }

    public void setRecipeName(String name){
        mTextView.setText(name);
    }
}

