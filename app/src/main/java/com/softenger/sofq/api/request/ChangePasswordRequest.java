package com.softenger.sofq.api.request;

public class ChangePasswordRequest {

    String newPassword;
    String oldPassword;
    int userId;
    String userName;

    public ChangePasswordRequest() {
    }

    public ChangePasswordRequest(int userId, String userName, String oldPassword, String newPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.userId = userId;
        this.userName = userName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest{" +
                "newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
