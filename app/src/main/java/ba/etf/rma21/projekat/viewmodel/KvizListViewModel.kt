package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository

class KvizListViewModel {

        fun sortiraj (lista: List<Kviz>):List<Kviz> {
            return KvizRepository.sortirajKviz(lista)
        }
        fun getKvizovi():List<Kviz>{
            return sortiraj(KvizRepository.getAll());
        }
        fun getMojiKvizovi():List<Kviz>{
            return sortiraj(KvizRepository.getMyKvizes());
        }
        fun getGotoviKvizovi():List<Kviz>{
            return sortiraj(KvizRepository.getDone());
        }
        fun getBuduciKvizovi():List<Kviz>{
            return sortiraj(KvizRepository.getFuture());
        }
        fun getNeodrzaniKvizovi():List<Kviz>{
            return sortiraj(KvizRepository.getNotTaken());
        }

    }
