package com.waitingfy.callhelper;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 接收来去电广播
 * @author hy511
 *
 */
public class PhoneStatReceiver extends BroadcastReceiver {

	private static final String TAG = "PhoneStatReceiver";
	private static WindowManager wm;
//	private static DBHelper helper;
	private static TextView tv;
	private static boolean incomingFlag = false;
	private static String incoming_number = null;
	static int lastetState; // 最后的状态
	@Override
	public void onReceive(Context context, Intent intent) {
			// 拨打电话
			if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
				incomingFlag = false;
				String phoneNumber = intent
						.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
					new ShowArea(context).execute(phoneNumber);
			} else {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Service.TELEPHONY_SERVICE);

				switch (tm.getCallState()) {
				case TelephonyManager.CALL_STATE_RINGING:
					incomingFlag = true;
					incoming_number = intent.getStringExtra("incoming_number");
						new ShowArea(context).execute(incoming_number);
					break;
				//电话摘机
				case TelephonyManager.CALL_STATE_OFFHOOK:
					if (incomingFlag) {
					}
					break;
				//电话挂机
				case TelephonyManager.CALL_STATE_IDLE:
					System.exit(0);
				}
			}
			
	}

	/**
	 * 异步任务
	 * @author hy511
	 *
	 */
	class ShowArea extends AsyncTask<String, Void, TextView> {

		private Context context;

		public ShowArea(Context context) {
			this.context = context;
		}

		@Override
		protected TextView doInBackground(String... param) {
			//构建显示内容
			tv = new TextView(context);
			tv.setTextColor(0xffffffff);
			tv.setTextSize(16);
			tv.setPadding(0,0,0,10);
			tv.setShadowLayer(1, 2, 2, 0xff000000);
			tv.setPadding(0, 0, 0, 50);
			
			//得到连接
			String incomingNumber = param[0];
			if ((incomingNumber != null && incomingNumber.length() >= 7)
					) {
				tv.setText(GetLocationByNumber.getCallerInfo(incomingNumber ,context));
			} else {
				tv.setText("未知");
			}
			return tv;
		}

		@Override
		protected void onPostExecute(TextView textView) {
			//获取当前的界面
			wm = (WindowManager) context.getApplicationContext()
					.getSystemService(Context.WINDOW_SERVICE);
			//构造显示参数
			WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			
			//在所有窗体之上
			params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
			
			//设置失去焦点，不能被点击
			params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
					| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
			//高度宽度
			params.width = WindowManager.LayoutParams.WRAP_CONTENT;
			params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			//透明
			params.format = PixelFormat.RGBA_8888;
			//显示
				wm.addView(tv, params);

		}
	}
}
