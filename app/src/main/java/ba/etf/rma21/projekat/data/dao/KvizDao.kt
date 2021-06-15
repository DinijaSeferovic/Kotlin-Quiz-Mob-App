package ba.etf.rma21.projekat.data.dao
import androidx.room.*
import ba.etf.rma21.projekat.data.models.Kviz

@Dao
interface KvizDao {
    @Query("SELECT * FROM Kviz")
    suspend fun getAll(): List<Kviz>
    @Query("SELECT * FROM Kviz WHERE id = :kId")
    suspend fun getById(kId:Int): Kviz
    @Insert
    suspend fun insertAll(vararg kviz: Kviz)
    @Insert
    suspend fun insertKviz(kviz: Kviz)
    @Query("DELETE FROM Kviz")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(kviz: List<Kviz>)
    /*@Query("UPDATE Kviz SET idKvizTaken = :takenId WHERE id = :id")
    suspend fun updateTaken(takenId: Int, id: Int)*/
}