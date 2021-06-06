package ba.etf.rma21.projekat.viewmodel

import android.util.Log
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getUpisaneGrupe
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getUpisanePredmete
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class PredmetListViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getPredmetiZaGod(god: Int, onSuccess: (pred: List<Predmet>) -> Unit, onError: () -> Unit){
        scope.launch{
            val result = PredmetIGrupaRepository.getPredmeti()?.filter { it.godina.equals(god) }
            when (result) {
                is List<Predmet> -> onSuccess.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun neupisaniPoGod(god: Int, onSuccess: (pred: List<Predmet>) -> Unit, onError: () -> Unit){
        scope.launch {
            val result = PredmetIGrupaRepository.neupisaniPoGod(god)
            when(result) {
                is List<Predmet> -> onSuccess.invoke(result)
                else-> onError.invoke()
            }
        }
    }

    fun getPredmeteZaKviz(k: Kviz, onSuccess: (pred: List<Predmet>) -> Unit, onError: () -> Unit) {
        scope.launch{
            val result = PredmetIGrupaRepository.getPredmeteZaKviz(k)
            when (result) {
                is List<Predmet> -> onSuccess.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun getPredmeteZaMojKviz(k: Kviz, onSuccess: (pred: List<Predmet>) -> Unit, onError: () -> Unit) {
        scope.launch{
            val result = PredmetIGrupaRepository.getPredmeteZaMojKviz(k)
            when (result) {
                is List<Predmet> -> onSuccess.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun upisiPredmet(p: Int, onSuccess: (b: Boolean) -> Unit, onError: () -> Unit) {
        scope.launch {
            val upis = PredmetIGrupaRepository.upisiPredmet(p)
            when (upis) {
                is Boolean -> onSuccess?.invoke(upis)
                else -> onError?.invoke()
            }
        }
    }


}