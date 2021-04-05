package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository

class KvizListViewModel {

        fun getKvizovi():List<Kviz>{
            return KvizRepository.getAll();
        }

    }
