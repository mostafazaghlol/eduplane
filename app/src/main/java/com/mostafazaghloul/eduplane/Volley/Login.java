package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mostafazaghloul.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class Login extends StringRequest {
    private static final String LOGIN_REQUEST_URL = Constants.url+"login";
    private Map<String, String> params;

    public Login(String username, String pass, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", username);
        params.put("password", pass);
//        params.put("lang", lang);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
