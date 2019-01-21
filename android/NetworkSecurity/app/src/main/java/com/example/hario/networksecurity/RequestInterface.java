package com.example.hario.networksecurity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RequestInterface {
    @GET()
    Call<SMS_API_POJO> getOtp(@Url String url);

    @FormUrlEncoded
    @POST("authenticate.php/")
    Call<SMS_API_POJO> getemail(@Field("id") String id,@Field("password") String password);

    @FormUrlEncoded
    @POST("change_amount.php/")
    Call<SMS_API_POJO> change(@Field("id") String id,@Field("password") String password,@Field("amount") Integer amount);
}
