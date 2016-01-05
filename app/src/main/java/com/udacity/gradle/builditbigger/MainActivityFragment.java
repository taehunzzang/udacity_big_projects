package com.udacity.gradle.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.MyJokes;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ProgressBar loadingProgressBar;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(progressHideShow, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        loadingProgressBar = (ProgressBar) root.findViewById(R.id.loadingProgressBar);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        //boolean isPaid = getActivity().getResources().getBoolean(R.bool.isPaid);
        //if(!isPaid){
//            AdRequest adRequest = new AdRequest.Builder()
//                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                    .build();
//            mAdView.loadAd(adRequest);
        //}
        return root;
    }

    private BroadcastReceiver progressHideShow = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("com.udacity.gradle.builditbigger.showprogress")) {
                loadingProgressBar.setVisibility(View.VISIBLE);
            }else{
                loadingProgressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onStop() {
        getActivity().unregisterReceiver(progressHideShow);
        super.onStop();
    }

    //    public void tellJoke(View view) {
//        MyJokes mMyJokes = new MyJokes();
//        Toast.makeText(getActivity(), mMyJokes.getJokes(), Toast.LENGTH_SHORT).show();
//    }
}
