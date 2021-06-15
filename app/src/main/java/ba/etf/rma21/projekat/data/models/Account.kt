package ba.etf.rma21.projekat.data.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
data class Account(
    @PrimaryKey @SerializedName("acHash") var acHash:String,
    @TypeConverters(Converter::class)
    @ColumnInfo var lastUpdate: Date) {
}