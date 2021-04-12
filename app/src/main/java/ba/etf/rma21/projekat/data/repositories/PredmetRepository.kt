package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.dataPredmeti
import ba.etf.rma21.projekat.data.models.Predmet

class PredmetRepository {
    companion object {
        private var upisani= mutableListOf<Predmet>()

        fun getUpisani(): List<Predmet> {

            return upisani
        }

        fun upisaniStringP(): List<String> {
            var predmeti = mutableListOf<String>()
            for (p in upisani) {
                predmeti.add(p.naziv)
            }
            return predmeti
        }

        fun getAll(): List<Predmet> {

            return dataPredmeti()
        }

        fun getPredmetsByGodina(god: String): List<Predmet> {

            var predPoGod = mutableListOf<Predmet>()
            for (p in getAll()) {
                if (p.godina.toString().equals(god)) predPoGod.add(p)
            }
            return predPoGod
        }

        fun upisiPredmeti(p: String){
            for (pred in getAll()) {
                if (p.equals(pred.naziv)) upisani.add(pred)
            }
        }


    }

}