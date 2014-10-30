package com.waitingfy.callhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class yun_location extends RelativeLayout {
	TextView yun_location;
	outgoingReceiver locationReceiver;
	String phoneNumber="15580192330",incoming_number="15555555555";
	TextView textView;
	public static final String SONG_NAME = "hehe";
	
	public yun_location(final Context context, AttributeSet attrs) {
		super(context, attrs);
		textView= new TextView(context);
		locationReceiver =new outgoingReceiver();
		IntentFilter songfilter = new IntentFilter();
		songfilter.addAction(SONG_NAME);
		context.registerReceiver(locationReceiver,songfilter);
		final Handler handler2 =new Handler();
		final Runnable runnable=new Runnable() {
			public void run() {
				update(context);
				handler2.postDelayed(this, 10000000);

			}
	};
		handler2.postDelayed(runnable, 2000);
	}
	public void update(Context context){
		RelativeLayout.LayoutParams textviewParams = new RelativeLayout.LayoutParams(ViewGroup
				 .LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		textView.setPadding(10, 20, 5, 15);
		textView.setTextSize(16);
		textView.setTextColor(0xffffffff);
		textView.setShadowLayer(1, 2, 2, 0xff000000);
		this.addView(textView,textviewParams);
	}
	public class outgoingReceiver extends BroadcastReceiver{
		public void onReceive(Context context, Intent intent) {
			String msg=intent.getStringExtra("name");//接收信息
			textView.setText(msg);
		}
	}
}
