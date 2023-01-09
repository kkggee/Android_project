package com.example.test.post;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.DTO.UserInfo;
import com.example.test.DTO.http.NonRequest;
import com.example.test.DTO.http.PostRequest;
import com.example.test.DTO.http.PostResponse;
import com.example.test.DTO.http.TagListResponse;
import com.example.test.R;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;

import java.util.ArrayList;
import java.util.HashMap;


public class PostActivity extends AppCompatActivity {
    PostActivity instance;
    private String userName;
    HttpService httpService;
    boolean mBound = false;
    UserInfo userInfo = new UserInfo();

    PostResponse postResponse = null;
//    private EditText login_user_id, login_user_password;

    public EditText edit,EditText_Script, EditText_Title;
    public TextView textView_Date, textView_Time, selecText,tv;
    public DatePickerDialog.OnDateSetListener callbackMethod;
    public TimePickerDialog.OnTimeSetListener callbackMethod2;
    public CheckBox checkBox1,checkBox2,checkBox3;
    public Button btn;
    public String[] tagArr,tagColor;
    public View dialogView, colorView;
    public ArrayList<String> items, tItems;
    public HashMap<String, String> map = new HashMap<>();
    public LinearLayout li;
    public Intent i;

    String date = null;
    String time = null;
    String price = null;
    String title= null;
    String script= null;
    String userID_s = "";
    
    @Override
    protected void onStart() {
        super.onStart();
        i=getIntent();
//        userInfo.setUserID("andro");
        userID_s = i.getStringExtra("UserInfo");
        System.out.println(userID_s);
        try {
            TagListResponse tagList = httpService.getTagList(new NonRequest(userInfo.getUserID()),instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        httpService = HttpService.getService();
        setContentView(R.layout.newpost);


        this.InitializeView();
        this.InitializeListener();
        this.InitializeListener2();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title= EditText_Title.getText().toString();
                script = EditText_Script.getText().toString();
                if(checkBox1.isChecked()==true){
                    date = textView_Date.getText().toString();
                }
                if(checkBox2.isChecked()==true){
                    time = textView_Time.getText().toString();
                }
                if(checkBox3.isChecked()==true){
                    price= edit.getText().toString();
                }
                try {
                    httpService.post(new PostRequest(title,script,date,time,price,tItems),PostActivity.this);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PostActivity.this, "게시글 업로드 실패", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
    public void tagListUpProcess(TagListResponse tagListResponse) {
        switch (tagListResponse.getCode()) {
            case (code.OK): {
                if (tagListResponse.isResult()) {
                    map = new HashMap<>();
                    for(int i =0; i<tagListResponse.getTagNameList().size() ;i++){
                        map.put(tagListResponse.getTagNameList().get(i),tagListResponse.getTagColorList().get(i));
                    }
                    System.out.println("태그 로드 성공");
                    items = tagListResponse.getTagNameList();
                } else {
                    System.out.println("태그 로드 실패");
                }
                break;
            }
            case (code.SQLError): {
                System.out.println("태그 로드 실패");
                System.out.println(code.SQLError);
                Toast.makeText(getApplicationContext(), "SQL 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.httpError): {
                System.out.println("태그 로드 실패");
                System.out.println(code.httpError);
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.UnknownError): {
                System.out.println("태그 로드 실패");
                System.out.println(code.UnknownError);
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }


    public void postProcess(PostResponse postResponse) {
        switch (postResponse.getCode()) {
            case (code.OK): {
                if (postResponse.isResult()) {
                    Toast.makeText(PostActivity.this, "게시글 업로드 완료", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PostActivity.this, "게시글 업로드 실패", Toast.LENGTH_SHORT).show();
                }
//                finish();
                break;
            }
            case (code.SQLError): {
                System.out.println("게시글 업로드 실패");
                Toast.makeText(getApplicationContext(), "입력 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.httpError): {
                System.out.println("게시글 업로드 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.UnknownError): {
                System.out.println("게시글 업로드 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void InitializeView(){
        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        textView_Date = (TextView)findViewById(R.id.textView_date);
        textView_Time = (TextView)findViewById(R.id.textView_time);
        EditText_Script = (EditText) findViewById(R.id.memo_edit);
        edit = (EditText) findViewById(R.id.edit);
        btn = (Button) findViewById(R.id.btn1);
        EditText_Title = (EditText) findViewById(R.id.title);
        li = (LinearLayout) findViewById(R.id.li);

    }

    public void InitializeListener(){
        callbackMethod = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                monthOfYear = monthOfYear+1;
                textView_Date.setText("날짜 : " + year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }
    public void InitializeListener2(){
        callbackMethod2 = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                textView_Time.setText("시간 : " + hourOfDay + "시" + minute + "분");
            }
        };
    }
    public static String[] asStrings(ArrayList<String> objArray) {
        String[] strArray = new String[objArray.size()];
        for (int i = 0; i < objArray.size(); i++) {
            strArray[i] = String.valueOf(objArray.get(i));
            System.out.println(objArray.get(i));
        }
        return strArray;
    }

    public void OnClickHandler4(View view){
        try {
            TagListResponse tagList = httpService.getTagList(new NonRequest(userInfo.getUserID()),instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayList<String> selectedItems = new ArrayList<String>();


        builder.setTitle("태그 선택");
        String [] itemArray = asStrings(items);
        builder.setMultiChoiceItems(itemArray, null, new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int pos, boolean isChecked)
            {
                if(isChecked == true) // Checked 상태일 때 추가
                {
//                    selectedItems.add(items[pos]);
                    selectedItems.add(itemArray[pos]);
                }
                else				  // Check 해제 되었을 때 제거
                {
                    selectedItems.remove(itemArray[pos]);
                }
                tItems = selectedItems;
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int pos)
            {

                for (int i=0; i<selectedItems.size();i++){
                    tv = new TextView(PostActivity.this);
                    tv.setTag(i);
                    tv.setText(" " + selectedItems.get(i) + " ");
                    tv.setTextSize(20);
                    tv.setTextColor(Color.parseColor("#000000"));
                    DisplayMetrics dm = getResources().getDisplayMetrics();
                    int size = Math.round(20 * dm.density);

                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    param.rightMargin = size;
                    tv.setLayoutParams(param);

                    String co = map.get(selectedItems.get(i));
                    System.out.println(co);
                    tv.setBackgroundColor(Color.parseColor(co));
                    li.addView(tv);

                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void OnClickHandler(View view){
        if(checkBox1.isChecked()==true){
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod,2022,11,7);
            textView_Date.setVisibility(View.VISIBLE);
            dialog.show();
        }
        else{
            textView_Date.setVisibility(View.INVISIBLE);
        }
    }

    public void OnClickHandler2(View view){
        if (checkBox2.isChecked()==true) {
            TimePickerDialog dialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, callbackMethod2, 12, 00, true);

            textView_Time.setVisibility(View.VISIBLE);
            dialog.show();
        }
        else{
            textView_Time.setVisibility(View.INVISIBLE);
        }
    }
    public void OnClickHandler3(View view){
        if (checkBox3.isChecked()==true) {
            edit.setVisibility(View.VISIBLE);
        }
        else{
            edit.setVisibility(View.INVISIBLE);
        }
    }

    public void OnClickHandler5(View view){
        Intent intent = new Intent(getApplicationContext(), dialog.class);
        startActivityForResult(intent,0);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String color = data.getStringExtra("color");
            String tag = data.getStringExtra("tag");
//            items = Add(items, tag);
            color = "#" + color;
            System.out.println(color);
            map.put(tag, color);
        }
    }
}
