package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mostafazaghloul.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class pdf extends StringRequest {
    private static final String pdfs_url= Constants.url+"pdf/courses";
    private Map<String, String> params;

    public pdf(Response.Listener<String> listener) {
        super(Method.POST, pdfs_url, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
