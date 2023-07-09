package com.example.tipcalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ScreenerListFragment extends Fragment implements View.OnClickListener {

    ArrayList<StockData> theStockList = new ArrayList<>();
    public String stockList[];
    public String linkStart;
    public String fullLink;
    public static String usersStockList = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_screener_list, container, false);

        if(!usersStockList.equals("")){
            stockList = usersStockList.split(",");

            for(int i = 0; i < stockList.length; i++){
                linkStart = "https://finance.yahoo.com/quote/";
                fullLink = linkStart + stockList[i];
                System.out.println(stockList[i]);
                StockData currentStock = new StockData(stockList[i], fullLink);
                theStockList.add(currentStock);
            }

        } else {
            StockData emptyListPlaceholderStock = new StockData("No stocks found!", "Please use a new scan method or Market Index!");
            theStockList.add(emptyListPlaceholderStock);
        }

        // on below line we are initializing our variables.
        ListView stockListView = thisView.findViewById(R.id.stockListVieww);
        Button restartScanBtn = thisView.findViewById(R.id.scanAgainBtn);
        restartScanBtn.setOnClickListener(this);

        StockListAdapter adapter = new StockListAdapter(getContext(), R.layout.screener_list_item_view, theStockList);
        stockListView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return thisView;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.scanAgainBtn:
                usersStockList = "";
                this.getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new StockScreenerMenuFragment())
                        //.addToBackStack(null)
                        .commit();
        }

    }
}