package com.example.progandro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = RegistrationActivity.class.getSimpleName();

    private EditText username;
    private EditText password;
    private EditText passwordConfirm;
    private Button btnSignUp;
    private TextView login;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        passwordConfirm = findViewById(R.id.txtPasswordConfirm);
        btnSignUp = findViewById(R.id.btnSignUp);
        login = findViewById(R.id.textViewLogin);
        db = new DatabaseHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUpBtn(username, password, passwordConfirm);
            }
        });

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onClickLoginText();
            }
        });
    }

    private void onClickSignUpBtn(EditText username, EditText password, EditText passwordConfirm){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String passConf = passwordConfirm.getText().toString().trim();

        if(passConf.equals(pass)){
            if(db.checkUser(user, pass)){
                Toast.makeText(getApplicationContext(), "Akun dengan username tersebut telah terdaftar, pilih username lain", Toast.LENGTH_LONG).show();
                return;
            }

            db.addUser(user, pass);
            Toast.makeText(getApplicationContext(), "Akun berhasil didaftarkan", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Password tidak sama", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent signUpIntent = new Intent(this, MainActivity.class);
        startActivity(signUpIntent);
    }

    private void onClickLoginText(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
