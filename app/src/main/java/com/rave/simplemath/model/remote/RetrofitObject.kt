package com.rave.simplemath.model.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

/**
 * This object class has an instance of [Retrofit] and helps up create [MathService].
 *
 * @constructor Create instance of [RetrofitObject]
 */
object RetrofitObject {
    // Example: http://api.mathjs.org/v4/?expr=2%2B2
    private const val BASE_URL = "https://api.mathjs.org/"
    private val contentType: MediaType = MediaType.get("application/json")

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    /**
     * Creates a new instance of [MathService].
     *
     * @return [MathService]
     */
    fun getMathService(): MathService = retrofit.create()
}
