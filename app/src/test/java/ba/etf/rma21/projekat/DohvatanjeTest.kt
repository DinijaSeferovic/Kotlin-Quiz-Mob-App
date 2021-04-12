package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers.hasProperty
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class DohvatanjeTest {
    @Test
    fun testGroupsByPredmet(){
        val grupe = GrupaRepository.getGroupsByPredmet("RMA")
        assertEquals(2, grupe.size)
        assertThat(grupe, hasItem<Kviz>(hasProperty("naziv", Is("PON"))))
    }

    @Test
    fun testNumGroupsByPredmet(){
        val grupe = GrupaRepository.getAll()
        assertEquals(18, grupe.size)
        var test:Boolean=false
        for (g in grupe) {
            if (grupe.count { it.naziv.equals(g.naziv) }>=2) test=true
            else test=false
        }
        assertTrue(test)
    }

    @Test
    fun testPredmetsByGodina1(){
        val predmeti = PredmetRepository.getPredmetsByGodina("1")
        assertEquals(3, predmeti.size)
        assertThat(predmeti, hasItem<Kviz>(hasProperty("naziv", Is("IM"))))
    }

    @Test
    fun testPredmetsByGodina2(){
        val predmeti = PredmetRepository.getPredmetsByGodina("2")
        assertEquals(5, predmeti.size)
        assertThat(predmeti, hasItem<Kviz>(hasProperty("naziv", Is("AFJ"))))
    }

    @Test
    fun testNumPredmetsByGodina(){
        val predmeti = PredmetRepository.getAll()
        assertEquals(8, predmeti.size)
        var test:Boolean=false
        for (p in predmeti) {
            if (predmeti.count { it.godina.equals(p.godina) }>=1) test=true
            else test=false
        }
        assertTrue(test)
    }

    @Test
    fun testUpisiPredmet(){
        PredmetRepository.upisiPredmeti("RMA")
        var upisani = PredmetRepository.getUpisani()
        var test:Boolean=false
        if (upisani.any { it.naziv.equals("RMA") }) test=true
        else test=false
        assertTrue(test)
    }

    @Test
    fun testUpisiGrupu(){
        GrupaRepository.upisiGrupu("PON")
        var upisane = GrupaRepository.getUpisane()
        var test:Boolean=false
        if (upisane.any { it.naziv.equals("PON") }) test=true
        else test=false
        assertTrue(test)
    }
}