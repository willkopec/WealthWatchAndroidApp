package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InterestCalculatorFragment extends Fragment implements View.OnClickListener {

    private EditText cashEarningInterest;
    private EditText interestRate;
    private EditText periodInYears;
    private EditText monthlyContributions;
    private Spinner interestMethod;
    private Button calculateInterestBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_interest_calculator, container, false);

        cashEarningInterest = (EditText) thisView.findViewById(R.id.cashEarningInterestText);
        interestRate = (EditText) thisView.findViewById(R.id.interestRate);

        interestMethod = (Spinner) thisView.findViewById(R.id.interestMethod);

        periodInYears = (EditText) thisView.findViewById(R.id.interestTimeInYears);
        monthlyContributions = (EditText) thisView.findViewById(R.id.interestContributions);

        calculateInterestBtn = (Button) thisView.findViewById(R.id.calculateInterestButton);
        calculateInterestBtn.setOnClickListener(this);

        String[] interestMethods = {"Annually", "SemiAnnually", "Quarterly", "Monthly", "Daily"};

        ArrayAdapter<String> interestMethodAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, interestMethods);

        interestMethod.setAdapter(interestMethodAdapter);
        interestMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                InterestCalculatedFragment.interestMethod = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });

        return thisView;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.calculateInterestButton:
                if(!cashEarningInterest.getText().toString().equals("") && !interestRate.getText().toString().equals("") && !periodInYears.getText().toString().equals("")){
                    System.out.println(InterestCalculatedFragment.interestMethod);
                    InterestCalculatedFragment.originalInvestment = Float.valueOf(cashEarningInterest.getText().toString());
                    InterestCalculatedFragment.interestRate = Float.valueOf(interestRate.getText().toString());
                    InterestCalculatedFragment.periodInYears = Float.valueOf(periodInYears.getText().toString());
                    if(!monthlyContributions.getText().toString().equals("")){
                        InterestCalculatedFragment.monthlyContribution = Float.valueOf(monthlyContributions.getText().toString());
                    }

                    this.getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new InterestCalculatedFragment())
                            //.addToBackStack(null)
                            .commit();
                } else if (cashEarningInterest.getText().toString().equals("") || interestRate.getText().toString().equals("") || periodInYears.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Please fill in all required fields. (Initial Investment, Interest, and Period.)",
                            Toast.LENGTH_LONG).show();
                }



        }

    }
}