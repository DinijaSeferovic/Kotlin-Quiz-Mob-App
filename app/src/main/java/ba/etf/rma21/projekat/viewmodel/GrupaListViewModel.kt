package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction0

class GrupaListViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun setContext(context: Context) {
        GrupaRepository.setContext(context)
    }
    fun getGrupeZaPred(idPredmeta: Int, onSuccess: (grupe: List<Grupa>) -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = PredmetIGrupaRepository.getGrupeZaPredmet(idPredmeta)
            when (result) {
                is List<Grupa> -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    suspend fun dodajPocetniUpisani(onSuccess: (b: Boolean) -> Unit, onError: () -> Unit) {
        scope.launch {
            val upis = PredmetIGrupaRepository.upisiUGrupu(3)
            PredmetIGrupaRepository.upisiPredmet(2)
            when (upis) {
                is Boolean -> onSuccess?.invoke(upis)
                else -> onError?.invoke()
            }
        }
    }

    fun upisiUGrupu(g: Int, onSuccess: (b: Boolean) -> Unit, onError: () -> Unit) {
        scope.launch {
            val upis = PredmetIGrupaRepository.upisiUGrupu(g)
            when (upis) {
                is Boolean -> onSuccess?.invoke(upis)
                else -> onError?.invoke()
            }
        }
    }


}