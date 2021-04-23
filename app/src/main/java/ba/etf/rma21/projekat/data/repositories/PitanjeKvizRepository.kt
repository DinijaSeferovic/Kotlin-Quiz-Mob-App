package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje

class PitanjeKvizRepository {
    companion object{
        fun getPitanja(nazivKviza:String,nazivPredmeta:String):List<Pitanje>{
            //todo Implementirati metodu da ispravno vraća rezultat
            return listOf(Pitanje("p1","Tačan odgovor je b", listOf("a","b","c"),1), Pitanje("p2","Tačan odgovor je c", listOf("a","b","c"),2))
        }
    }
}