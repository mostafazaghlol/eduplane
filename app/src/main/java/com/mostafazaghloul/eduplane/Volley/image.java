package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mostafazaghloul.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class image extends StringRequest {
    private static final String images_url= Constants.url+"image/courses";
    private Map<String, String> params;

    public image(Response.Listener<String> listener) {
        super(Method.POST, images_url, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}