package id.buaja.sainggik.data.source.categories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import id.buaja.sainggik.core.utils.DispatcherProvider
import id.buaja.sainggik.data.Categories
import id.buaja.sainggik.data.CategoriesQueries
import id.buaja.sainggik.data.SelectAllCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CategoriesLocalDataSourceImpl(
    private val categoriesQueries: CategoriesQueries,
    private val dispatcherProvider: DispatcherProvider
) : CategoriesLocalDataSource {
    override suspend fun insertCategories(categories: Categories): Long = withContext(dispatcherProvider.io) {
        return@withContext categoriesQueries.insertCategory(
            id = null,
            name = categories.name,
            type = categories.type,
            icon_resource_name = categories.icon_resource_name,
            created_at = categories.created_at,
            updated_at = categories.updated_at
        )
    }

    override fun getAllCategories(): Flow<List<SelectAllCategories>> {
        return categoriesQueries.selectAllCategories()
            .asFlow()
            .mapToList(dispatcherProvider.io)
    }

    override suspend fun deleteCategories(id: Long): Long = withContext(dispatcherProvider.io) {
        return@withContext categoriesQueries.deleteCategory(
            id = id
        )
    }

    override suspend fun updateCategories(categories: Categories): Long = withContext(dispatcherProvider.io) {
        return@withContext categoriesQueries.updateCategory(
            id = categories.id,
            name = categories.name,
            type = categories.type,
            icon_resource_name = categories.icon_resource_name,
            updated_at = categories.updated_at
        )
    }
}