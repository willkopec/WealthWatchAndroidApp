package com.example.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CalculatorsMenuFragment extends Fragment implements View.OnClickListener {

    View thisView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        thisView = inflater.inflate(R.layout.fragment_calculators_menu, container, false);

        Button tipCalculatorBtn = (Button) thisView.findViewById(R.id.tipCalculatorButton);
        Button interestCalculatorBtn = (Button) thisView.findViewById(R.id.interestCalculatorButton);

        tipCalculatorBtn.setOnClickListener(this);
        interestCalculatorBtn.setOnClickListener(this);


        return thisView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.interestCalculatorButton:
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new InterestCalculatorFragment())
                        .commit();
                break;

            case R.id.tipCalculatorButton:
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new TipCalculateFragment())
                        .commit();
                break;

        }

    }

}
