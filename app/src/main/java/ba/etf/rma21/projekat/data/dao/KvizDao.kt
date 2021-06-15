package ba.etf.rma21.projekat.data.dao
import androidx.room.*
import ba.etf.rma21.projekat.data.models.Kviz

@Dao
interface KvizDao {
    @Query("SELECT * FROM Kviz")
    suspend fun getAll(): List<Kviz>
    @Insert
    suspend fun insertAll(vararg movies: Kviz)
    @Insert
    suspend fun insertKviz(kviz: Kviz)
    @Query("DELETE FROM Kviz")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(kviz: List<Kviz>)
}