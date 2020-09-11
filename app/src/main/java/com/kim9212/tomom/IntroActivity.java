package com.kim9212.tomom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        handler.sendEmptyMessageDelayed(0,1500);

        String strChange = "<font color=\"#FF5722\">M</font>";
        String strBack = "INT \n SHOP";
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(Html.fromHtml(strChange+strBack));



    }
    Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Intent intent= new Intent(IntroActivity.this,MainActivity.class);
            startActivity(intent);
        }
    };
}