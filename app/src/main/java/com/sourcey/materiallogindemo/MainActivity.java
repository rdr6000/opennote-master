package com.sourcey.materiallogindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent1 = new Intent(MainActivity.this, UpdateService.class);
        startService(intent1);
        TextView newtext = (TextView) findViewById(R.id.textView);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            String displayName;
            if (user.getDisplayName() != null) {
                displayName = "Hello, " + user.getDisplayName().toString();
                newtext.setText(displayName);
            } else
                newtext.setText("Hello, user");
        }
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            Intent I = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(I);
        }

        return super.onOptionsItemSelected(item);

    }

}



