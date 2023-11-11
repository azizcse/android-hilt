package com.example.fintech.network

import com.example.fintech.network.model.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuoteService {
    @GET("today")
    suspend fun getQuoteOfTheDay(): Response<Quote>

    @GET("quotes")
    suspend fun getAllQuotes(): Response<List<Quote>>
}
