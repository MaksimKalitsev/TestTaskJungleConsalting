package ua.zp.testtaskjungleconsalting.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertAll(users: List<UserEntity>)

    @Query("SELECT*FROM user_entity")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM user_entity")
    suspend fun clearAll()

}