package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Pitanje

@Dao
interface PitanjeDao {
    @Query("SELECT * FROM Pitanje")
    suspend fun getAll(): List<Pitanje>
    @Insert
    suspend fun insertAll(vararg pitanja: Pitanje)
    @Insert
    suspend fun insertPitanje(pitanje: Pitanje)
    @Query("DELETE FROM Pitanje")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(pitanje: List<Pitanje>)
}