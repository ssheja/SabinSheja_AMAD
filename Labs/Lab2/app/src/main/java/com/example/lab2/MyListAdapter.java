package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.model.Bilionaire;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<Bilionaire> biliionaires;
    private Context mContext;

    public MyListAdapter(List<Bilionaire> bilionaires, Context mContext) {
        this.biliionaires = bilionaires;
        this.mContext = mContext;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;
        TextView mTextView4;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.nameTextview);
            mTextView2 = itemView.findViewById(R.id.rankTextview);
            mTextView3 = itemView.findViewById(R.id.countryTextview);
            mTextView4 = itemView.findViewById(R.id.netWorthTextview);
        }
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.card_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        Bilionaire bilionaire = biliionaires.get(position);
        holder.mTextView.setText(bilionaire.getNames());
        holder.mTextView2.setText("Ranking: " + bilionaire.getRank());
        holder.mTextView3.setText("Country : "+bilionaire.getCountry());
        holder.mTextView4.setText("Current NetWorth: " + bilionaire.getNetWorth());
    }

    @Override
    public int getItemCount() {
        return biliionaires.size();
    }
}
