package com.leoybkim.walkingbuddy.Map;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.leoybkim.walkingbuddy.LoginActivity;
import com.leoybkim.walkingbuddy.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Dan on 04/02/2017.
 */

public class DestinationActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ImageView imageView;
    private Button confirm;
    //location of destination
    private LatLng destination, origin;
    Bundle userInfoState;
    String FBid;
    String FBname;
    Bitmap FBbitmap;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            savedInstanceState = getIntent().getBundleExtra("bundle");
            userInfoState = getIntent().getBundleExtra("FB");
            FBid = userInfoState.getString("FBid");
            FBname = userInfoState.getString("FBname");
            FBbitmap = userInfoState.getParcelable("FBpicture");
            Log.d(TAG, "-----------------------------" + FBid);
            Log.d(TAG, "-----------------------------" + FBname);
        }
        Log.d(TAG, "DestinationActivity!");
        setContentView(R.layout.destination_confirmation_activity);

        imageView = (ImageView) findViewById(R.id.directionsImage);
        confirm = (Button) findViewById(R.id.confirmDirectionsButton);

        destination = (LatLng) savedInstanceState.get("destination");
        origin = (LatLng) savedInstanceState.get("origin");


        //measure screen dimensions
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        loadImage(width, height);

        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // CREATE USER ADD TO FIREBASE
                // profile, name, messenger info

            }
        });
    }

    private void loadImage(final int width, final int height) {
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Picasso.with(getApplicationContext())
                        .load(getStaticMapURL(origin, destination, width, height))
                        .into(imageView);
            }
        });

    }

    private String getStaticMapURL(LatLng origin, LatLng destination, int width, int height){
        return String.format("https://maps.googleapis.com/maps/api/staticmap" +
                        "?markers=color:red%%7C%f,%f" +
                        "&markers=color:green%%7C%f,%f" +
                        "&key=%s" +
                        "&size=%dx%d",
                origin.latitude,
                origin.longitude,
                destination.latitude,
                destination.longitude,
                getString(R.string.dan_googleMap_key),
                width,
                height);
    }


}