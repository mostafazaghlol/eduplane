package com.eltamiuz.eduplane.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.eltamiuz.eduplane.R;

import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplachActivity extends AppCompatActivity {
    @BindView(R.id.text)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface font = Typeface.createFromAsset(
                getAssets(),
                "font2.ttf");
        textView.setTypeface(font);
        Timer timer = new Timer();
        if(isNetworkConnected()) {
            timer.schedule(splashTimerTask, 1000);
        }else{

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            SplachActivity.this);

                    // set title
                    alertDialogBuilder.setTitle("غير متصل بالانترنت");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("حاول مره اخرى ")
                            .setCancelable(false)
                            .setPositiveButton("حاول مره اخرى",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    SplachActivity.this.recreate();
                                }
                            });


                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


        }
    }



    private TimerTask splashTimerTask = new TimerTask() {

    @Override
    public void run() {

        startActivity(new Intent(SplachActivity.this, loginActivity.class));
        finish();
    }

    };
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
