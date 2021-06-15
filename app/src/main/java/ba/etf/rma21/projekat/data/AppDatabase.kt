package ba.etf.rma21.projekat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ba.etf.rma21.projekat.data.dao.*
import ba.etf.rma21.projekat.data.models.*


@Database(entities = arrayOf(Kviz::class, Pitanje::class, Odgovor::class, Grupa::class, Predmet::class,Account::class), version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun kvizDao(): KvizDao
    abstract fun accountDao(): AccountDao
    abstract fun odgovorDao(): OdgovorDao
    abstract fun predmetDao(): PredmetDao
    abstract fun pitanjeDao(): PitanjeDao
    abstract fun grupaDao(): GrupaDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        fun setInstance(appdb:AppDatabase):Unit{
            INSTANCE=appdb
        }
        private fun buildRoomDB(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "RMA21DB"
                ).build()
    }
}