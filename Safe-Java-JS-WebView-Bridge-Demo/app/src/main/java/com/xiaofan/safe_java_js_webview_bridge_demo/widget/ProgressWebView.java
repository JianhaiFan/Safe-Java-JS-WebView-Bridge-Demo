package com.xiaofan.safe_java_js_webview_bridge_demo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.xiaofan.safe_java_js_webview_bridge_demo.R;
import com.xiaofan.safe_java_js_webview_bridge_demo.util.AndroidVersionUtil;
import com.xiaofan.safe_java_js_webview_bridge_demo.util.NetWorkUtil;

/**
 * @author: 范建海
 * @createTime: 2017/2/28 10:52
 * @className:  ProgressWebView
 * @description: 带进度条的WebView控件
 * @changed by:
 */
public class ProgressWebView extends WebView {

    private ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));

        Drawable drawable = context.getResources().getDrawable(R.drawable.layer_list_web_progress_bar);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);

        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 没有网络时，默认加载缓存
        if (!NetWorkUtil.isNetworkConnected(context)) {
            getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }else {
            // 有网络时，加载模式默认
            getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        // 开启 DOM storage API 功能
        getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        getSettings().setDatabaseEnabled(true);
        //开启 ApplicationCaches 功能
        getSettings().setAppCacheEnabled(true);
        // 如果有图片，图片之后加载
        if(AndroidVersionUtil.isKitkat()) {
            getSettings().setLoadsImagesAutomatically(true);
        } else {
            getSettings().setLoadsImagesAutomatically(false);
        }
    }

    /**
     * 给进度条设置进度
     * @param progress 进度值
     */
    public void setProgressBarValue(int progress) {
        if(progressbar != null) {
            progressbar.setProgress(progress);
        }
    }

    /**
     * 判断当前进度条是否可见
     * @return
     */
    public boolean isProgressBarShow() {
        if(progressbar.getVisibility() == View.VISIBLE) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 显示进度条控件
     */
    public void showProgressBar() {
        if (progressbar != null) {
            progressbar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏进度条控件
     */
    public void hideProgressBar() {
        if(progressbar != null) {
            progressbar.setVisibility(View.GONE);
        }
    }



}
