package com.example.volleywebapphttptest;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://soproject.dothome.co.kr/Register.php";
    private Map<String, String> map;
    private String userID;
    private String userPassward;
    private String userName;
    private int userAge;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge,  Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); // getParams()를 부름
        map = new HashMap<>();
        this.userID = userID;
        this.userPassward = userPassword;
        this.userName = userName;
        this.userAge = userAge;
    }

    // 반드시 Map으로 리턴!
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        map.put("userID", this.userID);
        map.put("userPassword", this.userPassward);
        map.put("userName", this.userName);
        map.put("userAge", String.valueOf(this.userAge));

        return map;
    }
}
