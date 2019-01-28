package com.sourcey.materiallogindemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class UpdateService extends Service {
    public UpdateService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://rdr6000.github.io/opennote-master/updater.html";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                if (response != null) {

                    boolean resp = response.contains("2");

                    if (!resp) {

                        //New Update Available
                        Intent intent1 = new Intent(UpdateService.this,UpdateDialog.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(UpdateService.this, "Running Current Version", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                volleyError.printStackTrace();

            }
        });

        queue.add(stringRequest);

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
