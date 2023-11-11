package com.example.fintech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintech.network.QuoteRepository
import com.example.fintech.network.model.Quote
import com.example.fintech.network.response.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val quoteRepository: QuoteRepository):ViewModel() {
    val quoteLiveData by lazy { MutableLiveData<ApiResponse<Quote>>() }
    fun getSingleQuote()=viewModelScope.launch {
        quoteRepository.getSingleQuote().onStart {
            quoteLiveData.postValue(ApiResponse.loading())
        }.catch {
            quoteLiveData.postValue(it.message?.let { it1 -> ApiResponse.error(message = it1) })
        }.collect{
            quoteLiveData.postValue(ApiResponse.success(data = it.body()) as ApiResponse<Quote>?)
        }
    }
}