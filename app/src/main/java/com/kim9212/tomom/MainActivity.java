package com.kim9212.tomom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> items = new ArrayList<>();

    MyAdapter adpater;
    ViewPager pager;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //Drawer를 열고닫는 햄버거아이콘 버튼
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        adpater = new MyAdapter(items, getLayoutInflater());

        for (int i = 0; i < 44; i++) {
            items.add(new Integer(R.drawable.work_0) + i);
        }
        //ViewPager에 아답터 설정
        pager = findViewById(R.id.pager);
        pager.setAdapter(adpater);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("MINTSHOP");


        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.nav);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.menu_gallery:

                        Intent intent=new Intent(MainActivity.this,NewWork.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_chat:
                        Intent intent2=new Intent(MainActivity.this,ChattingActivity.class);
                        startActivity(intent2);

                        break;
                    case R.id.menu_send:
                        Intent intent3=new Intent(MainActivity.this,YoutubeActivity.class);
                        startActivity(intent3);
                        break;

                }
                return false;
            }
        });

    }


    public void clickPrev(View view) {
        //현재 뷰페이저의 page번호 얻어오기
        int index = pager.getCurrentItem();
        //특정페이지로 이동
        pager.setCurrentItem(index - 1, false);
    }

    public void clickNext(View view) {
        int index = pager.getCurrentItem();
        pager.setCurrentItem(index + 1, false);
    }
    public void clickPlay(View view) {
        //뮤직을 백그라운드에서 실행하는 서비스를 시작!!
        Intent intent= new Intent(this, MusicService.class);

        //포어그라운드 서비스로 실행하도록.. (Mainfest.xml에 퍼미션)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) startForegroundService(intent);
        else startService(intent);

    }

    public void clickStop(View view) {
        //뮤직을 백그라운드에서 실행하는 서비스를 종료!!
        Intent intent= new Intent(this, MusicService.class);
        stopService(intent);
    }

    public void clickpush(View view) {
        //앱을 FCM서버에 등록하면 앱을 식별할 수 있는 고유 토큰값(문자열)을 줌
        //이 토큰값(InstanceID)을 통해 앱들(디바이스들)을 구별하여 메세지가 전달되는 것임.

        FirebaseInstanceId firebaseInstanceId= FirebaseInstanceId.getInstance();
        Task<InstanceIdResult> task= firebaseInstanceId.getInstanceId();
        task.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                String token= task.getResult().getToken();

                //토큰값 출력
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                //Logcat 창에 토큰값 출력
                Log.i("TAG", token);

                //실무에서는 이 token값을 본인의 웹서버(dothome서버)에
                //전송하여 웹 DB에 token값 저장하도록..해야함


            }
        });

    }
}