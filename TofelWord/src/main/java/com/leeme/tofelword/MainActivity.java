package com.leeme.tofelword;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.ConsoleMessage;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.leeme.tofelword.handlers.SentenceHandlerInterface;
import com.leeme.tofelword.handlers.WordtHandlerInterface;

public class MainActivity extends Activity {

    private WebView mWebView = null;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
        try{
            switch(msg.what){
                case 200:
                    Bundle bl = msg.getData();
                    String param = bl.getString("uid") + ",{'code':200, 'content': "+ bl.getInt("result") + "}";
                    mWebView.loadUrl("javascript:response("+param+")");
                    break;
            }
            super.handleMessage(msg);
        }catch (NullPointerException ex){

        }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tofelword", "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) this.findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.canGoBack();
        mWebView.setWebViewClient(new MyWebViewClient());

        Context ctx = getBaseContext();
        mWebView.addJavascriptInterface(new WordtHandlerInterface(ctx), "wordInterface");
        mWebView.addJavascriptInterface(new SentenceHandlerInterface(ctx), "sentenceInterface");

        Log.d("tofelword", "loadUrl");
        mWebView.loadUrl("file:///android_asset/index.html");

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            // Check to see if there is a progress dialog
        /*
        if (progressDialog == null) {
            // If no progress dialog, make one and set message
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Loading please wait...");
            progressDialog.show();

            // Hide the webview while loading
            webview.setEnabled(false);
        }
        */
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // Page is done loading;
            // hide the progress dialog and show the webview
        /*
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
            webview.setEnabled(true);
        }
        */
        }

        public boolean onConsoleMessage(ConsoleMessage cm) {
            String msg = cm.message() + " -- From line "+ cm.lineNumber() + " of "+ cm.sourceId();
            Log.d("tofelword", msg);
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(MainActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            //mWebView.goBack();
            mWebView.loadUrl("javascript:keydown()");
            return true;
        }else{

        }
        return false;
    }

}
