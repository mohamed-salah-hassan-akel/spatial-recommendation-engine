package com.taxuns.fastfoodapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BranchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
        BranchesOfRestaurant braRest = (BranchesOfRestaurant) new BranchesOfRestaurant().execute();
        Intent braIntent = getIntent();
        braIntent.getExtras().getString("rest_name");

    }

    private class BranchesOfRestaurant extends AsyncTask<Void,Void,String>{
        String branchesUrl;
        String branchesJson;
        @Override
        protected void onPreExecute() {
            branchesUrl = "http://10.0.3.2:8081/resturant_branches";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL braUrl = new URL(branchesUrl);
                HttpURLConnection braConn = (HttpURLConnection)braUrl.openConnection();
                InputStream braStream = braConn.getInputStream();
                BufferedReader branchesBuffer = new BufferedReader(new InputStreamReader(braStream));
                StringBuilder branchesStringBuilder = new StringBuilder();
                while((branchesJson=branchesBuffer.readLine())!=null){
                    branchesStringBuilder.append(branchesJson+"\n");
                }
                branchesBuffer.close();
                braStream.close();
                braConn.disconnect();
                return branchesStringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray branchesJsonArr = new JSONArray(s);
                JSONObject branchesJsonOb =
                        new JSONObject(String.valueOf(branchesJsonArr.getJSONObject(0)));
                JSONArray branches= new JSONArray(branchesJsonOb.getString("rest_branches"));



                ArrayList<String> branchesOfRest = new ArrayList<>();
                for(int i=0; i < branches.length(); i++){
                    branchesOfRest.add(branches.getString(i));
                }

                final ListView branchesListView = (ListView)findViewById(R.id.branches_list);
                RestaurantListAdapter branchesAdapter = new
                        RestaurantListAdapter(getApplicationContext()
                        ,R.layout.restaurant_list_view_layout,branchesOfRest);
                branchesListView.setAdapter(branchesAdapter);
                branchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                    }
                });



            } catch (JSONException e) {
               e.printStackTrace();
            }
        }
    }
}
