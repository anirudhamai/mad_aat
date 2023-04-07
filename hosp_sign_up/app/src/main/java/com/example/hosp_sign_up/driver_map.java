package com.example.hosp_sign_up;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.hosp_sign_up.databinding.ActivityDriverMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class driver_map extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private ActivityDriverMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityDriverMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String result1=getIntent().getStringExtra("u_cor");
        String res1[]=result1.split("\\+");
        LatLng source = new LatLng(Double.valueOf(res1[0]),Double.valueOf(res1[1]));
        mMap.addMarker(new MarkerOptions().position(source).title("User"));

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(12.95484041, 77.57318955);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Hospital location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}