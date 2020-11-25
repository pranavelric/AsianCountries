package com.asia.asiancountries.data.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.asia.asiancountries.data.dataBase.entities.Country;

import java.util.List;

@Dao
public interface CountriesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Country> countries);

    @Query("DELETE FROM country_table")
    void deleteAllCountries();

    @Query("SELECT * FROM country_table")
    LiveData<List<Country>> getAllCountries();

}

