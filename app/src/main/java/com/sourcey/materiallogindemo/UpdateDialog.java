package com.sourcey.materiallogindemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


public class UpdateDialog extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Update Available");
        alertDialog.setMessage("A new version of this application is available ."+"\nWe have improved a lot from previous version");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.dropbox.com/s/jyvb3llehx50gyr/app-release.apk?dl=0"));
                if(intent.resolveActivity(getPackageManager())  !=null){

                    startActivity(intent);
                }
            }
        });

        alertDialog.show();
    }
}