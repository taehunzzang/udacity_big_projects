package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.taehun.jokesactivity.JokesActivity;
import com.taehun.myapplication.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by taehun on 16. 1. 8..
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    public String greetingName="hello taehun";
    private boolean testGreeting = false;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //sendBroadcast(new Intent("com.udacity.gradle.builditbigger.showprogress"));
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(), null)
//                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("https://build-it-bigger-1173.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try{
            return myApiService.sayHi(name).execute().getData();
        }catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        // sendBroadcast(new Intent("com.udacity.gradle.builditbigger.hideprogress"));
        Intent intent = new Intent(context,JokesActivity.class);
//            MyJokes mMyJokes = new MyJokes();
//            String joke = mMyJokes.getJokes();
        if(result.equals(greetingName)){
            testGreeting = true;
        }
        intent.putExtra(JokesActivity.JOKE_KEY,result);
        context.startActivity(intent);
    }
}