package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mostafazaghloul.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class video extends StringRequest {
    private static final String videos_url= Constants.url+"video/courses";
    private Map<String, String> params;

    public video(Response.Listener<String> listener) {
        super(Method.POST, videos_url, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
