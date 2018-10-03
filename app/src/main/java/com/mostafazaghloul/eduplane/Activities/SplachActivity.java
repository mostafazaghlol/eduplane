package com.mostafazaghloul.eduplane.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.mostafazaghloul.eduplane.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);        Timer timer = new Timer();
        timer.schedule(splashTimerTask, 1000);
    }



    private TimerTask splashTimerTask = new TimerTask() {

    @Override
    public void run() {
        startActivity(new Intent(SplachActivity.this, loginActivity.class));
        finish();
    }

};
}
