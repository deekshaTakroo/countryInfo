package com.example.countryinfo.utils.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task?)

    @Query("SELECT * FROM Task ")
    fun fetchAllData():List<Task>

    @Query("SELECT * FROM Task WHERE flag = :flag")
    fun fetchParticularCountry(flag:String) : Task
}