package com.softenger.sofq.Dashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.softenger.sofq.R;
import com.softenger.sofq.api.response.PrivilegeResponse;

import java.util.List;

public class RoleAccessAdapter extends RecyclerView.Adapter<RoleAccessAdapter.RoleAccessHolder> {

    private static final String TAG = RoleAccessAdapter.class.getSimpleName();
    private Context mContext;
    private List<PrivilegeResponse> mDataList;

    public RoleAccessAdapter(Context mContext, List<PrivilegeResponse> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public RoleAccessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoleAccessHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_role_access, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoleAccessHolder holder, int position) {
        if (null == mDataList || mDataList.size() == 0) {
            Log.e(TAG, "empty data set");
            return;
        }

        PrivilegeResponse rolesResponse = mDataList.get(position);
        holder.setDataToView(rolesResponse);
    }

    @Override
    public int getItemCount() {
        if (null != mDataList) {
            return mDataList.size();
        }
        return 0;
    }

    class RoleAccessHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_role;
        private CheckBox cb_create_user;
        private CheckBox cb_add_project;
        private CheckBox cb_edit_project;
        private CheckBox cb_copy_project;
        private CheckBox cb_rename_project;
        private CheckBox cb_delete_project;
        private CheckBox cb_add_requirement;
        private CheckBox cb_delete_requirement;
        private CheckBox cb_submit_test_plan;
        private CheckBox cb_submit_rest_strategy;
        private PrivilegeResponse mRolesResponse;

        RoleAccessHolder(@NonNull View itemView) {
            super(itemView);
            cb_role = itemView.findViewById(R.id.cb_role);
            cb_create_user = itemView.findViewById(R.id.cb_create_user);
            cb_add_project = itemView.findViewById(R.id.cb_add_project);
            cb_edit_project = itemView.findViewById(R.id.cb_edit_project);
            cb_copy_project = itemView.findViewById(R.id.cb_copy_project);
            cb_rename_project = itemView.findViewById(R.id.cb_rename_project);
            cb_delete_project = itemView.findViewById(R.id.cb_delete_project);
            cb_add_requirement = itemView.findViewById(R.id.cb_add_requirement);
            cb_delete_requirement = itemView.findViewById(R.id.cb_delete_requirement);
            cb_submit_test_plan = itemView.findViewById(R.id.cb_submit_test_plan);
            cb_submit_rest_strategy = itemView.findViewById(R.id.cb_submit_rest_strategy);
        }

        void setDataToView(PrivilegeResponse rolesResponse) {
            if (null == rolesResponse) {
                Log.e(TAG, "empty dataset");
                return;
            }

            mRolesResponse = rolesResponse;

            cb_role.setText(mContext.getString(R.string.role, mRolesResponse.getRole()));
            if (mRolesResponse.isCreateUser()) {cb_create_user.setSelected(true);} else {cb_create_user.setSelected(false);}//cb_create_user.setText(mContext.getString(R.string.create_user, String.valueOf(mRolesResponse.isCreateUser())));
            if (mRolesResponse.isAddProject()) {cb_add_project.setSelected(true);} else {cb_add_project.setSelected(false);}//cb_add_project.setText(mContext.getString(R.string.add_project, String.valueOf(mRolesResponse.isAddProject())));
            if (mRolesResponse.isEditProject()) {cb_edit_project.setSelected(true);} else {cb_edit_project.setSelected(false);}//cb_edit_project.setText(mContext.getString(R.string.edit_project, String.valueOf(mRolesResponse.isEditProject())));
            if (mRolesResponse.isCopyProject()) {cb_copy_project.setSelected(true);} else {cb_copy_project.setSelected(false);}//cb_copy_project.setText(mContext.getString(R.string.copy_project, String.valueOf(mRolesResponse.isCopyProject())));
            if (mRolesResponse.isRenameProject()) {cb_rename_project.setSelected(true);} else {cb_rename_project.setSelected(false);}//cb_rename_project.setText(mContext.getString(R.string.rename_project, String.valueOf(mRolesResponse.isRenameProject())));
            if (mRolesResponse.isDeleteProject()) {cb_delete_project.setSelected(true);} else {cb_delete_project.setSelected(false);}//cb_delete_project.setText(mContext.getString(R.string.delete_project, String.valueOf(mRolesResponse.isDeleteProject())));
            if (mRolesResponse.isAddRequirement()) {cb_add_requirement.setSelected(true);} else {cb_add_requirement.setSelected(false);}//cb_add_requirement.setText(mContext.getString(R.string.add_requirement, String.valueOf(mRolesResponse.isAddRequirement())));
            if (mRolesResponse.isDeleteRequirement()) {cb_delete_requirement.setSelected(true);} else {cb_delete_requirement.setSelected(false);}//cb_delete_requirement.setText(mContext.getString(R.string.delete_requirement, String.valueOf(mRolesResponse.isDeleteRequirement())));
            if (mRolesResponse.isSubmitTestPlan()) {cb_submit_test_plan.setSelected(true);} else {cb_submit_test_plan.setSelected(false);}//cb_submit_test_plan.setText(mContext.getString(R.string.submit_test_plan, String.valueOf(mRolesResponse.isSubmitTestPlan())));
            if (mRolesResponse.isCreateUser()) {cb_submit_rest_strategy.setSelected(true);} else {cb_submit_rest_strategy.setSelected(false);}//cb_submit_rest_strategy.setText(mContext.getString(R.string.create_user, String.valueOf(mRolesResponse.isCreateUser())));
        }
    }
}
