package com.eltamiuz.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.eltamiuz.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class all extends StringRequest {
    private static final String pdfs_url= "https://edu-plane.com/edu/api/pdf/courses";
    private Map<String, String> params;

    public all(Response.Listener<String> listener,String dept_id) {
        super(Method.POST, pdfs_url, listener, null);
        params = new HashMap<>();
        params.put("dept_id",dept_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
