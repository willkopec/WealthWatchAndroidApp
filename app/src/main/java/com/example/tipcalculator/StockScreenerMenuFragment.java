package com.example.tipcalculator;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StockScreenerMenuFragment extends Fragment implements View.OnClickListener {

    private Spinner exchangeSpinner;
    private Spinner marketCapSpinner;
    private Spinner priceSpinner;
    private Spinner sectorSpinner;
    private Spinner avgVolumeSpinner;
    private Spinner patternSpinner;
    private Spinner countrySpinner;
    private Button screenStocksBtn;
    TextView screeningProgressText;
    private int exchangeSelection = 0;
    private int marketCapSelection = 0;
    private int priceSelection = 0;
    private int sectorSelection = 0;
    private int avgVolSelection = 0;
    private int patternSelection = 0;
    private int countrySelection = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_stock_screener_menu, container, false);

        exchangeSpinner = thisView.findViewById(R.id.screenerExchangeSpinner);
        marketCapSpinner = thisView.findViewById(R.id.screenerMarketCapSpinner);
        priceSpinner = thisView.findViewById(R.id.screenerPriceSpinner);
        sectorSpinner = thisView.findViewById(R.id.screenerSectorSpinner);
        avgVolumeSpinner = thisView.findViewById(R.id.screenerAvgVolumeSpinner);
        patternSpinner = thisView.findViewById(R.id.screenerPatternSpinner);
        countrySpinner = thisView.findViewById(R.id.screenerCountrySpinner);

        screenStocksBtn = thisView.findViewById(R.id.screenStocksButton);
        screenStocksBtn.setOnClickListener(this);

        screeningProgressText = (TextView) thisView.findViewById(R.id.stockScreenProgressText);

        String[] exchanges = {"Any", "AMEX", "NASDAQ", "NYSE"};
        ArrayAdapter<String> exchangesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, exchanges);
        exchangeSpinner.setAdapter(exchangesAdapter);

        exchangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                exchangeSelection = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });


        String[] marketCaps = {"Any","Mega (Over 200B)", "Large (10-200B)", "Mid (2-10B)", "+Large (Over 10B)", "+Mid (Over 2B)", "+Small (Over 300M)", "+Micro (Over 50M)"};
        ArrayAdapter<String> marketCapAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, marketCaps);
        marketCapSpinner.setAdapter(marketCapAdapter);

        marketCapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                marketCapSelection = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });

        String[] prices = {"Any", "Under $3", "Under $5", "Under $10", "Under $15", "Under $20", "Under $30", "Over $5", "Over $10", "Over $15", "Over $20", "Over $30",};
        ArrayAdapter<String> pricesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, prices);
        priceSpinner.setAdapter(pricesAdapter);

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                priceSelection = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });

        String[] sectors = {"Any", "Basic Materials", "Communication Services", "Consumer Cyclical", "Consumer Defensive", "Energy", "Financial", "HealthCare", "Industrials", "Real Estate", "Technology", "Utilities"};
        ArrayAdapter<String> sectorsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sectors);
        sectorSpinner.setAdapter(sectorsAdapter);

        sectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                sectorSelection = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });

        String[] volumes = {"Any", "Under 500K", "Under 1M", "Over 500K", "Over 1M", "Over 2M"};
        ArrayAdapter<String> volumesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, volumes);
        avgVolumeSpinner.setAdapter(volumesAdapter);

        avgVolumeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                avgVolSelection = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });

        String[] patterns = {"Any", "Golden Cross", "Death Cross", "Horizontal S/R", "TL Resistance", "TL Support", "Wedge Up", "Wedge Down", "Triangle Ascending", "Triangle Descending", "Wedge", "Channel Up", "Channel Down", "Double Top", "Double Bottom", "Multiple Top", "Multiple Bottom", "Head and Shoulders", "Head and Shoulders(Inverse)"};
        ArrayAdapter<String> patternsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, patterns);
        patternSpinner.setAdapter(patternsAdapter);

        patternSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                patternSelection = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });

        String[] countries = {"Any", "USA", "Canada", "China", "Russia", "Europe"};
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        countrySpinner.setAdapter(countriesAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                countrySelection = position;
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
            case R.id.screenStocksButton:
                getFinVizDataTask getData = new getFinVizDataTask();
                screenStocksBtn.setEnabled(false);
                screeningProgressText.setText("Retrieving List, Please wait...");
                getData.execute();
        }

    }

    private class getFinVizDataTask extends AsyncTask<String, String,String> {
        boolean hasResults = true;

        protected String doInBackground(String... urls) {
            String currentScreenerList = "";
            FinvizScraper thisFinVizInfo = new FinvizScraper();

            String currentScreenerLink = generateFinvizLink();

            try{
                currentScreenerList = thisFinVizInfo.getFinVizData(currentScreenerLink);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            if(currentScreenerList.length() > 0){
                ScreenerListFragment.usersStockList = currentScreenerList;
            } else {
                hasResults = false;
            }

            return null;
        }

        protected void onPostExecute(String s) {
            screenStocksBtn.setEnabled(true);

            if(hasResults){
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ScreenerListFragment())
                        .commit();
            } else {
                Toast.makeText(getContext(), "No stocks were found, Please try another search!",
                        Toast.LENGTH_LONG).show();
            }
            screeningProgressText.setText("");

        }
    }

    private String generateFinvizLink(){
        String finvizLink = "https://finviz.com/screener.ashx?v=111&f=";

        switch(marketCapSelection){
            case 1:
                finvizLink += "cap_mega";
                break;
            case 2:
                finvizLink += "cap_large";
                break;
            case 3:
                finvizLink += "cap_mid";
                break;
            case 4:
                finvizLink += "cap_largeover";
                break;
            case 5:
                finvizLink += "cap_midover";
                break;
            case 6:
                finvizLink += "cap_smallover";
                break;
            case 7:
                finvizLink += "cap_microover";
                break;
        }

        switch(exchangeSelection){
            case 1:
                if(marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "exch_amex";
                break;
            case 2:
                if(marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "exch_nasd";
                break;
            case 3:
                if(marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "exch_nyse";
                break;
        }

        switch(countrySelection){
            case 1:
                if(exchangeSelection != 0 || marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "geo_usa";
                break;
            case 2:
                if(exchangeSelection != 0 || marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "geo_canada";
                break;
            case 3:
                if(exchangeSelection != 0 || marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "geo_china";
                break;
            case 4:
                if(exchangeSelection != 0 || marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "geo_russia";
                break;
            case 5:
                if(exchangeSelection != 0 || marketCapSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "geo_europe";
                break;
        }

        switch(sectorSelection){
            case 1:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_basicmaterials";
                break;
            case 2:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_communicationservices";
                break;
            case 3:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_consumercyclical";
                break;
            case 4:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_consumerdefensive";
                break;
            case 5:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_energy";
                break;
            case 6:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_financial";
                break;
            case 7:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_healthcare";
                break;
            case 8:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_industrials";
                break;
            case 9:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_realestate";
                break;
            case 10:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_technology";
                break;
            case 11:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sec_utilities";
                break;
        }

        switch(avgVolSelection){
            case 1:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_avgvol_u500";
                break;
            case 2:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_avgvol_u1000";
                break;
            case 3:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_avgvol_o500";
                break;
            case 4:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_avgvol_o1000";
                break;
            case 5:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_avgvol_o2000";
                break;
        }

        switch(priceSelection){
            case 1:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_u3";
                break;
            case 2:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_u5";
                break;
            case 3:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_u10";
                break;
            case 4:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_u15";
                break;
            case 5:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_u20";
                break;
            case 6:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_u30";
                break;
            case 7:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_o5";
                break;
            case 8:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_o10";
                break;
            case 9:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_o15";
                break;
            case 10:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_o20";
                break;
            case 11:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "sh_price_o30";
                break;

        }

        switch(patternSelection){
            case 1:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_sma50_sa200";
                break;
            case 2:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_sma50_sb200";
                break;
            case 3:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_horizontal";
                break;
            case 4:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_tlresistance";
                break;
            case 5:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_tlsupport";
                break;
            case 6:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_wedgeup";
                break;
            case 7:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_wedgedown";
                break;
            case 8:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_wedgeresistance";
                break;
            case 9:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_wedgesupport";
                break;
            case 10:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_wedge";
                break;
            case 11:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_channelup";
                break;
            case 12:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_channeldown";
                break;
            case 13:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_doubletop";
                break;
            case 14:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_doublebottom";
                break;
            case 15:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_multipletop";
                break;
            case 16:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_multiplebottom";
                break;
            case 17:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_headandshoulders";
                break;
            case 18:
                if(exchangeSelection != 0 || marketCapSelection != 0 || countrySelection != 0 || sectorSelection != 0 || avgVolSelection != 0 || priceSelection != 0){
                    finvizLink += ",";
                }
                finvizLink += "ta_pattern_headandshouldersinv";
                break;
        }
        return finvizLink;
    }

}