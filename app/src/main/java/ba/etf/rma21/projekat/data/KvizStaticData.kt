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
                    Date(121,1,20),Date(121,2,20),
                    null,2,"PON",0F),
            Kviz( "Kviz 2","DM",
                    Date(121,4,20),Date(121,5,5),
                    null,2,"UTO",0F),
            Kviz( "Kviz 3","IM",
                    Date(121,3,20),Date(121,5,2),
                    Date(121,4,28),3,"PON",3.1F),
            Kviz( "Kviz 4","AFJ",
                    Date(121,4,3),Date(121,5,20),
                    Date(121,4,1),2,"SRI",4F),
            Kviz( "Kviz 5","RMA",
                    Date(121,2,20),Date(121,3,20),
                    Date(121,3,1),2,"PET",1F)

    )
}
