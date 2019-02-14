package com.kerolossalib.kirfood.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RESTController {
    private static final String BASE_URL = "http://138.68.86.70/";
    private static final String VERSION = "";
    private static final String URL = BASE_URL + VERSION;
    private RequestQueue queue;


    //End Points
    public static String RESTAURANT_ENDPOINT = "restaurants";



    public RESTController (Context context){

        queue = Volley.newRequestQueue(context);
    }

    public void getRequest(String endPoint, Response.Listener<String> success, Response.ErrorListener error) {

        StringRequest request = new StringRequest(Request.Method.GET,
                URL.concat(endPoint),
                success,
                error
        );
        queue.add(request);

    }

}
