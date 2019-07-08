package com.softenger.sofq.api.response;

public class PrivilegeResponse {
    private int previlageId;
    private String role;
    private boolean planning;
    private boolean execution;
    private boolean defectManagement;
    private boolean analysis;
    private boolean userProfile;
    private boolean setting;
    private boolean changePassword;
    private boolean adminSetting;
    private boolean addProject;
    private boolean editProject;
    private boolean copyProject;
    private boolean renameProject;
    private boolean deleteProject;
    private boolean addRequirement;
    private boolean deleteRequirement;
    private boolean submitTestPlan;
    private boolean submitTestStratergy;
    private boolean createUser;

    public int getPrevilageId() {
        return previlageId;
    }

    public void setPrevilageId(int previlageId) {
        this.previlageId = previlageId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isPlanning() {
        return planning;
    }

    public void setPlanning(boolean planning) {
        this.planning = planning;
    }

    public boolean isExecution() {
        return execution;
    }

    public void setExecution(boolean execution) {
        this.execution = execution;
    }

    public boolean isDefectManagement() {
        return defectManagement;
    }

    public void setDefectManagement(boolean defectManagement) {
        this.defectManagement = defectManagement;
    }

    public boolean isAnalysis() {
        return analysis;
    }

    public void setAnalysis(boolean analysis) {
        this.analysis = analysis;
    }

    public boolean isUserProfile() {
        return userProfile;
    }

    public void setUserProfile(boolean userProfile) {
        this.userProfile = userProfile;
    }

    public boolean isSetting() {
        return setting;
    }

    public void setSetting(boolean setting) {
        this.setting = setting;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    public boolean isAdminSetting() {
        return adminSetting;
    }

    public void setAdminSetting(boolean adminSetting) {
        this.adminSetting = adminSetting;
    }

    public boolean isAddProject() {
        return addProject;
    }

    public void setAddProject(boolean addProject) {
        this.addProject = addProject;
    }

    public boolean isEditProject() {
        return editProject;
    }

    public void setEditProject(boolean editProject) {
        this.editProject = editProject;
    }

    public boolean isCopyProject() {
        return copyProject;
    }

    public void setCopyProject(boolean copyProject) {
        this.copyProject = copyProject;
    }

    public boolean isRenameProject() {
        return renameProject;
    }

    public void setRenameProject(boolean renameProject) {
        this.renameProject = renameProject;
    }

    public boolean isDeleteProject() {
        return deleteProject;
    }

    public void setDeleteProject(boolean deleteProject) {
        this.deleteProject = deleteProject;
    }

    public boolean isAddRequirement() {
        return addRequirement;
    }

    public void setAddRequirement(boolean addRequirement) {
        this.addRequirement = addRequirement;
    }

    public boolean isDeleteRequirement() {
        return deleteRequirement;
    }

    public void setDeleteRequirement(boolean deleteRequirement) {
        this.deleteRequirement = deleteRequirement;
    }

    public boolean isSubmitTestPlan() {
        return submitTestPlan;
    }

    public void setSubmitTestPlan(boolean submitTestPlan) {
        this.submitTestPlan = submitTestPlan;
    }

    public boolean isSubmitTestStratergy() {
        return submitTestStratergy;
    }

    public void setSubmitTestStratergy(boolean submitTestStratergy) {
        this.submitTestStratergy = submitTestStratergy;
    }

    public boolean isCreateUser() {
        return createUser;
    }

    public void setCreateUser(boolean createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "PrivilegeResponse{" +
                "previlageId=" + previlageId +
                ", role='" + role + '\'' +
                ", planning=" + planning +
                ", execution=" + execution +
                ", defectManagement=" + defectManagement +
                ", analysis=" + analysis +
                ", userProfile=" + userProfile +
                ", setting=" + setting +
                ", changePassword=" + changePassword +
                ", adminSetting=" + adminSetting +
                ", addProject=" + addProject +
                ", editProject=" + editProject +
                ", copyProject=" + copyProject +
                ", renameProject=" + renameProject +
                ", deleteProject=" + deleteProject +
                ", addRequirement=" + addRequirement +
                ", deleteRequirement=" + deleteRequirement +
                ", submitTestPlan=" + submitTestPlan +
                ", submitTestStratergy=" + submitTestStratergy +
                ", createUser=" + createUser +
                '}';
    }
}
