package com.kichu.currencyexchange_rht.view.Db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "CountryRateDB", indices = @Index(value = {"CountryName"}, unique = true))
public class CountryRateDB {
    public CountryRateDB() {
    }

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name ="CountryName")
    private String CountryName;

    @ColumnInfo(name ="Rate")
    private String Rate;

    public CountryRateDB(String countryName, String rate) {
        CountryName = countryName;
        Rate = rate;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }
}
