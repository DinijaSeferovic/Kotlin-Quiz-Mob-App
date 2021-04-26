package ba.etf.rma21.projekat.data

import ba.etf.rma21.projekat.data.models.*
import java.util.*


fun dataKvizovi(): List<Kviz> {

    return listOf(
            Kviz( "Kviz 1","RMA",
                    Date(121,2,22),Date(121,6,2),
                    null,3,"PON",null),
            Kviz( "Kviz 2","DM",
                    Date(121,4,20),Date(121,6,5),
                    null,2,"UTO",null),
            Kviz( "Kviz 6","DM",
                    Date(121,5,11),Date(121,7,15),
                    null,2,"PON",null),
            Kviz( "Kviz 3","IM",
                    Date(121,1,25),Date(121,2,23),
                    null,2,"PON",null),
            Kviz( "Kviz 7","IM",
                    Date(121,3,28),Date(121,6,2),
                    Date(121,4,12),3,"PET",5F),
            Kviz( "Kviz 4","AFJ",
                    Date(121,3,3),Date(121,5,20),
                    Date(121,3,5),2,"SRI",4F),
            Kviz( "Kviz 8","AFJ",
                    Date(121,4,30),Date(121,6,20),
                    Date(121,5,5),2,"CET",4F),
            Kviz( "Kviz 5","RMA",
                    Date(121,2,20),Date(121,3,20),
                    Date(121,3,1),2,"PET",1F)

    )
}

/*Podaci pri pokretanju aplikacije moraju uključivati bar 3 predmeta na kojim korisnik nije upisan
sa minimalno po 2 grupe i 1 kviz po grupi,
i bar jedan predmet sa bar jednim kvizom na kojem je  korisnik upisan.
 */


fun dataGrupe(): List<Grupa> {
    return listOf(
            Grupa( "PON", "RMA"),
            Grupa("PET", "RMA"),
            Grupa("SRI", "AFJ"),
            Grupa("CET", "AFJ"),
            Grupa("PON", "DM"),
            Grupa("UTO", "DM"),
            Grupa("PON", "IM"),
            Grupa("PET", "IM"),
            Grupa("UTO", "IM"),
            Grupa("CET", "IM"),
            Grupa("SRI", "ASP"),
            Grupa("PET", "ASP"),
            Grupa("SRI", "TP"),
            Grupa("CET", "TP"),
            Grupa("SRI", "OBP"),
            Grupa("CET", "OBP"),
            Grupa("PON", "OR"),
            Grupa("UTO", "OR")

    )
}

fun dataPredmeti(): List<Predmet> {
    return listOf(
            Predmet("RMA", 2),
            Predmet("AFJ", 2),
            Predmet("DM", 2),
            Predmet("ASP", 2),
            Predmet("OBP", 2),
            Predmet("TP", 1),
            Predmet("IM", 1),
            Predmet("OR", 1)
    )
}

fun dataPitanja(): List<Pitanje> {
    return listOf(
            Pitanje("p1","Koji je tačan odgovor?", listOf("a","b","c"),1),
            Pitanje("p2","Koji je tačan odgovor ovdje?", listOf("a","b","c"),2),
            Pitanje("p3","Koji je tačan odgovor?", listOf("tacno","netacno","netacno"),0),
            Pitanje("p4","Koji je tačan rezultat?", listOf("1","2","3"),1),
            Pitanje("p5","Koji je tačan odgovor?", listOf("a","b","c"),2),
            Pitanje("p6","Koji je tačan odgovor?", listOf("1","2","3"),2),
            Pitanje("p7","Koji je tačan rezultat?", listOf("a","b","c"),0),
            Pitanje("p8","Koji je tačan odgovor?", listOf("netacno","tacno","netacno"),1),
            Pitanje("p9","Koji je tačan odgovor?", listOf("abc","bca","cab"),2),
            Pitanje("p10","Koji je tačan odgovor ovdje?", listOf("a","b","c"),1),
            Pitanje("p11","Koji je tačan odgovor?", listOf("a","b","c"),1)

    )
}

fun dataPitanjeKviz(): List<PitanjeKviz> {
    return listOf(
            PitanjeKviz("p1", "Kviz 1", "RMA"),
            PitanjeKviz("p2", "Kviz 1", "RMA"),
            PitanjeKviz("p3", "Kviz 1", "RMA"),
            PitanjeKviz("p4", "Kviz 1", "RMA"),
            PitanjeKviz("p1", "Kviz 5", "RMA"),
            PitanjeKviz("p2", "Kviz 5", "RMA"),
            PitanjeKviz("p3", "Kviz 5", "RMA"),
            PitanjeKviz("p4", "Kviz 5", "RMA"),
            PitanjeKviz("p3", "Kviz 6", "RMA"),
            PitanjeKviz("p1", "Kviz 6", "RMA"),
            PitanjeKviz("p2", "Kviz 6", "RMA"),
            PitanjeKviz("p4", "Kviz 6", "RMA"),
            PitanjeKviz("p4", "Kviz 18", "RMA"),
            PitanjeKviz("p1", "Kviz 18", "RMA"),
            PitanjeKviz("p5", "Kviz 7", "IM"),
            PitanjeKviz("p6", "Kviz 7", "IM"),
            PitanjeKviz("p7", "Kviz 7", "IM"),
            PitanjeKviz("p6", "Kviz 3", "IM"),
            PitanjeKviz("p6", "Kviz 3", "IM"),
            PitanjeKviz("p3", "Kviz 3", "IM"),
            PitanjeKviz("p7", "Kviz 4", "AFJ"),
            PitanjeKviz("p8", "Kviz 4", "AFJ"),
            PitanjeKviz("p9", "Kviz 4", "AFJ"),
            PitanjeKviz("p10", "Kviz 8", "AFJ"),
            PitanjeKviz("p11", "Kviz 8", "AFJ"),
            PitanjeKviz("p8", "Kviz 8", "AFJ"),
            PitanjeKviz("p9", "Kviz 6", "DM"),
            PitanjeKviz("p8", "Kviz 6", "DM"),
            PitanjeKviz("p7", "Kviz 6", "DM"),
            PitanjeKviz("p10", "Kviz 2", "DM"),
            PitanjeKviz("p2", "Kviz 2", "DM"),
            PitanjeKviz("p1", "Kviz 2", "DM"),
            PitanjeKviz("p3", "Kviz 12", "DM"),
            PitanjeKviz("p4", "Kviz 12", "DM")
    )

}
