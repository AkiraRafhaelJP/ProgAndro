package com.example.progandro;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity{

    private static final String TAG = HomeActivity.class.getSimpleName();
    private Button btnStartJob;
    private Button btnStopJob;
    private Button btnListFilm;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnStartJob = findViewById(R.id.startJob);
        btnStopJob = findViewById(R.id.stopJob);
        btnListFilm = findViewById(R.id.listFilm);

        //button lihat list film di halaman lain
        btnListFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnListFilm();
            }
        });
    }

    private void onClickBtnListFilm(){
        Intent intent = new Intent(this, ListFilm.class);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        if(rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270){
            Log.d("success", "onResume: Landscape");
        }
        else{
            Log.d("success", "onResume: Portrait");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void scheduleJob(View view){
        ComponentName componentName = new ComponentName(getApplicationContext(), MyJobService.class);
        JobInfo info = new JobInfo.Builder(100, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.i(TAG, "scheduleJob: Job Scheduled");
        }
        else{
            Log.i(TAG, "scheduleJob: Job scheduling failed");
        }
    }

    public void stopJob(View view){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(100);
        Toast.makeText(getApplicationContext(), "Toast berhenti", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Stop Job");
    }

    public void logout(View view){
        SessionManagement sessionManagement = new SessionManagement(this);
        sessionManagement.removeSession();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
