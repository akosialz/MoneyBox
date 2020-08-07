package com.fmpdroid.moneybox;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.fmpdroid.moneybox.adapter.ViewPagerAdapter;
import com.fmpdroid.moneybox.dto.MoneyBoxDto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScreenSlidePageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvMoneyBoxName = view.findViewById(R.id.tv_moneyBoxName);
        TextView tvDescription = view.findViewById(R.id.tv_description);
        TextView tvRemainingAmount = view.findViewById(R.id.tv_remainingAmount);
        TextView tvTargetAmount = view.findViewById(R.id.tv_targetAmount);
        TextView tvDateCreated = view.findViewById(R.id.tv_dateCreated);
        TextView tvTargetDate = view.findViewById(R.id.tv_targetDate);

        Bundle bundle = getArguments();
        if (bundle != null) {
            MoneyBoxDto moneybox = bundle.getParcelable(getResources().getString(R.string.key_moneybox));
            tvMoneyBoxName.setText(moneybox.getName());
            tvDescription.setText(moneybox.getDescription());
            tvRemainingAmount.setText(String.valueOf(moneybox.getRemainingAmount()));
            tvTargetAmount.setText(String.valueOf(moneybox.getTargetAmount()));
            tvDateCreated.setText(moneybox.getDateCreated());
            tvTargetDate.setText(moneybox.getTargetDate());
        }

        return view;
    }

    public static ScreenSlidePageFragment newInstance(MoneyBoxDto moneyBox, Context context) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(context.getResources().getString(R.string.key_moneybox), moneyBox);
        fragment.setArguments(bundle);
        return fragment;
    }
}
