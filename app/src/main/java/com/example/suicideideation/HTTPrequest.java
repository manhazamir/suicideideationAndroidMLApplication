package com.example.suicideideation;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class HTTPrequest {

    public interface VolleyCallback{
        void onSuccess(String result);
        void onFaliure(String faliure);
    }

    public static void callAPI(String method, String url, final JSONObject params, final JSONObject headers, final VolleyCallback callback, Context context){

        switch (method){
            case "Post":
                RequestQueue queue = Volley.newRequestQueue(context);

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url, params,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse( JSONObject response ) {
                                Log.e("Response",response.toString());
                                callback.onSuccess(response.toString());

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error: " + error);
                        callback.onFaliure(error.toString());

                    }
                });
                queue.add(jsonObjReq);
                break;

            case "Get":
                RequestQueue queue1 = Volley.newRequestQueue(context);

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, params,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("Response",response.toString());
                                callback.onSuccess(response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error: " + error);
                        callback.onFaliure(error.toString());
                    }
                });

                queue1.add(stringRequest);

                break;
        }


    }
}
