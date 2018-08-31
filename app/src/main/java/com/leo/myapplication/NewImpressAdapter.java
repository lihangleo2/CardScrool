package com.leo.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by lihang on 2016/4/18.
 */
public class NewImpressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<String> faceList;
    private Context context;
    private View.OnClickListener listener;
    private Activity activity;


    public NewImpressAdapter(Context context, View.OnClickListener listener, Activity activity) {
        this.context = context;
        this.listener = listener;
        this.activity = activity;
    }

    public void setData(ArrayList<String> faceList) {
        this.faceList = faceList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        holder = new ImpressOneHolder(v);

        return holder;
    }


    private float fingerY;//手指的Y轴

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        String item = faceList.get(position);
        final ImpressOneHolder holder1 = (ImpressOneHolder) holder;

        holder1.txt_num.setText(item);

        RelativeLayout.LayoutParams cardreParams = (RelativeLayout.LayoutParams) holder1.image.getLayoutParams();
        cardreParams.width = UIUtil.getWidth() * 3 / 4;
        cardreParams.height = UIUtil.getWidth() * 3 / 4;

        FrameLayout.LayoutParams relativeParams = (FrameLayout.LayoutParams) holder1.relative.getLayoutParams();
        relativeParams.width = UIUtil.getWidth() * 3 / 4;
        relativeParams.height = UIUtil.getWidth() * 3 / 4;



        holder1.relative.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    fingerY = motionEvent.getY();
                    Log.e("这里的手势判断", fingerY + "=================");
                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    Log.e("这里的手势判断", (motionEvent.getY() - fingerY) + "=======UP抬起了");
                    if ((motionEvent.getY() - fingerY) <= 20) {
                        Intent intent = new Intent(activity, DetailActivity.class);
                        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                activity, new Pair<View, String>(holder1.image, "shareView"),
                                new Pair<View, String>(holder1.relative_other_, "haha"),
                                new Pair<View, String>(holder1.text_name, "name"),
                                new Pair<View, String>(holder1.text_age, "age"),
                                new Pair<View, String>(holder1.text_zan, "zan")
                        );
                        ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
                    }
                }
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return faceList == null ? 0 : faceList.size();

    }

    class ImpressOneHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        RelativeLayout relative;
        RelativeLayout relative_other_;
        TextView text_name;
        TextView text_age;

        TextView txt_num;
        TextView text_zan;

        public ImpressOneHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
            relative = (RelativeLayout) itemView.findViewById(R.id.relative);
            relative_other_ = (RelativeLayout) itemView.findViewById(R.id.relative_other_);
            text_name = (TextView) itemView.findViewById(R.id.txt_name);
            text_age = (TextView) itemView.findViewById(R.id.txt_age);
            txt_num = (TextView) itemView.findViewById(R.id.txt_num);
            text_zan = (TextView) itemView.findViewById(R.id.text_zan);
        }
    }


}
