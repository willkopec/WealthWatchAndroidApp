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
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FinanceChartFragment extends Fragment implements View.OnClickListener {

    private ArrayList<DataEntry> chartData;
    TextView cashBalanceText;
    Button loginRedirectBtn;
    Python py;
    PyObject myScript;
    TextView loginText;
    boolean activeSession;
    boolean sessionTokenExists = true;
    String currentBalance;
    String sessionDirectory;
    public static int currentSessionToken;
    Random rand = new Random();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_finance_chart, container, false);
        loginRedirectBtn = thisView.findViewById(R.id.loginToExchangeBtn);
        cashBalanceText = thisView.findViewById(R.id.currentBalanceText);
        loginText = thisView.findViewById(R.id.loginToExchangeText);
        loginRedirectBtn.setOnClickListener(this);

        sessionDirectory = (this.getActivity().getDir("python", Context.MODE_PRIVATE) + "/sessionName.txt");
        //System.out.println(sessionDirectory);

        doesSessionExist();

        checkSessionTask checkThisSession = new checkSessionTask();
        checkThisSession.execute();

        return thisView;
    }

    public boolean doesSessionExist(){
        try {
            File myObj = new File(sessionDirectory);
            if (myObj.createNewFile()) {
                try{
                    currentSessionToken = rand.nextInt(1000);
                    FileWriter fWriter = new FileWriter(sessionDirectory);

                    fWriter.write(String.valueOf(currentSessionToken));
                    fWriter.close();

                    System.out.println("File is created successfully with the content.");
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }

                return false;
            } else {
                Scanner myReader = new Scanner(myObj);
                currentSessionToken = Integer.valueOf(myReader.nextLine());
                System.out.println("doesSessionExist sessionID: " + currentSessionToken);
                myReader.close();
                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred trying to read the sessionToken!");
            //e.printStackTrace();
        }
        return false;
    }

    private class checkSessionTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void...voids) {
            if(!Python.isStarted()){
                Python.start(new AndroidPlatform(getContext()));
            }
            py = Python.getInstance();
            //System.out.println(currentSessionToken);
            activeSession = py.getModule("robinhoodApi").callAttr("isActiveSession", String.valueOf(currentSessionToken)).toBoolean();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if(activeSession){
                loginText.setText("");
                loginRedirectBtn.setText("LogOut");
                currentBalance = py.getModule("robinhoodApi").callAttr("getCashBalance").toString();
                cashBalanceText.setText("Current Account Balance: $" + currentBalance);
            } else {
                System.out.println("NO ACTIVE SESSION FOUND");
            }
        }
    }

    private class logoutOfSessionTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void...voids) {
            currentSessionToken = rand.nextInt(1000);
            try{
                FileWriter fWriter = new FileWriter(sessionDirectory);

                fWriter.write(String.valueOf(currentSessionToken));
                fWriter.close();
                //System.out.println(currentSessionToken);

                System.out.println("File is created successfully with the content.");
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            loginText.setText("Login to Exchange:");
            loginRedirectBtn.setText("Login");
            cashBalanceText.setText("Current Account Balance: $~");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginToExchangeBtn:
                if(!activeSession){
                    System.out.println(currentSessionToken);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container, new ExchangeLoginFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    System.out.println("LOGGING OUT");
                    logoutOfSessionTask logout = new logoutOfSessionTask();
                    logout.execute();
                    System.out.println(currentSessionToken);
                    activeSession = false;
                }

        }
    }
}