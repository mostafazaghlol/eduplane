package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class searchCourseSubject extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://atls.sa/webService/SearchCourseSubjects.php";
    private Map<String, String> params;

    public searchCourseSubject(String course_id,
                               Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("course_id", course_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
