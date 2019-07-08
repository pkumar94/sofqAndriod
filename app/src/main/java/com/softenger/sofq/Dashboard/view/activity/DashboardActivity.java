package com.softenger.sofq.Dashboard.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.cache.DiskBasedCache;
import com.android.volley.error.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.softenger.sofq.Dashboard.presenter.DashboardAdminContract;
import com.softenger.sofq.Dashboard.view.fragment.AddViewUserFragment;
import com.softenger.sofq.Dashboard.view.fragment.AdminDashboardFragment;
import com.softenger.sofq.Dashboard.view.fragment.RoleAccessFragment;
import com.softenger.sofq.R;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.URLS;
import com.softenger.sofq.api.request.AddUserRequest;
import com.softenger.sofq.api.request.ChangePasswordRequest;
import com.softenger.sofq.api.response.DefaultResponse;
import com.softenger.sofq.api.response.UserDetailsResponse;
import com.softenger.sofq.api.util.JsonObjectRequestWithHeader;
import com.softenger.sofq.common.ICommonConstants;
import com.softenger.sofq.user.view.activity.LoginActivity;
import com.softenger.sofq.util.helper.AndroidHelper;
import com.softenger.sofq.util.helper.FragmentHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity implements AdminDashboardFragment.OnFragmentInteractionListener, AddViewUserFragment.OnFragmentInteractionListener, DashboardAdminContract.AdminMainView, FragmentManager.OnBackStackChangedListener {

    private static final String TAG = DashboardActivity.class.getSimpleName();
    //private LinearLayout rlDashboard;
    //private ProgressDialog mProgressDialog;
    private FragmentManager frgManager;
    private Fragment fragment;
    private Context mContext;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mContext = this;
        //rlDashboard = findViewById(R.id.rlDashboard);
        toolbar = findViewById(R.id.toolbar);
        setTopTitle(getString(R.string.title_user));
        setSupportActionBar(toolbar);

        frgManager = getSupportFragmentManager();
        fragment = AdminDashboardFragment.newInstance(ICommonConstants.UPDATE_PASSWORD_OR_DELETE);

        replaceFragment(fragment, false);

        frgManager.addOnBackStackChangedListener(this);
    }

    private void addFragment(Fragment fragment, boolean addToBackstack) {
        FragmentHelper.newInstance().addFragment(frgManager, fragment, addToBackstack, R.id.rlDashboard);
    }

    private void replaceFragment(Fragment fragment, Boolean addToBackstack) {
        FragmentHelper.newInstance().replaceFragment(frgManager, fragment, addToBackstack, R.id.rlDashboard);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_manage_user:
                setTopTitle(getString(R.string.title_manage_user));
                loadManageUsersScreen();
                return true;
            case R.id.item_change_role:
                setTopTitle(getString(R.string.title_role));
                loadRolesScreen();
                return true;
            case R.id.item_change_password:
                //showChangePasswordDialog(SofqApplication.getUserId(), SofqApplication.getUserName(), null, userDetails);
                return true;
            case R.id.item_logout:
                doLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTopTitle(String title) {
        toolbar.setTitle(title);
    }

    private void loadRolesScreen() {

        fragment = RoleAccessFragment.newInstance("", "");
        replaceFragment(fragment, true);
    }

    private void doLogout() {
        SofqApplication.clearPreferences();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void showChangePasswordDialog(UserDetailsResponse.UserDetails userDetails) {
        View promptsView = View.inflate(mContext, R.layout.dialog_change_password, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(promptsView);

        final TextView tvDialogHeading = promptsView.findViewById(R.id.tvHeading);
        final EditText etPassword = promptsView.findViewById(R.id.etNewPassword);
        final EditText etConfirmPassword = promptsView.findViewById(R.id.etConfirmPassword);
        final Button btnCancelDialog = promptsView.findViewById(R.id.btnCancel);
        final Button btnUpdatePassword = promptsView.findViewById(R.id.btnUpdatePassword);
        final Button btnReset = promptsView.findViewById(R.id.btnReset);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        tvDialogHeading.setText(getString(R.string.dialog_title_change_password));
        btnUpdatePassword.setText(getString(R.string.txt_ok));

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newPassword = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                if (newPassword.equals(confirmPassword)) {
                    requestUpdatePassword(etPassword.getText().toString(), userDetails);
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(mContext, getString(R.string.error_password_confirm_password), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void loadManageUsersScreen() {
        //clearBackstack();
        //frgManager.popBackStack();
        fragment = AdminDashboardFragment.newInstance(ICommonConstants.UPDATE_DETAILS);
        replaceFragment(fragment, true);
    }

    private void clearBackstack() {
        for (int i = frgManager.getBackStackEntryCount(); i >= 0; i--) {
            if (i != 0) {
                frgManager.popBackStack();
            }
        }
    }

    @Override
    public void viewUserDetails(UserDetailsResponse.UserDetails userDetails) {
        if (null != userDetails) {
            setTopTitle(getString(R.string.title_edit_user));
            replaceFragment(AddViewUserFragment.newInstance(userDetails), true);
        } else {
            Toast.makeText(mContext, "selected invalid user::", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void changeUserPassword(UserDetailsResponse.UserDetails userDetails) {

        showChangePasswordDialog(userDetails);
    }

    @Override
    public void deleteUser(UserDetailsResponse.UserDetails userDetails) {

        showDeleteConfirmationDialog(userDetails);
    }

    private void showDeleteConfirmationDialog(UserDetailsResponse.UserDetails userDetails) {

        View promptsView = View.inflate(mContext, R.layout.dialog_delete_user, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(promptsView);

        final TextView tvDialogHeading = promptsView.findViewById(R.id.tvHeading);
        final TextView tvMsg = promptsView.findViewById(R.id.tvMessage);
        final Button btnCancelDialog = promptsView.findViewById(R.id.btnCancel);
        final Button btnOperation = promptsView.findViewById(R.id.btnOk);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        tvDialogHeading.setText(getString(R.string.alert_heading_delete_user));
        tvMsg.setText(getString(R.string.alert_msg_delete_user));
        tvMsg.setTextColor(getResources().getColor(R.color.solid_black));
        btnOperation.setText(getString(R.string.txt_ok));

        btnOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDeleteUser(userDetails);
                alertDialog.dismiss();
            }
        });

        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void requestDeleteUser(UserDetailsResponse.UserDetails userDetails) {
        String finalUrl = URLS.URL_GET_USER_LIST + "/" + userDetails.getUserId();
        Log.e("Request", "deleteUser::finalUrl::" + finalUrl);
        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.DELETE, finalUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                DefaultResponse defaultResponse = new Gson().fromJson(response.toString(), DefaultResponse.class);
                if (defaultResponse.getSuccess() == 1) {

                    Log.e("Response", "Success::" + defaultResponse);
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    Fragment fragment = frgManager.findFragmentByTag(AdminDashboardFragment.class.getSimpleName());
                    if (fragment instanceof AdminDashboardFragment) {
                        AdminDashboardFragment dashboardFragment = (AdminDashboardFragment) fragment;
                        dashboardFragment.removeUserFromList(userDetails);
                    }
                } else if (defaultResponse.getFailure() == 1) {
                    Log.e("Response", "Success::" + response.toString());
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
                error.printStackTrace();
            }
        });
        Cache cache = new DiskBasedCache(mContext.getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonObjReq.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    private void requestUpdatePassword(String newPassword, UserDetailsResponse.UserDetails userDetails) {
        Log.i(TAG, "requestUpdatePassword::userId::" + userDetails.getUserId() + " userName::" + userDetails.getUserName() + " oldPassword::" + userDetails.getPassword() + " newPassword::" + newPassword);
        ChangePasswordRequest request = new ChangePasswordRequest(Integer.parseInt(userDetails.getUserId()), userDetails.getUserName(), userDetails.getPassword(), newPassword);
        Log.e("req", AndroidHelper.objectToString(request));
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(AndroidHelper.objectToString(request));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.POST, URLS.URL_ADMIN_CHANGE_PASSWORD, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                DefaultResponse defaultResponse = new Gson().fromJson(response.toString(), DefaultResponse.class);
                if (defaultResponse.getSuccess() == 1) {

                    Log.e("Response", "Success::" + response.toString());
                    Log.e("Response", "Success::" + defaultResponse);
                    userDetails.setPassword(newPassword);
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();

                } else if (defaultResponse.getFailure() == 1) {
                    Log.e("Response", "Success::" + response.toString());
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
                Toast.makeText(mContext, getString(R.string.dialog_msg_failed_to_update_password), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        Cache cache = new DiskBasedCache(mContext.getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonObjReq.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    @Override
    public void editUser(UserDetailsResponse.UserDetails userDetails) {
        Toast.makeText(mContext, "coming soon...editUser::" + userDetails.getFirstName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addNewUser() {
        setTopTitle(getString(R.string.title_add_new_user));
        replaceFragment(AddViewUserFragment.newInstance(null), true);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResponseSuccess(int actionType, int userType, String response) {

    }

    @Override
    public void onResponseFailure(int actionType, String error) {

    }

    @Override
    public void onBackStackChanged() {
        Fragment currentFragment = getCurrentFragment();

        if (currentFragment instanceof AdminDashboardFragment) {
            AdminDashboardFragment fragment = (AdminDashboardFragment) currentFragment;
            if (fragment.mScreenType == ICommonConstants.UPDATE_DETAILS) {
                setTopTitle(getString(R.string.title_manage_user));
            } else if (fragment.mScreenType == ICommonConstants.UPDATE_PASSWORD_OR_DELETE) {
                setTopTitle(getString(R.string.title_user));
            }
        } else {
            setTopTitle(getString(R.string.title_user));
        }
    }


    private Fragment getCurrentFragment() {
        return frgManager.findFragmentById(R.id.rlDashboard);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Fragment currentFragment = getCurrentFragment();
            if (currentFragment instanceof ImageUploadFragment) {
                showAttachmentWarning()
            } else {
                super.onBackPressed()
            }*/
    }

    @Override
    public void addNewUser(AddUserRequest addUserRequest) {

        Log.e("req", new Gson().toJson(addUserRequest));
        JSONObject jsonObj = null;

        try {
            jsonObj = new JSONObject(new Gson().toJson(addUserRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.POST, URLS.URL_GET_USER_LIST, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                DefaultResponse defaultResponse = new Gson().fromJson(response.toString(), DefaultResponse.class);
                if (defaultResponse.getSuccess() == 1) {

                    Log.e("Response", "Success::" + response.toString());
                    Log.e("Response", "Success::" + defaultResponse);
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    frgManager.popBackStack();
                } else if (defaultResponse.getFailure() == 1) {
                    Log.e("Response", "Success::" + response.toString());
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
                error.printStackTrace();
            }
        });
        Cache cache = new DiskBasedCache(mContext.getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonObjReq.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    @Override
    public void updateUser(AddUserRequest addUserRequest) {

        Log.e("req", new Gson().toJson(addUserRequest));
        JSONObject jsonObj = null;

        try {
            jsonObj = new JSONObject(new Gson().toJson(addUserRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.PUT, URLS.URL_GET_USER_LIST, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                DefaultResponse defaultResponse = new Gson().fromJson(response.toString(), DefaultResponse.class);
                if (defaultResponse.getSuccess() == 1) {

                    Log.e("Response", "Success::" + response.toString());
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    frgManager.popBackStack();
                } else if (defaultResponse.getFailure() == 1) {
                    Log.e("Response", "Success::" + response.toString());
                    Toast.makeText(SofqApplication.getInstance(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
                error.printStackTrace();
            }
        });
        Cache cache = new DiskBasedCache(mContext.getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonObjReq.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    @Override
    public void backCancelPressed() {
        frgManager.popBackStack();
    }
}
