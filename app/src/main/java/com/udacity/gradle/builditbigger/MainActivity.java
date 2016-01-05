package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.MyJokes;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.taehun.jokesactivity.JokesActivity;
import com.taehun.myapplication.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void tellJoke(View view) {
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this,"Taehun"));
//        Intent intent = new Intent(this,JokesActivity.class);
//        MyJokes mMyJokes = new MyJokes();
//        String joke = mMyJokes.getJokes();
//        intent.putExtra(JokesActivity.JOKE_KEY,joke);
//        startActivity(intent);
//        Toast.makeText(this, mMyJokes.getJokes(), Toast.LENGTH_SHORT).show();
    }

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private  MyApi myApiService = null;
        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            sendBroadcast(new Intent("com.udacity.gradle.builditbigger.showprogress"));
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

            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            sendBroadcast(new Intent("com.udacity.gradle.builditbigger.hideprogress"));
            Intent intent = new Intent(getApplicationContext(),JokesActivity.class);
//            MyJokes mMyJokes = new MyJokes();
//            String joke = mMyJokes.getJokes();
            intent.putExtra(JokesActivity.JOKE_KEY,result);
            startActivity(intent);
        }
    }

}
