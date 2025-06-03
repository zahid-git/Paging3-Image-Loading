package com.zahid.paging3example.di

import com.google.gson.Gson
import com.zahid.paging3example.BuildConfig
import com.zahid.paging3example.data.datasource.remote.ApiService
import com.zahid.paging3example.data.repository.ImageRepositoryImpl
import com.zahid.paging3example.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides GSON
     * */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    /**
     * Provides GSON Converter Factory
     * */
    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    /**
     * Provides OKHttp Client
     * */
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            retryOnConnectionFailure(true)
        }
        return okHttpBuilder.build()
    }

    /**
     * Provides Retrofit to use it in API Service
     * */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.API_BASE_URL)
            addConverterFactory(gsonConverterFactory)
            client(okHttpClient)
        }.build()
    }

    /**
     * Provides API Service
     * */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Provides Image repository
     * */
    @Provides
    @Singleton
    fun provideImageRepository(apiService: ApiService, gson: Gson): ImageRepository =
        ImageRepositoryImpl(apiService = apiService, gson = gson)


}