package com.example.sin_yunseob.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //ArrayList<String> data;
    //RecyclerView recyclerView;
    //MyAdapter myAdapter;
    //LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test complete
//        data = new ArrayList<>();
//
//        for(int i = 0 ; i < 10 ; i ++){
//            data.add("recycler" + i);
//        }
//
//        recyclerView = findViewById(R.id.recyclerView);
//        myAdapter = new MyAdapter(data, this);
//
//
//
//        recyclerView.setAdapter(myAdapter);

        readUserInfo();


    }

    void readUserInfo(){ //succ
        MyThread myThread = new MyThread();
        myThread.start();
    }
}


