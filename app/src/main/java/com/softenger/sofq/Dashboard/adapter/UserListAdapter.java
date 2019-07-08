package com.softenger.sofq.Dashboard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.softenger.sofq.Dashboard.view.fragment.AdminDashboardFragment;
import com.softenger.sofq.R;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.response.UserDetailsResponse;
import com.softenger.sofq.common.ICommonConstants;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserHolder> {

    private static final String TAG = UserListAdapter.class.getCanonicalName();
    private List<UserDetailsResponse.UserDetails> mDataList;
    private AdminDashboardFragment mFragment;
    private int mViewFor;

    public UserListAdapter(List<UserDetailsResponse.UserDetails> dataList, AdminDashboardFragment fragment, int viewFor) {
        this.mDataList = dataList;
        this.mFragment = fragment;
        this.mViewFor = viewFor;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        UserDetailsResponse.UserDetails userDetails = mDataList.get(position);
        holder.initView(userDetails);
    }

    @Override
    public int getItemCount() {
        if (null != mDataList && mDataList.size() > 0) {
            return mDataList.size();
        }
        return 0;
    }

    public void removeUser(UserDetailsResponse.UserDetails userDetails) {
        mDataList.remove(userDetails);
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        UserDetailsResponse.UserDetails mUserDetails;
        TextView tvName;
        TextView tvUserName;
        TextView tvRoleName;
        TextView tvStatusName;
        ImageButton ibChangePasswd;
        ImageButton ibDelete;
        RelativeLayout llUser;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvRoleName = itemView.findViewById(R.id.tvRoleName);
            tvStatusName = itemView.findViewById(R.id.tvStatusName);
            ibChangePasswd = itemView.findViewById(R.id.ibChangePasswd);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            llUser = itemView.findViewById(R.id.ll_user);
        }

        public void initView(UserDetailsResponse.UserDetails userDetails) {
            if (null == userDetails){
                Log.e(TAG, "found null or empty userDetails");
                return;
            }

            mUserDetails = userDetails;
            tvName.setText(SofqApplication.getInstance().getString(R.string.txt_name, mUserDetails.getFirstName(), mUserDetails.getLastName()));
            tvUserName.setText(SofqApplication.getInstance().getString(R.string.txt_user_name, mUserDetails.getUserName()));
            tvRoleName.setText(SofqApplication.getInstance().getString(R.string.txt_role, mUserDetails.getRoleDesc()));
            tvStatusName.setText(SofqApplication.getInstance().getString(R.string.txt_status, mUserDetails.getStatus()));
            Log.i(TAG,"initView::" + mViewFor);
            if (mViewFor == ICommonConstants.UPDATE_PASSWORD_OR_DELETE) {
                ibDelete.setVisibility(View.VISIBLE);
                ibChangePasswd.setVisibility(View.VISIBLE);
                tvRoleName.setVisibility(View.VISIBLE);
                llUser.setOnClickListener(null);

                ibChangePasswd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragment.onChangePasswordButtonPressed(mUserDetails);
                    }
                });

                ibDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mFragment){
                            mFragment.onDeleteUserButtonPressed(mUserDetails);
                        }
                    }
                });

            } else if (mViewFor == ICommonConstants.UPDATE_DETAILS){
                ibDelete.setVisibility(View.GONE);
                ibChangePasswd.setVisibility(View.VISIBLE);
                tvRoleName.setVisibility(View.GONE);
                ibChangePasswd.setImageResource(R.drawable.ic_edit);
                llUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG,"clicked on edit page");
                        mFragment.onViewUserDetailsButtonPressed(mUserDetails);
                    }
                });
            }
        }
    }
}
