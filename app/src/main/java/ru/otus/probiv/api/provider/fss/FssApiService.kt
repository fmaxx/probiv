package ru.otus.probiv.api.provider.fss

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.otus.probiv.Constant
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Maxim Firsov on 2019-11-17.
 * PowerDot
 * firsoffmaxim@gmail.com
 */
interface FssApiService {

    companion object{
        val instance: FssApi by lazy {

            val token = Constant.FSS_API_KEY
            val endPoint = Constant.FSS_API_END_POINT

            // add token to each request
            val interceptor = Interceptor { chain ->
                val url = chain.request().url().newBuilder().addQueryParameter("token", token).build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                chain.proceed(request)
            }

            // logging
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient()
                .newBuilder()
                .addInterceptor(logging)
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder().client(client)
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(FssApi::class.java)
        }
    }
}