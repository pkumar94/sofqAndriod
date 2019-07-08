package com.softenger.sofq.user.presentor;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.softenger.sofq.R;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.request.LoginRequest;
import com.softenger.sofq.api.response.UserData;

public class LoginPresenterImplementation implements LoginContract, LoginContract.Presenter, LoginContract.NetworkIntracter.OnFinishedListener {

    private static final String TAG = LoginPresenterImplementation.class.getSimpleName();
    private LoginRequest mLoginData;
    private LoginContract.MainView mMainView;
    private NetworkIntracter mNetworkIntracter;

    {
        mLoginData = new LoginRequest();
    }

    public LoginPresenterImplementation(LoginContract.MainView mainView, NetworkIntracter networkIntracter) {
        mMainView = mainView;
        mNetworkIntracter = networkIntracter;
    }

    @Override
    public void onLoginClicked() {

        if (TextUtils.isEmpty(mLoginData.getUserName())) {
            mMainView.onResponseFailure(LoginContract.LOGIN_ACTION, SofqApplication.resources.getString(R.string.error_msg_enter_valid_user_id));
            return;
        }

        if (TextUtils.isEmpty(mLoginData.getPassword())) {
            mMainView.onResponseFailure(LoginContract.LOGIN_ACTION, SofqApplication.resources.getString(R.string.error_msg_enter_password));
            return;
        }
        mMainView.showProgress();
        mNetworkIntracter.doLogin(mLoginData, this);
    }

    @Override
    public void updateUserId(String userId) {

        mLoginData.setUserName(userId);
    }

    @Override
    public void updatePassword(String password) {
        mLoginData.setPassword(password);
    }

    @Override
    public void onFinished(int actionType, int userType, String response) {
        Log.i(TAG, "onFinished::actionType::" + actionType + " userType::" + userType + " response::" + response);
        storeUserDetails(response);
        mMainView.hideProgress();
        mMainView.onResponseSuccess(actionType, userType, response);
    }

    private void storeUserDetails(String response) {
        Log.i(TAG, "onFinished::response::" + response);
        UserData registerResponse = new Gson().fromJson(response.toString(), UserData.class);
        if (!TextUtils.isEmpty(registerResponse.getId())) {
            SofqApplication.setUserId(registerResponse.getId());
        }
        if (!TextUtils.isEmpty(registerResponse.getUserName())) {
            SofqApplication.setUserName(registerResponse.getUserName());
        }
        if (registerResponse.getRoleId() != -1) {
            SofqApplication.setUserRoleId(registerResponse.getRoleId());
        }
        mNetworkIntracter.fetchRolePrivilegesData(registerResponse.getRoleId());

    }

    @Override
    public void onFailure(int actionType, String error) {
        Log.i(TAG, "onFailure::actionType::" + actionType + " error::" + error);
        mMainView.hideProgress();
        mMainView.onResponseFailure(actionType, error);
    }
}
