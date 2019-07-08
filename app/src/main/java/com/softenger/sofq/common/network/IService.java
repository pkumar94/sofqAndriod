package com.softenger.sofq.common.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IService {

    @POST("/api/Login")
    Call<ResponseBody> storeAsset(@Query("userLoginId") String userLoginId, @Query("loginUser") String loginUser, @Query("status") String status);

    @POST("/api/LoginTransaction")
    Call<ResponseBody> storeTransaction(@Query("loginStatus") String loginStatus, @Query("transactionId") String transactionId, @Query("timestamp") String timestamp);

    @POST("/api/User")
    Call<ResponseBody> storeParticipant(@Query("userId") String userId, @Query("loginUserId") String loginUserId);


}
