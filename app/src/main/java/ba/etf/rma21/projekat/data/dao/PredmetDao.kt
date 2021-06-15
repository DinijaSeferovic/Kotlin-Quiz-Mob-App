package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Predmet

@Dao
interface PredmetDao {
    @Query("SELECT * FROM Predmet")
    suspend fun getAll(): List<Predmet>
    @Insert
    suspend fun insertAll(vararg predmeti: Predmet)
    @Insert
    suspend fun insertPredmet(predmet: Predmet)
    @Query("DELETE FROM Predmet")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(predmet: List<Predmet>)
}