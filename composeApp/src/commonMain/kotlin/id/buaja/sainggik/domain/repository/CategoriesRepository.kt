package id.buaja.sainggik.domain.repository

import id.buaja.sainggik.domain.model.category.Category
import id.buaja.sainggik.domain.model.category.CategoryGroup
import id.buaja.sainggik.domain.model.category.CategoryParam
import id.buaja.sainggik.domain.model.category.CategoryUpdateParam
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun setCategory(param: CategoryParam)
    fun getAllCategory(): Flow<List<Category>>
    fun getCategoryGroup(): Flow<List<CategoryGroup>>
    suspend fun deleteCategory(id: Long)
    suspend fun updateCategory(param: CategoryUpdateParam)
}