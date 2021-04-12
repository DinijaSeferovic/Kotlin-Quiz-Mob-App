package ba.etf.rma21.projekat.data

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import java.util.*


fun dataKvizovi(): List<Kviz> {

    return listOf(
            Kviz( "Kviz 1","RMA",
                    Date(121,1,25),Date(121,2,23),
                    null,2,"PON",null),
            Kviz( "Kviz 2","DM",
                    Date(121,4,20),Date(121,6,5),
                    null,2,"UTO",null),
            Kviz( "Kviz 6","DM",
                    Date(121,5,11),Date(121,7,15),
                    null,2,"PON",null),
            Kviz( "Kviz 3","IM",
                    Date(121,2,22),Date(121,6,2),
                    null,3,"PON",null),
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
