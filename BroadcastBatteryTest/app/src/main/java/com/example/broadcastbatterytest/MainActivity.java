package com.example.broadcastbatterytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvBattery;
    private ImageView ivBattery;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBattery = findViewById(R.id.ivBattery);
        tvBattery = findViewById(R.id.tvBattery);

        callBroadCastReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 아무 인텐트나 받지 않고 필터링 할거야 - 배터리 변화만 받을거야
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        // 리시버 기능 설정
        registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 리시버 해제
        unregisterReceiver(broadcastReceiver);
    }

    // 브로드캐스트 리시버를 받고자 하는 앱은 브로드캐스트 리시버 객체를 만들어야 한다
    private void callBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            // 여기서 정보를 받음 - 처리하면 됨
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                    int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                    tvBattery.setText(remain + "%");

                    if (remain >= 90)
                        ivBattery.setImageResource(R.drawable.battery_full);
                    else if (remain >= 70)
                        ivBattery.setImageResource(R.drawable.battery_80);
                    else if (remain >= 50)
                        ivBattery.setImageResource(R.drawable.battery_50);
                    else if (remain >= 10)
                        ivBattery.setImageResource(R.drawable.battery_20);
                    else
                        ivBattery.setImageResource(R.drawable.battery_alert);

                    int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                    switch (plug){
                        case 0:
                            tvBattery.append("\n전원 연결 : ❌");
                            break;

                        case BatteryManager.BATTERY_PLUGGED_AC:
                            tvBattery.append("\n전원 연결 : 어댑터 연결 🔌");
                            ivBattery.setImageResource(R.drawable.battery_charging);
                            break;

                        case BatteryManager.BATTERY_PLUGGED_USB:
                            tvBattery.append("\n전원 연결 : usb 연결");
                            ivBattery.setImageResource(R.drawable.battery_charging);
                            break;
                    }
                }
            }
        };
    }

}
