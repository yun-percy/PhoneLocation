package com.waitingfy.callhelper;



import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class TestNum extends Activity{
	String number;
	TextView txtView ,come ;
	EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		editText = (EditText)findViewById(R.id.edit);
		txtView = (TextView)findViewById(R.id.txtResult);
		editText.addTextChangedListener(mTextWatcher);
		number = editText.getText().toString();
		come=(TextView)findViewById(R.id.incall);
		String m="18565710000";
		String location= GetLocationByNumber.getCallerInfo(m,getApplicationContext());
		come.setText(location);
	}
	TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,
                int arg3) {
            temp = s;
        }
       
        public void onTextChanged(CharSequence s, int arg1, int arg2,
                int arg3) {
        }
       
        public void afterTextChanged(Editable s) {
            editStart = editText.getSelectionStart();
            editEnd = editText.getSelectionEnd();
            if (temp.length() >= 7) {
            	number = editText.getText().toString();
				String location= GetLocationByNumber.getCallerInfo(number,getApplicationContext());
				txtView.setText(location);
            }
            else{
            	txtView.setText("");
            }
        }
    };

}
