package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by eribas on 10/05/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{

    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String url;

    public EarthquakeLoader(Context context, String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    //thread de background
    @Override
    public List<Earthquake> loadInBackground(){
        if(url == null){
            return null;
        }

        // Realiza requisição de rede, decodifica a resposta, e extrai uma lista de earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(url);
        return earthquakes;
    }
}
