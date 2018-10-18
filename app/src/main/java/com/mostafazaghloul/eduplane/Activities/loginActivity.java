package com.mostafazaghloul.eduplane.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mostafazaghloul.eduplane.R;
import com.mostafazaghloul.eduplane.Volley.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class loginActivity extends AppCompatActivity {
    private String email,password;
    @BindView(R.id.EdEmailLogin)
    EditText EdEmail;
    @BindView(R.id.EdPasswordLogin)
    EditText EdPassword;
    SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        if(checkDataSharedPre()){
            openHome2();
        }
    }



    public void signup(View view) {
        startActivity(new Intent(loginActivity.this,SignupActivity.class));
    }

    public void login(View view) {
       if(initEditText()){
           progressDialog = ProgressDialog.show(this, "أرجو الإنتظار",
                   "يتم تسجيل الدخول  ", true);
            login(email,password);
       }
    }

    private boolean initEditText() {
        if(!EdEmail.getText().toString().isEmpty()) {
            email = EdEmail.getText().toString().trim();
        }else{
            EdEmail.setError(getString(R.string.email));
            return false;
        }
        if(!EdPassword.getText().toString().isEmpty()) {
            password = EdPassword.getText().toString();
        }else{
            EdPassword.setError(getString(R.string.passworderror));
            return false;
        }
        return  true;
    }
    //login process {xuer --> username,xpass -->password ,xlan -->language}
    public void login(String xuser, String xpass) {
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    getData(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Login loginPatiRequest = new Login(xuser, xpass, responseListener);
        RequestQueue queue = Volley.newRequestQueue(loginActivity.this);
        queue.add(loginPatiRequest);

    }

    private void getData(JSONObject jsonResponse) {
        try{
            boolean status = jsonResponse.getBoolean("status");
            int code = jsonResponse.getInt("code");
            if(status&& code == 200){
                JSONObject output = jsonResponse.getJSONObject("output");
                int id = output.getInt("id");
                String name = output.getString("name");
                String email = output.getString("email");
                String phone = output.getString("phone");
                String work = output.getString("work");
                String specialization = output.getString("specialization");
                saveDataSharedPre(id,name,email,phone,work,specialization);
                openHome();
            }else{
                progressDialog.dismiss();
                Toast.makeText(this, getString(R.string.errorInput), Toast.LENGTH_LONG).show();
            }
        }catch (JSONException e1){
            e1.printStackTrace();
        }
    }

    private void saveDataSharedPre(int id, String name, String email, String phone
            , String work, String specialization) {
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("work", work);
        editor.putString("specialization", specialization);
        editor.commit();
    }

    private boolean checkDataSharedPre(){
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        if(sharedPreferences.contains("name") && sharedPreferences.contains("email")){
            return  true;
        }
        return false;
    }

    private void openHome() {
        progressDialog.dismiss();
        startActivity(new Intent(loginActivity.this,HomeActivity.class));
    }
    private void openHome2() {
        startActivity(new Intent(loginActivity.this,HomeActivity.class));
    }
}
