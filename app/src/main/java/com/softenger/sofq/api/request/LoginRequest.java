package com.softenger.sofq.api.request;

public class LoginRequest {

    //private String userId = null;
    private String userName = null;
    private String password = null;
    private int deviceType = 0;

    public LoginRequest() {}

    public LoginRequest(String userName, String password, int deviceType) {
        //this.userId = email;
        this.userName = userName;
        this.password = password;
        this.deviceType = deviceType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", deviceType=" + deviceType +
                '}';
    }


}
