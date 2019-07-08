package com.softenger.sofq.api.request;

public class AssetRequest {
    String userLoginId;
    String loginUser;
    String status;


    public AssetRequest() {}
    public AssetRequest(String userLoginId, String loginUser, String status) {
        this.userLoginId = userLoginId;
        this.loginUser = loginUser;
        this.status = status;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AssetRequest{" +
                "userLoginId='" + userLoginId + '\'' +
                ", loginUser='" + loginUser + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
