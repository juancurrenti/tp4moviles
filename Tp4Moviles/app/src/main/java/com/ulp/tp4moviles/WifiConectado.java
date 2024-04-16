package com.ulp.tp4moviles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class WifiConectado extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(intent.getBooleanExtra("connected", false)){
            Intent intentllamar= new Intent(Intent.ACTION_CALL);
            intentllamar.setData(Uri.parse("tel:2664553747"));
            intentllamar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentllamar);
        }else{
            Toast.makeText(context,"Wifi no conectado", Toast.LENGTH_LONG).show();
        }
    }
}