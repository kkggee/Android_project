package com.example.test.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.DTO.UserInfo;
import com.example.test.DTO.http.MainResponse;
import com.example.test.DTO.http.NonRequest;
import com.example.test.ListViewAdapter;
import com.example.test.MainActivity;
import com.example.test.Post;
import com.example.test.R;
import com.example.test.calendar.CalendarActivity;
import com.example.test.post.PostActivity;
import com.example.test.reply.ReplyActivity;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;

import java.util.ArrayList;

public class MainSceneActivity  extends AppCompatActivity {

    MainSceneActivity instance;
    private String userName;
    HttpService httpService;
    public Button btn;
    private UserInfo userInfo = new UserInfo();
    private LinearLayout View;
    private ListView listView;
    ListViewAdapter adapter;
    public Intent i;
    String userID_s = "";
    Button post_register, calendar_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        post_register = (Button) findViewById(R.id.post_add);
        calendar_View = (Button) findViewById(R.id.calendar_View);
        i = getIntent();
        userInfo = new UserInfo();
        instance = this;
        httpService = HttpService.getService();
        listView = (ListView) findViewById(R.id.post_item);
        adapter = new ListViewAdapter(getApplicationContext());
        listView.setAdapter(adapter);
        userID_s = i.getStringExtra("UserInfo");

        post_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra("UserInfo",userID_s);
                startActivity(intent);
            }
        });

        calendar_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                intent.putExtra("UserInfo",userID_s);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Bind to LocalService
        View = (LinearLayout) findViewById(R.id.testLayout);
        MainResponse mainResponse;
        View.removeAllViews();
        try {
            mainResponse = httpService.getPostList(new NonRequest(userInfo.getUserID()),instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            userInfo = (UserInfo) getIntent().getExtras().get("userInfo");
        } catch (NullPointerException e){
            userInfo = new UserInfo();
        }

    }

    public void mainProcess(MainResponse mainResponse) {
        switch (mainResponse.getCode()) {
            case (code.OK): {
                Log.d("mainMessage", "게시글 로드 성공");

                    ArrayList<String> postList = mainResponse.getPostList();
                    ArrayList<Post> testPostList = new ArrayList();
                    Post post;
                    for(int i = 0; i<postList.size(); i++) {

                        Log.d(Integer.toString(i), postList.get(i));
                        post = new Post();

                        post.id = postList.get(i).split("\t")[0];
                        post.userid = postList.get(i).split("\t")[1];
                        post.title = postList.get(i).split("\t")[2];
                        post.post = postList.get(i).split("\t")[3];
                        listView.setId(Integer.valueOf(postList.get(i).split("\t")[0]));

                        // item 클릭했을 때
                        listView.setOnItemClickListener((AdapterView<?> a_parent, android.view.View a_view, int a_position, long a_id)->{
                            final Post item = (Post)adapter.getItem(a_position);
                            Intent intent = new Intent(this, ReplyActivity.class);
                            intent.putExtra("post_id", item.id);
                            intent.putExtra("UserInfo",userInfo.getUserID());
                            startActivity(intent);
                        });

                        testPostList.add(post);
                        adapter.setItems(testPostList);
                        adapter.notifyDataSetChanged();
                    }
                break;
            }
            case (code.SQLError): {
                System.out.println("게시글 로드 실패1");
                Toast.makeText(getApplicationContext(), "로그인 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.httpError): {
                System.out.println("게시글 로드 실패2");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.UnknownError): {
                System.out.println("게시글 로드 실패3");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }


}
