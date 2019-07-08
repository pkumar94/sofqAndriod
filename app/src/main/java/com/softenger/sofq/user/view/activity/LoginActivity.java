package com.softenger.sofq.user.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.cache.DiskBasedCache;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.softenger.sofq.Dashboard.view.activity.DashboardActivity;
import com.softenger.sofq.R;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.URLS;
import com.softenger.sofq.api.response.DefaultResponse;
import com.softenger.sofq.api.util.JsonObjectRequestWithHeader;
import com.softenger.sofq.user.presentor.LoginContract;
import com.softenger.sofq.user.presentor.LoginPresenterImplementation;
import com.softenger.sofq.user.presentor.NetworkInteractionImplementation;
import com.softenger.sofq.user.view.fragment.LoginFragment;
import com.softenger.sofq.util.helper.FragmentHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, LoginContract.MainView, FragmentManager.OnBackStackChangedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    private FragmentManager frgManager;
    private LoginFragment loginFragment;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initProgressDialog();

        String userName = SofqApplication.getUserName();

        if (TextUtils.isEmpty(userName)) {
            mPresenter = new LoginPresenterImplementation(this, new NetworkInteractionImplementation());

            frgManager = getSupportFragmentManager();
            loginFragment = LoginFragment.newInstance("", "");
            replaceFragment(loginFragment, false);
            frgManager.addOnBackStackChangedListener(this);
        } else {
            openDashboard();
        }
    }

    private void replaceFragment(LoginFragment loginFragment, boolean b) {
        FragmentHelper.newInstance().replaceFragment(frgManager, loginFragment, b, R.id.ll_login_container);
    }

    @Override
    public void onLoginClicked(String userId, String password) {
        mPresenter.updateUserId(userId);
        mPresenter.updatePassword(password);
        mPresenter.onLoginClicked();
    }

    @Override
    public void onForgotPasswordClicked() {
        showForgotPasswordDialot();
    }

    private void showForgotPasswordDialot() {
        View promptsView = View.inflate(mContext, R.layout.dialog_forgot_password, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(promptsView);

        final TextView tvDialogHeading = promptsView.findViewById(R.id.tvHeading);
        final EditText etUserId = promptsView.findViewById(R.id.etUserId);
        final Button btnOk = promptsView.findViewById(R.id.btnOk);
        final Button btnCancel = promptsView.findViewById(R.id.btnCancel);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        btnOk.setText(getString(R.string.txt_ok));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = etUserId.getText().toString();
                if (!TextUtils.isEmpty(userId)) {
                    requestForgotPassword(userId);
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(mContext, getString(R.string.msg_enter_user_id), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void requestForgotPassword(String userId) {

        String url = URLS.URL_FORGOT_PASSWORD + "?userName=" + userId;
        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                DefaultResponse defaultResponse = new Gson().fromJson(response.toString(), DefaultResponse.class);
                if (defaultResponse.getSuccess() == 1) {

                    Log.e("Response", "Success::" + defaultResponse);
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
                error.printStackTrace();
            }
        });
        Cache cache = new DiskBasedCache(mContext.getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonObjReq.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onResponseSuccess(int actionType, int userType, String response) {
        switch (actionType) {
            case LoginContract.LOGIN_ACTION:
                openDashboard();

                break;
        }
    }

    private void openDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResponseFailure(int actionType, String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackStackChanged() {

    }
}
