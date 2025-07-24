package id.buaja.sainggik.data.source.categories

import id.buaja.sainggik.data.Categories
import id.buaja.sainggik.data.SelectAllCategories
import kotlinx.coroutines.flow.Flow

interface CategoriesLocalDataSource {
    suspend fun insertCategories(categories: Categories): Long
    fun getAllCategories(): Flow<List<SelectAllCategories>>
    suspend fun deleteCategories(id: Long): Long
    suspend fun updateCategories(categories: Categories): Long
}