package com.asia.asiancountries.data.dataBase;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.asia.asiancountries.data.dataBase.entities.Country;

@Database(entities = {Country.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class CountryDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "country_database";
    private static CountryDatabase instance;

    public abstract CountriesDao countriesDao();

    public static synchronized CountryDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CountryDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }



}
