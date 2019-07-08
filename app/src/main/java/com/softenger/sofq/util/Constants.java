package com.softenger.sofq.util;

public interface Constants {

    //String URL_PAYTM_VERIFICATION = "http://ec2-18-188-157-92.us-east-2.compute.amazonaws.com/api/?r=Payment/VerifyPayment";
    String URL_PAYTM_VERIFICATION = "http://192.168.25.113/api/?r=Payment/VerifyPayment";
    //String URL_PAYTM_GENERATECHECKSUM = "http://appsplanet.co.in/tourisz/?r=Payment/GenerateChecksum";
    String URL_PAYTM_GENERATECHECKSUM = "http://192.168.25.113/api/?r=Payment/GenerateChecksumDV";
    //String URL_PAYTM_GENERATECHECKSUM = "http://ec2-18-188-157-92.us-east-2.compute.amazonaws.com/api/?r=Payment/GenerateChecksumDV";
    //String PAYTM_URL_CALL_BACK="http://appsplanet.co.in/tourisz/?r=Payment/VerifyChecksum";
    String PAYTM_URL_CALL_BACK = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";

    String DATE_FORMAT = "dd/MM/yyyy";

    String TIME_FORMAT = "HH:mm:ss";

    String DATE_TIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;

    String SERVER_DATE_FORMAT = "yyyy-MM-dd";

    String SERVER_TIME_FORMAT = "HH:mm:ss";

    String SERVER_DATE_TIME_FORMAT = SERVER_DATE_FORMAT + " " + SERVER_TIME_FORMAT;



    String KEY_USER = "user_key";
    String APP_NAME = "Tourisz";
    String FCM_TOKEN = "fcm_token";
    String USER_DATA = "user_data";
    String UI_SETTINGS = "ui_settings";


    String ACTION = "upload";
    String ACTION_DONE = "done";
    String ACTION_PAYMENT_DONE = "pay_done";
    String ACTION_PAYMENT_FAIL = "pay_fail";

    /*Constants for shared preferences*/

    String KEY_SOFQ_PREFERENCE = "SOFQ_PREFERENCES";
    String KEY_PREFERENCE_USER_ID = "USER_ID";
    String KEY_PREFERENCE_USER_NAME = "USER_NAME";
    String KEY_PREFERENCE_USER_ROLE = "USER_ROLE";
    String KEY_PREFERENCE_USER_PRIVILEGES = "USER_PRIVILEGES";

}
