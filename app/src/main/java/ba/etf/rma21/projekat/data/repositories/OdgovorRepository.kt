package ba.etf.rma21.projekat.data.repositories

import android.util.Log
import ba.etf.rma21.projekat.data.models.ApiAdapter
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.OdgovorenoPitanje
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository.Companion.getPitanja
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository.Companion.getPocetiKvizovi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class OdgovorRepository {


    companion object {

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


            }
*/



        suspend fun postaviOdgovorKviz(idKvizTaken : Int,idPitanje : Int, odgovor : Int):Int?{

            val bodovi:Int = izracunajBodoveKviza(idKvizTaken,idPitanje, odgovor)


            withContext(Dispatchers.IO){
                ApiAdapter.retrofit.postaviOdgovorKviz(getHash(),idKvizTaken,OdgovorenoPitanje(odgovor,idPitanje,bodovi))
            }

            return bodovi

        }

        suspend fun izracunajBodoveKviza(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int {
            val pokusaji = getPocetiKvizovi()
            var idKviza : Int = -1

            for(pokusaj in pokusaji!!){
                if(pokusaj.id.equals(idKvizTaken)) idKviza = pokusaj.KvizId
            }
            if (idKviza==-1) return -1
            val pitanja = getPitanja(idKviza)
            val odgovori = getOdgovoriKviz(idKvizTaken)
            var brojTacnih = 0

                for(pitanje in pitanja!!){

                    if(pitanje.id == idPitanje && pitanje.tacan == odgovor)
                        brojTacnih++

                    for(odgovoreno in odgovori!!)
                        if(odgovoreno.PitanjeId == pitanje.id && odgovoreno.odgovoreno == pitanje.tacan)
                        {
                            brojTacnih++
                            break
                        }
                }

                return brojTacnih*100/pitanja.size
        }


    }
}