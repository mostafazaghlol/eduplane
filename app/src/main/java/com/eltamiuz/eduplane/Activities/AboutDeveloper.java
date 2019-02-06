package com.eltamiuz.eduplane.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eltamiuz.eduplane.R;

import org.w3c.dom.Text;

public class AboutDeveloper extends AppCompatActivity {

    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        TextView textView = (TextView)findViewById(R.id.tvTitle);
        textView.setText("المطور");
        view = (ImageView)findViewById(R.id.backAccount);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AboutDeveloper.this,HomeActivity.class));
        finish();
    }
}
