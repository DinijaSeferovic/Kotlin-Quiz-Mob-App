package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.DBRepository
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

class KvizListViewModel {

        val scope = CoroutineScope(Job() + Dispatchers.Main)

        fun setContext(context: Context) {
            KvizRepository.setContext(context)
            TakeKvizRepository.setContext(context)
            DBRepository.setContext(context)
        }

        fun sortiraj (lista: List<Kviz>):List<Kviz> {
            return KvizRepository.sortirajKviz(lista)
        }
        fun getKvizovi(onSuccess: (kvizovi: List<Kviz>, predmeti: MutableMap<Kviz,List<Predmet>>) -> Unit, onError: () -> Unit) {
            scope.launch {
                val resultK = sortiraj(KvizRepository.getAll()!!)
                var resultP = mutableMapOf<Kviz,List<Predmet>>()
                for (k in resultK) {
                    resultP.put(k,PredmetIGrupaRepository.getPredmeteZaKviz(k))
                }
                when (resultK) {
                    is List<Kviz> -> onSuccess.invoke(resultK, resultP)
                    else -> onError.invoke()
                }
            }
        }
        fun getMojiKvizovi(onSuccess: (kvizovi: List<Kviz>, predmeti: MutableMap<Kviz,List<Predmet>>) -> Unit, onError: () -> Unit) {
            scope.launch {
                DBRepository.updateNow()
                val resultK = sortiraj(KvizRepository.getMyKvizes()!!)
                var resultP = mutableMapOf<Kviz,List<Predmet>>()

                for (k in resultK) {
                    resultP.put(k,PredmetIGrupaRepository.getPredmeteZaMojKviz(k))
                }
                when (resultK) {
                    is List<Kviz> -> onSuccess.invoke(resultK, resultP)
                    else -> onError.invoke()
                }
            }
        }
        fun getGotoviKvizovi(onSuccess: (kvizovi: List<Kviz>, MutableMap<Kviz,List<Predmet>>) -> Unit, onError: () -> Unit) {
            scope.launch {
                val resultK = sortiraj(KvizRepository.getDone()!!)
                var resultP = mutableMapOf<Kviz,List<Predmet>>()
                for (k in resultK) {
                    resultP.put(k,PredmetIGrupaRepository.getPredmeteZaMojKviz(k))
                }
                when (resultK) {
                    is List<Kviz> -> onSuccess.invoke(resultK, resultP)
                    else -> onError.invoke()
                }
            }
        }

        fun getBuduciKvizovi(onSuccess: (kvizovi: List<Kviz>, MutableMap<Kviz,List<Predmet>>) -> Unit, onError: () -> Unit) {
            scope.launch {
                val resultK = sortiraj(KvizRepository.getFuture()!!)
                var resultP = mutableMapOf<Kviz,List<Predmet>>()
                for (k in resultK) {
                    resultP.put(k,PredmetIGrupaRepository.getPredmeteZaMojKviz(k))
                }
                when (resultK) {
                    is List<Kviz> -> onSuccess.invoke(resultK, resultP)
                    else -> onError.invoke()
                }
            }
        }
        fun getNeodrzaniKvizovi(onSuccess: (kvizovi: List<Kviz>, MutableMap<Kviz,List<Predmet>>) -> Unit, onError: () -> Unit) {
            scope.launch {
                val resultK = sortiraj(KvizRepository.getNotTaken()!!)
                var resultP = mutableMapOf<Kviz,List<Predmet>>()
                for (k in resultK) {
                    resultP.put(k,PredmetIGrupaRepository.getPredmeteZaMojKviz(k))
                }
                when (resultK) {
                    is List<Kviz> -> onSuccess.invoke(resultK, resultP)
                    else -> onError.invoke()
                }
            }
        }

        fun getPocetiKviz(kviz: Kviz,onSuccess: (kvizTaken: KvizTaken) -> Unit, onError: () -> Unit) {
            scope.launch {
                val pocetiKviz = KvizRepository.getPocetiKviz(kviz)
                when (pocetiKviz) {
                    is KvizTaken -> onSuccess.invoke(pocetiKviz)
                    else -> onError.invoke()
                }
            }
        }

        fun getPredmetZaKviz() {

        }

        fun zapocniKviz(kviz: Int, onSuccess: () -> Unit, onError: () -> Unit) {
            scope.launch {
                TakeKvizRepository.zapocniKviz(kviz)
                onSuccess.invoke()

                }
        }


    }
