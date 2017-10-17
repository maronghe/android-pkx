package com.example.mpc.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "http";

    private Button get_data_button;

    private Button kaideng_button;
    private Button chumi_button;
    private Button tingzhi_button;


    private TextView tempTextView;

    private TextView humTextView;

    private TextView yaliTextView;

    private String hum = "";
    private String temp = "";
    private String yali = "";

    //servlet 路径  此处IP需要查询自己本机的 cmd - ipconfig -all
    private  String baseURL = "http://9.112.87.210:8080/IntelligentBucket/login_app.app_action_get_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_data_button = (Button)findViewById(R.id.button);
        get_data_button.setOnClickListener(this);


        kaideng_button = (Button)findViewById(R.id.button2);
        kaideng_button.setOnClickListener(this);

        chumi_button = (Button)findViewById(R.id.button3);
        chumi_button.setOnClickListener(this);

        tingzhi_button = (Button)findViewById(R.id.button4);
        tingzhi_button.setOnClickListener(this);

        tempTextView = (TextView) findViewById(R.id.temp);

        humTextView = (TextView) findViewById(R.id.hum);

        yaliTextView = (TextView) findViewById(R.id.yali);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                sendRequest();break;
            case R.id.button2:
                kaideng();break;
            case R.id.button3:
                chumi();break;
            case R.id.button4:
                tingzhi();break;

        }
    }

    private void tingzhi() {

        Thread thread =  new Thread(runnable4);
        thread.start();
    }

    private void chumi() {

        Thread thread =  new Thread(runnable3);
        thread.start();
    }

    private void kaideng() {

        Thread thread =  new Thread(runnable2);
        thread.start();
    }


    private void sendRequest() {

        Thread thread =  new Thread(runnable);
        thread.start();
        try {
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        humTextView.setText(hum);
        tempTextView.setText(temp);
        yaliTextView.setText(yali);
    }

    private void showDatas(StringBuffer sb) {
        String str = sb.toString();

        String[] strArray = str.split(",");

        if(strArray.length == 3){
            hum  = strArray[1].split("=")[1]+"%";
            temp = strArray[0].split("=")[1]+"℃";
            yali = strArray[2].split("=")[1]+"N";
        }

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            HttpURLConnection http = null;
            try {
                URL url = new URL(baseURL);

                http = (HttpURLConnection)url.openConnection();

//                http.setDoInput(true);
//                http.setDoOutput(true);
                http.setRequestMethod("GET");
                http.setConnectTimeout(10*1000);
                int responseCode = http.getResponseCode();
                System.out.print("responseCode:"+responseCode);
                if(responseCode == 200){
                    BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line ;
                    while ((line = rd.readLine()) != null) {
//                System.out.println(line);
                        sb.append(line);
                    }
                    rd.close();

                    showDatas(sb);

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };



    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            HttpURLConnection http = null;
            try {
                //set new url
                String baseURL2 = "22222222";
                URL url = new URL(baseURL2);

                http = (HttpURLConnection)url.openConnection();

//                http.setDoInput(true);
//                http.setDoOutput(true);
                http.setRequestMethod("GET");
                http.setConnectTimeout(10*1000);
                int responseCode = http.getResponseCode();
                System.out.print("responseCode:"+responseCode);

                if(responseCode == 200)
                    System.out.println("success");
                else
                    System.out.println("error");


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            HttpURLConnection http = null;
            try {
                //set new url
                String baseURL2 = "333333";
                URL url = new URL(baseURL2);

                http = (HttpURLConnection)url.openConnection();

//                http.setDoInput(true);
//                http.setDoOutput(true);
                http.setRequestMethod("GET");
                http.setConnectTimeout(10*1000);
                int responseCode = http.getResponseCode();
                System.out.print("responseCode:"+responseCode);
                if(responseCode == 200)
                    System.out.println("success");
                else
                    System.out.println("error");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable runnable4 = new Runnable() {
        @Override
        public void run() {
            HttpURLConnection http = null;
            try {
                //set new url
                String baseURL2 = "44444";
                URL url = new URL(baseURL2);

                http = (HttpURLConnection)url.openConnection();

//                http.setDoInput(true);
//                http.setDoOutput(true);
                http.setRequestMethod("GET");
                http.setConnectTimeout(10*1000);
                int responseCode = http.getResponseCode();
                System.out.print("responseCode:"+responseCode);
                if(responseCode == 200)
                    System.out.println("success");
                else
                    System.out.println("error");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
