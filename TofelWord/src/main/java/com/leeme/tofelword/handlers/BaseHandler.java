package com.leeme.tofelword.handlers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * BaseHandler
 */
public class BaseHandler {
    private Handler mHandler;
    public BaseHandler(Handler handler){
        mHandler = handler;
    }

    protected void response(String uid, String result){
        Bundle bl = new Bundle();
        bl.putString("uid", uid);
        bl.putInt("code", 200);
        bl.putString("content", result);

        Message msg = new Message();
        msg.what = 200;
        msg.setData(bl);
        mHandler.sendMessage(msg);
    }
}
