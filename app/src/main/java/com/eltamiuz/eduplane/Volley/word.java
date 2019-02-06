package com.eltamiuz.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.eltamiuz.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class word extends StringRequest {
    private static final String words_url= Constants.url+"word/courses";
    private Map<String, String> params;

    public word(Response.Listener<String> listener) {
        super(Method.POST, words_url, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

