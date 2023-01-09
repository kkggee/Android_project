package com.example.test.calendar;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.test.DTO.UserInfo;
import com.example.test.DTO.http.CalendarRequest;
import com.example.test.DTO.http.CalendarResponse;
import com.example.test.ListViewAdapter;
import com.example.test.ListViewAdapterSche;
import com.example.test.R;
import com.example.test.Schedule;
import com.example.test.reply.ReplyActivity;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;

import java.util.ArrayList;

public class CalendarActivity extends Activity {
    public TextView current_date, text;
    public CalendarView calendarView;
    public View dialogView;
    HttpService httpService;
    CalendarActivity instance;
    private ListView listView;
    ListViewAdapterSche adapter;
    UserInfo userInfo = new UserInfo();

    String user_ID = "andro";

    @Override
    protected void onStart() {
        super.onStart();
        userInfo.setUserID("andro");

        try{
            httpService.getCalendar(new CalendarRequest(userInfo.getUserID()), CalendarActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        instance = this;
        httpService = HttpService.getService();

        this.InitializeView();

//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
//                tv2.setVisibility(View.VISIBLE);
//                calendar_add.setVisibility(View.VISIBLE);
//                tv2.setText(String.format("%d / %d / %d",year,month+1,day));
//
//                calendar_add.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialogView = (View) View.inflate(CalendarActivity.this, R.layout.calendar_dialog, null);
//                        AlertDialog.Builder dlg = new AlertDialog.Builder(CalendarActivity.this);
//                        dlg.setTitle("일정 추가");
//                        dlg.setIcon(R.drawable.calendar);
//                        dlg.setView(dialogView);
//                        dlg.setPositiveButton("확인",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        int i = 0;
//                                        text = new TextView(CalendarActivity.this);
//                                        text.setTag(i);
//                                        i++;
//                                        text2 = (EditText) dialogView.findViewById(R.id.calendar_add_edit);
//                                        text.setText(text2.getText().toString());
//                                        text.setTextSize(20);
//                                        text.setTextColor(Color.parseColor("#000000"));
//                                        li.addView(text);
//                                        text.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                calendar_edit.setVisibility(View.VISIBLE);
//                                                calendar_delete.setVisibility(View.VISIBLE);
//                                            }
//                                        });
//                                    }
//                                });
//                        dlg.setNegativeButton("취소", null);
//                        dlg.show();
//                    }
//                });
//            }
//        });
    }

    public void calendarProcess(CalendarResponse calendarResponse) {
        switch (calendarResponse.getCode()) {
            case (code.OK): {
                Log.d("scheduleGet", "일정 로드 성공");

                ArrayList<String> scheduleList = calendarResponse.getDescript();
//                Schedule schedule;
//                for(int i=0; i<scheduleList.size(); i++) {
//                    String date = scheduleList.get(i).split("\t")[0];
//                    int year1 = Integer.parseInt(date.substring(0,4)); int month1 = Integer.parseInt(date.substring(5,7)); int day1 = Integer.parseInt(date.substring(8,10));
//                    schedule = new Schedule();
//
//                    schedule.startTime = scheduleList.get(i).split("\t")[1];
//                    schedule.descript = scheduleList.get(i).split("\t")[2];
//
//                }

                ArrayList<Schedule> testSchduleList = new ArrayList();
                listView = (ListView) findViewById(R.id.schedule_item);
                adapter = new ListViewAdapterSche(getApplicationContext());

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                        Schedule schedule;

                        current_date.setVisibility(View.VISIBLE);
                        current_date.setText(String.format("%d / %d / %d", year, month + 1, day));
                        listView.setAdapter(adapter);

                        for(int i=0; i<scheduleList.size(); i++) {
                            schedule = new Schedule();
                            String date = scheduleList.get(i).split("\t")[0];
                            int year1 = Integer.parseInt(date.substring(0,4));
                            int month1 = Integer.parseInt(date.substring(5,7));
                            int day1 = Integer.parseInt(date.substring(8,10));
                            if (year == year1 && month+1 == month1 && day == day1 ) {
                                System.out.println("true");
                                schedule.startTime = scheduleList.get(i).split("\t")[1];
                                System.out.println(scheduleList.get(i).split("\t")[1]);
                                schedule.descript = scheduleList.get(i).split("\t")[2];
                                testSchduleList.add(schedule);
                                adapter.setItems(testSchduleList);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
                break;
            }
            case (code.SQLError): {
                System.out.println("일정 로드 실패");
                Toast.makeText(getApplicationContext(), "입력 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.httpError): {
                System.out.println("일정 로드 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.UnknownError): {
                System.out.println("일정 로드 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void InitializeView() {
        calendarView=findViewById(R.id.calendarView);
        current_date=findViewById(R.id.current_date);
    }
}
