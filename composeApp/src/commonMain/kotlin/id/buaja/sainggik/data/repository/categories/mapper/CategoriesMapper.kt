package id.buaja.sainggik.data.repository.categories.mapper

import id.buaja.sainggik.core.database.entity.CategoriesTypeEntity
import id.buaja.sainggik.core.datetime.DateTimeHelper
import id.buaja.sainggik.data.Categories
import id.buaja.sainggik.data.SelectAllCategories
import id.buaja.sainggik.domain.model.category.Category
import id.buaja.sainggik.domain.model.category.CategoryGroup
import id.buaja.sainggik.domain.model.category.CategoryParam
import id.buaja.sainggik.domain.model.category.CategoryUpdateParam
import id.buaja.sainggik.domain.model.category.TypeCategory

internal fun CategoryParam.asCategories(): Categories {
    return Categories(
        id = 0,
        name = this.name,
        type = when (this.typeCategory) {
            TypeCategory.INCOME -> CategoriesTypeEntity.INCOME
            TypeCategory.EXPENSE -> CategoriesTypeEntity.EXPENSE
        },
        icon_resource_name = null,
        created_at = DateTimeHelper.getCurrentDateTime(),
        updated_at = DateTimeHelper.getCurrentDateTime()
    )
}

internal fun CategoryUpdateParam.asCategoriesUpdate(): Categories {
    return Categories(
        id = this.id,
        name = this.name,
        type = when (this.typeCategory) {
            TypeCategory.INCOME -> CategoriesTypeEntity.INCOME
            TypeCategory.EXPENSE -> CategoriesTypeEntity.EXPENSE
        },
        icon_resource_name = null,
        created_at = DateTimeHelper.getCurrentDateTime(),
        updated_at = DateTimeHelper.getCurrentDateTime()
    )
}

internal fun List<SelectAllCategories>.asCategoriesDomainModel(): List<Category> {
    return this.map {
        Category(
            id = it.id,
            name = it.name,
            type = it.type.asCategoryTypeDomainModel(),
        )
    }
}

internal fun List<SelectAllCategories>.asCategoriesGroupDomainModel(): List<CategoryGroup> {
    return this.groupBy { item ->
        item.type
    }.map { (label, items) ->
        CategoryGroup(
            typeCategory = label.asCategoryTypeDomainModel(),
            category = items.sortedByDescending {
                it.created_at
            }.map {
                Category(
                    id = it.id,
                    name = it.name,
                    type = it.type.asCategoryTypeDomainModel(),
                )
            }
        )
    }
}

private fun CategoriesTypeEntity.asCategoryTypeDomainModel(): TypeCategory {
    return when (this) {
        CategoriesTypeEntity.INCOME -> TypeCategory.INCOME
        CategoriesTypeEntity.EXPENSE -> TypeCategory.EXPENSE
    }
}