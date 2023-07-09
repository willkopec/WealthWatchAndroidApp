package com.example.tipcalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EconomyNewsFragment extends Fragment {

    static MyOnClickListener myOnClickListener;
    private static RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    public static ArrayList<StockNewsData> economyNews = new ArrayList<StockNewsData>();
    private static MarketNewsAdapter adapter;
    private TextView newsTopicTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_news_layout, container, false);

        myOnClickListener = new EconomyNewsFragment.MyOnClickListener(getContext());

        recyclerView = (RecyclerView) thisView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        adapter = new MarketNewsAdapter(economyNews);
        recyclerView.setAdapter(adapter);

        newsTopicTitle = thisView.findViewById(R.id.newsTopicTitle);
        newsTopicTitle.setText("Economy News:");

        return thisView;
    }

    private class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        private MyOnClickListener(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            System.out.println("Clicked!");
        }
    }
}
