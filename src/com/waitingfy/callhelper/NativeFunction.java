package com.waitingfy.callhelper;



public class NativeFunction  {

    public static native byte[]  getLocationFromJni(String filename,String number);
    /** Load jni .so on initialization */
    static {
         System.loadLibrary("phone-number-jni");//注:不需要lib开头和.so结束,只需要中间的名字  
    }

}
