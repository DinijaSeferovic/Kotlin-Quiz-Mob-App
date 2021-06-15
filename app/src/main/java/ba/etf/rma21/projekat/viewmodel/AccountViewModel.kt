package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import ba.etf.rma21.projekat.data.models.Changed
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.repositories.AccountRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AccountViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun postaviHash(hash:String, onSuccess: () -> Unit, onError: () -> Unit){
        scope.launch{
            val result = AccountRepository.postaviHash(hash)
            when (result) {
                is String -> onSuccess?.invoke()
                else-> onError?.invoke()
            }
        }
    }

    fun zadnjiUpdate(date: String, onSuccess: () -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = AccountRepository.zadnjiUpdate(date)
            when (result) {
                is Changed -> onSuccess?.invoke()
                else -> onError?.invoke()
            }
        }
    }
}