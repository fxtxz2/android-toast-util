# android-toast-util
Toast弹出工具类:避免Toast重复频繁显示

# 作用
Toast弹出工具类  
锁机制,解决同时调用;  
对比消息内容:如果内容和上次消息内容不一致,则显示消息;  
对比时间内容:在消息内容一直的前提下,若本次显示时间减去上次显示时间的时间差,大于Toast的时间则调用show(),反之,不调用show()  
静态内部类调用实现单例模式

# 使用
```Java
ToastUtil.getInstance().showToast(getApplicationContext(), "显示内容");
// 使用消息id方式
ToastUtil.getInstance().showToast(getApplicationContext(), android.R.string.ok);
```

# Gradle
```Gradle
compile 'com.zyl.androidtoastutil:androidtoastutil:0.0.1'
```
