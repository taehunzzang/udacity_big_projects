package com.udacity.gradle.builditbigger;

import android.util.Log;

/**
 * Created by taehun on 16. 1. 2..
 */
public class Echo {
    public static <T> T echo(T o) {
        return echo(o, false);
    }

    public static <T> T echo(T o, boolean log) {
        if (log) Log.i(Echo.class.getName(), o.toString());

        return o;
    }
}
