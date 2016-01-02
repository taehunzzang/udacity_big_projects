package com.udacity.gradle.builditbigger;

import org.junit.Test;

/**
 * Created by taehun on 16. 1. 2..
 */
public class EchoTest {
    @Test
    public void verifyEchoResponse() {
        assert Echo.echo("hello").equals("hello");
    }

//    @Test
//    public void verifyLoggingEchoResponse() {
//        assert Echo.echo("hello", true).equals("hello");
//    }
}