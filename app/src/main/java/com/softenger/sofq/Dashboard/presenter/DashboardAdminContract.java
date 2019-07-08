package com.softenger.sofq.Dashboard.presenter;

import com.softenger.sofq.api.request.AssetRequest;
import com.softenger.sofq.api.request.ParticipantRequest;
import com.softenger.sofq.user.presentor.LoginContract;

public interface DashboardAdminContract {

    interface Presenter {

        void onViewUserDetailClick();

        void updateUserId(String uname);

        void updatePassword(String newPassword);

    }

    interface AdminMainView {

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

        void fetchUserData(LoginContract.NetworkIntracter.OnFinishedListener onFinishedListener);

        void doDeleteUser(AssetRequest assetRequest, final LoginContract.NetworkIntracter.OnFinishedListener onFinishedListener);

        void doUpdatePassword(ParticipantRequest participantRequest, final LoginContract.NetworkIntracter.OnFinishedListener onFinishedListener);

    }


}
