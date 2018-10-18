package com.mostafazaghloul.eduplane.Activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.mostafazaghloul.eduplane.Adapters.categoryAdapter;
import com.mostafazaghloul.eduplane.Adapters.courseAdapter;
import com.mostafazaghloul.eduplane.Models.category;
import com.mostafazaghloul.eduplane.Models.courseModel;
import com.mostafazaghloul.eduplane.R;
import com.mostafazaghloul.eduplane.Volley.image;
import com.mostafazaghloul.eduplane.Volley.pdf;
import com.mostafazaghloul.eduplane.Volley.register;
import com.mostafazaghloul.eduplane.Volley.video;
import com.mostafazaghloul.eduplane.Volley.word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class categoryActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private categoryAdapter categoryAdapter;
    public static ArrayList<category> categoryModelArrayList;
    private ProgressDialog progressDialog;
    private int idOfActivity;
    public static String type;
    private TextView TxType;
    private ImageView BtBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initScreen();
        getIdOfActivity();
        initRecycler();
        getDataFromInternet();
        BtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    private void initScreen() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_category);
        TxType = findViewById(R.id.tvTitleCat);
        BtBack = findViewById(R.id.backAccountCat);
        TxType.setText(getIntent().getStringExtra("type"));
    }

    private void getIdOfActivity() {
        idOfActivity = getIntent().getIntExtra("id",0);
        type = getIntent().getStringExtra("type");
    }

    private void getDataFromInternet() {
        progressDialog = ProgressDialog.show(this, "أرجو الإنتظار",
                "يتم تحميل البيانات ", true);
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    getDataFromJson(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RequestQueue queue = Volley.newRequestQueue(categoryActivity.this);
        if(idOfActivity == 0){
            image RigsterRequest = new image(responseListener);
            queue.add(RigsterRequest);
        }else if(idOfActivity == 1){
            pdf RigsterRequest = new pdf(responseListener);
            queue.add(RigsterRequest);
        }else if(idOfActivity == 2){
            video RigsterRequest = new video(responseListener);
            queue.add(RigsterRequest);
        }else{
            word RigsterRequest = new word(responseListener);
            queue.add(RigsterRequest);
        }
    }

    private void getDataFromJson(JSONObject jsonResponse) {
        try {
            boolean status = jsonResponse.getBoolean("status");
            int code = jsonResponse.getInt("code");
            if(status && code==200){
                JSONArray output = jsonResponse.getJSONArray("output");
                for(int i=0;i<output.length();i++){
                    JSONObject jsonObject = output.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String disc = jsonObject.getString("disc");
                    String start_at = jsonObject.getString("start_at");
                    String end_at = jsonObject.getString("end_at");
                    ArrayList<String> urls = new ArrayList<>();
                    JSONArray images = jsonObject.getJSONArray("images");
                    for(int j=0;j<images.length();j++){
                        urls.add(images.get(j).toString());
                    }
                    categoryModelArrayList.add(new category(id
                            ,name
                            ,disc
                            ,start_at
                            ,end_at
                            ,urls));
                }

                updateUI();
            }
        }catch (JSONException s){
            s.printStackTrace();
        }
    }

    private void updateUI() {
        progressDialog.dismiss();
        categoryAdapter.notifyDataSetChanged();
    }

    private void initRecycler() {
        categoryModelArrayList = new ArrayList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerCategory);
        categoryAdapter = new categoryAdapter(categoryModelArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(categoryAdapter);
    }
}
