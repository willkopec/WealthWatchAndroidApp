package com.example.tipcalculator;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class MarketNewsAdapter extends RecyclerView.Adapter<MarketNewsAdapter.MyViewHolder>{

    private ArrayList<StockNewsData> MarketNewsData;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView stockNewsTitle;
        TextView stockNewsSummary;
        TextView stockNewsSource;
        TextView stockNewsLink;
        ImageView stockNewsImage;

        public MyViewHolder(View itemView){
            super(itemView);
            this.stockNewsTitle = (TextView) itemView.findViewById(R.id.newsTitleText);
            this.stockNewsSummary = (TextView) itemView.findViewById(R.id.newsSummaryText);
            this.stockNewsSource = (TextView) itemView.findViewById(R.id.newsSourceText);
            this.stockNewsLink = (TextView) itemView.findViewById(R.id.newsArticleRedirectText);
            this.stockNewsImage = (ImageView) itemView.findViewById(R.id.image_view);
        }

    }

    public MarketNewsAdapter(ArrayList<StockNewsData> people){
        this.MarketNewsData = people;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item_layout, parent, false);
        //view.setOnClickListener(MainActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        TextView textViewTitle = holder.stockNewsTitle;
        TextView textViewSummary = holder.stockNewsSummary;
        TextView textViewSource = holder.stockNewsSource;
        TextView textViewLink = holder.stockNewsLink;
        ImageView imageView = holder.stockNewsImage;

        textViewTitle.setText(MarketNewsData.get(position).getTitle());
        textViewSummary.setText(MarketNewsData.get(position).getSummary());
        textViewSource.setText("Source: " + MarketNewsData.get(position).getSource());
        textViewLink.setClickable(true);
        textViewLink.setMovementMethod(LinkMovementMethod.getInstance());
        textViewLink.setText(Html.fromHtml("<a href=\"" + MarketNewsData.get(position).getLink() + "\">Read More</a>", Html.FROM_HTML_MODE_COMPACT));
        if(!MarketNewsData.get(position).getBannerImg().equals("")){
            Picasso.get().load(MarketNewsData.get(position).getBannerImg()).into(imageView);
        }


    }

    @Override
    public int getItemCount() {
        return MarketNewsData.size();
    }

}
