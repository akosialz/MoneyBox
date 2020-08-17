package com.fmpdroid.moneybox.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fmpdroid.moneybox.R;
import com.fmpdroid.moneybox.ScreenSlidePageFragment;
import com.fmpdroid.moneybox.dto.MoneyBoxDto;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<ScreenSlidePageFragment> fragments = new ArrayList<>();
    private FragmentActivity fa;

    public ViewPagerAdapter(FragmentActivity fa) {
        super(fa);
        this.fa = fa;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        TextView textView = fa.findViewById(R.id.tv_empty);
        int visibilityValue = fragments.size() == 0 ? View.VISIBLE : View.GONE;
        textView.setVisibility(visibilityValue);
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    public void add(MoneyBoxDto moneyBoxDto) {
        fragments.add(ScreenSlidePageFragment.newInstance(moneyBoxDto, fa.getApplicationContext()));
        notifyDataSetChanged();
    }
}
