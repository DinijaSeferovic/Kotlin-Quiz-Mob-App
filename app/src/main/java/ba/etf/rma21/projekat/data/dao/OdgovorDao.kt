package ba.etf.rma21.projekat.data.dao

import androidx.room.*
import ba.etf.rma21.projekat.data.models.Odgovor

@Dao
interface OdgovorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOdgovor(o: Odgovor)
    @Query("SELECT * FROM Odgovor")
    suspend fun getAll(): List<Odgovor>
    @Query("DELETE FROM Odgovor")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(odg: List<Odgovor>)
}