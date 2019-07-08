package com.softenger.sofq.Dashboard.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.softenger.sofq.Dashboard.adapter.RoleSpinnerAdapter;
import com.softenger.sofq.R;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.URLS;
import com.softenger.sofq.api.request.AddUserRequest;
import com.softenger.sofq.api.response.RolesResponse;
import com.softenger.sofq.api.response.UserDetailsResponse;
import com.softenger.sofq.util.TextViewUtils;

import org.json.JSONArray;

import java.util.Arrays;

public class AddViewUserFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = AddViewUserFragment.class.getSimpleName();
    private static final String KEY_USER_DETAILS = "param1";

    private OnFragmentInteractionListener mListener;

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etUserUniqueId;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etRoleDesc;
    private TextView tvTitleRole;
    private Spinner spinnerRole;
    private TextView tvTitleStatus;
    private Spinner spinnerStatus;
    private Button btnLogin;
    private Button btnCancel;

    private UserDetailsResponse.UserDetails mUserDetails;
    private AddUserRequest mAddUpdateUserRequest;

    private Context mContext;

    public AddViewUserFragment() {
        // Required empty public constructor
    }

    public static AddViewUserFragment newInstance(UserDetailsResponse.UserDetails userDetails) {
        AddViewUserFragment fragment = new AddViewUserFragment();
        if (userDetails != null) {
            Bundle args = new Bundle();
            args.putParcelable(KEY_USER_DETAILS, userDetails);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserDetails = getArguments().getParcelable(KEY_USER_DETAILS);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_update_user, container, false);
        mContext = getContext();
        initControls(view);
        return view;
    }

    private void initControls(View view) {
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etEmail = view.findViewById(R.id.etEmail);
        etUserUniqueId = view.findViewById(R.id.etUserUniqueId);
        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        etRoleDesc = view.findViewById(R.id.etRoleDesc);
        tvTitleRole = view.findViewById(R.id.tvTitleRole);
        spinnerRole = view.findViewById(R.id.spinnerRole);
        tvTitleStatus = view.findViewById(R.id.tvTitleStatus);
        spinnerStatus = view.findViewById(R.id.spinnerStatus);
        btnLogin = view.findViewById(R.id.btnAddUpdateUser);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnLogin.setText(getString(R.string.btn_submit));
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        addListeners();
        loadAndSetSpinnerAdapter();
        if (null != mUserDetails) {
            setDataToControls();
        }
    }

    private void setDataToControls() {
        etFirstName.setText(mUserDetails.getFirstName());
        etLastName.setText(mUserDetails.getLastName());
        etEmail.setText(mUserDetails.getEmailId());
        etUserUniqueId.setText(mUserDetails.getUserUniqueId());
        etUserName.setText(mUserDetails.getUserName());
        etPassword.setText(mUserDetails.getPassword());
        etConfirmPassword.setText(mUserDetails.getPassword());
        btnLogin.setText(getString(R.string.btn_update));
        String status = mUserDetails.getStatus();
        Log.i(TAG,"status::" + status);
        if (status.equals(getResources().getStringArray(R.array.user_account_status)[1])){
            spinnerStatus.setSelection(1);
        } else if (status.equals(getResources().getStringArray(R.array.user_account_status)[2])){
            spinnerStatus.setSelection(2);
        }
    }

    private void loadAndSetSpinnerAdapter() {
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(getContext(), R.array.user_account_status, android.R.layout.simple_list_item_1);
        spinnerStatus.setAdapter(statusAdapter);
        loadRoleAdapterData();
    }

    private void loadRoleAdapterData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLS.URL_GET_ROLE_LIST, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response.length() > 0) {
                    Log.e("Response", "Success::" + response.toString());
                    RolesResponse.Role[] userDetails = new Gson().fromJson(response.toString(), RolesResponse.Role[].class);
                    RoleSpinnerAdapter roleSpinnerAdapter = new RoleSpinnerAdapter(getContext(), R.layout.spinner_list_item, R.id.tvSpinnerItem, Arrays.asList(userDetails));
                    spinnerRole.setAdapter(roleSpinnerAdapter);
                    spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            RolesResponse.Role selectedRole = (RolesResponse.Role) spinnerRole.getSelectedItem();
                            etRoleDesc.setText(selectedRole.getRoleDesc());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    if (null != mUserDetails) {
                        for (int i = 0; i < userDetails.length; i++) {
                            if (userDetails[i].getRoleDesc().equals(mUserDetails.getRoleDesc())) {
                                Log.i(TAG, "userDetails[i].getRoleDesc()::" + userDetails[i].getRoleDesc() + "::mUserDetails.getRoleDesc()::" + mUserDetails.getRoleDesc());
                                Log.i(TAG, "Match found");
                                spinnerRole.setSelection(i);
                                break;
                            }
                        }
                        //Toast.makeText(SofqApplication.getInstance(), "loadViewUserData::response received", Toast.LENGTH_SHORT).show();
                    }

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

        Log.e("req","Request starting");
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonArrayRequest);
    }

    private void addListeners() {
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        TextViewUtils.setTextChangeListener(etFirstName);
        TextViewUtils.setTextChangeListener(etLastName);
        TextViewUtils.setTextChangeListener(etEmail);
        TextViewUtils.setTextChangeListener(etUserUniqueId);
        TextViewUtils.setTextChangeListener(etUserName);
        TextViewUtils.setTextChangeListener(etPassword);
        TextViewUtils.setTextChangeListener(etConfirmPassword);

        etPassword.setOnTouchListener((v, event) -> {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP && etPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {

                    if(event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        Toast.makeText(mContext, "Showing password", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    //return true;
                } else if(event.getAction() == MotionEvent.ACTION_UP && etPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    if(event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        Toast.makeText(mContext, "hiding password", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
        });


        etConfirmPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            Log.i(TAG,"setOnTouchListener::inputType::" + etConfirmPassword.getInputType() + " Visible Password::" + InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD + " Not Visible Password::" + InputType.TYPE_TEXT_VARIATION_PASSWORD);
            if(event.getAction() == MotionEvent.ACTION_UP && etConfirmPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {

                if(event.getRawX() >= (etConfirmPassword.getRight() - etConfirmPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    Log.i(TAG,"setOnTouchListener::touched on right drawable area");
                    etConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Toast.makeText(mContext, "setOnTouchListener::drawable right", Toast.LENGTH_SHORT).show();
                    return true;
                }  else {
                    Log.i(TAG,"setOnTouchListener::not touchec on expected area");
                }
            } else if(event.getAction() == MotionEvent.ACTION_UP && etConfirmPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                if(event.getRawX() >= (etConfirmPassword.getRight() - etConfirmPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    etConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Toast.makeText(mContext, "hiding password", Toast.LENGTH_SHORT).show();
                    return true;
                }  else {
                    Log.i(TAG,"setOnTouchListener::not touchec on expected area");
                }
            } else {
                Log.i(TAG,"setOnTouchListener::out of event");
            }
            return false;
        });
    }

    public void onAddNewUser(AddUserRequest addUserRequest) {
        if (mListener != null) {
            mListener.addNewUser(addUserRequest);
        }
    }

    public void onUpdateUser(AddUserRequest addUserRequest) {
        if (mListener != null) {
            mListener.updateUser(addUserRequest);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddUpdateUser:
                if (mUserDetails == null) {
                    if (validateFields()) {
                        onAddNewUser(mAddUpdateUserRequest);
                    }
                } else {
                    if (validateFields()) {
                        mAddUpdateUserRequest.setUserId(mUserDetails.getUserId());
                        onUpdateUser(mAddUpdateUserRequest);
                    }
                }
                break;
            case R.id.btnCancel:
                onBackPressed();
                break;
        }
    }

    private void onBackPressed() {
        mListener.backCancelPressed();
    }

    private boolean validateFields() {
        String value = etFirstName.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etFirstName, getString(R.string.error_first_name));
            return false;
        }
        if (mAddUpdateUserRequest == null) {
            mAddUpdateUserRequest = new AddUserRequest();
        }
        mAddUpdateUserRequest.setFirstName(value);
        value = etLastName.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etLastName, getString(R.string.error_last_name));
            return false;
        }
        mAddUpdateUserRequest.setLastName(value);
        value = etEmail.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etEmail, getString(R.string.error_user_email));
            return false;
        }
        mAddUpdateUserRequest.setEmailId(value);
        value = etUserUniqueId.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etUserUniqueId, getString(R.string.error_user_unique_id));
            return false;
        }
        mAddUpdateUserRequest.setUserUniqueId(value);
        value = etUserName.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etUserName, getString(R.string.error_user_name));
            return false;
        }
        mAddUpdateUserRequest.setUserName(value);
        value = etPassword.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etPassword, getString(R.string.error_password));
            return false;
        }
        mAddUpdateUserRequest.setPassword(value);
        value = etConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etConfirmPassword, getString(R.string.error_confirm_password));
            return false;
        } else {
            String password = etPassword.getText().toString();
            if (!password.equals(value)) {
                TextViewUtils.requestFocusNSetError(etConfirmPassword, getString(R.string.error_password_confirm_password));
                return false;
            }
        }
        if (spinnerRole.getSelectedItem() instanceof RolesResponse.Role) {
            RolesResponse.Role selectedRole = (RolesResponse.Role) spinnerRole.getSelectedItem();

            if (TextUtils.isEmpty(value.trim())) {
                TextViewUtils.requestFocusNSetError(etConfirmPassword, getString(R.string.error_select_role));
                return false;
            }
            AddUserRequest.Role role = new AddUserRequest.Role();
            role.setRoleId(selectedRole.getRoleId());
            mAddUpdateUserRequest.setRole(role);
        } else {
            TextViewUtils.requestFocusNSetError(etConfirmPassword, getString(R.string.error_select_role));
            return false;
        }

        //mAddUpdateUserRequest.setUserName(value);
        value = etRoleDesc.getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            TextViewUtils.requestFocusNSetError(etRoleDesc, getString(R.string.error_select_role_desc));
            return false;
        }
        mAddUpdateUserRequest.setRoleDesc(value);

        value = spinnerStatus.getSelectedItem().toString();
        if (TextUtils.isEmpty(value.trim())) {
            Toast.makeText(mContext, getString(R.string.error_select_status), Toast.LENGTH_SHORT).show();
            return false;
        }
        mAddUpdateUserRequest.setStatus(value);
        return true;
    }

    public interface OnFragmentInteractionListener {

        void addNewUser(AddUserRequest addUserRequest);

        void updateUser(AddUserRequest addUserRequest);

        void backCancelPressed();
    }
}
