package com.example.googlemap13_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    GroundOverlayOptions cctvMark;
    ArrayList<GroundOverlayOptions> cctvList = new ArrayList<GroundOverlayOptions>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        gMap = googleMap;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                cctvMark = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.star_with_eye)).position(latLng, 100f, 100f);

                cctvList.add(cctvMark);

                for(int i = 0 ; i < cctvList.size() ; i++){
                    gMap.addGroundOverlay(cctvList.get(i));
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성 지도");
        menu.add(0, 2, 0, "일반 지도");
        menu.add(0, 3, 0, "바로전 CCTV 지우기");
        menu.add(0, 4, 0, "모든 CCTV 지우기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case 2:
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                if(cctvList.size() == 0){
                    Toast.makeText(this, "지울 마커가 없습니다.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                gMap.clear();
                cctvList.remove(cctvList.size() - 1);
                for(int i=0; i<cctvList.size();i++)
                    gMap.addGroundOverlay(cctvList.get(i));
                return true;
            case 4:
                gMap.clear();
                cctvList.removeAll(cctvList);
                return true;
        }
        return false;
    }

}
