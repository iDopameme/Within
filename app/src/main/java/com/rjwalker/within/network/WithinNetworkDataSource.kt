package com.rjwalker.within.network

import com.rjwalker.within.network.model.NetworkQuote

interface WithinNetworkDataSource {
    suspend fun getRandomQuote(): NetworkQuote
}