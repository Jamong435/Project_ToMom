package com.kim9212.tomom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {

    ArrayList<Integer> items;
    LayoutInflater inflater;


    public MyAdapter(ArrayList<Integer> items, LayoutInflater inflater){
        this.items= items;
        this.inflater= inflater;
    }


    @Override
    public int getCount() {
        return items.size();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //page.xml문서를 읽어와서 View객체로 만들기(부풀리다)
        View page= inflater.inflate(R.layout.page, null);


        ImageView iv= page.findViewById(R.id.iv);
        iv.setImageResource(  items.get(position)  );

        container.addView(page);

        return page;//만들어진 페이지 뷰 객체 리턴 : 검증을 위해서..
    }

    //화면에 더이상 보이지 않아 메모리에서 page제거하라는 메소드@Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (View)object );
    }

    //instateItem()메소드에서 만들어서 리턴한 page가 이 메소드의 2번째 파라미터로 전달됨
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object; //뷰페이저가 보여줄 view와 위에서 만든 page(object)객체가 같은지 리턴
    }
}