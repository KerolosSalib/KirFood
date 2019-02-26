package com.kerolossalib.kirfood.dao;

import com.kerolossalib.kirfood.datamodels.Order;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `order`")
    List<Order> getAll();

    @Insert
    void insert(Order order);

    @Delete
    void delete(Order order);
}
