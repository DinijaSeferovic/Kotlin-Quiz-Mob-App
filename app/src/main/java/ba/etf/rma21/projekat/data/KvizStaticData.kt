package ba.etf.rma21.projekat.data

import android.os.Build
import androidx.annotation.RequiresApi
import ba.etf.rma21.projekat.data.models.Kviz
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun dataKvizovi(): List<Kviz> {

    return listOf(
            Kviz( "Kviz 1","RMA",
                    Date(121,1,25),Date(121,2,23),
                    null,2,"PON",0F),
            Kviz( "Kviz 2","DM",
                    Date(121,4,20),Date(121,6,5),
                    null,2,"UTO",0F),
            Kviz( "Kviz 3","IM",
                    Date(121,2,22),Date(121,6,2),
                    null,3,"PON",0F),
            Kviz( "Kviz 4","AFJ",
                    Date(121,3,3),Date(121,5,20),
                    Date(121,3,5),2,"SRI",4F),
            Kviz( "Kviz 5","RMA",
                    Date(121,2,20),Date(121,3,20),
                    Date(121,3,1),2,"PET",1F)

    )
}
