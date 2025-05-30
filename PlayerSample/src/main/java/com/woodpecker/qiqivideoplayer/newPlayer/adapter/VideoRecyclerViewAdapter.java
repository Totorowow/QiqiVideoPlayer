package com.woodpecker.qiqivideoplayer.newPlayer.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import com.woodpecker.qiqivideoplayer.newPlayer.list.OnItemChildClickListener;
import com.woodpecker.qiqivideoplayer.newPlayer.list.OnItemClickListener;
import com.woodpecker.video.config.VideoInfoBean;
import com.woodpecker.video.ui.view.CustomPrepareView;

import com.woodpecker.qiqivideoplayer.R;

import java.util.List;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.VideoHolder> {

    private List<VideoInfoBean> videos;

    private OnItemChildClickListener mOnItemChildClickListener;

    private OnItemClickListener mOnItemClickListener;

    public VideoRecyclerViewAdapter(List<VideoInfoBean> videos) {
        this.videos = videos;
    }

    @Override
    @NonNull
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoHolder(itemView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {

        VideoInfoBean videoBean = videos.get(position);

        Glide.with(holder.mThumb.getContext())
                .load(videoBean.getCover())
                .placeholder(android.R.color.darker_gray)
                .into(holder.mThumb);
        holder.mTitle.setText(videoBean.getTitle());

        holder.mPosition = position;
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void addData(List<VideoInfoBean> videoList) {
        int size = videos.size();
        videos.addAll(videoList);
        //使用此方法添加数据，使用notifyDataSetChanged会导致正在播放的视频中断
        notifyItemRangeChanged(size, videos.size());
    }

    public class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public int mPosition;
        public FrameLayout mPlayerContainer;
        public TextView mTitle;
        public ImageView mThumb;
        public CustomPrepareView mPrepareView;

        VideoHolder(View itemView) {
            super(itemView);
            mPlayerContainer = itemView.findViewById(R.id.player_container);
            mTitle = itemView.findViewById(R.id.tv_title);
            mPrepareView = itemView.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.getThumb();
            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
                }
            }

        }
    }


    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}