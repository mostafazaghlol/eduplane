package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mostafazaghloul.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class register extends StringRequest {
    private static final String Rigster_REQUEST_URL = Constants.url+"register";
    private Map<String, String> params;

    public register(String email,
                    String pass,
                    String name,
                    String phone,
                    String specialization,
                    String work,
                    Response.Listener<String> listener) {
        super(Method.POST, Rigster_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", pass);
        params.put("name", name);
        params.put("phone", phone);
        params.put("specialization", specialization);
        params.put("work", work);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
