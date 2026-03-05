package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category

class CategoryRepositoryImpl : CategoryRepository {

    private val categoriesList = listOf(
        Category(
            id = "1",
            name = "Programming",
            description = "Books about software development"
        ),
        Category(
            id = "2",
            name = "Algorithms",
            description = "Books about data structures"
        ),
        Category(
            id = "3",
            name = "Databases",
            description = "Books about database systems"
        ),
        Category(
            id = "4",
            name = "Mobile Development",
            description = "Books about Android and mobile apps"
        ),
        Category(
            id = "5",
            name = "Artificial Intelligence",
            description = "Books about AI and machine learning"
        )
    )

    override fun getAllCategories(): List<Category> {
        return categoriesList
    }

    override fun getCategoryById(id: String): Category? {
        return categoriesList.find { it.id == id }
    }
}