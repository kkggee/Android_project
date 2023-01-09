package com.example.test.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.DTO.UserInfo;
import com.example.test.DTO.http.LoginRequest;
import com.example.test.DTO.http.LoginResponse;
import com.example.test.R;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;


public class LoginActivity extends AppCompatActivity {
    LoginActivity instance;
    private String userName;
    HttpService httpService;
    boolean mBound = false;
    private EditText login_user_id, login_user_password;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        httpService = HttpService.getService();
        setContentView(R.layout.login);

        final Button loginBt = (Button)findViewById(R.id.login_button);        // 로그인 버튼
        final Button signBt = (Button) findViewById(R.id.login_signup);
//        final ImageButton signupJoinBt = (ImageButton)findViewById(R.id.signup_join_activity_btn); // 회원가입 버튼

        login_user_id = findViewById(R.id.login_id);
        login_user_password = findViewById(R.id.login_password);

        // 로그인화면에서 회원가입 버튼 클릭 시 회원가입 창으로 전환
        signBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.test.signup.RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 로그인
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo usIn = new UserInfo();
                String userID = login_user_id.getText().toString();
                String userPassword = login_user_password.getText().toString();

                LoginResponse loginResponse = null;
                try {
                    loginResponse = httpService.login(new LoginRequest(userID, userPassword),instance);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loginProcess(LoginResponse loginResponse) {
        switch (loginResponse.getCode()) {
            case (code.OK): {
                Log.d("loginMessage", "사용자 로그인 성공");
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                UserInfo userInfo = new UserInfo();
                userInfo.setUserID(loginResponse.getUserId());
                userInfo.setUserIcon((loginResponse.getUserIcon()));
                Log.d("UserID",userInfo.getUserID());
//                userInfo.setUserName(loginResponse.getNickname());
                Intent intent = new Intent(getApplicationContext(), com.example.test.main.MainSceneActivity.class);
                intent.putExtra("userInfo",userInfo);
                startActivity(intent);  //charoom화면을 띄운다.
                finish();   //현재 액티비티 종료
                break;
            }
            case (code.SQLError): {
                System.out.println("사용자 로그인 실패");
                Toast.makeText(getApplicationContext(), "로그인 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                login_user_id.requestFocus();
                break;
            }
            case (code.httpError): {
                System.out.println("사용자 로그인 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                login_user_id.requestFocus();
                break;
            }
            case (code.UnknownError): {
                System.out.println("사용자 로그인 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                login_user_id.requestFocus();
                break;
            }
        }
    }


}
