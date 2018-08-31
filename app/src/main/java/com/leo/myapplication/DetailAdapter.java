package com.leo.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by lihang on 2016/4/18.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<String> faceList;
    private Context context;
    private View.OnClickListener listener;
    private Activity activity;


    public DetailAdapter(Context context, View.OnClickListener listener, Activity activity) {
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

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        holder = new ImpressOneHolder(v);

        return holder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ImpressOneHolder holder1 = (ImpressOneHolder) holder;
    }


    @Override
    public int getItemCount() {
        return faceList == null ? 0 : faceList.size();

    }

    class ImpressOneHolder extends RecyclerView.ViewHolder {


        public ImpressOneHolder(View itemView) {
            super(itemView);

        }
    }


}
