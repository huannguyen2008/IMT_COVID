package android.study.imt_covid.data.dtbAndDAO

import android.content.Context
import android.study.imt_covid.data.dataClass.VnSummary
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [VnSummary::class],
    version = 1
)
abstract class VnSummaryDtb: RoomDatabase() {
    abstract fun VnSummaryDAO(): VnSummaryDAO

    companion object{
        @Volatile private var instance: VnSummaryDtb? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                VnSummaryDtb::class.java, "vn_summary.db")
                .build()
    }
}