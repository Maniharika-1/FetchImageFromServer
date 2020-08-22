package com.practice.fetchanddisplayproducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONArray;
import com.google.gson.internal.ObjectConstructor;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RetrofitInterface retrofitInterface;
    String baseUrl = "http://192.168.0.103:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String, Integer> mobileNumber = new HashMap<>();
        final int number = 1234567809;
        mobileNumber.put("mobileNumber",number);


        final TextView productName = findViewById(R.id.productName);
        final TextView productDetails = findViewById(R.id.productDetails);
        final TextView productPrice = findViewById(R.id.productPrice);
        final ImageView product = findViewById(R.id.product);

        Call<JSONArray> productInfo = retrofitInterface.fetchProducts(mobileNumber);
        productInfo.enqueue(new Callback<JSONArray>() {
            @Override
            public void onResponse(@NonNull Call<JSONArray> call, @NonNull Response<JSONArray> response) {
                if(response.code() == 200)
                {
                    JSONArray info = response.body();
                    Log.i("info ", String.valueOf(info)+" "+ info.getClass().getName());

                    try {
                        //JSONArray productInfoArray = (JSONArray)new JSONParser().parse(info);
                        //Toast.makeText(getApplicationContext(), String.valueOf(info.get("0")), Toast.LENGTH_SHORT).show();
                        ArrayList<String> productNameList = new ArrayList<>();
                        ArrayList<String> productDetailsList = new ArrayList<>();
                        ArrayList<String> productPriceList = new ArrayList<>();


                        /*for(int i = 0; i<info; i++)
                        {
                            //JSONArray jsonObject = productInfoArray.get(i);
                            Toast.makeText(getApplicationContext(), String.valueOf(productInfoArray.get(i)), Toast.LENGTH_SHORT).show();
                            Log.i("product: ", String.valueOf(productInfoArray.get(i)));
                        }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*productName.setText(info.getProductName());
                    productDetails.setText(info.getProductDetails());
                    productPrice.setText(info.getProductPrice());
                    Glide.with(getApplicationContext()).load("http://192.168.0.103:3000/products").centerCrop().into(product);*/
                }
                else
                {
                    Log.i("Error ","dljsldjo");
                    Toast.makeText(getApplicationContext(), "Sorry! Couldn't load your products.hgfhgjh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JSONArray> call, Throwable t) {
                Log.i("Error ","Failure" + t.getMessage());
                Toast.makeText(getApplicationContext(), "Sorry! Couldn't load your products." + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}