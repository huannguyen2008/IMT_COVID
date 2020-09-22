package android.study.imt_covid.data.repository

import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.dataClass.VnSummaryResponse
import android.study.imt_covid.data.dtbAndDAO.VnSummaryDAO
import android.study.imt_covid.data.network.networkSource.DataSource
import android.study.imt_covid.data.unitlocalized.UnitSpecifyVnSummaryInfo
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class VnSummaryRepositoryImpl(
    private val VnSummaryDAO: VnSummaryDAO,
    private val DataSource: DataSource
) : VnSummaryRepository {

    init {
        DataSource.downloadedVnSummary.observeForever { newVnSummary ->
            persistFetchedVnSummary(newVnSummary)
        }
    }

    override suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out UnitSpecifyVnSummaryInfo> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnSummaryDAO.getVnSummary()
        }
    }

    private fun persistFetchedVnSummary(fetchedVnSummary: VnSummaryResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnSummaryDAO.upsert(fetchedVnSummary.VnSummary)
        }
    }

    private suspend fun initData() {
        if (isFetchVnSummaryNeeded(ZonedDateTime.now().minusHours(1)))
            fetchVnSummary()
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

    private fun isFetchVnSummaryNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinAgo)
    }
}