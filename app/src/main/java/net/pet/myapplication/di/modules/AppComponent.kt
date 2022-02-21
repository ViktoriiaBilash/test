package net.pet.myapplication.di.modules

import dagger.Component
import net.pet.myapplication.api.`interface`.RetrofitServicesVideo
import net.pet.myapplication.ui.MainActivity
import net.pet.myapplication.ui.viewmodel.VideoViewModel
import net.pet.myapplication.usecases.GetVideoResponseUseCase

@Component(modules = [CommonModule::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)

}