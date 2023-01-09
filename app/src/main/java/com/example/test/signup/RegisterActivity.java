package com.example.test.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.DTO.http.SignupRequest;
import com.example.test.DTO.http.SignupResponse;
import com.example.test.R;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;

public class RegisterActivity extends AppCompatActivity {
    private RegisterActivity instance;
    private EditText signup_user_id, signup_user_password, signup_user_phone, signup_user_password_ck;
    private Button signup_activity_btn, confirm_activity_btn;
    private boolean confirmFlag = false;
    private String userID, userPassword, userPhone, userPasswordCk;
    HttpService httpService;
    boolean mBound = false;

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onCreate(Bundle savedInstanceState) {
        // 회원가입 페이지 불러오기
        super.onCreate(savedInstanceState);
        instance =  this;
        httpService = new HttpService();
        setContentView(R.layout.sign);

        signup_user_id = findViewById(R.id.join_id);
        signup_user_password = findViewById(R.id.join_password);
        signup_user_phone = findViewById(R.id.join_phone);
        signup_user_password_ck = findViewById(R.id.join_pwck);

        signup_activity_btn = findViewById(R.id.join_button);
        confirm_activity_btn = findViewById(R.id.confirm_activity_btn);

        confirm_activity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이디 중복
                try {
                    userID = signup_user_id.getText().toString();
                    String tmp= userID.replace(" ","");
                    if(tmp.equals("")||userID.length()!=tmp.length()){
                     Toast.makeText(getApplicationContext(),"ID 공백문자 빼주세요", Toast.LENGTH_SHORT).show();
                    }else {
                        SignupRequest signupRequest = new SignupRequest();
                        signupRequest.setInputId(userID);
                        System.out.println("아이디: " + userID);
                        httpService.checkUserID(signupRequest, instance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        signup_activity_btn.setOnClickListener(new View.OnClickListener() {
            // 회원 가입 버튼
            @Override
            public void onClick(View view) {
                userID = signup_user_id.getText().toString();
                userPassword = signup_user_password.getText().toString();
                userPhone = signup_user_phone.getText().toString();
                userPasswordCk = signup_user_password_ck.getText().toString();
                // 빈칸인 경우
                if (userID.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "ID를 입력하세요", Toast.LENGTH_SHORT).show();
                    signup_user_id.requestFocus();
                } else if (userPassword.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Password를 입력하세요", Toast.LENGTH_SHORT).show();
                    signup_user_password.requestFocus();
                } else if (userPhone.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "User Name을 입력하세요", Toast.LENGTH_SHORT).show();
                    signup_user_phone.requestFocus();
                } else if (!confirmFlag) {
                    Toast.makeText(RegisterActivity.this, "ID 중복 확인을 하세요", Toast.LENGTH_SHORT).show();
                    signup_user_id.requestFocus();
                }  else if (!userPassword.equals(userPasswordCk)) {
                    Toast.makeText(RegisterActivity.this, "Password가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    signup_user_password_ck.requestFocus();
                } else {
                    try {
                        SignupResponse signupResponse = httpService.signUp(new SignupRequest(userID, userPassword, userPhone),instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    public void signUpProcess(SignupResponse signupResponse){
        switch (signupResponse.getCode()) {
            case (code.OK): {
                if (signupResponse.isResult()) {
                    Toast.makeText(RegisterActivity.this, "회원 가입 완료", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "회원 가입 실패", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            }
            case (code.SQLError): {
                System.out.println("회원가입 실패");
                Toast.makeText(getApplicationContext(), "회원가입이 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.httpError): {
                System.out.println("회원가입 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.UnknownError): {
                System.out.println("알 수 없는 이유로 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    public void checkIDProcess(SignupResponse signupResponse) {
        switch (signupResponse.getCode()) {
            case (code.OK): {
                if (signupResponse.isResult()) {
                    System.out.println("아이디 중복");
                    Toast.makeText(RegisterActivity.this, "ID가 중복됩니다", Toast.LENGTH_SHORT).show();
                    signup_user_id.requestFocus();
                    confirmFlag = false;
                } else {
                    System.out.println("아이디가 중복되지 않음");
                    Toast.makeText(RegisterActivity.this, "사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();
                    confirmFlag = true;
                }
                break;
            }
            case (code.SQLError): {
                System.out.println("CheckID SQL 실패");
                Toast.makeText(getApplicationContext(), "로그인 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.httpError): {
                System.out.println("CheckID 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.UnknownError): {
                System.out.println("알수 없는 이유로 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                break;

            }
        }
    }
}
