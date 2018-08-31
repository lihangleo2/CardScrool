package com.leo.myapplication;

import android.app.SharedElementCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.leo.myapplication.R.id.recyclerView;
import static com.leo.myapplication.R.id.text_red;

/**
 * Created by Leo on 2018/8/29.
 */

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image;
    private RelativeLayout dragView;

    private TextView text_name;
    private TextView text_age;

    private TextView text_name_head;
    private TextView text_age_head;

    private RecyclerView recyclerView_;
    private DetailAdapter adapter;

    private LinearLayout linear_shareView;
    private TextView text_zan;

    private ImageView image_back;

    /**
     * 可滑动上去的布局
     */
    SlidingUpPanelLayout mLayout;

    private SlidingUpPanelLayout.PanelState currentState = SlidingUpPanelLayout.PanelState.COLLAPSED;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 11:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        isFinish = true;
                        DetailActivity.this.finishAfterTransition();
                    } else {
                        finish();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_detail);
        image = findViewById(R.id.image);
        dragView = findViewById(R.id.dragView);
        text_name = findViewById(R.id.text_name);
        text_age = findViewById(R.id.text_age);
        text_zan = findViewById(R.id.text_zan);

        image_back = findViewById(R.id.image_back);
        linear_shareView = findViewById(R.id.linear_shareView);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "我点击了", Toast.LENGTH_SHORT).show();
                if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    mhandler.sendEmptyMessageDelayed(11, 300);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        isFinish = true;
                        DetailActivity.this.finishAfterTransition();
                    } else {
                        finish();
                    }
                }

            }
        });


        text_name_head = findViewById(R.id.text_name_head);
        text_age_head = findViewById(R.id.text_age_head);

        mLayout = findViewById(R.id.sliding_layout);

        recyclerView_ = findViewById(R.id.recyclerView_);
        adapter = new DetailAdapter(this, this, this);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        adapter.setData(arrayList);
        final LinearLayoutManager layoutManagertip = new LinearLayoutManager(this);
        layoutManagertip.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_.setLayoutManager(layoutManagertip);
        recyclerView_.setAdapter(adapter);


        RelativeLayout.LayoutParams cardreParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        cardreParams.width = UIUtil.getWidth();
        cardreParams.height = UIUtil.getWidth();

        if (Build.VERSION.SDK_INT >= 22) {
            //这个可以看做个管道  每次进入和退出的时候都会进行调用  进入的时候获取到前面传来的共享元素的信息
            //退出的时候 把这些信息传递给前面的activity
            //同时向sharedElements里面put view,跟对view添加transitionname作用一样
            setEnterSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    sharedElements.clear();
                    sharedElements.put("shareView", image);
//                    sharedElements.put("haha", dragView);
                    sharedElements.put("haha", linear_shareView);
                    sharedElements.put("name", text_name);
                    sharedElements.put("age", text_age);
                    sharedElements.put("zan", text_zan);
                }
            });


            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
//                    LogUtils.i("共享元素动画的实现", "onTransitionStart");
                    text_name_head.setVisibility(View.GONE);
                    text_age_head.setVisibility(View.GONE);
                    recyclerView_.setVisibility(View.GONE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
//                    LogUtils.i("共享元素动画的实现", "onTransitionEnd");
                    if (!isFinish) {
                        text_name_head.setVisibility(View.VISIBLE);
                        text_age_head.setVisibility(View.VISIBLE);
                        recyclerView_.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }


        mLayout.setPanelHeight(UIUtil.getHeight() - UIUtil.getWidth());
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.e("这些是什么状态", slideOffset + "========");
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.e("这些是什么状态", previousState + "========" + newState);
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    if (currentState == newState) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            isFinish = true;
                            DetailActivity.this.finishAfterTransition();
                        } else {
                            finish();
                        }
                    } else {
                        currentState = newState;
                    }
                } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    currentState = newState;
                }
            }
        });
    }


    private boolean isFinish = false;

    @Override
    public void onBackPressed() {
        isFinish = true;
        if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            mhandler.sendEmptyMessageDelayed(11, 300);
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                isFinish = true;
                DetailActivity.this.finishAfterTransition();
            } else {
                finish();
            }

        }
    }


    @Override
    public void onClick(View view) {

    }
}
