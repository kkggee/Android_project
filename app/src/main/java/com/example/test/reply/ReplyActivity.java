package com.example.test.reply;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.DTO.UserInfo;
import com.example.test.DTO.http.PostGetRequest;
import com.example.test.DTO.http.PostGetResponse;
import com.example.test.DTO.http.ReplyDeleteRequest;
import com.example.test.DTO.http.ReplyDeleteResponse;
import com.example.test.DTO.http.ReplyEditRequest;
import com.example.test.DTO.http.ReplyEditResponse;
import com.example.test.DTO.http.ReplyRequest;
import com.example.test.DTO.http.ReplyResponse;
import com.example.test.ListViewAdapter;
import com.example.test.ListViewAdapterReply;
import com.example.test.Post;
import com.example.test.R;
import com.example.test.Reply;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReplyActivity extends AppCompatActivity {
    ReplyActivity instance;
    private String userName;
    HttpService httpService;
    boolean mBound = false;
    SimpleDateFormat sdf1;
    Calendar now = Calendar.getInstance();

    ReplyResponse replyResponse = null;

    public LinearLayout tagLayout;
    public TextView post_title, title_tv, post_date, date_tv, post_script, content_tv, post_reply;
    public TextView cmt_userid_tv, cmt_content_tv, cmt_date_tv, tag_tv;
    public EditText comment_et;
    public Button reg_button, reply_edit, reply_delete;
    public LinearLayout comment_layout;
    public LinearLayout reply_detail_layout;
    private UserInfo userInfo;
    public Intent i;
    String rep_content_s = "";
    String userID_s = "";
    String datetime_s = "";
    String title_s = "";
    String content_s = "";
    String post_id;
    String userID_s1 = "";
    private ListView listView;
    ListViewAdapterReply adapter;
    private Context mContext;
    private View convertView;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cmment);
        instance = this;
        i = getIntent();
        userInfo = new UserInfo();
        post_id = i.getStringExtra("post_id");
        userID_s = i.getStringExtra("UserInfo");
        httpService = HttpService.getService();
        sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        listView = (ListView) findViewById(R.id.reply_list);
        adapter = new ListViewAdapterReply(getApplicationContext());
        listView.setAdapter(adapter);

        System.out.println(userID_s);

