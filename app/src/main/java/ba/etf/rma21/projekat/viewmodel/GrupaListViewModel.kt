package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository

class GrupaListViewModel {

    fun getGrupeZaPred(pred: String): List<String> {

        var grupPoPred: List<String> = GrupaRepository.getGroupsByPredmet(pred).map {it.naziv }.toList()

        return grupPoPred
    }
}