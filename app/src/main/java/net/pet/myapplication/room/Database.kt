package net.pet.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 3, entities = [EmployeeEntity::class])
abstract class Database() : RoomDatabase() {
    abstract fun employeeDao() : EmployeeDao
}