package ua.zp.testtaskjungleconsalting.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [UserEntity::class])
abstract class UserDatabase : RoomDatabase() {

    abstract val dao: UserDao
}