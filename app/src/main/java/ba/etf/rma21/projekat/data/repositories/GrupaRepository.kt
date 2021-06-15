package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.ApiAdapter
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Message
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getGrupe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GrupaRepository {

    companion object {
        private var upisane = mutableListOf<Grupa>()
        private var context: Context = PredmetIGrupaRepository.getContext()
        fun setContext(_context: Context){
            context= _context
        }
        init {

        }
        suspend fun upisiUGrupu(idGrupe: Int) : Message {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                if (!db!!.grupaDao().getAll().any { gr -> gr.id.equals(idGrupe) })
                db!!.grupaDao().insertGrupa(getGrupe()!!.find{ g -> g.id.equals(idGrupe)}!!)
                return@withContext ApiAdapter.retrofit.upisiUGrupu(idGrupe, AccountRepository.getHash())
            }
        }

        /*fun getGroupsByPredmet(nazivP: String): List<Grupa> {

            var grupe = mutableListOf<Grupa>()
            for (g in dataGrupe()) {
                if (g.nazivPredmeta.equals(nazivP)) grupe.add(g)
            }
            return grupe
        }

        fun getAll(): List<Grupa> {

            return dataGrupe()
        }

        fun upisiGrupu(g: String) {

            for (gru in getAll()) {
                if (g.equals(gru.naziv)) upisane.add(gru)
            }
        }

        fun getUpisane(): List<Grupa> {
            return upisane
        }

        fun upisaniStringG(): List<String> {
            var grupe = mutableListOf<String>()
            for (g in upisane) {
                grupe.add(g.naziv)
            }
            return grupe
        }*/
    }
}