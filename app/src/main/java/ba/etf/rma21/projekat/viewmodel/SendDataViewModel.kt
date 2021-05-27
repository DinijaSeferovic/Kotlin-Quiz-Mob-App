package ba.etf.rma21.projekat.viewmodel

import android.view.Menu
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import com.google.android.material.navigation.NavigationView

class SendDataViewModel : ViewModel() {
    val dataKviz = MutableLiveData<Kviz>()
    val dataPitanje = MutableLiveData<Pitanje>()
    val redniBrPitanja = MutableLiveData<Int>()
    var navigacija= MutableLiveData<Menu>()
    var mapa= MutableLiveData<HashMap<Pitanje, Int>>()
    var tacnost= MutableLiveData<Double>()
    var brojTacnih= MutableLiveData<Int>()
    var poruka= MutableLiveData<String>()
    var brojPitanja= MutableLiveData<Int>()

    fun sendDataKviz(kviz: Kviz) {
        dataKviz.value = kviz
    }

    fun sendDataPitanje(pitanje: Pitanje, rb: Int) {
        dataPitanje.value = pitanje
        redniBrPitanja.value = rb
    }

    fun sendNav(nav:Menu) {
        navigacija.value=nav
    }

    fun saveDataOdgovor(mapaOdg: HashMap<Pitanje, Int>) {
        mapa.value= mapaOdg
    }

    fun sendTacnost(t: Double){
        tacnost.value=t
    }

    fun sendBrojTacnih(t: Int){
        brojTacnih.value=t
    }

    fun sendBrojPitanja(b: Int){
        brojPitanja.value=b
    }


}
