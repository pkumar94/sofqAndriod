package com.softenger.sofq.api.response;

public class AssetLoginResponse {
    private String $class;
    private String userLoginId;
    private String loginUser;
    private String status;

    public AssetLoginResponse () {
    }

    public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
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
        return "AssetLoginResponse {" +
                "$class='" + $class + '\'' +
                ", userLoginId='" + userLoginId + '\'' +
                ", loginUser='" + loginUser + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
