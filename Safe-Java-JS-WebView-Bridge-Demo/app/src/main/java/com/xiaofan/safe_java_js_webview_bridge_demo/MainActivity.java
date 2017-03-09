package com.xiaofan.safe_java_js_webview_bridge_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import com.xiaofan.safe_java_js_webview_bridge_demo.component.js.HostJsScope;
import com.xiaofan.safe_java_js_webview_bridge_demo.component.js.InjectedChromeClient;
import com.xiaofan.safe_java_js_webview_bridge_demo.widget.ProgressWebView;

public class MainActivity extends Activity {

    private ProgressWebView pwv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pwv = (ProgressWebView) findViewById(R.id.pwv);
        WebSettings ws = pwv.getSettings();
        ws.setJavaScriptEnabled(true);

        pwv.setWebChromeClient(
                new InjectedChromeClient("HostApp", HostJsScope.class,pwv,MainActivity.this)
        );
        pwv.loadUrl("http://192.168.1.25:9090/yxck/control/getArticle_?articleId=13209&partyId=000000000000&flag=Android");
//        pwv.loadUrl("file:///android_asset/test.html");
    }


    public void click(View view) {
        startActivity(new Intent(this,JavascriptInterfaceActivity.class));
    }
}
