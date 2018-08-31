package com.leo.myapplication;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static android.R.attr.left;
import static com.leo.myapplication.UIUtil.getWidth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private NewImpressAdapter adapter;

    boolean isFirst = true;
    private ArrayList<String> arrayList = new ArrayList<>();

    private int currentPosition = 0;//当前recyclerView充当ViewPager的选中项目

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        int padding = (UIUtil.getWidth() / 4 - UIUtil.dip2px(this, 40)) / 2;
        int truePading = padding + UIUtil.dip2px(this, 20);
        recyclerView.setPadding(truePading, 0, truePading, 0);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        adapter = new NewImpressAdapter(this, this, this);

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        adapter.setData(arrayList);
        final LinearLayoutManager layoutManagertip = new LinearLayoutManager(this);
        layoutManagertip.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManagertip);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstPosition = layoutManagertip.findFirstVisibleItemPosition();
                    int lastPosition = layoutManagertip.findLastVisibleItemPosition();

                    currentPosition = firstPosition;
                    Log.e("我看看这两首什么值", firstPosition + "=============" + lastPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //(做细一点的话，判断item当前项是否在之前的浏览里。是，则插入后需要滑动)，如果不是则直接插入

                /**
                 * 假设这里点击的是3
                 * */
                if (currentPosition == 2) {//这里如果点击的 就是当前的  注意 不做判断

                } else if (currentPosition < 2) {//当前点击选中的position小于 点击的position
                    arrayList.remove(2);
                    adapter.notifyItemRemoved(2);//这是移除带有动画效果的
                    adapter.notifyItemRangeChanged(2, adapter.getItemCount());

                    arrayList.add(currentPosition + 1, "3");
                    adapter.notifyItemInserted(currentPosition + 1);
                    adapter.notifyItemRangeChanged(currentPosition + 1, adapter.getItemCount());

                    recyclerView.smoothScrollToPosition(currentPosition + 1);
                } else if (currentPosition > 2) {
                    arrayList.remove(2);
                    adapter.notifyItemRemoved(2);//这是移除带有动画效果的
                    adapter.notifyItemRangeChanged(2, adapter.getItemCount());

                    arrayList.add(currentPosition, "3");
                    adapter.notifyItemInserted(currentPosition);
                    adapter.notifyItemRangeChanged(currentPosition, adapter.getItemCount());

                    recyclerView.smoothScrollToPosition(currentPosition);
                }


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.relative:
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);//FacesosearchActivity
//                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        MainActivity.this, new Pair<View, String>(recyclerView_select.getChildAt(0), "shareView"));
//                ActivityCompat.startActivity(getActivity(), intent, activityOptions.toBundle());
//
//                break;
        }
    }
}
