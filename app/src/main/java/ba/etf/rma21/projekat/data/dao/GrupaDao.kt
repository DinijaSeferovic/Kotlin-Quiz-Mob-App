package ba.etf.rma21.projekat.data.dao

import androidx.room.*
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.Grupa

@Dao
interface GrupaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrupa(g: Grupa)
    @Query("SELECT * FROM Grupa")
    suspend fun getAll(): List<Grupa>
    @Query("DELETE FROM Grupa")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(grupa: List<Grupa>)
}