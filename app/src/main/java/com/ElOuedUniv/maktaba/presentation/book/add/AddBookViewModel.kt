package com.ElOuedUniv.maktaba.presentation.book.add

import androidx.lifecycle.ViewModel
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: AddBookUiAction) {
        when (action) {
            is AddBookUiAction.OnTitleChange -> {
                _uiState.update { it.copy(title = action.title) }
                validateInputs()
            }
            is AddBookUiAction.OnIsbnChange -> {
                _uiState.update { it.copy(isbn = action.isbn) }
                validateInputs()
            }
            is AddBookUiAction.OnPagesChange -> {
                _uiState.update { it.copy(nbPages = action.pages) }
                validateInputs()
            }
            is AddBookUiAction.OnImageSelected -> {
                _uiState.update { it.copy(imageUri = action.uri) }
                validateInputs()
            }
            AddBookUiAction.OnAddClick -> {
                addBook()
            }
        }
    }

    private fun validateInputs() {
        val currentState = _uiState.value
        val isTitleValid = currentState.title.isNotBlank()
        val isIsbnValid = currentState.isbn.length == 13 && currentState.isbn.all { it.isDigit() }
        val isPagesValid = currentState.nbPages.toIntOrNull()?.let { it > 0 } == true
        val isImageValid = currentState.imageUri != null

        _uiState.update { 
            it.copy(isFormValid = isTitleValid && isIsbnValid && isPagesValid && isImageValid) 
        }
    }

    private fun addBook() {
        if (!_uiState.value.isFormValid) return

        val currentState = _uiState.value
        val book = Book(
            isbn = currentState.isbn,
            title = currentState.title,
            nbPages = currentState.nbPages.toIntOrNull() ?: 0,
            imageUrl = currentState.imageUri?.toString()
        )
        addBookUseCase(book)
        _uiState.update { it.copy(isSuccess = true) }
    }
}
