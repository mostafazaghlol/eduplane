package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class searchMyCourses extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://arabliveksa.com/WebServices/User/SearchMyCoursers.php";
    private Map<String, String> params;

    public searchMyCourses(String user_id,
                           Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id", user_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
