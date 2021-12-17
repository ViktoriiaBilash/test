package net.pet.myapplication.room.faker
import net.pet.myapplication.room.EmployeeEntity

class FakeGenerationEmployees {

    fun getEmployees() : MutableList<EmployeeEntity>{
        return mutableListOf<EmployeeEntity>().apply {
            for (n in 0..20) {
                add(EmployeeEntity((n*100).toLong(), "fake name"))
            }
        }
    }
}