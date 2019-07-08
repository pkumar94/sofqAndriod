package com.softenger.sofq.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddUserRequest {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("role")
    @Expose
    private Role role;
    @SerializedName("userUniqueId")
    @Expose
    private String userUniqueId;
    @SerializedName("roleDesc")
    @Expose
    private String roleDesc;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                "userId='" + userId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                ", userUniqueId='" + userUniqueId + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }

    public static class Role {

        @SerializedName("roleId")
        @Expose
        private Integer roleId;

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        @Override
        public String toString() {
            return "Role{" +
                    "roleId=" + roleId +
                    '}';
        }
    }
}



