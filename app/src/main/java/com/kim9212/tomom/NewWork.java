package com.kim9212.tomom;

import androidx.appcompat.app.AppCompatActivity;


import android.app.FragmentManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class NewWork extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_work);

        fragmentManager=getFragmentManager();
        mapFragment=(MapFragment)fragmentManager.findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location= new LatLng(37.511548, 127.098722);
        MarkerOptions markerOptions= new MarkerOptions();
        markerOptions.title("가상사무실");
        markerOptions.snippet("010-1234-5678");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));
    }
}