package com.ooftf.pulltorefresh.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import android.util.AttributeSet;

import android.view.LayoutInflater;

import android.widget.TextView;

import com.ooftf.pulltorefresh.R;

/**
 * 存在问题：下拉刷新的时候，因为ListView接受到了down事件所以item会比那成pressed状态，但是up事件被拦截下来，所以pressed状态不会消失
 * 临时解决方案：去掉item的pressed状态
 *
 *
 * Created by master on 2016/9/20.
 */
public class PullToRefreshHeader extends APullToRefreshHeader {
    public PullToRefreshHeader(Context context) {
        super(context);


    }
    public PullToRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public PullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


    }
    TextView mTextDesc;
    @Override
    void setContentView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_refresh_header,this);
        mTextDesc = (TextView) findViewById(R.id.text_desc);
    }


    @Override
    void onPullDownRefreshView() {
        mTextDesc.setText("下拉刷新");
    }

    @Override
    void onReleaseRefreshView() {
        mTextDesc.setText("释放刷新");
    }

    @Override
    void onRefreshingView() {
        mTextDesc.setText("正在刷新");
    }

    @Override
    void onScrollView() {
        mTextDesc.setText("下拉刷新");
    }
}
