package com.example.muthobank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muthobank.R;
import com.example.muthobank.model.TransactionsPostModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.MyViewHolder> {
    private Context mContext;
    private List<TransactionsPostModel> teamsList = new ArrayList<>();


    public TransactionsListAdapter(Context mContext, List<TransactionsPostModel> teamsList) {
        this.mContext = mContext;
        this.teamsList = teamsList;
    }

    @NonNull
    @Override
    public TransactionsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsListAdapter.MyViewHolder holder, int position) {
        TransactionsPostModel model= teamsList.get(position);
        holder.name.setText(model.getAccountHolderName());
        holder.date.setText(model.getCreatedAt());
        holder.amount.setText("-"+model.getSendMoney());

    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;
        public TextView amount;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.transactions_name);
            date = (TextView) itemView.findViewById(R.id.transactions_date);
            amount = (TextView) itemView.findViewById(R.id.transactions_amount);
        }
    }
}
