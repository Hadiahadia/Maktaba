package com.ElOuedUniv.maktaba.domain.usecase

import com.ElOuedUniv.maktaba.data.model.Category
import com.ElOuedUniv.maktaba.data.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow


class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<List<Category>> {
        return categoryRepository.getAllCategories()
    }
}
