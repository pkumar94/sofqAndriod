package com.softenger.sofq.api.response;

public class ParticipantLoginResponse {
    private String userId;
    private String loginUserId;

    public ParticipantLoginResponse() {
    }

    public ParticipantLoginResponse(String userId, String loginUserId) {
        this.userId = userId;
        this.loginUserId = loginUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    @Override
    public String toString() {
        return "ParticipantLoginResponse{" +
                "userId='" + userId + '\'' +
                ", loginUserId='" + loginUserId + '\'' +
                '}';
    }
}
