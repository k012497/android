package com.example.myvideoplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {
    private Button btnRecord, btnFinish;
    private SurfaceView surfaceView;

    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private MediaRecorder mediaRecorder;
    private boolean flag, isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecord = findViewById(R.id.btnRecord);
        btnFinish = findViewById(R.id.btnFinish);
        surfaceView = findViewById(R.id.surfaceView);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        btnRecord.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        // 다이얼로그 창에서 진행하는 퍼미션 라이브러리
        TedPermission.with(this).setPermissionListener(new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                toastDisplay("권한이 허용되었어요.");
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                toastDisplay("녹화를 시작할 수 없어.");
            }
        }).
                setRationaleMessage("녹화를 허용해주실래요 ?").
                setDeniedMessage("권한 거부").
                setPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE).check();

    }

    private void toastDisplay(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("MainActivity","surfaceCreated ");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("MainActivity","surfaceChanged ");
        //surfaceHolder 가 null 이면 리턴해라.
        if(surfaceHolder.getSurface() == null){
            return;
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRecord:
                setButtonEnabled(false, true);
                camera = Camera.open();
                try {
                    if(camera == null){
                        camera.setPreviewDisplay(surfaceHolder);
                        camera.startPreview();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
                    @Override
                    public void run() {
                        toastDisplay("녹화를 시작합니다.");
                        try {
                            mediaRecorder = new MediaRecorder();
                            camera.unlock(); // lock을 풀어야 시작됨
                            mediaRecorder.setCamera(camera);
                            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER); // 촬영 시작음
                            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); // 카메라에서 오는 자료를 받음
                            mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P)); // 해상도
                            mediaRecorder.setOrientationHint(0); // 촬영 각도
                            mediaRecorder.setOutputFile("/sdcard/DCIM/Camera/"  + "test.mp4");
                            mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("/sdcard/DCIM/Camera/test.mp4")));
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                            isRecording = true;
                            flag = true;

                        } catch (IOException e) {
                            e.printStackTrace();
                            btnFinish.callOnClick();
                            toastDisplay("실패");
                        }
                    }
                });

                break;

            case R.id.btnFinish:
                setButtonEnabled(true, false);
                //녹화 기능 중지
                if(isRecording){
                    mediaRecorder.stop();
                    mediaRecorder.release(); // 해제
                }

                // 카메라 기능 중지
                if(camera != null){
                    camera.lock();
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                }
                break;
        }
    }
    private String getVideoFilePath() {

        final File dir = Environment.getExternalStorageDirectory().getAbsoluteFile();
        Log.d("sdg","111");

        String path = dir.getPath() + "/DCIM/Test/";
        File dst = new File(path);
        if(!dst.exists()) {
            Log.d("sdg","222");
            dst.mkdirs();
        }

        Log.d("sdg", path);
        return path + System.currentTimeMillis() + ".mp4";
    }

    private void setButtonEnabled(boolean record, boolean finish) {
        btnRecord.setEnabled(false);
        btnFinish.setEnabled(true);
    }
}
