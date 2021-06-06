package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.ApiAdapter
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PitanjeKvizRepository {
    companion object {

        suspend fun getPitanja(idKviza: Int?): List<Pitanje>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPitanja(idKviza!!)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

    }

}


/*
class PitanjeKvizRepository {
    companion object{
        fun getPitanja(nazivKviza: String?, nazivPredmeta: String?): List<Pitanje>{
            var pitanjaZaKviziPredmet= mutableListOf<Pitanje>()
            var kvizPostoji:Boolean = false
            for (k: Kviz in getMyKvizes()) {
                if (k.naziv.equals(nazivKviza)) kvizPostoji=true
            }
            if (kvizPostoji) {
                for (pk: PitanjeKviz in dataPitanjeKviz()) {
                    if (pk.kviz.equals(nazivKviza) && pk.predmet.equals(nazivPredmeta)) {
                        dajPitanje(pk.nazivPitanja)?.let { pitanjaZaKviziPredmet.add(it) }
                    }
                }
            }
            return pitanjaZaKviziPredmet
        }

        private fun dajPitanje(nazivPitanja: String): Pitanje? {
            var pitanje: Pitanje? = null
            for (p: Pitanje in dataPitanja()) {
                if (p.naziv.equals(nazivPitanja)) pitanje=p
            }
            return pitanje
        }

        fun svi() : List<PitanjeKviz>{
            return dataPitanjeKviz()
        }
    }
}*/