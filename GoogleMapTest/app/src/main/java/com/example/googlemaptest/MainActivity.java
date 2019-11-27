package com.example.googlemaptest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        mapFragment=(MapFragment)fragmentManager.findFragmentById(R.id.fgGoogleMap);
        mapFragment.getMapAsync(this);



    }

    //구글맵이 준비되면 호출되는 콜백함수임
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //구글맵에 가서 위도 경도를 가져올것  37.562349, 127.035154
        LatLng location = new LatLng(37.562349, 127.035154);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("미래능력개발교육원");
        //세부적인 내용
        markerOptions.snippet("안웹앱8기가 머물던곳");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
//        켜질때 카메라 살짝 멀리서 서서히 오듯 이동하는것이 보임
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));
    }
}
