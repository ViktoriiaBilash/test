package net.pet.myapplication.retrofit.repositoryImages

import net.pet.myapplication.model.Image

interface ApiRepository {
   suspend fun getAll(): List<Image>
}