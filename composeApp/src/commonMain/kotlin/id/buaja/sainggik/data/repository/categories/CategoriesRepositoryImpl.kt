package id.buaja.sainggik.data.repository.categories

import co.touchlab.kermit.Logger
import id.buaja.sainggik.data.repository.categories.mapper.asCategories
import id.buaja.sainggik.data.repository.categories.mapper.asCategoriesDomainModel
import id.buaja.sainggik.data.repository.categories.mapper.asCategoriesGroupDomainModel
import id.buaja.sainggik.data.repository.categories.mapper.asCategoriesUpdate
import id.buaja.sainggik.data.source.categories.CategoriesLocalDataSource
import id.buaja.sainggik.domain.model.category.Category
import id.buaja.sainggik.domain.model.category.CategoryGroup
import id.buaja.sainggik.domain.model.category.CategoryParam
import id.buaja.sainggik.domain.model.category.CategoryUpdateParam
import id.buaja.sainggik.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoriesRepositoryImpl(
    private val categoriesLocalDataSource: CategoriesLocalDataSource
) : CategoriesRepository {
    override suspend fun setCategory(param: CategoryParam) {
        categoriesLocalDataSource.insertCategories(
            categories = param.asCategories()
        )
    }

    override fun getAllCategory(): Flow<List<Category>> {
       return categoriesLocalDataSource.getAllCategories()
           .map {
               it.asCategoriesDomainModel()
           }
    }

    override fun getCategoryGroup(): Flow<List<CategoryGroup>> {
        return categoriesLocalDataSource.getAllCategories()
            .map {
                it.asCategoriesGroupDomainModel()
            }
    }

    override suspend fun deleteCategory(id: Long) {
        categoriesLocalDataSource.deleteCategories(
            id = id
        )
    }

    override suspend fun updateCategory(param: CategoryUpdateParam) {
        categoriesLocalDataSource.updateCategories(
            categories = param.asCategoriesUpdate()
        )
    }
}