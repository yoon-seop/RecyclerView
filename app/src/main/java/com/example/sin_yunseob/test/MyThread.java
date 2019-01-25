package com.example.sin_yunseob.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.UiThread;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MyThread extends Thread{
    String TAG = "RESULT";
    HttpURLConnection httpURLConnection;
    String address = "https://api.github.com/users/JakeWharton/repos";
    String str, receiveMsg;
    ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();


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
                    userInfos.add(new UserInfo(login,avatar_url,name,description,stargazers_count));
                }

                for(int i = 0 ; i < userInfos.size(); i ++){
                    Log.d(TAG, "UserInfo: " + userInfos.get(i).getPost_name());
                }

                Looper.prepare();
                Handler handler = new Handler(Looper.getMainLooper());
                Message message = Message.obtain();
                message.obj = userInfos;
                handler.sendMessage(message);
                Looper.loop();



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



}
