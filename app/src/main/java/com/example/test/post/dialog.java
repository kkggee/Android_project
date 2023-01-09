package com.example.test.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.DTO.http.TagRequest;
import com.example.test.DTO.http.TagResponse;
import com.example.test.R;
import com.example.test.service.http.HttpService;
import com.example.test.service.http.code;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;

public class dialog extends AppCompatActivity {
    dialog instance;
    HttpService httpService;

    String tag_name = null;
    String color = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        httpService = HttpService.getService();
        setContentView(R.layout.dialog);

        TextView tag = findViewById(R.id.tag);
        TextView colorTextView = findViewById(R.id.color_text_view);
        View colorView = findViewById(R.id.color_view);
        ColorPickerView colorPickerView = findViewById(com.skydoves.colorpickerview.R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                String hex = envelope.getHexCode();
                hex = hex.substring(2);
                colorTextView.setText(hex);
                colorView.setBackgroundColor(envelope.getColor());
            }
        });
        BrightnessSlideBar brigSlideBar = findViewById(R.id.brig_sliderBar);
        colorPickerView.attachBrightnessSlider(brigSlideBar);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tag_name = tag.getText().toString();
                color = colorTextView.getText().toString();
                Intent outIntent = new Intent(getApplicationContext(), PostActivity.class);

                outIntent.putExtra("color", colorTextView.getText().toString());
                outIntent.putExtra("tag", tag.getText().toString());
                setResult(RESULT_OK, outIntent);

                try{
                    httpService.tagRegister(new TagRequest(tag_name, color), dialog.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }

    public void tagProcess(TagResponse tagResponse) {
        switch (tagResponse.getCode()) {
            case (code.OK) : {
                if (tagResponse.isResult()) {
                    Toast.makeText(dialog.this, "태그 추가 완료", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(dialog.this, "태그 추가 실패", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            }
            case (code.SQLError): {
                System.out.println("태그 추가 실패");
                Toast.makeText(getApplicationContext(), "입력 정보가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.httpError): {
                System.out.println("태그 추가 실패");
                Toast.makeText(getApplicationContext(), "통신 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
            case (code.UnknownError): {
                System.out.println("태그 추가 실패");
                Toast.makeText(getApplicationContext(), "알 수 없는 이유로 실패", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}