package com.waitingfy.callhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class yun_location_display extends FrameLayout {
	
	public yun_location_display(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
        String phonenumber ="15580092330";
        yun_setlocation(context,phonenumber);

	}

	private void yun_setlocation(Context context,String phonenumber) {
		TextView mlocation=new TextView(context);
		String mlocation1=GetLocationByNumber.getCallerInfo(phonenumber,context);
		mlocation.setText(mlocation1);
		LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.setMargins(450, 700,0,500);
		mlocation.setLayoutParams(params);
		mlocation.setTextColor(0xffffffff);
		mlocation.setTextSize(16);
		mlocation.setShadowLayer(1, 2, 2, 0xff000000);
        this.addView(mlocation);

	}
}
