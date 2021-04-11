package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.dataGrupe
import ba.etf.rma21.projekat.data.models.Grupa

class GrupaRepository {

    companion object {
        private var upisane = mutableListOf<Grupa>()
        init {
            // TODO: Implementirati
        }

        fun getGroupsByPredmet(nazivP: String): List<Grupa> {
            // TODO: Implementirati
            var grupe = mutableListOf<Grupa>()
            for (g in dataGrupe()) {
                if (g.nazivPredmeta.equals(nazivP)) grupe.add(g)
            }
            return grupe
        }

        fun getAll(): List<Grupa> {

            return dataGrupe()
        }

        fun upisiGrupu(g: String) {

            for (gru in getAll()) {
                if (g.equals(gru.naziv)) upisane.add(gru)
            }
        }

        fun upisaneGrupe(g: Grupa): List<Grupa> {
            return upisane
        }

        fun upisaniStringG(): List<String> {
            var grupe = mutableListOf<String>()
            for (g in upisane) {
                grupe.add(g.naziv)
            }
            return grupe
        }
    }
}