package com.asia.asiancountries.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.asia.asiancountries.data.dataBase.CountriesDao;
import com.asia.asiancountries.data.dataBase.CountryDatabase;
import com.asia.asiancountries.data.dataBase.entities.Country;

import java.util.List;

public class CountryRepository {

    private CountriesDao countriesDao;
    private LiveData<List<Country>> allCountries;

    public CountryRepository(Application application) {

        CountryDatabase database = CountryDatabase.getInstance(application);
        countriesDao = database.countriesDao();
        allCountries = countriesDao.getAllCountries();

    }


    public void insert(List<Country> countries) {
        new InsertCountriesAsyncTask(countriesDao).execute(countries);

    }

    public void deleteAllCountrie() {
        new DeleteAllCountriesAsyncTask(countriesDao).execute();
    }

    public LiveData<List<Country>> getAllCountries() {
        return allCountries;
    }


    private static class InsertCountriesAsyncTask extends AsyncTask<List<Country>, Void, Void> {

        private CountriesDao countriesDao;

        public InsertCountriesAsyncTask(CountriesDao countriesDao) {
            this.countriesDao = countriesDao;
        }

        @Override
        protected Void doInBackground(List<Country>... country) {
            countriesDao.insert(country[0]);
            return null;
        }
    }

    private static class DeleteAllCountriesAsyncTask extends AsyncTask<List<Country>, Void, Void> {
        private CountriesDao countriesDao;



        public DeleteAllCountriesAsyncTask(CountriesDao countriesDao) {
            this.countriesDao = countriesDao;
        }

        @Override
        protected Void doInBackground(List<Country>... country) {
            countriesDao.deleteAllCountries();
            return null;
        }
    }

}
