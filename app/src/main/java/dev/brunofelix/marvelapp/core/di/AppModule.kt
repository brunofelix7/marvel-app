package dev.brunofelix.marvelapp.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.brunofelix.marvelapp.BuildConfig
import dev.brunofelix.marvelapp.core.data.local.MarvelDatabase
import dev.brunofelix.marvelapp.core.data.local.converter.Converters
import dev.brunofelix.marvelapp.core.data.remote.interceptor.HttpInterceptor
import dev.brunofelix.marvelapp.core.data.remote.MarvelApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpInterceptor() = HttpInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        httpInterceptor: HttpInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): MarvelApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MarvelApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MarvelDatabase =
        Room.databaseBuilder(
                context = context.applicationContext,
                klass = MarvelDatabase::class.java,
                name = "marvel.db")
            .addTypeConverter(Converters())
            .build()
}