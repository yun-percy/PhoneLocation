package com.waitingfy.callhelper;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * 接收来去电广播
 * @author hy511
 *
 */
public class PhoneStatReceiver extends BroadcastReceiver {

	private static String phoneNumber= "18555555555";
	static int lastetState; // 最后的状态
	public static final String SONG_NAME = "hehe";
	@Override
	public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
				phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
				
			} else {
//				TelephonyManager tm = (TelephonyManager) context
//						.getSystemService(Service.TELEPHONY_SERVICE);
				phoneNumber = intent.getStringExtra("incoming_number");
			}
			Intent song=new Intent();
			song.setAction(SONG_NAME);  //标记作用的
			if(phoneNumber != null){
			System.out.println("%%%%%%%%%%%%%%%%%%"+phoneNumber);
			
			String location=GetLocationByNumber.getCallerInfo(phoneNumber,context);
			song.putExtra("name",location);//发送的消息
			context.sendBroadcast(song);
			}
	}
}
