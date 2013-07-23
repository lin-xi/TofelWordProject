package com.leeme.tofelword;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.leeme.tofelword.handlers.SentenceHandlerInterface;
import com.leeme.tofelword.handlers.WordtHandlerInterface;

public class MainActivity extends Activity {

    private WebView mWebView = null;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 200:
                    Bundle bl = msg.getData();
                    String param = bl.getString("uid") + ",{'code':200, 'content': "+ bl.getInt("result") + "}";
                    mWebView.loadUrl("javascript:response("+param+")");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) this.findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());

        Context ctx = getBaseContext();
        mWebView.addJavascriptInterface(new WordtHandlerInterface(ctx, mHandler), "wordInterface");
        mWebView.addJavascriptInterface(new SentenceHandlerInterface(ctx, mHandler), "sentenceInterface");
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

}
