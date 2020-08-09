package com.fmpdroid.moneybox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fmpdroid.moneybox.adapter.RecyclerViewAdapter;
import com.fmpdroid.moneybox.dto.MoneyBoxDto;
import com.fmpdroid.moneybox.singleton.MoneyBoxSingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        setUpRecyclerView(view);

        button = view.findViewById(R.id.fabAddToList);
        button.setOnClickListener(view1 -> {
            Intent myIntent = new Intent(getContext(), CreateMoneyBox.class);
            getActivity().startActivity(myIntent);
        });
        return view;
    }

    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        List<MoneyBoxDto> moneyBoxList = MoneyBoxSingleton.getInstance().getMoneyBoxList();
        if (moneyBoxList.size() > 0){
            adapter = new RecyclerViewAdapter(moneyBoxList);
            recyclerView.setAdapter(adapter);
        }
        else{
            recyclerView.setVisibility(View.GONE);
            TextView txtEmpty = view.findViewById(R.id.txtEmptyRecyclerView);
            txtEmpty.setVisibility(View.VISIBLE);
        }


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}