package com.nisum.demo

import com.nisum.demo.user.data.network.ApiService
import com.nisum.demo.user.data.repository.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import retrofit2.HttpException


@ExperimentalCoroutinesApi
class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    //  Successful API response
    @Test
    fun `fetchUsers returns correct user list`() = runBlocking {
        // Mock API Response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                  "results": [
                    {
                      "name": { "first": "John", "last": "Doe" },
                      "location": { "city": "New York", "country": "USA" },
                      "picture": { "large": "https://randomuser.me/api/portraits/men/1.jpg" }
                    }
                  ]
                }
                """.trimIndent()
            )

        mockWebServer.enqueue(mockResponse)

        // Call API
        val response = apiService.getUserss(1, 1)

        // Assertions
        assertEquals(1, response.results[0].dob)
        assertEquals("John", response.results[0].name.first)
        assertEquals("Doe", response.results[0].name.last)
        assertEquals("New York", response.results[0].location.city)
    }


    // Empty response handling
    @Test
    fun `fetchUsers handles empty response`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{ "results": [] }""") // Empty list

        mockWebServer.enqueue(mockResponse)

        val response = apiService.getUserss(10, 1)

        // Assertions
        assertTrue(response.results.isEmpty())
    }
}



