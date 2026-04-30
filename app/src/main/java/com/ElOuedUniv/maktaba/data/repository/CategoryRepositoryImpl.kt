package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    private val _categoriesList = listOf(
        Category(
            id = "1",
            name = "Programming",
            description = "Master the art of coding with the best programming guides and tutorials.",
            iconRes = android.R.drawable.ic_menu_edit
        ),
        Category(
            id = "2",
            name = "Algorithms",
            description = "Dive deep into data structures and complex problem-solving techniques.",
            iconRes = android.R.drawable.ic_menu_compass
        ),
        Category(
            id = "3",
            name = "Databases",
            description = "Learn how to design, manage, and scale robust database systems.",
            iconRes = android.R.drawable.ic_menu_save
        ),
        Category(
            id = "4",
            name = "Mobile Dev",
            description = "Build amazing Android and iOS applications with modern frameworks.",
            iconRes = android.R.drawable.ic_menu_call
        ),
        Category(
            id = "5",
            name = "Cloud Computing",
            description = "Explore AWS, Azure, and GCP to deploy your apps at scale.",
            iconRes = android.R.drawable.ic_menu_share
        )
    )

    private val categoriesFlow = MutableSharedFlow<List<Category>>(replay = 1).apply {
        tryEmit(_categoriesList)
    }
    
    override fun getAllCategories(): Flow<List<Category>> = flow {
        delay(1000) // Reduced delay for better UX
        emitAll(categoriesFlow)
    }

    override fun getCategoryById(id: String): Category? {
        return _categoriesList.find { it.id == id }
    }
}
