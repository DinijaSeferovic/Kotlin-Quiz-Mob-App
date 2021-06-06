package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PitanjeKvizViewModel {
    fun getPitanja(idKviza: Int, onSuccess: (List<Pitanje>) -> Unit, onError: () -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = PitanjeKvizRepository.getPitanja(idKviza)
            when (result) {
                is List<Pitanje> -> onSuccess.invoke(result)
                else -> onError.invoke()
            }
        }
    }
}