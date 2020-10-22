package com.example.progandro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReciver extends BroadcastReceiver{
    private static final String TAG = BroadcastReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: from BroadcastReciver");
        Toast.makeText(context, "Broadcast recived", Toast.LENGTH_LONG).show();
    }
}
