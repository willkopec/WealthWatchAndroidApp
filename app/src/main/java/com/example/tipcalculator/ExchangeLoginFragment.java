package com.example.tipcalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ExchangeLoginFragment extends Fragment {

    EditText emailInputBox;
    EditText passwordInputBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_exchange_login, container, false);

        emailInputBox = thisView.findViewById(R.id.editTextTextEmailAddress2);
        passwordInputBox = thisView.findViewById(R.id.editTextTextPassword);



        return thisView;
    }
}