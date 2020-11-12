package com.example.progandro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
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
    private Button btnSignUp;
    private DatabaseHelper db;

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        SessionManagement sessionManagement = new SessionManagement(this);
        int status = sessionManagement.getSession();

        if(status != -1){
            moveToHomeActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate: dipanggil");
        Log.i(TAG, "onCreate: percobaan");
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnLogin();
//                onNotificationBtnClicked();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onClickBtnSignUp();
            }
        });
    }

    void onClickBtnLogin(){
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        boolean res = db.checkUser(username, password);
        if(res == true){
            Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();

            saveSession(username, password);
            moveToHomeActivity();
        } else{
            Toast.makeText(getApplicationContext(), "Username/password salah", Toast.LENGTH_LONG).show();
        }
    }

    void onClickBtnSignUp(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    void moveToHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    void saveSession(String username, String password){
        int status = 1;
        SessionManagement sessionManagement = new SessionManagement(this);
        sessionManagement.saveSession(status);
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