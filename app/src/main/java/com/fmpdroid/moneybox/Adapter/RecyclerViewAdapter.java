package com.fmpdroid.moneybox.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fmpdroid.moneybox.R;
import com.fmpdroid.moneybox.dto.MoneyBoxDto;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MoneyBoxViewHolder> {

    private List<MoneyBoxDto> moneyBoxList;

    public RecyclerViewAdapter(List<MoneyBoxDto> moneyBoxList) {
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
        MoneyBoxDto moneyBox = moneyBoxList.get(position);
        holder.icon.setImageResource(R.drawable.ic_menu_currency);
        holder.title.setText(moneyBox.getName());
        holder.dateCreated.setText(moneyBox.getDateCreated());
        holder.description.setText(moneyBox.getDescription());
        holder.targetAmount.setText(String.valueOf(moneyBox.getTargetAmount()));
    }

    @Override
    public int getItemCount() {
        return moneyBoxList.size();
    }

    public static class MoneyBoxViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView description;
        TextView targetAmount;
        TextView dateCreated;

        public MoneyBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_icon);
            title = itemView.findViewById(R.id.item_title);
            targetAmount = itemView.findViewById(R.id.item_target_amount);
            description = itemView.findViewById(R.id.item_description);
            dateCreated = itemView.findViewById(R.id.item_date_created);
        }
    }
}
