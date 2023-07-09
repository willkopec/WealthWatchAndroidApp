package com.example.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TipCalculateFragment extends Fragment implements View.OnClickListener {

    View thisView;

    public static float billTotal;
    public static float percentageTip;
    public static int tipSplit = 1;
    public static boolean roundTip = false;
    public float tipPercent = percentageTip / 100;
    public float tipTotal = billTotal * tipPercent;
    public float tipAfterSplit = tipTotal / tipSplit;
    private EditText billTotalInput;
    private EditText percentageTipInput;
    private EditText tipSplitInput;
    private Button calculateTipBtn;
    private Button roundTipBtn;
    private ImageView roundUpHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        thisView = inflater.inflate(R.layout.fragment_tip_calculate, container, false);

        billTotalInput = (EditText) thisView.findViewById(R.id.billTotal);
        percentageTipInput = (EditText) thisView.findViewById(R.id.percentageTip);
        tipSplitInput = (EditText) thisView.findViewById(R.id.tipSplit);

        calculateTipBtn = (Button) thisView.findViewById(R.id.calculateTipButton);
        roundTipBtn = (Button) thisView.findViewById(R.id.calculateInterestButton);

        calculateTipBtn.setOnClickListener(this);
        roundTipBtn.setOnClickListener(this);

        roundUpHelper = (ImageView) thisView.findViewById(R.id.roundUpTipHelper);
        roundUpHelper.setOnClickListener(this);

        return thisView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.calculateTipButton:

                System.out.println("CLICKED HERE!");

                if(!billTotalInput.getText().toString().equals("") && !percentageTipInput.getText().toString().equals("")){

                    TipSplitFragment.billTotal = Float.valueOf(billTotalInput.getText().toString());
                    TipSplitFragment.percentageTip = Float.valueOf(percentageTipInput.getText().toString());
                    if(!tipSplitInput.getText().toString().equals("")){
                        TipSplitFragment.tipSplit = Integer.valueOf(tipSplitInput.getText().toString());
                    }

                    this.getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new TipSplitFragment())
                            //.addToBackStack(null)
                            .commit();
                } else if (billTotalInput.getText().toString().equals("") || percentageTipInput.getText().toString().equals("")) {
                    //send toast saying to input stuff for billTotal or percentageTip
                    Toast.makeText(getContext(), "Please fill in all of the fields. (Bill Total and Tip Percentage)",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.calculateInterestButton:
                if(!billTotalInput.getText().toString().equals("") && !percentageTipInput.getText().toString().equals("")){
                    TipSplitFragment.roundTip = true;
                    TipSplitFragment.billTotal = Float.valueOf(billTotalInput.getText().toString());
                    TipSplitFragment.percentageTip = Float.valueOf(percentageTipInput.getText().toString());
                    if(!tipSplitInput.getText().toString().equals("")){
                        TipSplitFragment.tipSplit = Integer.valueOf(tipSplitInput.getText().toString());
                    }

                    this.getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new TipSplitFragment())
                            //.addToBackStack(null)
                            .commit();
                } else if (billTotalInput.getText().toString().equals("") || percentageTipInput.getText().toString().equals("")) {
                    //send toast saying to input stuff for billTotal or percentageTip
                    Toast.makeText(getContext(), "Please fill in all of the fields. (Bill Total and Tip Percentage)",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.roundUpTipHelper:
                Toast.makeText(getContext(), "This will round the tip up to the nearest dollar amount",
                        Toast.LENGTH_LONG).show();
                break;

        }

    }


}
