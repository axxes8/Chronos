package com.team6.chronos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class QuickView extends AppCompatActivity {

    // Variables for RecyclerView.
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AlertAdapter adapter;
    private List<Alert> testList = new ArrayList<Alert>(); // Stopgap measure

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.AlertRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new AlertAdapter(this, testList);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

}
