package com.example.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.gson.*;

public class ExchangeLoginFragment extends Fragment implements View.OnClickListener{

    ImageView exchangeLogo;
    EditText emailInputBox;
    EditText passwordInputBox;
    EditText authCodeInputBox;
    Button loginButton;
    private Python py;
    private PyObject myScript;
    loginTask thisLogin;
    private boolean loginSucessful = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_exchange_login, container, false);

        exchangeLogo = thisView.findViewById(R.id.exchangeLogoImg);
        emailInputBox = thisView.findViewById(R.id.editTextTextEmailAddress2);
        passwordInputBox = thisView.findViewById(R.id.editTextTextPassword);
        authCodeInputBox = thisView.findViewById(R.id.editTextNumber);

        exchangeLogo.setImageDrawable(getResources().getDrawable(R.drawable.robinhood_logo));

        loginButton = thisView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        return thisView;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginButton:
                thisLogin = new loginTask();
                thisLogin.execute();

                try{
                    Thread.sleep(2000);
                } catch(InterruptedException e){

                }

                if(loginSucessful){
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FinancesFragment())
                            .commit();
                } else {
                    Toast.makeText(getContext(), "Error while logging in. Your information may be incorrect or this Exchange is currently offline. Please try again",
                            Toast.LENGTH_LONG).show();
                }

        }

    }

    private class loginTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void...voids) {
            SharedPreferences myPrefs = getActivity().getSharedPreferences(MainActivity.MyPrefs, Context.MODE_PRIVATE);

            if(!Python.isStarted()){
                Python.start(new AndroidPlatform(getContext()));
            }
            py = Python.getInstance();
            myScript = py.getModule("robinhoodApi");

            //System.out.println(FinanceChartFragment.currentSessionToken);
            loginSucessful = myScript.callAttr("getLoginSession", emailInputBox.getText().toString(), passwordInputBox.getText().toString(), authCodeInputBox.getText().toString(), String.valueOf(FinanceChartFragment.currentSessionToken)).toBoolean();

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }

}