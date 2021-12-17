package net.pet.myapplication.room.repository

import net.pet.myapplication.room.EmployeeDao
import net.pet.myapplication.room.EmployeeEntity

class RoomRepositoryImpl(
    private val employeeDao: EmployeeDao
): RoomRepository {


    override fun write(employees: List<EmployeeEntity>){
        employeeDao.insert(employees)
    }

    override fun getAll(): List<EmployeeEntity> {
        return employeeDao.getAll()
    }
}