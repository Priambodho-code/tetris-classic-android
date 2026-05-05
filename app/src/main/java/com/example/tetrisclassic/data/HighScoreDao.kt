package com.example.tetrisclassic.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HighScoreDao {
    @Query("SELECT * FROM high_scores ORDER BY score DESC LIMIT 1")
    fun getHighestScore(): Flow<HighScore?>

    @Insert
    suspend fun insert(highScore: HighScore)
}
