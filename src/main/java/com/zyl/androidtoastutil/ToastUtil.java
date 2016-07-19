package com.crazyspread.mall.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast弹出工具类
 * 锁机制,解决同时调用;
 * 对比消息内容:如果内容和上次消息内容不一致,则显示消息;
 * 对比时间内容:在消息内容一直的前提下,若本次显示时间减去上次显示时间的时间差,大于Toast的时间则调用show(),反之,不调用show()
 * 静态内部类调用实现单例模式
 * Created by zyl on 15/9/9.
 */
public class ToastUtil {
    public static ToastUtil getInstance() {
        return ToastUtilHolder.INSTANCE;
    }

    private static class ToastUtilHolder {
        private static final ToastUtil INSTANCE = new ToastUtil();
    }

    private ToastUtil() {
    }

    /**
     * 上次消息
     */
    private String oldMsg = "";
    /**
     * 当前时间
     */
    private long oldTime = System.currentTimeMillis();
    /**
     * false:表示可以调用
     * True:表示不可以调用
     */
    private Boolean isLock = false;
    /**
     * 长时间
     */
    private static final int LONG_DELAY = 3500; // 3.5 seconds
    /**
     * 短时间
     */
    private static final int SHORT_DELAY = 2000; // 2 seconds

    /**
     * 显示消息
     *
     * @param context 当前上下文
     * @param msg     消息内容
     */
    public void showToast(Context context, String msg) {
        if (!isLock) {
            isLock = true;// 锁住
            if (oldMsg.equals(msg)) {// 消息内容一致
                if (System.currentTimeMillis() - oldTime > SHORT_DELAY) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    oldMsg = msg;
                    oldTime = System.currentTimeMillis();
                }
            } else {// 消息内容不一致
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                oldMsg = msg;
                oldTime = System.currentTimeMillis();
            }
            isLock = false;// 解锁
        }
    }

    public void showToast(Context context, int msgId) {
        showToast(context, context.getString(msgId));
    }
}//end class
