package com.dianatorres.icesi.examen1_dianatorres;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 11;
    private GoogleMap mMap;
    private LocationManager manager;
    private Marker me;

    private Location currentLocation;
    private LatLng currentLocationLatLng;



    private Button btn_canjeMaps;
    private Button btn_preguntasMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);


//        //Universidad ICESI
//        LatLng u=new LatLng(3.341750,-76.529688);
//        mMap.addMarker(new MarkerOptions().position(u).title("ICESI").snippet("Universidad"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(u));

        //Biblioteca
        LatLng bi1 = new LatLng(3.341946, -76.530087);
        LatLng bi2 = new LatLng(3.341946, -76.529808);
        LatLng bi3 = new LatLng(3.341657, -76.529792);
        LatLng bi4 = new LatLng(3.341651, -76.530087);
        LatLng bi5 = new LatLng(3.341946, -76.530092);


        mMap.addPolyline(new PolylineOptions().add(bi1, bi2, bi3, bi4, bi5));




        //Edificio C
        LatLng sa1 = new LatLng(3.341258, -76.530428);
        LatLng sa2 = new LatLng(3.341231, -76.529870);
        LatLng sa3 = new LatLng(3.341044, -76.529881);
        LatLng sa4 = new LatLng(3.341081, -76.530433);
        LatLng sa5 = new LatLng(3.341258, -76.530422);

        mMap.addPolyline(new PolylineOptions().add(sa1, sa2, sa3, sa4, sa5));


        //Edificio D
        LatLng ed1 = new LatLng(3.341035, -76.530479);
        LatLng ed2 = new LatLng(3.341014, -76.529943);
        LatLng ed3 = new LatLng(3.340768, -76.529948);
        LatLng ed4 = new LatLng(3.340800, -76.530479);
        LatLng ed5 = new LatLng(3.341035, -76.530484);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ed1));

        mMap.addPolyline(new PolylineOptions().add(ed1, ed2, ed3, ed4, ed5));





        //Agregar el listener de ubicacion
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.e(">>>","LAT: "+location.getLatitude()+ " , LONG: "+location.getLongitude());

                if(me != null){
                    me.remove();
                }
                me = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title("Ubicación actual")

                );
                currentLocationLatLng=new LatLng(me.getPosition().latitude,me.getPosition().longitude);
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16));

                checkUserLocation();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });




    }


    public void checkUserLocation() {





        //Biblioteca
        if (currentLocationLatLng.latitude >= 3.341651 && currentLocationLatLng.longitude >=-76.530087
                && currentLocationLatLng.latitude <= 3.341946 && currentLocationLatLng.longitude <=-76.529808 ) {

            btn_canjeMaps = (Button) findViewById(R.id.btn_canjeMaps);
            btn_canjeMaps.setVisibility(View.VISIBLE);
            btn_canjeMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MapsActivity.this, CanjeActivity.class);
                    i.putExtra("sumarPuntos",0+"");
                    startActivity(i);
                }
            });

        }   //Edificio C --> preguntas faciles
        else if(currentLocationLatLng.latitude>= 3.341044 && currentLocationLatLng.longitude>=-76.530428
                && currentLocationLatLng.latitude<= 3.341258 && currentLocationLatLng.longitude<=-76.529881){


            btn_preguntasMaps = (Button) findViewById(R.id.btn_preguntasMaps);
            btn_preguntasMaps.setVisibility(View.VISIBLE);
            btn_preguntasMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MapsActivity.this, PreguntasActivity.class);
                    i.putExtra("dificultadPreguntas","facil");
                    startActivity(i);
                }
            });

        }
        //Edificio D --> preguntas dificiles
        else if(currentLocationLatLng.latitude>= 3.340768 && currentLocationLatLng.longitude>=-76.530484
                && currentLocationLatLng.latitude<=3.341035 && currentLocationLatLng.longitude<=-76.529943){

            btn_preguntasMaps = (Button) findViewById(R.id.btn_preguntasMaps);
            btn_preguntasMaps.setVisibility(View.VISIBLE);
            btn_preguntasMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MapsActivity.this, PreguntasActivity.class);
                    i.putExtra("dificultadPreguntas","dificil");
                    startActivity(i);
                }
            });

        }
        else{
            btn_canjeMaps=(Button) findViewById(R.id.btn_canjeMaps);
            btn_canjeMaps.setVisibility(View.INVISIBLE);

            btn_preguntasMaps = (Button) findViewById(R.id.btn_preguntasMaps);
            btn_preguntasMaps.setVisibility(View.INVISIBLE);
        }
    }



}
