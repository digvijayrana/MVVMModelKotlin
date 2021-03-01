package com.rana.mvvmmodelkotlin.network.responses

import com.rana.mvvmmodelkotlin.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessfull:Boolean,
    val quotes:List<Quote>
)