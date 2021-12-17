package net.pet.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeEntity(
    val salary : Long,
    val name : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L
}