package com.asia.asiancountries.ui.home;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.asia.asiancountries.data.dataBase.entities.Country;
import com.asia.asiancountries.data.network.NetworkService;
import com.asia.asiancountries.data.repository.CountryRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends ViewModel {
    private CountryRepository countryRepository;
    private LiveData<List<Country>> allCountries;
    private Context context;

    public HomeViewModel(@NonNull Application application) {
        countryRepository = new CountryRepository(application);
        allCountries = countryRepository.getAllCountries();
        context = application;

    }



    public void insert(List<Country> countries) {
        countryRepository.insert(countries);
    }

    public void deleteAllCountries() {
        countryRepository.deleteAllCountrie();
    }

    public LiveData<List<Country>> getAllCountries() {
        return allCountries;
    }

    public void netWorkRequest() {
        NetworkService.getInstance().getJSONApi().getContries().enqueue(
                new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                        if (response.isSuccessful()) {

                            insert(response.body());


                        } else {
                            Toast.makeText(context, "Some error occured" + response.message(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {
                        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();

                    }
                }
        );

    }


}
