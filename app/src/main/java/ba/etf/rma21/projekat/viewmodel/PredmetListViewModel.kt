package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.repositories.PredmetRepository

class PredmetListViewModel {
    fun getPredmetiZaGod(god: String): List<String> {

        var predPoGod: List<String> = PredmetRepository.getPredmetByGod(god).map {it.naziv }.toList()

        return predPoGod
    }

    fun neupisaniPoGod(god: String) : List<String> {
        var nePredPoGod = mutableListOf<String>()
        for (s in getPredmetiZaGod(god)) {
            if (!PredmetRepository.upisaniStringP().contains(s))  nePredPoGod.add(s)
        }
        return nePredPoGod
    }
}