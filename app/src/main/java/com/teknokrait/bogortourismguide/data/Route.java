package com.teknokrait.bogortourismguide.data;

import android.net.Uri;

/**
 * Created by sirius on 3/12/2017.
 */

public class Route {

    public static String depart = null;
    public static String destination = null;

    public static String getDepart() {
        return depart;
    }

    public static void setDepart(String depart) {
        Route.depart = depart;
    }

    public static String getDestination() {
        return destination;
    }

    public static void setDestination(String destination) {
        Route.destination = destination;
    }
}
