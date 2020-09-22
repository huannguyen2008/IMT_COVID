package android.study.imt_covid.data.repository

import android.study.imt_covid.data.dataClass.entity.VnCity
import android.study.imt_covid.data.dataClass.VnCityResponse
import android.study.imt_covid.data.dataClass.entity.VnSummary
import android.study.imt_covid.data.dataClass.VnSummaryResponse
import android.study.imt_covid.data.dtbAndDAO.VnCityDAO
import android.study.imt_covid.data.dtbAndDAO.VnSummaryDAO
import android.study.imt_covid.data.network.networkSource.DataSource
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnCityInfo
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnSummaryInfo
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CovidRepositoryImpl(
    private val VnSummaryDAO: VnSummaryDAO,
    private val VnCityDAO: VnCityDAO,
    private val DataSource: DataSource
) : CovidRepository {

    init {
        DataSource.apply {
            downloadedVnSummary.observeForever { newVnSummary ->
                persistFetchedVnSummary(newVnSummary)
            }
            downloadedVnCity.observeForever { newVnCity ->
                persistFetchedVnCity(newVnCity)
            }
        }
    }

    override suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out UnitSpecifyVnSummaryInfo> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnSummaryDAO.getVnDataSummary()
        }
    }

    override suspend fun getVnCity(VnCity: List<VnCity>): LiveData<out List<VnCity>> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnCityDAO.getVnDataCity()
        }
    }

    private fun persistFetchedVnSummary(fetchedVnSummary: VnSummaryResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnSummaryDAO.upsert(fetchedVnSummary.VnSummary)
        }
    }

    private fun persistFetchedVnCity(fetchedVnCity: VnCityResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnCityDAO.upsert(fetchedVnCity.VnCity)
        }
    }

    private suspend fun initData() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchVnSummary()
        }
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))){
            fetchVnCity()
        }
    }

    private suspend fun fetchVnSummary() {
        val diff = 0
        val recover = 0
        val totalDeath = 0
        val active = 0
        val total = 0
        val newCases = 0
        val newDeath = 0
        DataSource.fetchVnSummary(
            VnSummary = VnSummary(diff, recover, totalDeath, active, total, newCases, newDeath)
        )
    }

    private suspend fun fetchVnCity() {
        val city = "nothing"
        val totalCity = 0
        val activeCity = 0
        val recoveredCity = 0
        val deathCity = 0
        val diffCity = 0
        val listCity =
            listOf(VnCity(city, totalCity, activeCity, recoveredCity, deathCity, diffCity))
        DataSource.fetchVnCity(listCity)
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinAgo)
    }

}