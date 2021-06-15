package ba.etf.rma21.projekat.data.dao

import androidx.room.*
import ba.etf.rma21.projekat.data.models.Account

@Dao
interface AccountDao {
    @Query("SELECT * FROM Account")
    suspend fun getAll(): List<Account>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccount(a:Account)
    @Transaction
    @Query("SELECT EXISTS(SELECT * FROM Account WHERE acHash = :id)")
    suspend fun isRowExist(id : String) : Boolean
    @Query("DELETE FROM Account")
    suspend fun deleteAll()
    @Query("DELETE FROM Account WHERE acHash = :id")
    suspend fun deleteAcc(id: String)
    @Update
    suspend fun updateAcc(acc: Account)


}