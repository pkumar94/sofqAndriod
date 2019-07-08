package com.softenger.sofq;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.softenger.sofq.util.Constants;

public class SofqApplication extends MultiDexApplication {

    private static final String TAG = SofqApplication.class.getSimpleName();
    public static Resources resources;

    private static Application sInstance;

    public static synchronized Application getInstance() {
        return sInstance;
    }
    private static SharedPreferences mPreference;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        resources = getResources();

        sInstance = this;

        mPreference = getSharedPreferences(Constants.KEY_SOFQ_PREFERENCE, MODE_PRIVATE);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    public static void registerForPreferenceChange(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreference.registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unRegisterForPreferenceChange(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreference.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static String getUserId() {
        String userId = mPreference.getString(Constants.KEY_PREFERENCE_USER_ID, "");
        Log.i(TAG, "getUserId::userId::" + userId);
        return userId;
    }

    public static void setUserId(String userId) {
        Log.i(TAG, "setUserId::userId::" + userId);
        SharedPreferences.Editor lEditor = mPreference.edit();
        lEditor.putString(Constants.KEY_PREFERENCE_USER_ID, userId);
        lEditor.apply();
    }

    public static String getUserName() {
        String userName = mPreference.getString(Constants.KEY_PREFERENCE_USER_NAME,"");
        Log.i(TAG, "getUserName::userName::" + userName);
        return userName;
    }

    public static void setUserName(String userName) {
        Log.i(TAG, "setUserId::userName::" + userName);
        SharedPreferences.Editor lEditor = mPreference.edit();
        lEditor.putString(Constants.KEY_PREFERENCE_USER_NAME, userName);
        lEditor.apply();
    }

    public static int getUserRoleId() {
        int userRoleId = mPreference.getInt(Constants.KEY_PREFERENCE_USER_ROLE,-1);
        Log.i(TAG, "getUserName::userRoleId::" + userRoleId);
        return userRoleId;
    }

    public static void setUserRoleId(int userRoleId) {
        Log.i(TAG, "setUserRole::userRoleId::" + userRoleId);
        SharedPreferences.Editor lEditor = mPreference.edit();
        lEditor.putInt(Constants.KEY_PREFERENCE_USER_ROLE, userRoleId);
        lEditor.apply();
    }

    public static String getUserPrivilegesData() {
        String userPrivileges = mPreference.getString(Constants.KEY_PREFERENCE_USER_PRIVILEGES,"");
        Log.i(TAG, "getUserPrivilegesData::userPrivileges::" + userPrivileges);
        return userPrivileges;
    }

    public static void setUserPrivilegesData(String userPrivilegesJson) {
        Log.i(TAG, "setUserPrivilegesData::userPrivilegesJson::" + userPrivilegesJson);
        SharedPreferences.Editor lEditor = mPreference.edit();
        lEditor.putString(Constants.KEY_PREFERENCE_USER_PRIVILEGES, userPrivilegesJson);
        lEditor.apply();
    }

    public static void clearPreferences() {
        Log.i(TAG, "clearPreferences::");
        SharedPreferences.Editor lEditor = mPreference.edit();
        lEditor.clear();
        lEditor.apply();
    }
}
