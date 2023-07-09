package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TipCalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tip_calculate);

        EditText billTotalInput = (EditText) findViewById(R.id.billTotal);
        EditText percentageTipInput = (EditText) findViewById(R.id.percentageTip);
        EditText tipSplitInput = (EditText) findViewById(R.id.tipSplit);

        Button calculateTipBtn = (Button) findViewById(R.id.calculateTipButton);
        calculateTipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((billTotalInput.getText().toString() != "" && isNumeric(billTotalInput.getText().toString())) && (percentageTipInput.getText().toString() != "" && isNumeric(percentageTipInput.getText().toString()))){

                    TipSplitActivity.billTotal = Float.valueOf(billTotalInput.getText().toString());
                    TipSplitActivity.percentageTip = Float.valueOf(percentageTipInput.getText().toString());
                    if(!tipSplitInput.getText().toString().equals("")){
                        TipSplitActivity.tipSplit = Integer.valueOf(tipSplitInput.getText().toString());
                    }

                    Intent tipAmountPage = new Intent(TipCalculateActivity.this, TipSplitActivity.class);
                    startActivity(tipAmountPage);
                    //finish();
                } else if (billTotalInput.getText().toString() == "" || percentageTipInput.getText().toString() == "") {
                    //send toast saying to input stuff for billTotal or percentageTip
                    Toast.makeText(TipCalculateActivity.this, "Please fill in all of the fields. (Bill Total and Tip Percentage)",
                            Toast.LENGTH_LONG).show();
                } else if (!isNumeric(billTotalInput.getText().toString()) || !isNumeric(percentageTipInput.getText().toString())){
                    Toast.makeText(TipCalculateActivity.this, "Bill Total and Tip Percentage must be valid numbers (No percents or dollar signs)",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        Button roundTipBtn = findViewById(R.id.calculateInterestButton);
        roundTipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((billTotalInput.getText().toString() != "" && isNumeric(billTotalInput.getText().toString())) && (percentageTipInput.getText().toString() != "" && isNumeric(percentageTipInput.getText().toString()))){

                    TipSplitActivity.billTotal = Float.valueOf(billTotalInput.getText().toString());
                    TipSplitActivity.percentageTip = Float.valueOf(percentageTipInput.getText().toString());
                    if(!tipSplitInput.getText().toString().equals("")){
                        System.out.println(tipSplitInput.getText().toString());
                        TipSplitActivity.tipSplit = Integer.valueOf(tipSplitInput.getText().toString());
                    }
                    TipSplitActivity.roundTip = true;

                    Intent tipAmountPage = new Intent(TipCalculateActivity.this, TipSplitActivity.class);
                    startActivity(tipAmountPage);
                    //finish();
                } else if (billTotalInput.getText().toString() == "" || percentageTipInput.getText().toString() == "") {
                    //send toast saying to input stuff for billTotal or percentageTip
                    Toast.makeText(TipCalculateActivity.this, "Please fill in all of the fields. (Bill Total and Tip Percentage)",
                            Toast.LENGTH_LONG).show();
                } else if (!isNumeric(billTotalInput.getText().toString()) || !isNumeric(percentageTipInput.getText().toString())){
                    Toast.makeText(TipCalculateActivity.this, "Bill Total and Tip Percentage must be valid numbers (No percents or dollar signs)",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        ImageView roundUpHelper = (ImageView) findViewById(R.id.roundUpTipHelper);
        roundUpHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TipCalculateActivity.this, "This will round the tip up to the nearest dollar amount",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}