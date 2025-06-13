package com.example.cashappstocks

import com.example.cashappstocks.network.StockService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StockServiceTest {
    private lateinit var service:StockService
    private lateinit var mockServer:MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

        service = Retrofit.Builder()
            .baseUrl(mockServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockService::class.java)
    }

    private fun enqueueMockResponse(fileName:String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockServer.enqueue(mockResponse)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun getStocks_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("stockResponse.json")
            val responseBody = service.getStocks()
            val request = mockServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/portfolio.json")
        }
    }

    @Test
    fun getStocks_receiveResponse_correctStocksSize(){
        runBlocking {
            enqueueMockResponse("stockResponse.json")
            val responseBody = service.getStocks()
            val request = mockServer.takeRequest()
            val stocksListSize = responseBody.stocks
            assertThat(stocksListSize.size).isEqualTo(16)
        }
    }

    @Test
    fun getStocks_receiveResponse_correctStocksContent(){
        runBlocking {
            enqueueMockResponse("stockResponse.json")
            val responseBody = service.getStocks()
            val request = mockServer.takeRequest()
            val stocksListSize = responseBody.stocks
            val stock = stocksListSize[0]
            assertThat(stock.name).isEqualTo("S&P 500")
            assertThat(stock.ticker).isEqualTo("^GSPC")
        }
    }

    @Test
    fun getStocks_sentIncorrectRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("stockResponse.json")
            val responseBody = service.getStocks()
            val request = mockServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isNotEqualTo("/portfolio_malformed.json")
        }
    }

    @Test
    fun getStocks_sentNullRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("stockResponse.json")
            val responseBody = service.getStocks()
            val request = mockServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isNotEqualTo("/portfolio_empty.json")
        }
    }

 /*   @Test
    fun getStocks_sentRequest_receivedMalformedExpected() {
        runBlocking {
            enqueueMockResponse("malformedResponse.json")
            val responseBody = service.getMalformedStocks()
            val request = mockServer.takeRequest()

           //assertThat(request.path).isEqualTo("/portfolio.json")
        }
    }*/

}