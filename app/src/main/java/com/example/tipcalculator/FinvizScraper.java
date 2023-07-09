package com.example.tipcalculator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FinvizScraper {

    public FinvizScraper(){
    }

    public String getFinVizData(String finvizUrl) throws InterruptedException{

        String thisStockList = "";

        try{
            Document doc = Jsoup.connect(finvizUrl).get();

            Elements numPagesElements = doc.getElementsByClass("screener-pages");
            Element maxPage = numPagesElements.last();

            if(maxPage == null){
                System.out.println("NO RESULTS!");
            } else {
                int numPagesInCurrentScreener = Integer.parseInt(maxPage.text());
                System.out.println(numPagesInCurrentScreener);

                int curStockCount = 1;
                int i = 1;
                while(i <= numPagesInCurrentScreener){
                    Document curDoc;
                    if(i == 1){
                        curDoc = Jsoup.connect(finvizUrl).get();
                    } else {
                        curStockCount += 20;
                        curDoc = Jsoup.connect(finvizUrl + "&r=" + curStockCount).get();
                        Thread.sleep(200);
                        System.out.println(finvizUrl + "&r=" + curStockCount);
                    }
                    Elements stocksOnThisPage = curDoc.getElementsByClass("screener-link-primary");
                    for(Element thisStock : stocksOnThisPage){
                        thisStockList += thisStock.text() + ",";
                    }
                    i++;
                }
            }

        } catch(IOException e){

        }
        //System.out.println(finvizUrl);
        System.out.println(thisStockList);
        return thisStockList;
    }

}
