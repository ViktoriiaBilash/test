package net.pet.myapplication.api.repositoryImages

import net.pet.myapplication.model.Image

interface ApiRepository {
   suspend fun getAll(): List<Image>
}