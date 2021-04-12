package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.hasProperty
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.junit.Assert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class FiltriranjeTest {
    @Test
    fun testGetAllKvizes(){
        val kvizovi = KvizRepository.getAll()
        assertEquals(kvizovi.size,8)
        assertThat(kvizovi, hasItem<Kviz>(hasProperty("nazivPredmeta", Is("AFJ"))))
        assertThat(kvizovi, not(hasItem<Kviz>(hasProperty("naziv", Is("Kviz 10")))))
    }

    @Test
    fun testGetAllMyKvizes(){
        var kvizListViewModel =  KvizListViewModel()
        val myKvizovi = kvizListViewModel.getMojiKvizovi()
        assertEquals(myKvizovi.size,0)
        assertThat(myKvizovi, not(hasItem<Kviz>(hasProperty("nazivPredmeta", Is("RMA")))))
        assertThat(myKvizovi, not(hasItem<Kviz>(hasProperty("trajanje", Is(0)))))
    }

    @Test
    fun testGetBuduciKvizes(){
        var kvizListViewModel =  KvizListViewModel()
        val myKvizovi = kvizListViewModel.getMojiKvizovi()
        assertEquals(myKvizovi.size,0)
        assertThat(myKvizovi, not(hasItem<Kviz>(hasProperty("nazivPredmeta", Is("IM")))))
        assertThat(myKvizovi, not(hasItem<Kviz>(hasProperty("osvojeniBodovi", Is(5)))))
    }

    @Test
    fun testGetNeodrzaniAllKvizes(){
        val myKvizovi = KvizRepository.getAll().filter { k -> k.datumRada!=null }
        assertEquals(myKvizovi.size,4)
        assertThat(myKvizovi, (hasItem<Kviz>(hasProperty("nazivPredmeta", Is("IM")))))
    }

    @Test
    fun testGetZavrseniKvizes(){
        val myKvizovi = KvizRepository.getDone()
        assertEquals(myKvizovi.size,0)
        assertThat(myKvizovi, not(hasItem<Kviz>(hasProperty("nazivPredmeta", Is("IM")))))
    }

    @Test
    fun testGetProsliKvizes(){
        var kvizListViewModel =  KvizListViewModel()
        val myKvizovi = kvizListViewModel.getMojiKvizovi()
        assertEquals(myKvizovi.size,0)
    }


    @Test
    fun testSortAllMyKvizes(){
        var kvizListViewModel =  KvizListViewModel()
        val myKvizovi = kvizListViewModel.getKvizovi()
        assertEquals(myKvizovi.get(0).nazivPredmeta,"RMA")
        assertEquals(myKvizovi.get(1).naziv,"Kviz 5")

    }
}