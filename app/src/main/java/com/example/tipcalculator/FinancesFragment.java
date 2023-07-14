package com.example.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FinancesFragment extends Fragment {

    Fragment accountBalanceFragment;
    Fragment robinHoodApiFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountBalanceFragment = new FinancesBalanceFragment();
        robinHoodApiFragment = new FinanceChartFragment();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.accountBalanceContainer, robinHoodApiFragment)
                .commit();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_finances, container, false);


        return thisView;
    }
}
