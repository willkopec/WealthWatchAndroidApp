package com.example.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class TipSplitFragment extends Fragment implements View.OnClickListener {

    public static float billTotal;
    public static float percentageTip;
    public static int tipSplit = 1;
    public static boolean roundTip = false;
    public float tipPercent = percentageTip / 100;
    public float tipTotal = billTotal * tipPercent;
    public float tipAfterSplit = tipTotal / tipSplit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_tip_split, container, false);

        if(roundTip){
            tipAfterSplit = (float) Math.ceil(tipAfterSplit);
            tipTotal = tipAfterSplit * tipSplit;
        }

        TextView tipTotalText = (TextView) thisView.findViewById(R.id.totalTipAmountText);
        tipTotalText.setText(("$" + String.format("%.02f", tipTotal)));

        TextView tipSplitText = (TextView) thisView.findViewById(R.id.tipSplitAmountText);
        tipSplitText.setText(("$" + String.format("%.02f", tipAfterSplit)));

        Button homeBtn = (Button) thisView.findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);

        return thisView;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.homeBtn:
                roundTip = false;
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FinancesFragment())
                        //.addToBackStack(null)
                        .commit();
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_finances);
                break;
        }


    }
}
