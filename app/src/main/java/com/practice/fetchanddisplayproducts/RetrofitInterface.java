package com.practice.fetchanddisplayproducts;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/myproducts")
    Call<JSONArray> fetchProducts(@Body HashMap<String, Integer> mobileNumber);
}
