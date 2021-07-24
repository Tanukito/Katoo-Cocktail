package com.katoo.cocktail.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.katoo.cocktail.domain.constants.TAG
import kotlinx.coroutines.*

abstract class BaseViewModel(
    private val coroutineJob: CompletableJob = SupervisorJob(),
    private val coroutineContext: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, throwable.toString())
    }
) : ViewModel() {
    private var coroutineScope: CoroutineScope = initCoroutineScope()
        get() = if (field.isActive) field else initCoroutineScope().apply {
            field = this
        }

    private fun initCoroutineScope(): CoroutineScope =
        CoroutineScope(coroutineJob + coroutineContext + coroutineExceptionHandler)

    protected fun launch(body: suspend CoroutineScope.() -> Unit) {
        coroutineScope.launch {
            body()
        }
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScope.cancel()
    }
}