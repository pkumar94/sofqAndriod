package com.softenger.sofq.Dashboard.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.cache.DiskBasedCache;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.softenger.sofq.Dashboard.adapter.UserListAdapter;
import com.softenger.sofq.R;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.URLS;
import com.softenger.sofq.api.response.UserDetailsResponse;
import com.softenger.sofq.common.ICommonConstants;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminDashboardFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = AdminDashboardFragment.class.getSimpleName();
    private static final String ARG_SCREEN_TYPE = "PAGE_TYPE";

    public int mScreenType;

    private RecyclerView rvUsersList;

    private OnFragmentInteractionListener mListener;

    public AdminDashboardFragment() {
        // Required empty public constructor
    }


    public static AdminDashboardFragment newInstance(int param1) {
        AdminDashboardFragment fragment = new AdminDashboardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCREEN_TYPE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate::");

        if (getArguments() != null) {
            mScreenType = getArguments().getInt(ARG_SCREEN_TYPE);
            Log.i(TAG, "onCreate::" + mScreenType);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_dashboard, container, false);
        rvUsersList = view.findViewById(R.id.rvUsersList);
        FloatingActionButton fabAddNewUser = view.findViewById(R.id.fabAddNewUser);
        rvUsersList.setLayoutManager(new LinearLayoutManager(getContext()));

        /*Fetch data*/
        if (mScreenType == ICommonConstants.UPDATE_PASSWORD_OR_DELETE) {
            Log.i(TAG, "onCreateView::opening for update password");
            fabAddNewUser.setVisibility(View.GONE);
            loadViewUserData();
        } else {
            Log.i(TAG, "onCreateView::opening for update user details");
            fabAddNewUser.setVisibility(View.VISIBLE);
            fabAddNewUser.setOnClickListener(this);
            loadManageUserData();
        }
        return view;
    }


    public void onDeleteUserButtonPressed(UserDetailsResponse.UserDetails userDetails) {
        if (mListener != null) {
            mListener.deleteUser(userDetails);
        }
    }

    public void onChangePasswordButtonPressed(UserDetailsResponse.UserDetails userDetails) {
        if (mListener != null) {
            mListener.changeUserPassword(userDetails);
        }
    }

    public void onViewUserDetailsButtonPressed(UserDetailsResponse.UserDetails userDetails) {
        Log.i(TAG, "onViewUserDetailsButtonPressed");
        if (mListener != null) {
            mListener.viewUserDetails(userDetails);
        }
    }

    public void onAddNewUserButtonPressed() {
        if (mListener != null) {
            mListener.addNewUser();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAddNewUser:
                onAddNewUserButtonPressed();
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void removeUserFromList(UserDetailsResponse.UserDetails userDetails) {
        RecyclerView.Adapter adapter = rvUsersList.getAdapter();
        if (adapter instanceof UserListAdapter) {
            UserListAdapter userListAdapter = (UserListAdapter) adapter;
            userListAdapter.removeUser(userDetails);
        }
    }

    public interface OnFragmentInteractionListener {

        void viewUserDetails(UserDetailsResponse.UserDetails userDetails);

        void changeUserPassword(UserDetailsResponse.UserDetails userDetails);

        void deleteUser(UserDetailsResponse.UserDetails userDetails);

        void editUser(UserDetailsResponse.UserDetails userDetails);

        void addNewUser();
    }

    private void loadViewUserData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLS.URL_GET_USER_LIST, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response.length() > 0) {
                    Log.e("Response", "Success::" + response.toString());
                    UserDetailsResponse.UserDetails[] userDetails = new Gson().fromJson(response.toString(), UserDetailsResponse.UserDetails[].class);
                    List<UserDetailsResponse.UserDetails> dataList = new ArrayList<>();
                    for (UserDetailsResponse.UserDetails userDetails1 : userDetails) {
                        if (!userDetails1.getRoleDesc().equalsIgnoreCase("Admin")) {
                            dataList.add(userDetails1);
                        }
                    }
                    rvUsersList.setAdapter(new UserListAdapter(dataList, AdminDashboardFragment.this, mScreenType));

                } else {
                    Log.e("Response", "Failure::" + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
            }
        });

        Log.e("req", "Request starting::Url::" + jsonArrayRequest.getUrl());
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonArrayRequest);
    }

    private void loadManageUserData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLS.URL_GET_USER_LIST, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response.length() > 0) {
                    Log.e("Response", "Success::" + response.toString());
                    UserDetailsResponse.UserDetails[] userDetails = new Gson().fromJson(response.toString(), UserDetailsResponse.UserDetails[].class);
                    rvUsersList.setAdapter(new UserListAdapter(Arrays.asList(userDetails), AdminDashboardFragment.this, mScreenType));
                    //Toast.makeText(SofqApplication.getInstance(), "loadManageUserData::response received", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Response", "Failure::" + response.toString());
                    //onFinishedListener.onFailure(LoginContract.LOGIN_ACTION, registerResponse.getResponseCode());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
            }
        });

        Log.e("req", "Request starting");
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonArrayRequest);
    }
}
