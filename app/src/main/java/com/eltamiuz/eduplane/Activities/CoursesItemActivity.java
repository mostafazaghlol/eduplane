package com.eltamiuz.eduplane.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.eltamiuz.eduplane.Adapters.courseItemAdapter;
import com.eltamiuz.eduplane.Models.courseItem;
import com.eltamiuz.eduplane.R;
import com.eltamiuz.eduplane.Volley.searchCourseSubject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoursesItemActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private courseItemAdapter courseAdapter;
    private ArrayList<courseItem> courseModelArrayList;
    private int id,course_id;
    private String file,course_name, course_time,course_desc,updated_at;
    TextView empty;
    private ProgressDialog progressDialog;
    public static String course_type;
    private ImageView imageViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursesitem);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_courses);
        empty = (TextView)findViewById(R.id.noCourses);
        imageViewBack = findViewById(R.id.backAccountx);
        initScreen();
        initRecycler();
        getId();
        prepareData();
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CoursesItemActivity.this,HomeActivity.class));
        finish();
    }

    private void getId() {
        course_id = getIntent().getIntExtra("course_id",0);
    }

    private void initScreen() {

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
                    progressDialog.dismiss();
                    Toast.makeText(CoursesItemActivity.this, getString(R.string.emptyItem), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        };

        searchCourseSubject RigsterRequest = new searchCourseSubject(String.valueOf(course_id), responseListener);
        RequestQueue queue = Volley.newRequestQueue(CoursesItemActivity.this);
        queue.add(RigsterRequest);
//        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
//            @Override
//            public void onRequestFinished(Request<Object> request) {
//                if(courseModelArrayList.size() == 0){
//                    recyclerView.setVisibility(View.INVISIBLE);
//                    empty.setVisibility(View.VISIBLE);
//                    progressDialog.dismiss();
//                }else{
//                    recyclerView.setVisibility(View.VISIBLE);
//                    empty.setVisibility(View.VISIBLE);
//                    progressDialog.dismiss();
//                    courseAdapter.notifyDataSetChanged();
//                }
//            }
//        });
    }

    private void getData(JSONArray jsonResponse) {
        try{
            for(int i =0;i<jsonResponse.length();i++){
                JSONObject object = jsonResponse.getJSONObject(i);
                id = object.getInt("id");
                course_name = object.getString("name");
                course_type = object.getString("type");
                course_desc = object.getString("disc");
                course_time = object.getString("created_at");
                updated_at = object.getString("updated_at");
                file = object.getString("file");
                courseModelArrayList.add(new courseItem(id,course_id,course_name,course_type,course_desc,file,course_time,updated_at));
            }
            progressDialog.dismiss();
            courseAdapter.notifyDataSetChanged();
            if(courseModelArrayList.size() == 0){
                    recyclerView.setVisibility(View.INVISIBLE);
                    empty.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                }else{
                    recyclerView.setVisibility(View.VISIBLE);
                    //empty.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                    courseAdapter.notifyDataSetChanged();
                }
        }catch (JSONException e){
            e.printStackTrace();
            empty.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
        }
    }

    private int getImageID(String course_type) {
        if(course_type == "video"){
            return  R.drawable.vido;
        }else if(course_type == "pdf"){
            return  R.drawable.pdf;
        }else if(course_type == "word"){
            return  R.drawable.word;

        }else {
            return  R.drawable.pictur;
        }
    }


    private void initRecycler() {
        courseModelArrayList = new ArrayList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerCourseItem);
        courseAdapter = new courseItemAdapter(courseModelArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(courseAdapter);
    }
}
