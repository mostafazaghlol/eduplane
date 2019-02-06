package com.eltamiuz.eduplane.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.eltamiuz.eduplane.R;

public class ShowingContent extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initScreen();
        WebView webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String website = intent.getStringExtra("web");

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setTitle(getString(R.string.app_name));
        pDialog.setMessage("جارى التحميل ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pDialog.show();
                Log.e(
                        "Starts"
                        ,"We start"
                );
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pDialog.dismiss();
                Log.e(
                        "Starts"
                        ,"We stop"
                );
            }
        });
        webView.loadUrl(website);
        webView.getSettings().setJavaScriptEnabled(true);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    private void initScreen() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_courses_items);
        imageView = findViewById(R.id.backAccountxx);
    }
}
