package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getMarketNewsData("https://www.alphavantage.co/query?function=NEWS_SENTIMENT&apikey=I9J07FNNQWKLYMTV", 1);
        getMarketNewsData("https://www.alphavantage.co/query?function=NEWS_SENTIMENT&topics=blockchain&apikey=I9J07FNNQWKLYMTV", 2);
        getMarketNewsData("https://www.alphavantage.co/query?function=NEWS_SENTIMENT&topics=technology&apikey=I9J07FNNQWKLYMTV", 3);
        getMarketNewsData("https://www.alphavantage.co/query?function=NEWS_SENTIMENT&topics=real_estate&apikey=I9J07FNNQWKLYMTV", 4);
        getMarketNewsData("https://www.alphavantage.co/query?function=NEWS_SENTIMENT&topics=economy_macro&topics=economy_monetary&topics=economy_fiscal&apikey=I9J07FNNQWKLYMTV", 5);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FinancesFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_finances);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_finances:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FinancesFragment()).commit();
                break;
            case R.id.nav_investments:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InvestmentsFragment()).commit();
                break;
            case R.id.nav_stock_screener:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StockScreenerMenuFragment()).commit();
                //System.out.println("TODO1");
                break;
            case R.id.nav_market_news:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MarketNewsFragment()).commit();
                //System.out.println("TODO2");
                break;
            case R.id.nav_calculators:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatorsMenuFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public void getMarketNewsData(String apiURL, int currentNewsTopic){
        /*
        Query the data from the link
        */
        RequestQueue requestQueue = null;
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(this);
        }

        //Check if the data is being received via cache or
        //via network thread (NOTE: Get requests get cached
        //after first request and parsed via cache after the
        //first request to avoid multiple network requests)
        if(requestQueue.getCache().get(apiURL) != null){
            System.out.println("Getting data from the cache!");
        } else {
            System.out.println("Using Network Threads to get data!");
        }

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,apiURL,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray feed = response.getJSONArray("feed");
                    for(int i = 0; i < feed.length(); i++){
                        JSONObject curFeedElement = feed.getJSONObject(i);
                        StockNewsData thisData = new StockNewsData(curFeedElement.get("title").toString(), curFeedElement.get("url").toString(), curFeedElement.get("summary").toString(), curFeedElement.get("source").toString(), "", curFeedElement.getString("banner_image"));

                        if(currentNewsTopic == 1){
                            StockNewsFragment.stockNews.add(thisData);
                        } else if (currentNewsTopic == 2){
                            CryptoNewsFragment.cryptoNews.add(thisData);
                        } else if (currentNewsTopic == 3){
                            TechnologyNewsFragment.technologyNews.add(thisData);
                        } else if (currentNewsTopic == 4){
                            RealEstateNewsFragment.realEstateNews.add(thisData);
                        } else if (currentNewsTopic == 5){
                            EconomyNewsFragment.economyNews.add(thisData);
                        }

                    }

                } catch (JSONException e){
                    Toast.makeText(MainActivity.this, "The Maximum API Requests for market news has been reached. This will result in some or all market news to not show up, please try to reload the application in a few minutes.",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("my-api","Something Went Wrong! :(");
                System.out.println(error);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}