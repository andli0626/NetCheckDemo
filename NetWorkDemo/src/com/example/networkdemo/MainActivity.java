package com.example.networkdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView netStatusTXT;
	
	public MessageReceiver mMessageReceiver;
	public static String ACTION_INTENT_RECEIVER = "com.andli.receivermsg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		netStatusTXT = (TextView) findViewById(R.id.netstatus_txt);

		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_INTENT_RECEIVER);
		registerReceiver(mMessageReceiver, filter);
	}

	// 在销毁时要与广播解绑
	@Override
	protected void onDestroy() {
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}

	// 消息接收广播
	public class MessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ACTION_INTENT_RECEIVER)) {
				netStatusTXT.setText(intent.getStringExtra("message"));
			}
		}
	}

}
