package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.CountrySummary
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountrySummaryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (VnCity: List<CountrySummary>)
    @Query(value = "select * from country_summary")
    fun getCountrySummaryData(): LiveData<List<CountrySummary>>
}