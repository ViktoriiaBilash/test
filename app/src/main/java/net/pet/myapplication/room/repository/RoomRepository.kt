package net.pet.myapplication.room.repository

import net.pet.myapplication.room.EmployeeEntity

interface RoomRepository {
    fun write(employees: List<EmployeeEntity>)
    fun getAll(): List<EmployeeEntity>
}