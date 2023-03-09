package com.kichu.currencyexchange_rht.view.Db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kichu.currencyexchange_rht.view.model.Rates;

import java.util.List;


@Dao
public interface CountryRateDao {
    @Query("SELECT * FROM CountryRateDB")
    LiveData<List<CountryRateDB>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(CountryRateDB... tagListDBS);



    @Query("DELETE FROM CountryRateDB")
    void deleteAll();

}
