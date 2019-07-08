package com.softenger.sofq.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class RolesResponse {

    private Role[] userArray;

    public Role[] getUserArray() {
        return userArray;
    }

    public void setUserArray(Role[] userArray) {
        this.userArray = userArray;
    }

    @Override
    public String toString() {
        return "UserDetailsResponse{" +
                "userArray=" + Arrays.toString(userArray) +
                '}';
    }

    public static class Role implements Parcelable {
        private Integer roleId;
        private String roleDesc;
        private String status;
        private String companyMaster;

        protected Role(Parcel in) {

            roleId = in.readInt();
            roleDesc = in.readString();
            status = in.readString();
            companyMaster = in.readString();

        }

        public static final Creator<Role> CREATOR = new Creator<Role>() {
            @Override
            public Role createFromParcel(Parcel in) {
                return new Role(in);
            }

            @Override
            public Role[] newArray(int size) {
                return new Role[size];
            }
        };

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

        public String getCompanyMaster() {
            return companyMaster;
        }

        public void setCompanyMaster(String companyMaster) {
            this.companyMaster = companyMaster;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        @Override
        public String toString() {
            return "UserDetailsResponse{" +
                    "roleId='" + roleId + '\'' +
                    ", roleDesc='" + roleDesc + '\'' +
                    ", status='" + status + '\'' +
                    ", companyMaster='" + companyMaster + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(roleId);
            dest.writeString(roleDesc);
            dest.writeString(status);
            dest.writeString(companyMaster);

        }
    }
}
