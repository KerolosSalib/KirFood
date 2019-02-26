package com.kerolossalib.kirfood.datamodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

public class ProductTypeConverter {

    private static Gson gson = new Gson();



    @TypeConverter
    public static List<Product> stringToProductList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Product>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String productListToString(List<Product> someObjects) {
        return gson.toJson(someObjects);
    }}
