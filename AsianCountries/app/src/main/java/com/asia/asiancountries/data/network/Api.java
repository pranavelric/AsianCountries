package com.asia.asiancountries.data.network;

import com.asia.asiancountries.data.dataBase.entities.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("asia")
    Call<List<Country>> getContries();

}
