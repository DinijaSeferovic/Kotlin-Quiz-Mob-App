package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.dataKvizovi
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.GrupaRepository.Companion.upisaniStringG
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.upisaniStringP
import java.util.*

class KvizRepository {

    companion object {
        // TODO: Implementirati
        init {
            // TODO: Implementirati
        }

        fun getMyKvizes(): List<Kviz> {

            var mojiKvizovi = mutableListOf<Kviz>()
            for (k in getAll()) {
                if (upisaniStringP().any { p -> p.equals(k.nazivPredmeta) }
                        && upisaniStringG().any {  g -> g.equals(k.nazivGrupe) }) mojiKvizovi.add(k)

            }
            return mojiKvizovi
        }

        fun getAll(): List<Kviz> {

            return dataKvizovi()
        }

        fun getDone(): List<Kviz> {

            var uradjeniKvizovi= mutableListOf<Kviz>()
            for (k in getMyKvizes()) {
                if (k.datumRada!=null) uradjeniKvizovi.add(k)
            }
            return uradjeniKvizovi
        }

        fun getFuture(): List<Kviz> {

            var buduciKvizovi= mutableListOf<Kviz>()
            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current
            for (k in getMyKvizes()) {
                if (k.datumRada==null && k.datumPocetka>current) buduciKvizovi.add(k)
            }
            return buduciKvizovi
        }

        fun getNotTaken(): List<Kviz> {

            var neuradjeniKvizovi= mutableListOf<Kviz>()
            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current
            for (k in getMyKvizes()) {
                if (k.datumRada==null && k.datumKraj<current) neuradjeniKvizovi.add(k)
            }
            return neuradjeniKvizovi
        }
        // TODO: Implementirati i ostale potrebne metode
        fun sortirajKviz(lista: List<Kviz>): List<Kviz> {
            return lista.sortedBy {it.datumPocetka }
        }
    }
}