package com.example.tipcalculator;

public class StockNewsData {

    private String title;
    private String link;
    private String summary;
    private String source;
    private String tickers;
    private String bannerImg;

    public StockNewsData(String title, String link, String summary, String source, String tickers, String bannerImg){
        this.title = title;
        this.link = link;
        this.summary = summary;
        this.source = source;
        this.tickers = tickers;
        this.bannerImg = bannerImg;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getSummary() {
        return summary;
    }

    public String getSource() {
        return source;
    }

    public String getTickers() {
        return tickers;
    }

    public String getBannerImg() {
        return bannerImg;
    }
}
