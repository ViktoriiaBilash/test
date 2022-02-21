package net.pet.myapplication

import android.app.Application
import net.pet.myapplication.di.modules.AppComponent
import net.pet.myapplication.di.modules.DaggerAppComponent


class App : Application(){

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}