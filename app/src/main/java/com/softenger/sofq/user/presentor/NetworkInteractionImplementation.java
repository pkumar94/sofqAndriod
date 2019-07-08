package com.softenger.sofq.user.presentor;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.URLS;
import com.softenger.sofq.api.request.AssetRequest;
import com.softenger.sofq.api.request.LoginRequest;
import com.softenger.sofq.api.request.ParticipantRequest;
import com.softenger.sofq.api.response.AssetLoginResponse;
import com.softenger.sofq.api.response.ParticipantLoginResponse;
import com.softenger.sofq.api.response.UserData;
import com.softenger.sofq.api.util.JsonObjectRequestWithHeader;
import com.softenger.sofq.util.Constants;
import com.softenger.sofq.util.helper.AndroidHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkInteractionImplementation implements LoginContract.NetworkIntracter {
    private static final String TAG = NetworkInteractionImplementation.class.getSimpleName();

    @Override
    public void doLogin(LoginRequest loginData, final OnFinishedListener onFinishedListener) {
        //fetchAssetData();
        Log.e("req", AndroidHelper.objectToString(loginData));
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(AndroidHelper.objectToString(loginData));
            //jsonObj = new JSONObject("{\"emailId\":\"abhishek.modi@softenger.com\",\"firstName\":\"Abhishek\",\"lastName\":\"Modi\",\"password\":\"Abhishekm\",\"role\":{\"roleId\":1},\"roleDesc\":\"Abhishekm\",\"status\":\"Active\",\"userName\":\"Abhishekm\",\"userUniqueId\":\"Abhishekm\"}");

            Log.e("req", jsonObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.POST, URLS.URL_LOGIN, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                UserData registerResponse = new Gson().fromJson(response.toString(), UserData.class);
                if (registerResponse.getResponseCode().equals("200")) {
                    Log.e("Response", "onResponse::Success::" + response.toString());
                    //Toast.makeText(SofqApplication.getInstance(), response.toString(), Toast.LENGTH_SHORT).show();
                    //storeAssets(new AssetRequest(String.valueOf(System.currentTimeMillis()), registerResponse.getUserName(), "login"), null);
                    onFinishedListener.onFinished(LoginContract.LOGIN_ACTION, registerResponse.getRoleId(), response.toString());
                    AndroidHelper.addSharedPreference(SofqApplication.getInstance(), Constants.USER_DATA, new Gson().toJson(registerResponse.getUserName()));
                } else {
                    Log.e("Response", "onResponse::Failure::" + response.toString());
                    onFinishedListener.onFailure(LoginContract.LOGIN_ACTION, registerResponse.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
                //onFinishedListener.onFailure(LoginContract.LOGIN_ACTION, registerResponse.getResponseCode());
            }
        });
        Log.e("req", "Request starting");
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    @Override
    public void storeAssets(final AssetRequest assetRequest, final OnFinishedListener onFinishedListener) {
        Log.e("req", "storeAssets::" + AndroidHelper.objectToString(assetRequest));
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(AndroidHelper.objectToString(assetRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.POST, URLS.URL_BLOCKCHAIN_SET_ASSET, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                AssetLoginResponse registerResponse = new Gson().fromJson(response.toString(), AssetLoginResponse.class);
                if (registerResponse.getUserLoginId().equals(assetRequest.getUserLoginId())) {
                    Log.e("Response", "Success::" + response.toString());
                    storeParticipant(new ParticipantRequest(String.valueOf(System.currentTimeMillis()), registerResponse.getLoginUser()), null);

                } else {
                    Log.e("Response", "Failure::" + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SofqApplication.getInstance(), "Failed:: " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Response", "Failure::" + error.toString());
            }
        });
        Log.e("req", "Request starting");
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    @Override
    public void storeParticipant(final ParticipantRequest participantRequest, OnFinishedListener onFinishedListener) {
        Log.e("req", "storeAssets::" + AndroidHelper.objectToString(participantRequest));
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(AndroidHelper.objectToString(participantRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.POST, URLS.URL_BLOCKCHAIN_SET_PARTICIPANT, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                ParticipantLoginResponse registerResponse = new Gson().fromJson(response.toString(), ParticipantLoginResponse.class);
                if (registerResponse.getLoginUserId().equals(participantRequest.getLoginUserId())) {
                    Log.e("Response", "Success::" + response.toString());

                } else {
                    Toast.makeText(SofqApplication.getInstance(), "Failed:: " + response.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("Response", "Failure::" + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SofqApplication.getInstance(), "Failed:: " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Response", "Failure::" + error.toString());
            }
        });
        Log.e("req", "Request starting");
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    @Override
    public void fetchRolePrivilegesData(int roleId) {
        Log.e("req", "fetchRolePrivilegesData");

        String url = URLS.URL_GET_PRIVILEGES_LIST + "/" + roleId;
        Log.e("req", "fetchRolePrivilegesData::url::" + url);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", "Successful::fetchAssetData::" + response.toString());
                SofqApplication.setUserPrivilegesData(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::fetchAssetData::" + error.toString());
            }
        } );
        Log.e("req", "fetchAssetData::Request starting");
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }

    @Override
    public void fetchAssetData() {
        Log.e("req", "Requesting to fetch Assets");

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET, URLS.URL_BLOCKCHAIN_GET_ASSET, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.e("Response", "Successful::fetchAssetData::" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::fetchAssetData::" + error.toString());
            }
        });
        Log.e("req", "fetchAssetData::Request starting");
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonObjReq);
    }
}