package com.fmpdroid.moneybox;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.fmpdroid.moneybox.dto.MoneyBoxDto;

public class ScreenSlidePageFragment extends Fragment {

    public AlertDialog.Builder alertBuilder;
    public AlertDialog alertDialog;
    public EditText editTextAmount;
    public ImageView imageViewMoneyBox;
    public TextView textViewAmount;
    public TextView tvMoneyBoxName;
    public TextView tvDescription;
    public TextView tvRemainingAmount;
    public TextView tvTargetAmount;
    public TextView tvDateCreated;
    public TextView tvTargetDate;
    public View customView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvMoneyBoxName = view.findViewById(R.id.tv_moneyBoxName);
        tvDescription = view.findViewById(R.id.tv_description);
        tvRemainingAmount = view.findViewById(R.id.tv_remainingAmount);
        tvTargetAmount = view.findViewById(R.id.tv_targetAmount);
        tvDateCreated = view.findViewById(R.id.tv_dateCreated);
        tvTargetDate = view.findViewById(R.id.tv_targetDate);

        customView = View.inflate(getActivity(), R.layout.dialog_input_amount, null);

        textViewAmount = customView.findViewById(R.id.textViewDialogAmount);
        editTextAmount = customView.findViewById(R.id.editTextDialogAmount);
        imageViewMoneyBox = view.findViewById(R.id.imageView);

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

        editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textAmount = charSequence.toString().trim().length() > 0 ? "Amount: " + charSequence.toString() : "Amount: 0";
                textViewAmount.setText(textAmount);

                boolean isEnabled = charSequence.toString().length() == 0 ? false : true;
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(isEnabled);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                    s.clear();
                }
            }
        });
        editTextAmount.setOnFocusChangeListener((view2, hasFocus) -> {
            String hintText = hasFocus ? "" : "Enter Amount";
            editTextAmount.setHint(hintText);
        });

        alertBuilder = new AlertDialog.Builder(getActivity(), R.style.custom_alert_dialog_style);
        alertBuilder.setView(customView);
        alertBuilder.setPositiveButton("OK", (dialogInterface, i) -> {
            float inputAmount = Float.parseFloat(editTextAmount.getText().toString());
            float remainingAmount = Float.parseFloat(tvRemainingAmount.getText().toString());
            if (inputAmount > remainingAmount){
                Toast.makeText(getActivity(), "Entered amount should not be greater than the remaining amount", Toast.LENGTH_SHORT).show();
            }
            else{
                float newAmount = remainingAmount - inputAmount;
                tvRemainingAmount.setText(String.valueOf(newAmount));
            }
        });
        alertBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        alertDialog = alertBuilder.create();

        imageViewMoneyBox.setOnClickListener(view1 -> {
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        });

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
