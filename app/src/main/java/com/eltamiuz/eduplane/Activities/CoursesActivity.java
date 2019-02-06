package com.eltamiuz.eduplane.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.eltamiuz.eduplane.Adapters.courseAdapter;
import com.eltamiuz.eduplane.Models.courseModel;
import com.eltamiuz.eduplane.R;
import com.eltamiuz.eduplane.Volley.searchMyCourses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private courseAdapter courseAdapter;
    private ArrayList<courseModel> courseModelArrayList;
    private int user_id,image_id;
    private String course_id,course_name, course_time,course_type;
    TextView empty;
    private ProgressDialog progressDialog;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        initScreen();
        initRecycler();
        getId();
        prepareData();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getId() {
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        user_id = sharedPreferences.getInt("id",0);
    }

    private void initScreen() {
        empty = (TextView)findViewById(R.id.noCourses);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_courses);
        imageView = findViewById(R.id.backAccountx);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CoursesActivity.this,HomeActivity.class));
        finish();

    }

    private void prepareData() {
        progressDialog = ProgressDialog.show(this, "أرجو الإنتظار",
                "يتم تحمـيل البيانات ", true);
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    getData(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        searchMyCourses RigsterRequest = new searchMyCourses(String.valueOf(user_id), responseListener);
        RequestQueue queue = Volley.newRequestQueue(CoursesActivity.this);
        queue.add(RigsterRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                progressDialog.dismiss();
                courseAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getData(JSONArray jsonResponse) {
        Boolean success = false;
        try{
            for(int i =0;i<jsonResponse.length();i++){
                JSONObject object = jsonResponse.getJSONObject(i);
                success = object.getBoolean("success");
                if(success) {
                    course_id = object.getString("course_id");
                    course_name = object.getString("course_name");
                    course_time = object.getString("star_at");
                    course_type = object.getString("course_type");
                    image_id = getImageID(course_type);
                    courseModelArrayList.add(new courseModel(course_name, course_time, image_id, course_id));
                }
            }
            if(!success){
                progressDialog.dismiss();
                empty.setVisibility(View.VISIBLE);
                return;
            }
            progressDialog.dismiss();
            courseAdapter.notifyDataSetChanged();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private int getImageID(String course_type) {
        if(course_type.equals("video")){
            return  R.drawable.vido;
        }else if(course_type.equals("pdf")){
            return  R.drawable.pdf;
        }else if(course_type.equals("word")){
            return  R.drawable.word;
        }else {
            return  R.drawable.pictur;
        }
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
