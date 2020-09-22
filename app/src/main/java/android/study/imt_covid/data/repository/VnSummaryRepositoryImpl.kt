package android.study.imt_covid.data.repository

import android.os.Build
import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.dataClass.VnSummaryResponse
import android.study.imt_covid.data.dtbAndDAO.VnSummaryDAO
import android.study.imt_covid.data.network.networkSource.VnSummarySource
import android.study.imt_covid.data.unitlocalized.UnitSpecifyVnSummaryInfo
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class VnSummaryRepositoryImpl(
    private val VnSummaryDAO: VnSummaryDAO,
    private val VnSummarySource: VnSummarySource
) : VnSummaryRepository {

    init {
        VnSummarySource.downloadedVnSummary.observeForever { newVnSummary ->
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
        val diff: Int = 0
        val recover: Int = 0
        val totalDeath: Int = 0
        val active: Int = 0
        val total: Int = 0
        val newCases: Int = 0
        val newDeath: Int = 0
        VnSummarySource.fetchVnSummary(
            VnSummary = VnSummary(diff, recover, totalDeath, active, total, newCases, newDeath)
        )
    }

    private fun isFetchVnSummaryNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinAgo)
    }
}