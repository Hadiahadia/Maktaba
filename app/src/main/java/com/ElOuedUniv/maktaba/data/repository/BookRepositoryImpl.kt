package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf(
        Book(
            isbn = "11111",
            title = "Clean Code",
            nbPages = 464,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg"
        ),
        Book(
            isbn = "22222",
            title = "The Pragmatic Programmer",
            nbPages = 352,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780201616224-L.jpg"
        ),
        Book(
            isbn = "33333",
            title = "Design Patterns",
            nbPages = 416,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780201633610-L.jpg"
        ),
        Book(
            isbn = "44444",
            title = "Refactoring",
            nbPages = 448,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780201485677-L.jpg"
        ),
        Book(
            isbn = "55555",
            title = "Head First Design Patterns",
            nbPages = 694,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780596007126-L.jpg"
        )
    )

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1).apply {
        tryEmit(_booksList.toList())
    }

    override fun getAllBooks(): Flow<List<Book>> = flow {
        delay(2000) // ⏳ simulate loading
        emitAll(booksFlow)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList()) // 🔥 تحديث مباشر للـ UI
    }
}