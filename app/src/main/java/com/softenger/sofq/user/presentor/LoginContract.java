package com.softenger.sofq.user.presentor;

import com.softenger.sofq.api.request.AssetRequest;
import com.softenger.sofq.api.request.LoginRequest;
import com.softenger.sofq.api.request.ParticipantRequest;

public interface LoginContract {

    int LOGIN_ACTION = 1;

    interface Presenter {

        void onLoginClicked();

        void updateUserId(String uname);

        void updatePassword(String password);

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void onResponseSuccess(int actionType,int userType, String response);

        void onResponseFailure(int actionType, String error);

    }

    interface NetworkIntracter {

        interface OnFinishedListener {

            void onFinished(int actionType, int userType, String response);

            void onFailure(int actionType,  String error);

        }

        void doLogin(LoginRequest loginRequest, OnFinishedListener onFinishedListener);

        void storeAssets(AssetRequest assetRequest, final OnFinishedListener onFinishedListener);

        void storeParticipant(ParticipantRequest participantRequest, final OnFinishedListener onFinishedListener);

        void fetchAssetData();

        void fetchRolePrivilegesData(int roleId);
    }
}
