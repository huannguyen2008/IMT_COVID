package android.study.imt_covid.data.repository

import android.os.Build
import android.study.imt_covid.data.CasesInfoDAO
import android.study.imt_covid.data.dataClass.CaseInfo
import android.study.imt_covid.data.dataClass.TotalAndActiveResponse
import android.study.imt_covid.data.network.CasesNetworkDataSource
import android.study.imt_covid.data.unitlocalized.UnitSpecificCasesInfo
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class CasesRepositoryImpl(
    private val casesInfoDao: CasesInfoDAO,
    private val casesNetworkDataSource: CasesNetworkDataSource
) : CasesRepository {

    init {
        casesNetworkDataSource.downloadedTotalAndActive.observeForever { newTotalAndActive ->
            persistFetchedTotalAndActive(newTotalAndActive)
        }
    }

    override suspend fun getTotalAndActive(cases: List<CaseInfo>): LiveData<out UnitSpecificCasesInfo> {
        return withContext(Dispatchers.IO) {
            return@withContext casesInfoDao.getCasesInfo()
        }
    }

    private fun persistFetchedTotalAndActive(fetchedCases: TotalAndActiveResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            casesInfoDao.upsert(fetchedCases.TotalAndActive)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun initCasesData() {
        if(isFetchCasesNeeded(ZonedDateTime.now().minusHours(1)))
            fetchTotalAndActive()
    }

    private suspend fun fetchTotalAndActive() {
        casesNetworkDataSource.fetchTotalAndActive(
            "cases"
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchCasesNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinAgo)
    }
}