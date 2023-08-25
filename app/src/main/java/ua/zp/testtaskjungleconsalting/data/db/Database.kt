package ua.zp.testtaskjungleconsalting.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [UserEntity::class, RepoEntity::class])
abstract class Database : RoomDatabase() {

    abstract val daoUser: UserDao
    abstract val daoRepo: ReposDao
}