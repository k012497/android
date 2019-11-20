package com.example.volleywebapphttptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtID, edtPassword, edtName, edtAge;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtID = findViewById(R.id.edtID);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                String userID = edtID.getText().toString().trim();
                String userPassword = edtPassword.getText().toString().trim();
                String userName = edtName.getText().toString().trim();
                String userAge = edtAge.getText().toString().trim();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.putExtra("userID", edtID.getText().toString());
                                intent.putExtra("userPassword", edtPassword.getText().toString());
                                intent.putExtra("userName", edtName.getText().toString());
                                intent.putExtra("userAge", edtAge.getText().toString());
                                startActivity(intent);
                                toastDisplay("로그인 성공 !");
                            }else{
                                toastDisplay("로그인 실패 !");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, Integer.parseInt(userAge), responseListener); // 자료 다 들어있음. 상대방주소,데이터,데이터랩방식 등
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
        }
    }

    private void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
