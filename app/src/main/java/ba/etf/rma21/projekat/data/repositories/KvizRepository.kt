package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.dataKvizovi
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.getUpisani
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.upisaniString
import java.util.*

class KvizRepository {

    companion object {
        // TODO: Implementirati
        init {
            // TODO: Implementirati
        }

        fun getMyKvizes(): List<Kviz> {

            var mojiKvizovi: List<Kviz> = listOf()
            for (k in getAll()) {
                if (upisaniString().contains(k.nazivPredmeta)) mojiKvizovi.toMutableList().add(k)
            }
            return mojiKvizovi
        }

        fun getAll(): List<Kviz> {

            return dataKvizovi()
        }

        fun getDone(): List<Kviz> {

            var uradjeniKvizovi: List<Kviz> = listOf()
            for (k in getAll()) {
                if (k.datumRada!=null) uradjeniKvizovi.toMutableList().add(k)
            }
            return uradjeniKvizovi
        }

        fun getFuture(): List<Kviz> {

            var buduciKvizovi: List<Kviz> = listOf()
            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current
            for (k in getAll()) {
                if (k.datumRada==null && k.datumPocetka>current) buduciKvizovi.toMutableList().add(k)
            }
            return buduciKvizovi
        }

        fun getNotTaken(): List<Kviz> {

            var neuradjeniKvizovi: List<Kviz> = listOf()
            val current = Date()
            val cal = Calendar.getInstance()
            cal.time = current
            for (k in getAll()) {
                if (k.datumRada==null && k.datumKraj<current) neuradjeniKvizovi.toMutableList().add(k)
            }
            return neuradjeniKvizovi
        }
        // TODO: Implementirati i ostale potrebne metode
        fun sortirajKviz(lista: List<Kviz>): List<Kviz> {
            return lista.sortedBy {it.datumPocetka }
        }
    }
}