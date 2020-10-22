package com.example.progandro;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class FragmentBottom extends Fragment {
    private static final String TAG = FragmentBottom.class.getSimpleName();
    Button buttonNotifTrigger;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.e(TAG, "onCreateView: FragmentBottom");
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        buttonNotifTrigger = view.findViewById(R.id.btnLogin);
        buttonNotifTrigger.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                onNotificationBtnClicked();
            }
        });
        return view;
    }

//    private void onNotificationBtnClicked(){
//        String CHANNEL_ID = "MY_NOTIF_CHANNEL";
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("My Notification")
//                .setContentText("My notification content")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
//        int notificationID = 1;
//        notificationManager.notify(notificationID, builder.build());
//    }
}
