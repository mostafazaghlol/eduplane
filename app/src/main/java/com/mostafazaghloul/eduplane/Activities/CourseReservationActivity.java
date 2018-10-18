package com.mostafazaghloul.eduplane.Activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mostafazaghloul.eduplane.Models.category;
import com.mostafazaghloul.eduplane.R;
import com.mostafazaghloul.eduplane.Volley.RegisterCourses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseReservationActivity extends AppCompatActivity {
    @BindView(R.id.sliderDetalis)
    SliderLayout sliderLayout;
    @BindView(R.id.detailsDetalis)
    TextView textViewDetalis;
    @BindView(R.id.startDetalis)
    TextView textViewStart;
    @BindView(R.id.endDetalis)
    TextView textViewEnd;
    @BindView(R.id.backReservation)
    ImageView imageViewBack;
    String itemName,clientName,clientPhone,type,start_at,end_at;
    int itemid,clientId;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reservation);
        initScreen();
        ButterKnife.bind(this);
        int position = getIntent().getIntExtra("position",0);
        type = categoryActivity.type;
        category category = getObject(position);
        updateUI(category);
        getData();
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    private void getData() {
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        clientName = sharedPreferences.getString("name", null);
        clientPhone = sharedPreferences.getString("phone", null);
        clientId = sharedPreferences.getInt("id", 0);
    }
    private void updateUI(category category) {
        for (String name1 : category.getUrls()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(""+type)
                    .image(name1)
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            sliderLayout.addSlider(textSliderView);
        }
        textViewDetalis.setText(category.getDisc());
        start_at = category.getStart_at();
        end_at = category.getEnd_at();
        textViewStart.setText("بداية حجز الكورس"+category.getStart_at());
        textViewEnd.setText("نهاية حجز الكورس"+category.getEnd_at());
        itemid=category.getId();
        itemName=category.getName();
    }
    private category getObject(int position) {
        return categoryActivity.categoryModelArrayList.get(position);
    }
    private void initScreen() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_courses_reservation);
    }
    public void finishReservation(View view) {
        Log.e("detalisID",String.valueOf(itemid));
        Log.e("detalisID",itemName);
        progressDialog = ProgressDialog.show(this, "أرجو الإنتظار",
                "يتم حجز الكورس  ", true);
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    getJsonData(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterCourses loginPatiRequest = new RegisterCourses(String.valueOf(itemid),
                                                                    itemName,
                                                                    type
                                                                    ,start_at
                                                                    ,end_at
                                                                    ,String.valueOf(clientId)
                                                                    ,clientName
                                                                    ,clientPhone,
                                                                    "1",responseListener);
        RequestQueue queue = Volley.newRequestQueue(CourseReservationActivity.this);
        queue.add(loginPatiRequest);
    }
    private void getJsonData(JSONArray jsonResponse) {
        try {
            JSONObject jsonObject = jsonResponse.getJSONObject(0);
            int flag  = jsonObject.getInt("flag");
            if(flag == 0){
                progressDialog.dismiss();
                Toast.makeText(this, "تم حجز الكورس مسبقا", Toast.LENGTH_SHORT).show();
            }else{
                progressDialog.dismiss();
                Toast.makeText(this, "تم حجز الكورس ", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
