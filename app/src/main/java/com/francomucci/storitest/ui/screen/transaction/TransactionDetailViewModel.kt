package com.francomucci.storitest.ui.screen.transaction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.francomucci.storitest.domain.model.TransactionDetail
import com.francomucci.storitest.domain.model.TransactionUser
import com.francomucci.storitest.domain.usecases.GetTransactionDetailUseCase
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(
    private val getTransactionDetailUseCase: GetTransactionDetailUseCase,
) : ViewModel() {

    var transactionState = mutableStateOf(TransactionDetail())
        private set

    var errorMessageState = mutableStateOf("")
        private set

    var loadingState = mutableStateOf(false)
        private set

    fun getTransaction(id: String) {
        loadingState.value = true
        runBlocking {
            withContext(Dispatchers.IO) {
                getTransactionDetailUseCase.getTransactionDetail(
                    id = id,
                    onSuccessListener = { document: QuerySnapshot ->
                        val userDB = document.documents.firstOrNull()
                        userDB?.let {
                            it.toObject(TransactionUser::class.java)?.transactions?.firstOrNull { tx -> tx.id == id }
                                ?.let { txDetail ->
                                    transactionState.value = txDetail
                                    loadingState.value = false
                                }
                        } ?: run {
                            errorMessageState.value = "Something went wrong"
                            loadingState.value = false
                        }
                        loadingState.value = false
                    },
                ) { error: Exception ->
                    errorMessageState.value = error.message ?: ""
                    loadingState.value = false
                }
            }
        }
    }
}
