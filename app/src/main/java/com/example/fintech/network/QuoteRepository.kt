package com.example.fintech.network

import com.example.fintech.network.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(val quoteService: QuoteService) {
    fun getSingleQuote(): Flow<Response<Quote>> {
        return flow {
            val result = quoteService.getQuoteOfTheDay()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}