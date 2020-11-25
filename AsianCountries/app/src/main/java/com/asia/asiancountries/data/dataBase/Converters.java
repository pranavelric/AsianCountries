package com.asia.asiancountries.data.dataBase;

import androidx.room.TypeConverter;

import com.asia.asiancountries.data.dataBase.entities.Languages;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Converters {

    @TypeConverter
    public String fromArrayListToString(ArrayList<String> borders) {
        String value = "";
        for (String border : borders) {
            value += border + ",";
        }

        return value;
    }

    @TypeConverter
    public ArrayList<String> fromStringToArrayList(String value) {
        ArrayList<String> borders = new ArrayList<>( Arrays.asList(value.split("\\s*,\\s*")));
        return borders;
    }


    @TypeConverter
    public String fromJsonListToString(ArrayList<Languages> languages) {
        Gson gson = new Gson();
        return gson.toJson(languages);

    }

    @TypeConverter
    public ArrayList<Languages> fronStringToJsonObj(String value) {

        Type listType = new TypeToken<ArrayList<Languages>>() {
        }.getType();

        Gson gson = new Gson();
        return gson.fromJson(value, listType);

    }


}
