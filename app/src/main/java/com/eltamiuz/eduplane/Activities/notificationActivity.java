package com.eltamiuz.eduplane.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eltamiuz.eduplane.Adapters.notificationAdapter;
import com.eltamiuz.eduplane.Models.notificationModel;
import com.eltamiuz.eduplane.R;

import java.util.ArrayList;

public class notificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private notificationAdapter notificationAdapter;
    private ArrayList<notificationModel> notificationModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initScreen();
        initRecycler();
        prepareData();
    }

    private void initScreen() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_notification);
    }

    private void prepareData() {
    notificationModelArrayList.add(new notificationModel("course1","9:40pm",R.drawable.fackperson));
    notificationModelArrayList.add(new notificationModel("course2","1:60pm",R.drawable.fackperson));
    notificationModelArrayList.add(new notificationModel("course3","2:20pm",R.drawable.fackperson));
    notificationModelArrayList.add(new notificationModel("course4","3:44pm",R.drawable.fackperson));
    notificationModelArrayList.add(new notificationModel("course5","9:56pm",R.drawable.fackperson));
    notificationModelArrayList.add(new notificationModel("course6","9:20pm",R.drawable.fackperson));
    notificationModelArrayList.add(new notificationModel("course7","9:30pm",R.drawable.fackperson));
    notificationAdapter.notifyDataSetChanged();
    }


    private void initRecycler() {
        notificationModelArrayList = new ArrayList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerNotification);
        notificationAdapter = new notificationAdapter(notificationModelArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(notificationAdapter);
    }
}
