package net.pet.myapplication

import android.app.Application
import net.pet.myapplication.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    //get started koin
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin(){
        startKoin {
            androidContext(this@App)
            modules(
                //list of koins module
                mainModule
            )
        }
    }
}