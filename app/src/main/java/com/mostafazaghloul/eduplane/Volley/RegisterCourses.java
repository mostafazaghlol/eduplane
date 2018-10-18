package com.mostafazaghloul.eduplane.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mostafazaghloul.eduplane.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class RegisterCourses extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://arabliveksa.com/WebServices/User/RegisterCourses.php";
    private Map<String, String> params;

    public RegisterCourses(String course_id,
                           String course_name,
                           String course_type,
                           String star_at,
                           String endd_at,
                           String user_id,
                           String user_name,
                           String user_phone,
                           String reserv,
                           Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("course_id", course_id);
        params.put("course_name", course_name);
        params.put("course_type", course_type);
        params.put("star_at", star_at);
        params.put("endd_at", endd_at);
        params.put("user_id", user_id);
        params.put("user_name", user_name);
        params.put("user_phone", user_phone);
        params.put("reserv", reserv);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
