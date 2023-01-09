package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.DTO.UserInfo;
import com.example.test.DTO.http.MainResponse;
import com.example.test.DTO.http.NonRequest;
import com.example.test.reply.ReplyActivity;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivity instance;
    private String userName;
    HttpService httpService;
    public ImageView userIcon;
    public Button btn;
    private UserInfo userInfo = new UserInfo();
    private LinearLayout View;
    private ListView listView;
    ListViewAdapter adapter;
    public Intent i;
    String userID_s="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Intent intent = new Intent(getApplicationContext(), com.example.test.login.LoginActivity.class);
//        Intent intent = new Intent(getApplicationContext(), com.example.test.post.PostActivity.class);
        startActivity(intent);
        btn = (Button) findViewById(R.id.login_button);
        i = getIntent();
    }

    protected void onStart() {
        super.onStart();
    }


}