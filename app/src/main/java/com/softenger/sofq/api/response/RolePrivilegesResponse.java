package com.softenger.sofq.api.response;

public class RolePrivilegesResponse {

    private int roleId;
    private String roleDesc;
    private String status;
    private CompanyMaster companyMaster;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public CompanyMaster getCompanyMaster() {
        return companyMaster;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public static class CompanyMaster {
        private int cmpId;
        private String cmpName;
        private String cmpDesc;
        private String cmpAddress;
        private String status;
        private String roleList;

        public int getCmpId() {
            return cmpId;
        }

        public void setCmpId(int cmpId) {
            this.cmpId = cmpId;
        }

        public String getCmpName() {
            return cmpName;
        }

        public void setCmpName(String cmpName) {
            this.cmpName = cmpName;
        }

        public String getCmpDesc() {
            return cmpDesc;
        }

        public void setCmpDesc(String cmpDesc) {
            this.cmpDesc = cmpDesc;
        }

        public String getCmpAddress() {
            return cmpAddress;
        }

        public void setCmpAddress(String cmpAddress) {
            this.cmpAddress = cmpAddress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRoleList() {
            return roleList;
        }

        public void setRoleList(String roleList) {
            this.roleList = roleList;
        }

        @Override
        public String toString() {
            return "CompanyMaster{" +
                    "cmpId=" + cmpId +
                    ", cmpName='" + cmpName + '\'' +
                    ", cmpDesc='" + cmpDesc + '\'' +
                    ", cmpAddress='" + cmpAddress + '\'' +
                    ", status='" + status + '\'' +
                    ", roleList='" + roleList + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RolePrivilegesResponse{" +
                "roleId=" + roleId +
                ", roleDesc='" + roleDesc + '\'' +
                ", status='" + status + '\'' +
                ", companyMaster=" + companyMaster +
                '}';
    }
}
