package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics; import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.push.Push;
import com.microsoft.appcenter.distribute.Distribute;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pushlink.android.PushLink;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCenter.start(getApplication(), "59803557-7793-4d90-a059-4771cab23de4",
                Analytics.class, Crashes.class);
        AppCenter.start(getApplication(), "59803557-7793-4d90-a059- 4771cab23de4", Push.class);
        AppCenter.start(getApplication(), "59803557-7793-4d90-a059- 4771cab23de4", Distribute.class);
        //String yourDeviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        //PushLink.start(this, R.mipmap.ic_launcher, "j6ns2cobiife7u9l", yourDeviceID);
        TextView newtext = (TextView) findViewById(R.id.textView);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        }
        else {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            String displayName;
            if (user.getDisplayName() != null) {
                displayName = "Hello, " +user.getDisplayName().toString();
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

