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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

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

        for (int i = 0; i < 24; i++) {
            items.add(new Integer(R.drawable.work_1) + i);
        }
        //ViewPager에 아답터 설정
        pager = findViewById(R.id.pager);
        pager.setAdapter(adpater);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("Namjunghee");


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
}