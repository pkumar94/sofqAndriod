package com.softenger.sofq.Dashboard.presenter;

import com.softenger.sofq.api.request.AssetRequest;
import com.softenger.sofq.api.request.ParticipantRequest;
import com.softenger.sofq.user.presentor.LoginContract;

public class NetworkInteractionImplementation implements DashboardAdminContract.NetworkIntracter{

    @Override
    public void fetchUserData(LoginContract.NetworkIntracter.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void doDeleteUser(AssetRequest assetRequest, LoginContract.NetworkIntracter.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void doUpdatePassword(ParticipantRequest participantRequest, LoginContract.NetworkIntracter.OnFinishedListener onFinishedListener) {

    }
}
