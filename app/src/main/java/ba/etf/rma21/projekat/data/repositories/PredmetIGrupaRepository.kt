package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.models.*
import ba.etf.rma21.projekat.data.repositories.KvizRepository.Companion.getKvizoveZaGrupu
import ba.etf.rma21.projekat.data.repositories.KvizRepository.Companion.idUpisaneGrupe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PredmetIGrupaRepository {
        companion object{
            private lateinit var context: Context
            fun setContext(_context: Context){
                context=_context
            }
            private var upisani= mutableListOf<Predmet>()

            suspend fun getPredmeti():List<Predmet>? {
                return withContext(Dispatchers.IO) {
                    var response = ApiAdapter.retrofit.getPredmeti()
                    return@withContext response
                }
            }
            suspend fun getGrupe():List<Grupa>?{
                return withContext(Dispatchers.IO) {
                    var response = ApiAdapter.retrofit.getGrupe()
                    val responseBody = response.body()
                    return@withContext responseBody
                }
            }

            suspend fun getGrupeZaPredmet(idPredmeta:Int):List<Grupa>{
                return withContext(Dispatchers.IO) {
                    var response = ApiAdapter.retrofit.getGrupeZaPredmet(idPredmeta)
                    //val responseBody = response.body()
                    return@withContext response
                }
            }

            suspend fun getKvizZaPredmet (pId: Int): List<Kviz> {
                return withContext(Dispatchers.IO) {
                    var kvizoviZaPredmet = mutableListOf<Kviz>()
                    var grupeZaPredmet = getGrupeZaPredmet(pId)
                    for (g in grupeZaPredmet!!) {
                        if (getUpisaneGrupe()!!.contains(g))
                            for (k in getKvizoveZaGrupu(g.id)!!)
                                kvizoviZaPredmet.add(k)
                    }
                    return@withContext kvizoviZaPredmet
                }
            }

            suspend fun getPredmeteZaKviz(kviz: Kviz): List<Predmet> {
                return withContext(Dispatchers.IO) {
                    var predmeti = ApiAdapter.retrofit.getPredmeti()
                    var predmetiZaKviz = mutableListOf<Predmet>()
                    var grupeZaKviz = ApiAdapter.retrofit.getGrupeZaKviz(kviz.id)
                    for (g in grupeZaKviz) {
                        if (!predmetiZaKviz.any{ pr -> pr.id.equals(g.PredmetId)} ) {
                            predmetiZaKviz.add(predmeti.find { p-> p.id.equals(g.PredmetId) }!!)

                        }
                    }

                    return@withContext predmetiZaKviz

                }
            }

            suspend fun getPredmeteZaMojKviz(kviz: Kviz): List<Predmet> {
                return withContext(Dispatchers.IO) {
                    var predmeti = ApiAdapter.retrofit.getPredmeti()
                    var predmetiZaKviz = mutableListOf<Predmet>()
                    var grupeZaKviz = ApiAdapter.retrofit.getGrupeZaKviz(kviz.id)
                    for (g in grupeZaKviz) {
                        if (getUpisaneGrupe()!!.contains(g) && getUpisanePredmete().any{p -> p.id.equals(g.PredmetId)} && !predmetiZaKviz.any{ pr -> pr.id.equals(g.PredmetId)}) {
                            predmetiZaKviz.add(predmeti.find { p-> p.id.equals(g.PredmetId) }!!)
                        }
                    }

                    return@withContext predmetiZaKviz
                }
            }



            suspend fun upisiUGrupu(idGrupa:Int):Boolean {
                val mess = GrupaRepository.upisiUGrupu(idGrupa)
                if(mess.message == "Grupa not found." || mess.message.contains("Ne postoji account gdje je hash=")) return false
                idUpisaneGrupe(idGrupa)
                return true
            }

            suspend fun getUpisaneGrupe():List<Grupa>?{
                return withContext(Dispatchers.IO) {
                    var response = ApiAdapter.retrofit.getUpisaneGrupe(AccountRepository.getHash())
                    val responseBody = response.body()
                    return@withContext responseBody
                }
            }

             suspend fun upisiPredmet(pr: Int): Boolean{
                var predmeti = getPredmeti()
                for (p in predmeti!!) {
                    if (pr.equals(p.id)) upisani.add(p)
                }
                 Log.i ("upisiPredmet", pr.toString())
                 return true
            }

            fun getUpisanePredmete(): List<Predmet> {
                return upisani
            }

            suspend fun getPredmetiZaGod(god: Int) : List<Predmet> {
                return getPredmeti()?.filter { it.godina.equals(god) }!!
            }

            suspend fun neupisaniPoGod(god: Int): List<Predmet> {
                var nePredPoGod = mutableListOf<Predmet>()
                val result = getPredmetiZaGod(god)
                for (p in result) {
                    if (!getUpisanePredmete().contains(p)) {
                        nePredPoGod.add(p)
                    }
                }
                return nePredPoGod
            }





        }
}