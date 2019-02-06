package com.eltamiuz.eduplane.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.eltamiuz.eduplane.R;
import com.eltamiuz.eduplane.Utils.CustomTypefaceSpan;
//import com.onesignal.OneSignal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog progressDialog;
    @BindView(R.id.text24)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        ButterKnife.bind(this);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface font = Typeface.createFromAsset(
                getAssets(),
                "font2.ttf");
        textView.setTypeface(font);
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();
//        OneSignal.setSubscription(true);
//        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
//            @Override
//            public void idsAvailable(String userId, String registrationId) {
//               // Toast.makeText(HomeActivity.this, "userID"+userId, Toast.LENGTH_LONG).show();
//            }
//        });
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView textView2 = (TextView) headerView.findViewById(R.id.text34);
//        TextView textView2 = (TextView)navigationView.findViewById(R.id.text34);
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
        textView2.setTypeface(font);
    }
    //To add TypeFace
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "belam.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.account) {
            startActivity(new Intent(HomeActivity.this,AccountActivity.class));
            finish();
        } else if (id == R.id.courses) {
            startActivity(new Intent(HomeActivity.this,CoursesActivity.class));
            finish();

        }else if(id == R.id.developer){
            startActivity(new Intent(HomeActivity.this,AboutDeveloper.class));
            finish();
        } else if (id == R.id.out) {
            clearSharedPrefereance();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void clearSharedPrefereance() {
        progressDialog = ProgressDialog.show(this, "أرجو الإنتظار",
                "يتم تسجيل الخروج ", true);
        progressDialog.show();
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        progressDialog.dismiss();
        finish();

    }

    public void categoryActivity(View view) {
        int id = view.getId();
        Intent intent = new Intent(HomeActivity.this,categoryActivity.class);
        if(id == R.id.contentPicture){
            intent.putExtra("id",0);
            intent.putExtra("type",getString(R.string.pic));
        }else if(id == R.id.contentPdf){
            intent.putExtra("id",1);
            intent.putExtra("type",getString(R.string.pdf));
        }else if(id == R.id.contentVideo){
            intent.putExtra("id",2);
            intent.putExtra("type",getString(R.string.video));
        }else if(id == R.id.contentWord){
            intent.putExtra("id",3);
            intent.putExtra("type",getString(R.string.word));
        }
        startActivity(intent);
        finish();
    }
}
