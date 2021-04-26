package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Pitanje

class SaveStateViewModel {

    private var odabranaGod:Int = 0
    private var odabraniPred:Int=0
    private var odabranaGru:Int = 0
    private var mapa: HashMap<Pitanje, Int> = HashMap<Pitanje, Int>()

    constructor() {
        odabranaGod= 0
        odabraniPred=0
        odabranaGru= 0
    }


    fun setGod (god:Int) {
        odabranaGod=god
    }
    fun setPred (pred:Int) {
        odabraniPred=pred
    }
    fun setGru (gru:Int) {
        odabranaGru=gru
    }

    fun getGod(): Int {
        return odabranaGod
    }

    fun getPred(): Int {
        return odabraniPred
    }

    fun getGru(): Int {
        return odabranaGru
    }


    fun saveDataOdgovor(mapaOdg: HashMap<Pitanje, Int>) {
        mapa= mapaOdg
    }

    fun getDataOdgovor(): HashMap<Pitanje, Int> {
        return mapa
    }


}