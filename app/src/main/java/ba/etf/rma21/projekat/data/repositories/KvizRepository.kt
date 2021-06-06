package ba.etf.rma21.projekat.data.repositories

import android.util.Log
import ba.etf.rma21.projekat.data.models.ApiAdapter
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getPredmeteZaKviz
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getPredmeteZaMojKviz
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getUpisaneGrupe
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getUpisanePredmete
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository.Companion.getPocetiKvizovi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class KvizRepository {

    companion object {
        // TODO: Implementirati
        init {
            // TODO: Implementirati
        }
        var idUpisaneGrupe: Int =0

        suspend fun getAll(): List<Kviz>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getAll()
                val responseBody = response.body()
                return@withContext responseBody
                }
        }

        suspend fun getById(id: Int): Kviz? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getKviz(id)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }
        suspend fun getKvizoveZaGrupu(id: Int): List<Kviz>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getUpisani(id).body()

                return@withContext response
            }
        }
        suspend fun getUpisani(): List<Kviz> {

            return withContext(Dispatchers.IO) {
                val upisaniKvizovi= mutableListOf<Kviz>()
                val upisaneGrupe = getUpisaneGrupe()
                for (g in upisaneGrupe!!) {
                    //if (getUpisanePredmete().any{ p -> p.id.equals(g.PredmetId)})
                        upisaniKvizovi+= getKvizoveZaGrupu(g.id)!!
                }

                return@withContext upisaniKvizovi
            }
        }

        fun idUpisaneGrupe(id: Int) {
            idUpisaneGrupe=id
        }


        suspend fun getMyKvizes(): List<Kviz> {

            var mojiKvizovi = mutableListOf<Kviz>()
            for (k in getUpisani()!!) {
                for (p in getPredmeteZaMojKviz(k)) {
                    if (getUpisaneGrupe()!!.any { g -> g.PredmetId.equals(p.id)} && getUpisanePredmete().contains(p)) {
                        mojiKvizovi.add(k)
                    }
                }
            }

            return mojiKvizovi
        }

        suspend fun getDone(): List<Kviz> {

            var uradjeniKvizovi= mutableListOf<Kviz>()
            if (getPocetiKvizovi()==null) return emptyList()
            for (k in getPocetiKvizovi()!!) {
                if (getById(k.id!!)!=null && getMyKvizes().contains(getById(k.id!!)))  {
                    getById(k.id!!)?.let { uradjeniKvizovi.add(it) }
                }

            }
            return uradjeniKvizovi

        }

        suspend fun getFuture(): List<Kviz> {

            var buduciKvizovi= mutableListOf<Kviz>()
            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current
            for (k in getAll()!!) {
                if (k.datumPocetka>current && getMyKvizes().contains(getById(k.id!!))) buduciKvizovi.add(k)
            }
            return buduciKvizovi
        }

        suspend fun getNotTaken(): List<Kviz> {

            var neuradjeniKvizovi= mutableListOf<Kviz>()
            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current
            for (k in getAll()!!) {
                if (getMyKvizes().contains(getById(k.id!!)) && getPocetiKvizovi()!!.any { taken -> taken.id!!.equals(k.id) }) neuradjeniKvizovi.add(k)
            }
            return neuradjeniKvizovi
        }

        fun sortirajKviz(lista: List<Kviz>): List<Kviz> {
            return lista.sortedBy {it.datumPocetka }
        }

        suspend fun getPocetiKviz(kviz: Kviz): KvizTaken? {
            val pocetiKvizovi = getPocetiKvizovi()
            var pocetiKviz: KvizTaken? = null
            if (pocetiKvizovi== null) return null
            for (p in pocetiKvizovi!!) {
                if (p.KvizId.equals(kviz.id)) pocetiKviz = p
            }
            return pocetiKviz
        }


    }
}