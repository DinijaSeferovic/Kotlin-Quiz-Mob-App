package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.ApiAdapter
import ba.etf.rma21.projekat.data.models.Changed
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import ba.etf.rma21.projekat.data.repositories.KvizRepository.Companion.getUpisaniApi
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getPredmeteZaKviz
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getUpisaneGrupeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class DBRepository {
    companion object{
        private lateinit var updateDate: Date
        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }
        suspend fun updateNow():Boolean{
            return withContext(Dispatchers.IO) {

                var db = AppDatabase.getInstance(context)
                var listaAcc= db!!.accountDao().getAll()
                //var date:Date = listaAcc.find { a -> a.acHash.equals(AccountRepository.getHash()) }!!.lastUpdate
                var date:Date = Date(0,0,0)
                if (listaAcc.size!=0) date = listaAcc[0].lastUpdate
                var changed = ApiAdapter.retrofit.zadnjiUpdate(AccountRepository.getHash(), toSimpleString(date))
                if (changed.changed) {
                    db!!.kvizDao().deleteAll()
                    db!!.predmetDao().deleteAll()
                    for (k in getUpisaniApi()) {
                        if (!db!!.kvizDao().getAll().any { pr -> pr.id.equals(k.id) }) db!!.kvizDao().insertKviz(k)
                        for (p in getPredmeteZaKviz(k)) {
                            if (!db!!.predmetDao().getAll().any { pr -> pr.id.equals(p.id) }) db!!.predmetDao().insertPredmet(p)
                        }
                    }
                    db!!.grupaDao().deleteAll()
                    for (g in getUpisaneGrupeApi()!!) {
                        db!!.grupaDao().insertGrupa(g)
                    }
                    val current = Date()
                    val cal = Calendar.getInstance()
                    cal.time = current
                    var acc = Account(getHash(), current)
                    db!!.accountDao().insertAccount(acc)
                }

                if(changed.message!=null) return@withContext false
                else return@withContext changed.changed

            }

        }

        fun toSimpleString(date: Date?) : String {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date)
        }

        fun updateDate (date: Date) {
            this.updateDate=date
        }

        fun getUpdateDate() : Date {
            return updateDate
        }
    }


}