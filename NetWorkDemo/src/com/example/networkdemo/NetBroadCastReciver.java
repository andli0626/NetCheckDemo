package com.example.networkdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 
 * @author lilin
 * @date 2016年10月18日 下午3:29:31
 * @annotation 只有当网络改变的时候才会 经过广播
 */
public class NetBroadCastReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		// 判断wifi是打开还是关闭
//		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) { // 此处无实际作用，只是看开关是否开启
//			int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
//			switch (wifiState) {
//			case WifiManager.WIFI_STATE_DISABLED:
//				break;
//
//			case WifiManager.WIFI_STATE_DISABLING:
//				break;
//			}
//		}
		/**** 此处是主要代码 ****/
		// 如果是在开启wifi连接和有网络状态下
//		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
		// ps:由于boradCastReciver触发器组件，他和Service服务一样，都是在主线程的，所以，如果你的后续操作是耗时的操作，请new Thread获得AsyncTask等，进行异步操作
			ConnectivityManager cm 	= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info 		= cm.getActiveNetworkInfo();
			String msg;
			if (info != null && info.isConnectedOrConnecting() && info.isAvailable()) {
				msg = "网络连接正常!";
				Log.i("andli", "---有网络连接---");
			} else {
				msg = "网络连接异常，请检查网络配置!";
				Log.i("andli", "---无网络连接---");
			}
			// 发送消息到 MainActivity：通过发送消息广播的方式
			Intent mIntent = new Intent(MainActivity.ACTION_INTENT_RECEIVER);
			mIntent.putExtra("message", msg);
			context.sendBroadcast(mIntent);
			
//		}

	}

}
