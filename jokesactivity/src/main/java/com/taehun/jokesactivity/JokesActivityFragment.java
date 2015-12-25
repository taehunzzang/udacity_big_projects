package com.taehun.jokesactivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokesActivityFragment extends Fragment {

    public JokesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(JokesActivity.JOKE_KEY);
        View root = inflater.inflate(R.layout.fragment_jokes, container, false);
        TextView jokeTextView = (TextView) root.findViewById(R.id.joke_textview);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }
        return root;
    }
}
