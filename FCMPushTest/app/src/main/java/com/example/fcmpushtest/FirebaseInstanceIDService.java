package com.example.fcmpushtest;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

// token을 받는 클래스
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIDService";
    // firebase와 나 사이의 token
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);
        sendRegistrationToServer(token); // 확장성을 위해 일단 만들어 둠. 서버에 정보 보낼 때 사용
    }

    private void sendRegistrationToServer(String token) {
    }
}

