package com.example.restaurant.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.restaurant.entities.Dish;

import java.util.List;

/**
 * Data Access Object
 * - interface used to define methods needed to operate on dishes
 */
@Dao
public interface DishesDao {

    @Query("SELECT * FROM dishes WHERE dishCategoryId = :categoryId")
    List<Dish> getDishesByCategory(Long categoryId);

    @Query("SELECT * FROM dishes WHERE id =:id")
    Dish getDishById(Long id);

    @Query("SELECT COUNT(*) FROM dishes")
    int getDishCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dish dish);
}
