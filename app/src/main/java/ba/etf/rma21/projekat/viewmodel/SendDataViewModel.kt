package ba.etf.rma21.projekat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje

class SendDataViewModel : ViewModel() {
    val dataKviz = MutableLiveData<Kviz>()
    val dataPitanje = MutableLiveData<Pitanje>()

    fun sendDataKviz(kviz: Kviz) {
        dataKviz.value = kviz
    }

    fun sendDataPitanje(pitanje: Pitanje) {
        dataPitanje.value = pitanje
    }



}
