package com.example.tipcalculator;

import static java.lang.Math.round;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.Chart;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;

public class InterestCalculatedFragment extends Fragment implements View.OnClickListener{

    public static int interestMethod = 0;
    public static float originalInvestment;
    public static float interestRate;
    public static float periodInYears;
    public static float monthlyContribution;
    //private AnyChartView compoundInterestChart;
    private ArrayList<DataEntry> chartData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_interest_calculated, container, false);
        TextView cashAfterInterestText = thisView.findViewById(R.id.cashAfterInterestText);
        TextView originalInvestmentText = thisView.findViewById(R.id.originalInvestmentAmountText);

        //compoundInterestChart = thisView.findViewById(R.id.any_chart_view);

        double cashAfterInterest = 0;
        double currentAccumulatedInterest = 1;

        chartData = new ArrayList<>();
        chartData.add(new ValueDataEntry("Year 0", originalInvestment));

        if(interestMethod == 0){
            //cashAfterInterest = originalInvestment * Math.pow((1 + (interestRate/100)), periodInYears);
            for(int i = 1; i <= periodInYears; i++){
                currentAccumulatedInterest *= (1 + (interestRate/100));
                chartData.add(new ValueDataEntry("Year " + (i), round(originalInvestment * currentAccumulatedInterest)));
            }
            cashAfterInterest = originalInvestment * currentAccumulatedInterest;
        } else if (interestMethod == 1){
            //cashAfterInterest = originalInvestment * Math.pow((1 + (interestRate/100)/2), periodInYears * 2);
            for(int i = 1; i <= periodInYears; i++){
                currentAccumulatedInterest *= ( Math.pow((1 +  (interestRate/100)/2), 2) );
                chartData.add(new ValueDataEntry("Year " + (i), round(originalInvestment * currentAccumulatedInterest)));
            }
            cashAfterInterest = originalInvestment * currentAccumulatedInterest;
            System.out.println(cashAfterInterest);
        } else if (interestMethod == 2){
            //cashAfterInterest = originalInvestment * Math.pow((1 + (interestRate/100)/4), (periodInYears * 4));
            for(int i = 1; i <= periodInYears; i++){
                currentAccumulatedInterest *= ( Math.pow((1 +  (interestRate/100)/4), 4) );
                chartData.add(new ValueDataEntry("Year " + (i), round(originalInvestment * currentAccumulatedInterest)));
            }
            cashAfterInterest = originalInvestment * currentAccumulatedInterest;
        } else if (interestMethod == 3){
            //cashAfterInterest = originalInvestment * Math.pow((1 + (interestRate/100)/12), (periodInYears * 12));
            for(int i = 1; i <= periodInYears; i++){
                currentAccumulatedInterest *= ( Math.pow((1 +  (interestRate/100)/12), 12) );
                chartData.add(new ValueDataEntry("Year " + (i), round(originalInvestment * currentAccumulatedInterest)));
            }
            cashAfterInterest = originalInvestment * currentAccumulatedInterest;
        }else if (interestMethod == 4){
            //cashAfterInterest = originalInvestment * Math.pow((1 + (interestRate/100)/365), (periodInYears * 365));
            for(int i = 1; i <= periodInYears; i++){
                currentAccumulatedInterest *= ( Math.pow((1 +  (interestRate/100)/365), 365) );
                chartData.add(new ValueDataEntry("Year " + (i), round(originalInvestment * currentAccumulatedInterest)));
            }
            cashAfterInterest = originalInvestment * currentAccumulatedInterest;
        }

        originalInvestmentText.setText("$ " + originalInvestment);
        cashAfterInterestText.setText("$ " + String.format("%,.02f", cashAfterInterest));

        Cartesian thisLineChart = AnyChart.line();

        thisLineChart.animation(true);

        thisLineChart.tooltip().positionMode(TooltipPositionMode.POINT);

        Set set = Set.instantiate();
        set.data(chartData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Line series1 = thisLineChart.line(series1Mapping);
        series1.stroke("#116A7B", 3, (String) null, (String) null, (String) null);
        series1.name("Cash");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        thisLineChart.background().enabled();
        thisLineChart.background().fill("#ECE5C7");
        thisLineChart.title("Interest Over Time:");

        thisLineChart.data(chartData);


        AnyChartView anyChartView = (AnyChartView) thisView.findViewById(R.id.any_chart_view);
        anyChartView.setChart(thisLineChart);

        //compoundInterestChart.setChart(thisLineChart);

        // Inflate the layout for this fragment
        return thisView;
    }

    @Override
    public void onClick(View view) {

    }
}