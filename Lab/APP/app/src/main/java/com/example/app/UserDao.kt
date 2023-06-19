package com.example.apptest
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)
    @Query("SELECT * FROM user")
    suspend fun getAll():List<User>
    @Query("SELECT COUNT(*) FROM user")
    fun getUserCount(): Int
    @Query("SELECT * FROM user WHERE name = :username AND password = :password")
    suspend fun getUserByUsernameAndPassword(username: String, password: String): User?

}