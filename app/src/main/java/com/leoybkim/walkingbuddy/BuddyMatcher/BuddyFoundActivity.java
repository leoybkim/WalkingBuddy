package com.leoybkim.walkingbuddy.BuddyMatcher;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leoybkim.walkingbuddy.Buddy;
import com.leoybkim.walkingbuddy.R;

/**
 * Created by dmedinag on 04/02/2017.
 */

public class BuddyFoundActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buddy_found);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manageri
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Buddy testbuddy = new Buddy("sdf", "Dani Medina", null);
        Buddy testbuddy2 = new Buddy("sdf", "Dan Kim", null);

        // specify an adapter (see also next example)
        Buddy[] dataset = { testbuddy, testbuddy2 };
        mAdapter = new CardAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
    }

}