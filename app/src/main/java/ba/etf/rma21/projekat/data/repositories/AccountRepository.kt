package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.ApiAdapter
import ba.etf.rma21.projekat.data.models.Changed
import ba.etf.rma21.projekat.data.models.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.*


class AccountRepository {

    companion object {
        //TODO Ovdje trebate dodati hash string vašeg accounta
        var acHash: String = "f59b1f11-0f78-4018-90f2-419573edeacb"

        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }
        /*fun postaviHash(acHash: String): Boolean {

            return true
        }*/

        fun getHash(): String {
            return acHash
        }

        fun setHash(acHash: String) {
            this.acHash = acHash
        }

        suspend fun getAccounts(context: Context) : List<Account> {
            return withContext(Dispatchers.IO) {

                var db = AppDatabase.getInstance(context)
                var acc = db!!.accountDao().getAll()
                return@withContext acc
            }
        }

        suspend fun obrisiSve(currentContext: Context) {
            return withContext(Dispatchers.IO) {
                try {
                    var db = AppDatabase.getInstance(currentContext)
                    db!!.accountDao().deleteAll()
                    db!!.odgovorDao().deleteAll()
                    db!!.kvizDao().deleteAll()
                    db!!.predmetDao().deleteAll()
                    db!!.pitanjeDao().deleteAll()
                    db!!.grupaDao().deleteAll()
                }
                catch(error:Exception){
                    return@withContext
                }
            }
        }
        suspend fun postaviHash(hash:String) : String?{
            return withContext(Dispatchers.IO) {
                setHash(hash)

                try{
                    var db = AppDatabase.getInstance(context)

                    if (db!!.accountDao().getAll().size>0 && !db!!.accountDao().getAll().any{a -> a.acHash.equals(hash)}) {

                        obrisiSve(context)
                        var acc = Account(hash, Date(0,0,0))
                        db!!.accountDao().insertAccount(acc)
                    }
                    if (db!!.accountDao().getAll().size==0) {

                        val current = Date()
                        val cal = Calendar.getInstance()
                        cal.time = current
                        var acc = Account(hash, current)
                        //obrisiSve(context)
                        db!!.accountDao().insertAccount(acc)
                    }
                    if (db!!.accountDao().getAll().any{a -> a.acHash.equals(hash)}) {

                        val current = Date()
                        val cal = Calendar.getInstance()
                        cal.time = current
                        var acc = Account(hash, current)
                        //obrisiSve(context)
                        db!!.accountDao().updateAcc(acc)
                    }
                    return@withContext "success"
                }
                catch(error:Exception){
                    return@withContext null
                }
            }
        }

        suspend fun zadnjiUpdate(date: String) : Changed {
            return withContext(Dispatchers.IO) {
                return@withContext ApiAdapter.retrofit.zadnjiUpdate(AccountRepository.getHash(), date)
            }
        }




    }
}