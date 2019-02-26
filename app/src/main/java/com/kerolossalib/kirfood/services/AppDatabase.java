package com.kerolossalib.kirfood.services;

import android.content.Context;

import com.kerolossalib.kirfood.dao.OrderDao;
import com.kerolossalib.kirfood.datamodels.Order;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Order.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    private static String databaseName = "local_database";

    public abstract OrderDao orderDao();

    public static AppDatabase create(Context context) {
        if (appDatabase != null) {
            return appDatabase;
        } else {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, databaseName).build();
            return appDatabase;
        }
    }
}