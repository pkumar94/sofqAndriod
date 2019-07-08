package com.softenger.sofq.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class UserDetailsResponse {

    private UserDetails[] userArray;

    public UserDetails[] getUserArray() {
        return userArray;
    }

    public void setUserArray(UserDetails[] userArray) {
        this.userArray = userArray;
    }

    @Override
    public String toString() {
        return "UserDetailsResponse{" +
                "userArray=" + Arrays.toString(userArray) +
                '}';
    }

    public static class UserDetails implements Parcelable {
        private String userId;
        private String userName;
        private String password;
        private String firstName;
        private String lastName;
        private String profileImage;
        private String role;
        private String roleDesc;
        private String status;
        private String emailId;
        private String userUniqueId;
        private String userList;

        public UserDetails() {}
        protected UserDetails(Parcel in) {
            userId = in.readString();
            userName = in.readString();
            password = in.readString();
            firstName = in.readString();
            lastName = in.readString();
            profileImage = in.readString();
            role = in.readString();
            roleDesc = in.readString();
            status = in.readString();
            emailId = in.readString();
            userUniqueId = in.readString();
            userList = in.readString();
        }

        public static final Creator<UserDetails> CREATOR = new Creator<UserDetails>() {
            @Override
            public UserDetails createFromParcel(Parcel in) {
                return new UserDetails(in);
            }

            @Override
            public UserDetails[] newArray(int size) {
                return new UserDetails[size];
            }
        };

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getRoleDesc() {
            return roleDesc;
        }

        public void setRoleDesc(String roleDesc) {
            this.roleDesc = roleDesc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getUserUniqueId() {
            return userUniqueId;
        }

        public void setUserUniqueId(String userUniqueId) {
            this.userUniqueId = userUniqueId;
        }

        public String getUserList() {
            return userList;
        }

        public void setUserList(String userList) {
            this.userList = userList;
        }

        @Override
        public String toString() {
            return "UserDetailsResponse{" +
                    "userId='" + userId + '\'' +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", profileImage='" + profileImage + '\'' +
                    ", role='" + role + '\'' +
                    ", roleDesc='" + roleDesc + '\'' +
                    ", status='" + status + '\'' +
                    ", emailId='" + emailId + '\'' +
                    ", userUniqueId='" + userUniqueId + '\'' +
                    ", userList='" + userList + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(userId);
            dest.writeString(userName);
            dest.writeString(password);
            dest.writeString(firstName);
            dest.writeString(lastName);
            dest.writeString(profileImage);
            dest.writeString(role);
            dest.writeString(roleDesc);
            dest.writeString(status);
            dest.writeString(emailId);
            dest.writeString(userUniqueId);
            dest.writeString(userList);
        }
    }
}
