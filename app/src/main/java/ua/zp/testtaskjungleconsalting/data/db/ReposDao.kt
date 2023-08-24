package ua.zp.testtaskjungleconsalting.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ReposDao {
    @Upsert
    suspend fun upsertAll(repos: List<RepoEntity>)

    @Query("SELECT*FROM repo_entity")
    fun pagingSource(): PagingSource<Int, RepoEntity>

    @Query("DELETE FROM repo_entity")
    suspend fun clearAll()
}