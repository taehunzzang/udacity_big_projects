package com.udacity.gradle.builditbigger.test;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by taehun on 16. 1. 8..
 */
public class AsyncTaskTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public AsyncTaskTest() {
        super(MainActivity.class);
    }

    private MainActivity activity;
    private Button mButton;

    private String joke;
    private boolean mBoolean;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        activity = getActivity();
        mButton = (Button) activity.findViewById(R.id.tellJokeBtn);
    }


    public void testInitialValue() throws Exception {

        //boolean initValue =

        boolean initialValue = true ;

        try {
            EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
            jokeTask.execute(new Pair<Context, String>(getActivity(),activity.greetingName));
            joke = jokeTask.get(30, TimeUnit.SECONDS);

        } catch (Exception e){
            fail("Timed out");
            initialValue= false;
        }
//        assertEquals(initialNumber, initialNumber);
        assertEquals(initialValue,true);
    }
}
