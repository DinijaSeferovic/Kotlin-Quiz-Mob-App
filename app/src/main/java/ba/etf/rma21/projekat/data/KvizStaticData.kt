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
            Kviz( "Kviz 3","IM",
                    Date(121,2,22),Date(121,6,2),
                    null,3,"PON",null),
            Kviz( "Kviz 4","AFJ",
                    Date(121,3,3),Date(121,5,20),
                    Date(121,3,5),2,"SRI",4F),
            Kviz( "Kviz 5","RMA",
                    Date(121,2,20),Date(121,3,20),
                    Date(121,3,1),2,"PET",1F)

    )
}

fun dataGrupe(): List<Grupa> {
    return listOf(
            Grupa( "PON", "RMA"),
            Grupa("PET", "RMA"),
            Grupa("SRI", "AFJ"),
            Grupa("UTO", "DM"),
            Grupa("PON", "IM"),
            Grupa("PET", "IM"),
            Grupa("UTO", "IM"),
            Grupa("PET", "ASP"),
            Grupa("SRI", "ASP"),
            Grupa("SRI", "TP"),
            Grupa("CET", "TP"),
            Grupa("CET", "OBP"),
            Grupa("SRI", "OBP"),
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
