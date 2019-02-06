package com.eltamiuz.eduplane.Activities;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.eltamiuz.eduplane.R;
import com.eltamiuz.eduplane.Volley.register;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    @BindView(R.id.EdEmailSignup)
    EditText EdEmail;
    @BindView(R.id.EdNameSignup)
    EditText EdName;
    @BindView(R.id.EdSpecialSignup)
    EditText EdSpecial;
    @BindView(R.id.EdPasswordSignup)
    EditText EdPassword;
    @BindView(R.id.EdPasswordSignup2)
    EditText EdPassword2;
    @BindView(R.id.EdGovernateSignup)
    EditText EdGovernate;
    @BindView(R.id.EdPhoneSignup)
    EditText EdPhone;
    @BindView(R.id.EdCodeConfirm)
    EditText EdCodeConfirm;
    @BindView(R.id.EdAdministrationSignup)
    EditText EdAdmin;

    String email,name,special,password,governate,phone;
    SharedPreferences.Editor editor;
    private ProgressDialog progressDialog2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
    }

    public void signupFinished(View view) {
        if (initEditText()){
            progressDialog2 = ProgressDialog.show(this, "أرجو الإنتظار",
                    "يتم التسجيل ", true);
            signup(email
            ,name, special,password,governate,phone);
        }
    }

    private void signup(String email, String name,
                        String special, String password,
                        String governate, String phone) {
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

        register RigsterRequest = new register(email, password
                ,name,phone,special,governate, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
        queue.add(RigsterRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                progressDialog2.dismiss();
                //openHome();
            }
        });
    }

    private void getData(JSONObject jsonResponse) {
        try{
            boolean status = jsonResponse.getBoolean("status");
            int code = jsonResponse.getInt("code");
            JSONObject output = jsonResponse.getJSONObject("output");
            if(status){
                int id = output.getInt("id");
                String name = output.getString("name");
                String email = output.getString("email");
                String phone = output.getString("phone");
                String work = output.getString("work");
                String specialization = output.getString("specialization");
                saveDataSharedPre(id,name,email,phone,work,specialization);
                openHome();
            }else{
                progressDialog2.dismiss();
                if(output.has("email")){
                    Toast.makeText(this, getString(R.string.errorEmail), Toast.LENGTH_LONG).show();
                    EdEmail.setError(getString(R.string.errorEmail));
                }
                if(output.has("phone")){
                    Toast.makeText(this, getString(R.string.errorPhone), Toast.LENGTH_LONG).show();
                    EdPhone.setError(getString(R.string.errorPhone));
                }
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
    private void openHome() {
        progressDialog2.dismiss();
        startActivity(new Intent(SignupActivity.this,HomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignupActivity.this,loginActivity.class));
        finish();
    }
    private boolean initEditText() {
        if(!EdEmail.getText().toString().isEmpty()) {
            email = EdEmail.getText().toString().trim();
        }else{
            EdEmail.setError(getString(R.string.email));
            return false;
        }
        if(!EdEmail.getText().toString().contains("@")){
            EdEmail.setError("هذا البريد خطأ ");
            return false;
        }
        if(!EdPassword.getText().toString().isEmpty()) {
            password = EdPassword.getText().toString();
        }else{
            EdPassword.setError(getString(R.string.passworderror));
            return false;
        }
        if(!EdGovernate.getText().toString().isEmpty()) {
            governate = EdGovernate.getText().toString();
        }else{
            EdGovernate.setError(getString(R.string.governateerror));
            return false;
        }
        if(!EdSpecial.getText().toString().isEmpty()) {
            special = EdSpecial.getText().toString();
        }else{
            EdSpecial.setError(getString(R.string.specialerror));
            return false;
        }
        if(!EdName.getText().toString().isEmpty()) {
            name = EdName.getText().toString();
        }else{
            EdName.setError(getString(R.string.nameerror));
            return false;
        }
        if(!EdPhone.getText().toString().isEmpty()) {
            phone = EdPhone.getText().toString();
        }else{
            EdPhone.setError(getString(R.string.phoneerror));
            return false;
        }
        if(!EdAdmin.getText().toString().isEmpty()) {
            //phone = EdPhone.getText().toString();
        }else{
            EdAdmin.setError(getString(R.string.Adminerror));
            return false;
        }
        if(!EdCodeConfirm.getText().toString().isEmpty()) {
            String code = EdCodeConfirm.getText().toString();
            if(!code.equals("0102030405")){
                EdCodeConfirm.setError(getString(R.string.codeErrorcorrect));
                return false;
            }
        }else{
            EdCodeConfirm.setError(getString(R.string.codeError));
            return false;
        }

        String pass1,pass2;
        pass1 = EdPassword.getText().toString();
        pass2 = EdPassword2.getText().toString();
        if(!pass1.equals(pass2)){
            EdPassword2.setError("تـأكد من كلمة المرور !");
            return false;
        }
        return  true;
    }
}
