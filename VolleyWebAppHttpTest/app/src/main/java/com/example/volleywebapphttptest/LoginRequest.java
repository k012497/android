package com.example.volleywebapphttptest;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://soproject.dothome.co.kr/Login.php";
    private Map<String, String> map;
    private String userID;
    private String userPassward;

    public LoginRequest(String userID, String userPassward, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); // getParams()를 부름
        map = new HashMap<>();
        this.userID = userID;
        this.userPassward = userPassward;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        map.put("userID", this.userID);
        map.put("userPassword", this.userPassward);

        return map;
    }
}
