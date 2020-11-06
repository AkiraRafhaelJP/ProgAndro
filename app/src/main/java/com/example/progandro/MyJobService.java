package com.example.progandro;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.util.Log;

import android.os.Handler;
import android.widget.Toast;

public class MyJobService extends JobService {
    private static final String TAG = MyJobService.class.getSimpleName();
    private boolean jobCancelled = false;
    Context context;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob: Start job");
        context = getApplicationContext();
        doBackground(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob: cancel");
        jobCancelled = true;
        return false;
    }

    private void doBackground(final JobParameters parameters){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; ; i++){
                    if(jobCancelled){
                        return;
                    }
                    final int finalI = i;
                    Handler myHandler = new Handler(getMainLooper());

                    myHandler.post(new Runnable(){
                        @Override
                        public void run(){
                            Toast.makeText(getApplicationContext(), "Toast ke-"+finalI, Toast.LENGTH_LONG).show();
                        }
                    });



                    try{
                        Thread.sleep(3000);
                    } catch(InterruptedException e){
                        Log.i(TAG, "InterruptedException: ", e.getCause());
                    }
                }
            }
        }).start();
    }
}
