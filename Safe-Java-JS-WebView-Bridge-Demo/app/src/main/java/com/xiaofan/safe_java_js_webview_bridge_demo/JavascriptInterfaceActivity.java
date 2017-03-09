package com.xiaofan.safe_java_js_webview_bridge_demo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.xiaofan.safe_java_js_webview_bridge_demo.util.LogUtil;
import com.xiaofan.safe_java_js_webview_bridge_demo.widget.SafeWebView;

/**
 * @author: 范建海
 * @createTime: 2017/3/7 10:43
 * @className:  JavascriptInterfaceActivity
 * @description: 测试注解JavascriptInterface
 * @changed by:
 */
public class JavascriptInterfaceActivity extends Activity {

    SafeWebView mWebView;
    //	SafeWebView mWebView;
    String mUrl2 = "http://192.168.1.25:9090/yxck/control/getArticle_?articleId=13209&partyId=000000000000&flag=Android";
    //    String mUrl2 = "file:///android_asset/test.html";
    String mUrl3 = "file:///android_asset/js3.html";
    Object mJsObj = new JSInterface();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javascript_interface);

        mWebView = (SafeWebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 在这里注册的对象实例应该和Js代码中的保持一致
//        mWebView.addJavascriptInterface(new JSInterface(), "jsInterface");
        // 用来测试头像跳转的逻辑
        mWebView.addJavascriptInterface(new JSInterface(), "demo");
        mWebView.loadUrl(mUrl2);
    }

    class JSInterface {
        @JavascriptInterface
        public String onButtonClick(String text) {
            final String str = text;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "onButtonClick: text = " + str, Toast.LENGTH_LONG).show();
                }
            });

            return "This text is returned from Java layer.  js text = " + text;  //return数据在JS中alert显示
        }

        @JavascriptInterface
        public void onImageClick(String url, int width, int height) {
            final String str = "onImageClick: text = " + url + "  width = " + width + "  height = " + height;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                }
            });
        }

        @JavascriptInterface
        public void clickOnAndroidPersonalHome(final String partyId) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"跳转个人头像: " + partyId,Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
