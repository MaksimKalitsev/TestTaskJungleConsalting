package ua.zp.testtaskjungleconsalting.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.zp.testtaskjungleconsalting.Confiq
import ua.zp.testtaskjungleconsalting.data.db.RepoEntity
import ua.zp.testtaskjungleconsalting.data.db.UserDatabase
import ua.zp.testtaskjungleconsalting.data.db.UserEntity
import ua.zp.testtaskjungleconsalting.data.network.Api
import ua.zp.testtaskjungleconsalting.data.network.ReposRemoteMediator
import ua.zp.testtaskjungleconsalting.data.network.UserRemoteMediator
import ua.zp.testtaskjungleconsalting.repository.IReposRepository
import ua.zp.testtaskjungleconsalting.repository.IUsersRepository
import ua.zp.testtaskjungleconsalting.repository.ReposRepository
import ua.zp.testtaskjungleconsalting.repository.UsersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Bindings {
        @Binds
        fun bindUserRepository(impl: UsersRepository): IUsersRepository
        @Binds
        fun bindReposRepository(impl: ReposRepository): IReposRepository
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideClient(loggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Confiq.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideUserPager(userDb: UserDatabase, remoteMediator: UserRemoteMediator): Pager<Int, UserEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                userDb.daoUser.pagingSource()
            }
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideRepoPager(repoDb: UserDatabase, remoteMediator: ReposRemoteMediator): Pager<Int, RepoEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                repoDb.daoRepo.pagingSource()
            }
        )
    }


    @Provides
    @Singleton
    fun provideUserRemoteMediator(userDb: UserDatabase, userRepository: IUsersRepository) = UserRemoteMediator(
        userDb = userDb,
        usersRepository = userRepository
    )

    @Provides
    @Singleton
    fun provideReposRemoteMediator(repoDb: UserDatabase, reposRepository: IReposRepository) = ReposRemoteMediator(
        repoDb = repoDb,
        reposRepository = reposRepository
    )

}