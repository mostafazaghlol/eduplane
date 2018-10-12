package com.mostafazaghloul.eduplane.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mostafazaghloul.eduplane.R;
import com.mostafazaghloul.eduplane.Volley.register;
import com.mostafazaghloul.eduplane.Volley.update;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity {
    String name, phone,email,password,password2;
    int id;
    @BindView(R.id.nameAccount)
    EditText EdName;
    @BindView(R.id.emailAccount)
    EditText EdEmail;
    @BindView(R.id.phoneAccount)
    EditText EdPhone;
    @BindView(R.id.passwordAccount)
    EditText EdPassword;
    @BindView(R.id.passwordAccount2)
    EditText EdPassword2;
    
    ImageView view;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        view = (ImageView)findViewById(R.id.backAccount);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getDataFromShared();
    }

    private void getDataFromShared() {
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        id = sharedPreferences.getInt("id",0);
        Log.e("id",String.valueOf(id));
        name = sharedPreferences.getString("name",null);
        email = sharedPreferences.getString("email",null);
        phone = sharedPreferences.getString("phone",null);
        setData();
    }

    private void setData() {
        EdName.setText(name);
        EdEmail.setText(email);
        EdPhone.setText(phone);
    }

    public void save(View view) {
        if(!EdPassword.getText().toString().isEmpty() &&!EdPassword2.getText().toString().isEmpty()){
            if(EdPassword.getText().toString().equals(EdPassword2.getText().toString())){
                password = EdPassword.getText().toString();
                password2 = EdPassword2.getText().toString();
                progressDialog = ProgressDialog.show(this, "أرجو الإنتظار",
                        "يتم تحديث البيانات ", true);
                name = EdName.getText().toString();
                email= EdEmail.getText().toString();
                phone= EdPhone.getText().toString();
                updateUser(id,name,email,phone,password);
            }else{
                Toast.makeText(this, "تأكد من صحة كلمة المرور", Toast.LENGTH_SHORT).show();
            }
        }else{
            EdPassword.setError("ادخل كلمة المرور");
            EdPassword2.setError("ادخل كلمة المرور");
        }
    }

    private void updateUser(int id, String name, String email, String phone, String password) {
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

        update AccountRequest = new update(email, password
                ,name,phone,String.valueOf(id), responseListener);
        RequestQueue queue = Volley.newRequestQueue(AccountActivity.this);
        queue.add(AccountRequest);
    }

    private void getData(JSONObject jsonResponse) {

            try{
                boolean status = jsonResponse.getBoolean("status");
                int code = jsonResponse.getInt("code");
                JSONObject output = jsonResponse.getJSONObject("output");
                if(status&& code == 200){
                    int id = output.getInt("id");
                    String name = output.getString("name");
                    String email = output.getString("email");
                    String phone = output.getString("phone");
                    saveDataSharedPre(id,name,email,phone);
                    openHome();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(this, "لم يتم تحديث البيانات !", Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e1){
                e1.printStackTrace();
            }
    }

    private void saveDataSharedPre(int id, String name, String email, String phone) {
        final SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.commit();
    }

    private void openHome() {
        progressDialog.dismiss();
        startActivity(new Intent(AccountActivity.this,HomeActivity.class));
    }
}
