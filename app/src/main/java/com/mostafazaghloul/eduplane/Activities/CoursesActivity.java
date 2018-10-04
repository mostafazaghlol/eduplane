package com.mostafazaghloul.eduplane.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mostafazaghloul.eduplane.Adapters.courseAdapter;
import com.mostafazaghloul.eduplane.Models.courseModel;
import com.mostafazaghloul.eduplane.R;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private courseAdapter courseAdapter;
    private ArrayList<courseModel> courseModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        initScreen();
        initRecycler();
        prepareData();
    }

    private void initScreen() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_courses);
    }

    private void prepareData() {
    courseModelArrayList.add(new courseModel("course1","9:40pm",R.drawable.fackperson));
    courseModelArrayList.add(new courseModel("course2","1:60pm",R.drawable.fackperson));
    courseModelArrayList.add(new courseModel("course3","2:20pm",R.drawable.fackperson));
    courseModelArrayList.add(new courseModel("course4","3:44pm",R.drawable.fackperson));
    courseModelArrayList.add(new courseModel("course5","9:56pm",R.drawable.fackperson));
    courseModelArrayList.add(new courseModel("course6","9:20pm",R.drawable.fackperson));
    courseModelArrayList.add(new courseModel("course7","9:30pm",R.drawable.fackperson));
    courseAdapter.notifyDataSetChanged();
    }


    private void initRecycler() {
        courseModelArrayList = new ArrayList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerNotification);
        courseAdapter = new courseAdapter(courseModelArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(courseAdapter);
    }
}