//        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.reply_item, reply_detail_layout, false);

        this.InitializeView();


        try {
            httpService.getPost(new PostGetRequest(post_id),instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        reg_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                inflater.inflate(R.layout.reply_item, listView, false);
//                datetime_s = sdf1.format(now.getTime());
//                Reply reply = new Reply();
//                InitializeView1();
//
//                reply.userID = userID_s;
//                reply.timestamp = datetime_s;
//                reply.script = comment_et.getText().toString();
//
//
//                try{
//                    httpService.reply(new ReplyRequest(userID_s, reply.script, post_id, datetime_s), ReplyActivity.this);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
////                comment_et.setText("");
////                cmt_content_tv.setText(rep_content_s);
////                cmt_date_tv.setText(datetime_s);
//                comment_et.setText("");
//                cmt_content_tv.setText(reply.script);
//                cmt_date_tv.setText(reply.timestamp);
//            }
//        });
    }

    public void postGetProcess(PostGetResponse postGetResponse) {
        switch (postGetResponse.getCode()) {
            case (code.OK): {
                InitializeView();
                Log.d("postGet", "게시글 로드 성공");
                title_tv.setText(postGetResponse.getTitle());
                String script = postGetResponse.getScript()+"\n\n";
                if(postGetResponse.getDate()!=null){
                    script+="\n날짜: "+postGetResponse.getDate();
                }
                if(postGetResponse.getTime()!=null){
                    script+="\n시간: "+postGetResponse.getTime();
                }
                if(postGetResponse.getPrice()!=null){
                    script+="\n가격: "+postGetResponse.getPrice();
                }
                content_tv.setText(script);
                ArrayList<String> tagList = postGetResponse.getTags();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,0,10,0);
                for(int i = 0; i<tagList.size(); i++){
                    tag_tv = new TextView(ReplyActivity.this);
                    tag_tv.setText(tagList.get(i).split("\t")[0]);
                    tag_tv.setBackgroundColor(Color.parseColor("#"+tagList.get(i).split("\t")[1]));
                    tag_tv.setPadding(16,16,16,16);
                    tag_tv.setLayoutParams(params);
                    tagLayout.addView(tag_tv);
                }
                ArrayList<String> comments = postGetResponse.getComments();
                ArrayList<Reply> testReplyList = new ArrayList();
                Reply reply;
                for(int i = 0; i<comments.size();i++){

                    reg_button.setOnClickListener(new View.OnClickListener() {   // 댓글등록
                        @Override
                        public void onClick(View view) {
                            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            inflater.inflate(R.layout.reply_item, listView, false);
                            datetime_s = sdf1.format(now.getTime());
                            Reply reply = new Reply();
                            InitializeView1();

                            reply.userID = userID_s;
                            reply.timestamp = datetime_s;
                            reply.script = comment_et.getText().toString();
                            testReplyList.add(reply);
                            adapter.setItems(testReplyList);
                            adapter.notifyDataSetChanged();

                            try{
                                httpService.reply(new ReplyRequest(userID_s, reply.script, post_id, datetime_s), ReplyActivity.this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            comment_et.setText("");
                            cmt_content_tv.setText(reply.script);
                            cmt_date_tv.setText(reply.timestamp);
                        }
                    });
//                    InitializeView1();
                    reply = new Reply();

                    reply.userID = comments.get(i).split("\t")[0];
                    reply.script = comments.get(i).split("\t")[1];
                    reply.timestamp = comments.get(i).split("\t")[2];
                    testReplyList.add(reply);
                    adapter.setItems(testReplyList);
                    adapter.notifyDataSetChanged();

                    userID_s1 = reply.userID;
                    String reply_id = "3";

                    if (userID_s1.equals(userID_s)) {
                        LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        inflater1.inflate(R.layout.reply_item, listView, false);
                        InitializeView1();
//                        reply_edit.setVisibility(View.VISIBLE);
//                        reply_delete.setVisibility(View.VISIBLE);
//                        reply_edit.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                datetime_s = sdf1.format(now.getTime());
//                                rep_content_s = comment_et.getText().toString();
//                                try{
//                                    httpService.replyEdit(new ReplyEditRequest(reply_id, rep_content_s, datetime_s), ReplyActivity.this);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                comment_et.setText("");
//                                cmt_content_tv.setText(rep_content_s);
//                                cmt_date_tv.setText(datetime_s);
//                            }
//                        });
//
//                        reply_delete.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                try{
//                                    httpService.replyDelete(new ReplyDeleteRequest(reply_id), ReplyActivity.this);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                reply_detail_layout.setBackgroundColor(Color.parseColor("#00ff0000"));
//                                reply_detail_layout.removeAllViews();
//                            }
//                        });
                    }
                }
                break;
            }
            case (code.SQLError): {
                System.out.println("게시글 로드 실패");
                Toast.makeText(getApplicationContext(), "로그인 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
            case (code.httpError): {
                System.out.println("게시글 로드 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
            case (code.UnknownError): {
                System.out.println("게시글 로드 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
        }
    }

    public void replyEditProcess(ReplyEditResponse replyEditResponse) {
        switch (replyEditResponse.getCode()) {
            case (code.OK): {
                Log.d("replyEdit", "댓글 수정 성공");
                Toast.makeText(getApplicationContext(), "댓글 수정", Toast.LENGTH_SHORT).show();

                break;
            }
            case (code.SQLError): {
                System.out.println("댓글 수정 실패");
                Toast.makeText(getApplicationContext(), "로그인 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
            case (code.httpError): {
                System.out.println("댓글 수정 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
            case (code.UnknownError): {
                System.out.println("댓글 수정 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
        }
    }

    public void replyDeleteProcess(ReplyDeleteResponse replyDeleteResponse) {
        switch (replyDeleteResponse.getCode()) {
            case (code.OK): {
                Log.d("replyDelete", "댓글 삭제 성공");
                Toast.makeText(getApplicationContext(), "댓글 삭제", Toast.LENGTH_SHORT).show();

                break;
            }
            case (code.SQLError): {
                System.out.println("댓글 삭제 실패");
                Toast.makeText(getApplicationContext(), "로그인 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
            case (code.httpError): {
                System.out.println("댓글 삭제 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
            case (code.UnknownError): {
                System.out.println("댓글 삭제 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
        }
    }

    public void replyProcess(ReplyResponse replyResponse) {
        switch (replyResponse.getCode()) {
            case (code.OK): {
                Log.d("replyMessage", "댓글 등록 성공");
                Toast.makeText(getApplicationContext(), "댓글 등록", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
            case (code.SQLError): {
                System.out.println("댓글 등록 실패");
                Toast.makeText(getApplicationContext(), "로그인 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
            case (code.httpError): {
                System.out.println("댓글 등록 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
            case (code.UnknownError): {
                System.out.println("댓글 등록 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                comment_et.requestFocus();
                break;
            }
        }
    }

    public void InitializeView() {
        title_tv = (TextView) findViewById(R.id.title_tv);
        date_tv = (TextView) findViewById(R.id.date_tv);
        content_tv = (TextView) findViewById(R.id.content_tv);
        comment_et = (EditText) findViewById(R.id.comment_et);
        reg_button = (Button) findViewById(R.id.reg_button);
        comment_layout = (LinearLayout) findViewById(R.id.comment_layout);
        reply_detail_layout = (LinearLayout) findViewById(R.id.reply_detail_layout);
        tagLayout = (LinearLayout) findViewById(R.id.tagLayout);

//        cmt_userid_tv = comment_layout.findViewById(R.id.cmt_userid_tv);
//        cmt_content_tv = comment_layout.findViewById(R.id.cmt_content_tv);
//        cmt_date_tv = comment_layout.findViewById(R.id.cmt_date_tv);
    }
    public void InitializeView1() {
        cmt_userid_tv = (TextView) findViewById(R.id.cmt_userid_tv);
        cmt_content_tv = (TextView) findViewById(R.id.cmt_content_tv);
        cmt_date_tv = (TextView) findViewById(R.id.cmt_date_tv);
//        reply_edit = (Button) findViewById(R.id.reply_edit);
//        reply_delete = (Button) findViewById(R.id.reply_delete);
    }
}
