package android.study.imt_covid.data

import android.content.Context
import android.study.imt_covid.data.dataClass.CaseInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CaseInfo::class],
    version = 1
)
abstract class CasesDatabase: RoomDatabase() {
    abstract fun caseInfoDAO(): CasesInfoDAO

    companion object{
        @Volatile private var instance: CasesDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CasesDatabase::class.java, "case.db")
                .build()
    }
}