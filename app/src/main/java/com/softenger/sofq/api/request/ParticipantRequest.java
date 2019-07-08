package com.softenger.sofq.api.request;

public class ParticipantRequest {
    private String userId;
    private String loginUserId;

    public ParticipantRequest() {
    }

    public ParticipantRequest(String userId, String loginUserId) {
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
        return "ParticipantRequest{" +
                "userId='" + userId + '\'' +
                ", loginUserId='" + loginUserId + '\'' +
                '}';
    }
}
