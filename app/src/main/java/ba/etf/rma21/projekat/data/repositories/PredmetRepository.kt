package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.dataPredmeti
import ba.etf.rma21.projekat.data.models.Predmet

class PredmetRepository {
    companion object {
        private var upisani: List<Predmet> = listOf()

        fun getUpisani(): List<Predmet> {

            return upisani
        }

        fun upisaniString(): List<String> {
            var predmeti: List<String> = listOf()
            for (p in getUpisani()) {
                predmeti.toMutableList().add(p.naziv)
            }
            return predmeti
        }

        fun getAll(): List<Predmet> {

            return dataPredmeti()
        }

        fun getPredmetByGod(god: String): List<String> {

            var predPoGod: List<String> = listOf()
            for (p in getAll()) {
                if (p.godina.toString()==god) predPoGod.toMutableList().add(p.naziv)
            }
            return predPoGod
        }
        // TODO: Implementirati i ostale potrebne metode
        fun upisiPredmeti(p: Predmet){
            upisani.toMutableList().add(p)
        }

        fun neupisaniPoGod(god: String) : List<String> {
            var nePredPoGod: List<String> = listOf()
            for (s in getPredmetByGod(god)) {
                if (!upisaniString().contains(s))  nePredPoGod.toMutableList().add(s)
            }
            return nePredPoGod
        }

    }

}