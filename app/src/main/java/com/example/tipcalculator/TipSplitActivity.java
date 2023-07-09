package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TipSplitActivity extends AppCompatActivity {

    public static float billTotal;
    public static float percentageTip;
    public static int tipSplit = 1;
    public static boolean roundTip = false;
    public float tipPercent = percentageTip / 100;
    public float tipTotal = billTotal * tipPercent;
    public float tipAfterSplit = tipTotal / tipSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tip_split);

        if(roundTip){
            tipAfterSplit = (float) Math.ceil(tipAfterSplit);
            tipTotal = tipAfterSplit * tipSplit;
        }

        TextView tipTotalText = (TextView) findViewById(R.id.totalTipAmountText);
        tipTotalText.setText(("$" + String.format("%.02f", tipTotal)));

        TextView tipSplitText = (TextView) findViewById(R.id.tipSplitAmountText);
        tipSplitText.setText(("$" + String.format("%.02f", tipAfterSplit)));

        Button homeBtn = (Button) findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundTip = false;
                Intent homeActivity = new Intent(TipSplitActivity.this, MainActivity.class);
                startActivity(homeActivity);
            }
        });

    }
}