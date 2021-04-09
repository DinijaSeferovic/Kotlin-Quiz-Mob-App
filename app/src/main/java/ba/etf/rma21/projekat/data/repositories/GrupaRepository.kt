package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet

class GrupaRepository {
    companion object {
        init {
            // TODO: Implementirati
        }

        fun getGroupsByPredmet(nazivPredmeta: String): List<Grupa> {
            // TODO: Implementirati
            return emptyList()
        }

        fun upisaneGrupe(g: Grupa): List<Grupa> {
            var upisane: List<Grupa> = listOf()
            upisane.toMutableList().add(g)
            return upisane
        }
    }
}