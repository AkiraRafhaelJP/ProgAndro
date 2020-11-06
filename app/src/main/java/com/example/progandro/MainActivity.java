package com.example.progandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate: dipanggil");
        Log.i(TAG, "onCreate: percobaan");
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtn();
//                onNotificationBtnClicked();
            }
        });

    }

    void onClickBtn(){
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        if(username.equals("admin") && password.equals("admin")){
            Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "Username/password salah", Toast.LENGTH_LONG).show();
    }

//    private void onNotificationBtnClicked(){
//        String CHANNEL_ID = "MY_NOTIF_CHANNEL";
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("My Notification")
//                .setContentText("My notification content")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        int notificationID = 1;
//        notificationManager.notify(notificationID, builder.build());
//    }
}