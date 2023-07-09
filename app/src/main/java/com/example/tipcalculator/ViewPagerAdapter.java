package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        System.out.println(position);
        switch(position){
            case 0: return new StockNewsFragment();
            case 1: return new CryptoNewsFragment();
            case 2: return new TechnologyNewsFragment();
            case 3: return new RealEstateNewsFragment();
            case 4: return new EconomyNewsFragment();
            default: return new StockNewsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
