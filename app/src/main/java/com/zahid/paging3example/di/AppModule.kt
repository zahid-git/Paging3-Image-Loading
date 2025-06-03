package com.zahid.paging3example.di

import com.zahid.paging3example.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiClient() : OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.apply {
            if(BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            retryOnConnectionFailure(true)
        }
        return okHttpBuilder.build()
    }

}