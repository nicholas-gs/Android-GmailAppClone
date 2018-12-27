package com.example.user.gmailappclone.Helper;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton instance;
    private RequestQueue requestQueue;

    // VolleySingleton lasts the lifetime of the application, not just the activity
    // Hence use getApplicationContext()
    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton checkInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }



}
