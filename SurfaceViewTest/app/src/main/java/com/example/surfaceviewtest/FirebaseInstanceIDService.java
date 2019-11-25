package com.example.surfaceviewtest;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseInstanceIDService";

    @SuppressLint("LongLogTag")
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {

    }
}
