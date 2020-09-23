package android.study.imt_covid.data.dtbAndDAO

import android.content.Context
import android.study.imt_covid.data.dataClass.entity.VnCity
import android.study.imt_covid.data.dataClass.entity.VnSummary
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [VnSummary::class, VnCity::class],
    version = 1
)
abstract class CovidDtb: RoomDatabase() {
    abstract fun VnSummaryDAO(): VnSummaryDAO
    abstract fun VnCityDAO(): VnCityDAO


    companion object{
        @Volatile private var instance: CovidDtb? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CovidDtb::class.java, "covid.db")
                .build()
    }
}