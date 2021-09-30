package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.*
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository.Companion.getPitanja
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository.Companion.getPocetiKvizovi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


class OdgovorRepository {


    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }

        suspend fun getOdgovoriKviz(idKviza: Int): List<Odgovor>? {

            return withContext(Dispatchers.IO) {
                var idKvizTaken: Int = 0
                for (poc in getPocetiKvizovi()!!) {
                    if (poc.KvizId!!.equals(idKviza)) idKvizTaken = poc.id
                }
                return@withContext ApiAdapter.retrofit.getOdgovoriKviz(getHash(), idKvizTaken)
            }

        }
/*
        suspend private fun izracunajBodoveKviza(idKvizTaken: Int, idP: Int, idO: Int): Int {
            var ukupnoTacnih: Int=0
            var idKviza:Int =0
            for (poc in getPocetiKvizovi()!!) {
                if (poc.id!!.equals(idKvizTaken)) idKviza= poc.KvizId
            }
            val o=  getOdgovoriKviz(idKviza)!!.find { odg -> odg.id.equals(idO) }
            if (getPitanja(getPocetiKvizovi()!!.find { k -> k.id!!.equals(idKvizTaken) }!!.KvizId!!)!!.find { p -> p.id.equals(idP) }!!.tacan.equals(o!!.odgovoreno)) ukupnoTacnih++

            return (ukupnoTacnih/ getPitanja(idKvizTaken)!!.size)*100
        }

        suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int? {
            return withContext(Dispatchers.IO) {
                var bodovi:Int = izracunajBodoveKviza(idKvizTaken, idPitanje, odgovor)

                withContext(Dispatchers.IO){
                    ApiAdapter.retrofit.postaviOdgovorKviz(AccountRepository.getHash(),idKvizTaken,OdgovorenoPitanje(odgovor,idPitanje,bodovi))
                }

                return@withContext bodovi
            }
*/

        suspend fun predajOdgovore(idKviz: Int) {
        }


        suspend fun postaviOdgovorKvizApi(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int? {

            val bodovi: Int = izracunajBodoveKviza(idKvizTaken, idPitanje, odgovor)


            withContext(Dispatchers.IO) {
                ApiAdapter.retrofit.postaviOdgovorKviz(getHash(), idKvizTaken, OdgovorenoPitanje(odgovor, idPitanje, bodovi))
            }

            return bodovi

        }

        suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int? {

            val bodovi: Int = izracunajBodoveKviza(idKvizTaken, idPitanje, odgovor)

            withContext(Dispatchers.IO) {

                var db = AppDatabase.getInstance(context)
                val pokusaji = getPocetiKvizovi()
                var idKviza: Int = -1

                for (pokusaj in pokusaji!!) {
                    if (pokusaj.id.equals(idKvizTaken)) idKviza = pokusaj.KvizId
                }
                val odgovori = getPitanja(idKviza)!!.find { p -> p.id.equals(idPitanje) }!!.opcije
                val odg: Odgovor = Odgovor(db!!.odgovorDao().getAll().size + 1, odgovor, idKvizTaken, idPitanje, idKviza)
                if (!db!!.odgovorDao().getAll().any { o -> o.PitanjeId.equals(idPitanje) && o.odgovoreno.equals(odgovor) && o.KvizTakenId.equals(idKvizTaken) })
                    db!!.odgovorDao().insertOdgovor(odg)


            }
            return bodovi

        }


        suspend fun izracunajBodoveKviza(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int {
            val pokusaji = getPocetiKvizovi()
            var idKviza: Int = -1

            for (pokusaj in pokusaji!!) {
                if (pokusaj.id.equals(idKvizTaken)) idKviza = pokusaj.KvizId
            }
            if (idKviza == -1) return -1
            val pitanja = getPitanja(idKviza)
            val odgovori = getOdgovoriKviz(idKvizTaken)
            var brojTacnih = 0

            for (pitanje in pitanja!!) {

                if (pitanje.id == idPitanje && pitanje.tacan == odgovor)
                    brojTacnih++

                for (odgovoreno in odgovori!!)
                    if (odgovoreno.PitanjeId == pitanje.id && odgovoreno.odgovoreno == pitanje.tacan) {
                        brojTacnih++
                        break
                    }
            }

            return brojTacnih * 100 / pitanja.size
        }


    }
}
