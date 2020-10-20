package com.kryptkode.farmz.app.di.app.network


import com.kryptkode.farmz.BuildConfig
import com.kryptkode.farmz.app.data.network.service.FarmersService
import com.kryptkode.farmz.app.di.app.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [MapperModule::class])
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideFarmersService(
        retrofit: Retrofit
    ): FarmersService {
        return retrofit.create(FarmersService::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        val builder = Retrofit.Builder()
        builder.client(client)
        builder.addConverterFactory(moshiConverterFactory)
        builder.baseUrl(BuildConfig.BASE_URL)
        return builder.build()
    }

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    @Provides
    @ApplicationScope
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @ApplicationScope
    @Provides
    fun provideOkhttpClient(httpInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
    }


    companion object {
        private const val TIMEOUT = 50L
    }


}