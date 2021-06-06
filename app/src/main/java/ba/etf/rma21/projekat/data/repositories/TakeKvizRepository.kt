package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.ApiAdapter
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class TakeKvizRepository {
    companion object {

        suspend fun zapocniKviz(idKviza: Int?): KvizTaken? {
            return withContext(Dispatchers.IO) {
                try {
                    var response = ApiAdapter.retrofit.zapocniKviz(getHash(), idKviza!!)
                    return@withContext response.body()
                }
                catch (e:Exception) {
                    return@withContext null
                }
            }
        }


        suspend fun getPocetiKvizovi(): List<KvizTaken>? {
            return withContext(Dispatchers.IO) {
                try {
                    val result = ApiAdapter.retrofit.getPocetiKvizovi(getHash()).body()
                    if (result!!.isEmpty()) return@withContext null
                    return@withContext result
                }
                catch (e:Exception) {
                    return@withContext null
                }
            }
            /*val pokusaji: List<KvizTaken> = withContext(Dispatchers.IO) {
                return@withContext ApiAdapter.retrofit.getPocetiKvizovi(getHash())
            }
            if (pokusaji.isEmpty()) {
                return null
            }
            return pokusaji*/
        }
    }

}

