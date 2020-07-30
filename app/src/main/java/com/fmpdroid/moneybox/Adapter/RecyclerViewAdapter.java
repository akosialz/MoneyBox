package com.fmpdroid.moneybox.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import com.fmpdroid.moneybox.R;
import com.fmpdroid.moneybox.dto.MoneyBox;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MoneyBoxViewHolder> {

    private List<MoneyBox> moneyBoxList;

    public RecyclerViewAdapter(List<MoneyBox> moneyBoxList) {
        this.moneyBoxList = moneyBoxList;
    }

    @NonNull
    @Override
    public MoneyBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        MoneyBoxViewHolder moneyBoxViewHolder = new MoneyBoxViewHolder(view);
        return moneyBoxViewHolder;
    }

    @Override
    public void onBindViewHolder(MoneyBoxViewHolder holder, int position) {
        MoneyBox moneyBox = moneyBoxList.get(position);
        holder.Icon.setImageResource(R.drawable.ic_menu_currency);
        holder.Title.setText(moneyBox.getTitle());
        holder.DateCreated.setText(moneyBox.getDateCreated());
        holder.Description.setText(moneyBox.getDescription());
        holder.TargetAmount.setText(String.valueOf(moneyBox.getTargetAmount()));
    }

    @Override
    public int getItemCount() {
        return moneyBoxList.size();
    }

    public static class MoneyBoxViewHolder extends RecyclerView.ViewHolder {

        ImageView Icon;
        TextView Title;
        TextView Description;
        TextView TargetAmount;
        TextView DateCreated;

        public MoneyBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            Icon = itemView.findViewById(R.id.item_icon);
            Title = itemView.findViewById(R.id.item_title);
            TargetAmount = itemView.findViewById(R.id.item_target_amount);
            Description = itemView.findViewById(R.id.item_description);
            DateCreated = itemView.findViewById(R.id.item_date_created);
        }
    }
}
