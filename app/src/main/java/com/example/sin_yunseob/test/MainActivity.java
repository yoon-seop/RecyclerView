package com.example.sin_yunseob.test;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //ArrayList<String> data;
    RecyclerView recyclerView;
    TextView textView_login;
    ImageView imageView_photo;
    MyAdapter myAdapter;
    LinearLayoutManager linearLayoutManager;
    String TAG = "MainActivity";
    ArrayList<UserInfo> userInfo;
    Thread worker;
    public Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView_photo = findViewById(R.id.imageView_photo);
        textView_login = findViewById(R.id.textView_loginNama);
        recyclerView = findViewById(R.id.recyclerView);

        readUserInfo();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                userInfo = (ArrayList<UserInfo>) msg.obj;

                Glide.with(MainActivity.this).load(userInfo.get(0).getUser_image_url()).into(imageView_photo);
                textView_login.setText(userInfo.get(0).getUser_name());


                myAdapter = new MyAdapter(userInfo, MainActivity.this);
                recyclerView.setAdapter(myAdapter);
            }
        };


    }

    void readUserInfo(){ //succ
        worker = new Thread(new Runnable() {
            HttpURLConnection httpURLConnection;
            String address = "https://api.github.com/users/JakeWharton/repos";
            String str, receiveMsg;
            ArrayList<UserInfo> userInfo = new ArrayList<UserInfo>();

            @Override
            public void run() {
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    httpURLConnection.setRequestMethod("GET");

                    if(httpURLConnection.getResponseCode() == httpURLConnection.HTTP_OK){
                        InputStreamReader tmp = new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8");
                        BufferedReader reader = new BufferedReader(tmp);
                        StringBuffer buffer = new StringBuffer();
                        //read user info
                        while ((str = reader.readLine()) != null) {
                            buffer.append(str);
                        }
                        receiveMsg = buffer.toString();
                        Log.i("receiveMsg : ", receiveMsg);
                        reader.close();

                        //json parse
                        JSONArray jsonArray = new JSONArray(receiveMsg);
                        String login = jsonArray.getJSONObject(0).getJSONObject("owner").opt("login").toString();
                        String avatar_url = jsonArray.getJSONObject(0).getJSONObject("owner").opt("avatar_url").toString();

                        //Log.d(TAG, "rundddc: " + name);

                        Log.d(TAG, "hello");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String name = jsonArray.getJSONObject(i).opt("name").toString();
                            String description = jsonArray.getJSONObject(i).opt("description").toString();
                            String stargazers_count = jsonArray.getJSONObject(i).opt("stargazers_count").toString();
                            userInfo.add(new UserInfo(login,avatar_url,name,description,stargazers_count));
                        }

                        for(int i = 0 ; i < userInfo.size(); i ++){
                            Log.d(TAG, "UserInfo: " + userInfo.get(i).getPost_name());
                        }

//                        Looper.prepare();
                        Message message = Message.obtain();
                        message.obj = userInfo;
                        mHandler.sendMessage(message);
//                        Looper.loop();



                    }else{
                        Log.d(TAG, "error" + " " + httpURLConnection.HTTP_INTERNAL_ERROR);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        worker.start();
    }


}


