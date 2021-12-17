package net.pet.myapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employeeEntity")
    fun getAll(): List<EmployeeEntity>

    @Insert
    fun insert(employees : List<EmployeeEntity>)

    @Delete
    fun delete(employee: EmployeeEntity)

    @Update
    fun update(employee: EmployeeEntity)
}